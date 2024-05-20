import com.hy.DemoApplication;
import com.hy.utils.SdkUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Description:
 *
 * @author: yhong
 * Date: 2024/5/15
 */
@SpringBootTest(classes = DemoApplication.class)
public class Test {

    @org.junit.jupiter.api.Test
    public void test1() throws Exception {
        String keyId = SdkUtils.getKeyId();
        System.out.println(keyId);
    }

    @org.junit.jupiter.api.Test
    public void encryptTest() throws Exception {
        String encrypted = SdkUtils.encryptByApi("成天加班好吧， 再也不出差了， 再也不当牛马了");
        System.out.println(encrypted);
        // MGoCAQIwEgIBATAKBggqgRzPVQFoAQoBAQNRAOJBekrhl8ZM/bg9yCN8mBvu9fVG+m0OMeBxKUw9+P9LguiH+rcEldaE0wLVWT8uJXmakjCa8JVLbzULWl8oQWU9UuOcKeijkmvkfuFr/dzf
    }

    @org.junit.jupiter.api.Test
    public void decryptTest() throws Exception {
        String decrypted = SdkUtils.decryptByApi("MGoCAQIwEgIBATAKBggqgRzPVQFoAQoBAQNRAOJBekrhl8ZM/bg9yCN8mBvu9fVG+m0OMeBxKUw9+P9LguiH+rcEldaE0wLVWT8uJXmakjCa8JVLbzULWl8oQWU9UuOcKeijkmvkfuFr/dzf");
        System.out.println(SdkUtils.decodeBase64(decrypted));
        System.out.println(SdkUtils.decodeToString(SdkUtils.decodeBase64(decrypted)));
    }

}
