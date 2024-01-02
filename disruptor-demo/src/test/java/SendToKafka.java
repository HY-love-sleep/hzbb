import com.fasterxml.jackson.core.JsonProcessingException;
import com.hy.Application;
import com.hy.entity.Student;
import com.hy.service.KafkaProducerService;
import com.hy.service.StudentProducerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Description: 发送消息至kafka
 * Author: yhong
 * Date: 2024/1/2
 */
@SpringBootTest(classes = Application.class)
public class SendToKafka {
    @Autowired
    StudentProducerService studentProducerService;
    @Test
    public void test() throws JsonProcessingException {
        Random random = new Random();
        List<Student> students = new ArrayList<>(1000);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 1000; j++) {
                Student student = new Student();
                // todo:分数和Name不对
                student.setName("Student " + (i + 1));
                student.setScore(60 + random.nextInt() * (100 - 60));
                students.add(student);
            }
            studentProducerService.sendStudentsToKafka(students);
            students.clear();
        }

    }
}
