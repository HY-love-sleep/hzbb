import com.hy.Application;
import com.hy.service.PacketCaptureService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.pcap4j.core.PcapNetworkInterface;
import org.pcap4j.util.NifSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * Description: 网络抓包测试
 * Author: yhong
 * Date: 2024/3/1
 */
@SpringBootTest(classes = Application.class)
public class PacketCaptureTest {
    @Autowired
    private PacketCaptureService packetCaptureService;
    @Test
    public void test() {
        packetCaptureService.startPacketCapture("\\Device\\NPF_{ED6F3E55-C7E4-4B79-AD50-898F29B53BB9}");
    }

    @Test
    public void selectNetInterfaceTest() throws IOException {
        PcapNetworkInterface networkInterface2 = new NifSelector().selectNetworkInterface();
        System.out.println(networkInterface2);
    }

}
