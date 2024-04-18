package com.hy.service;
import com.hy.utils.TcpPacketReassembler;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.parser.HttpParser;
import org.springframework.http.HttpMessage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Description: 定时处理完整的tcpStream以及清理过期连接
 * Author: yhong
 * Date: 2024/4/9
 */
@Service
@Slf4j
public class TcpReassemblyProcessor {

    private final TcpPacketReassembler reassembler;
    private final ScheduledExecutorService executorService;

    public TcpReassemblyProcessor(TcpPacketReassembler reassembler) {
        this.reassembler = reassembler;
        this.executorService = Executors.newSingleThreadScheduledExecutor();
        // 初始化定时任务，每5秒执行一次
        executorService.scheduleAtFixedRate(this::processCompletedStreamsAndCleanup, 0, 5, TimeUnit.SECONDS);
    }

    private void processCompletedStreamsAndCleanup() {
        List<byte[]> completedStreams = reassembler.getCompletedStreams();
        for (byte[] completedStream : completedStreams) {
            // 处理已完成重组的TCP数据流
            processCompleteStream(completedStream);
        }

        reassembler.cleanup();
    }

    private void processCompleteStream(byte[] completedStream) {
        try {
            // 将字节数组转换为字符串，便于解析
            String httpMessage = new String(completedStream, StandardCharsets.UTF_8);
            log.info("httpMessage:{}", httpMessage);
        } catch (Exception e) {
            log.error("处理completedStream失败", e);
        }
    }


    // 当不再需要定时任务时，记得关闭ScheduledExecutorService以释放资源
    public void shutdown() {
        executorService.shutdown();
    }
}
