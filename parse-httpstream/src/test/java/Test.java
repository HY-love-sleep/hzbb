import com.hy.Application;
import com.hy.service.HttpMessageService;
import com.hy.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

/**
 * Description:
 *
 * @author: yhong
 * Date: 2024/9/26
 */
@SpringBootTest(classes = Application.class)
@Slf4j
public class Test {
    @Autowired
    private HttpMessageService messageService;
    @org.junit.jupiter.api.Test
    public void test() {
        String filePath = "C:\\My_Work\\IdeaProjects\\MyGitProject\\hzbb\\parse-httpstream\\src\\main\\resources\\bin_file\\dsmm-response-jpg.bin";
        String outputDir = "C:\\My_Work\\IdeaProjects\\MyGitProject\\hzbb\\parse-httpstream\\src\\main\\resources\\output_file";
        try {
            byte[] rawData = FileUtils.readFile(filePath);
            messageService.parseHttpMessages(rawData, outputDir);
        } catch (IOException e) {
            log.error("解析流量失败!!", e);
        }
    }
}
