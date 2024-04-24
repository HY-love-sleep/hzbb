package demo;

import lombok.Data;
import org.springframework.stereotype.Service;

/**
 * Description: 任务示例
 * Author: yhong
 * Date: 2024/4/23
 */
@Data
@Service
public class Cat {
    private String catName;
    public Cat setCatName(String name) {
        this.catName = name;
        return this;
    }
}
