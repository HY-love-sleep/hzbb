import com.hy.Application;
import com.hy.service.PacketCaptureService;
import com.hy.service.TcpReassemblyProcessor;
import com.hy.utils.TcpPacketReassembler;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.pcap4j.core.PcapHandle;
import org.pcap4j.core.PcapNativeException;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.core.Pcaps;
import org.pcap4j.util.NifSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import static org.hamcrest.Matchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

/**
 * Description: 网络抓包测试
 * Author: yhong
 * Date: 2024/3/1
 */
@SpringBootTest(classes = Application.class)
@Slf4j
public class PacketCaptureTest {
    @Autowired
    private PacketCaptureService packetCaptureService;
    @Autowired
    private TcpPacketReassembler reassembler;

    @Test
    public void test() throws PcapNativeException, InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        // 获取指定ip所有网络接口
        List<PcapNetworkInterface> interfaces  = Pcaps.findAllDevs();
        ExecutorService executorService = Executors.newFixedThreadPool(interfaces.size() + 1);
        for (PcapNetworkInterface networkInterface : interfaces) {
            if (networkInterface.getName().contains("lo")) {
                continue;
            }
            try {
                executorService.submit(() -> packetCaptureService.startPacketCapture(networkInterface));
            } catch (Exception e) {
                log.error("startPacketCapture error", e);
            }
        }
        countDownLatch.await();
        // executorService.shutdown();
    }
    @Test
    public void test1() {
        TcpReassemblyProcessor tcpReassemblyProcessor = new TcpReassemblyProcessor(reassembler);
    }


}
