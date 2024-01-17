import com.hy.Application;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;

/**
 * Description:
 * Author: yhong
 * Date: 2024/1/17
 */
@SpringBootTest(classes = Application.class)
public class FlowableTest {
    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ProcessEngine processEngine;
    @Test
    public void getProcessTest() {
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("Expense")
                .latestVersion()
                .singleResult();

        if (processDefinition == null) {
            System.out.println("未找到键为 'Expense' 的流程定义");
        }
    }

    @Test
    public void deployProcessTest() {
        // 获取流程引擎的 RepositoryService
        RepositoryService repositoryService = processEngine.getRepositoryService();

        // 从类路径下读取 BPMN 文件
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("process/ExpenseProcess.bpmn20.xml");

        // 部署流程定义
        Deployment deployment = repositoryService.createDeployment()
                .addInputStream("ExpenseProcess.bpmn20.xml", inputStream)
                .name("Expense")
                .deploy();

        // 输出部署的流程定义信息
        System.out.println("流程定义部署成功，部署ID：" + deployment.getId());

    }
}
