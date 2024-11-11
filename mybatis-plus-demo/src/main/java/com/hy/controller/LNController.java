package com.hy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: 辽宁API获取用户、结构
 *
 * @author: yhong
 * Date: 2024/11/11
 */
@RestController
@RequestMapping("/dgpc/api")
public class LNController {
    @GetMapping(value = "/dept/pageList")
    public Map<String, Object> deptPageList(
            @RequestParam Long current,
            @RequestParam Long size
    ) {
        // 模拟查询成功的情况
        if (current == 1 && size == 10) {
            return getDeptSuccessResponse();
        } else {
            // 模拟查询失败的情况
            return getDeptErrorResponse();
        }
    }

    @GetMapping(value = "/user/pageList")
    public Map<String, Object> userPageList(
            @RequestParam Long current,
            @RequestParam Long size
    ) {
        // 模拟查询成功的情况
        if (current == 1 && size == 10) {
            return getSuccessResponse();
        } else {
            // 模拟查询失败的情况
            return getErrorResponse();
        }
    }



    private Map<String, Object> getDeptErrorResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 2008);
        response.put("timestamp", System.currentTimeMillis());
        response.put("msg", "查询失败");
        response.put("data", null);
        return response;
    }

    private Map<String, Object> getDeptSuccessResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("msg", "ok");
        response.put("timestamp", System.currentTimeMillis());

        Map<String, Object> data = new HashMap<>();
        data.put("total", 3);
        data.put("records", getDeptRecords());
        data.put("orders", new String[]{});
        data.put("size", 10);
        data.put("current", 1);
        data.put("pages", 1);

        response.put("data", data);
        return response;
    }

    private Object[] getDeptRecords() {
        return new Object[]{
                new HashMap<String, Object>() {{
                    put("id", "1");
                    put("deptName", "浙江省");
                    put("deptDesc", "中国浙江省123");
                    put("sortCode", 1);
                    put("gradeCode", "0,1");
                    put("parentId", "0");
                    put("enabled", 1);
                    put("areaCode", "S00");
                    put("areaName", "浙江省");
                    put("areaLevel", "1");
                }},
                new HashMap<String, Object>() {{
                    put("id", "2");
                    put("deptName", "杭州市");
                    put("deptDesc", "中国浙江省杭州市");
                    put("sortCode", 2);
                    put("gradeCode", "0,1,2");
                    put("parentId", "1");
                    put("enabled", 1);
                    put("areaCode", "S01");
                    put("areaName", "杭州市");
                    put("areaLevel", "2");
                }},
                new HashMap<String, Object>() {{
                    put("id", "3");
                    put("deptName", "绍兴市");
                    put("deptDesc", "中国浙江省");
                    put("sortCode", 3);
                    put("gradeCode", "0,1,3");
                    put("parentId", "1");
                    put("enabled", 1);
                    put("areaCode", "S02");
                    put("areaName", "绍兴市");
                    put("areaLevel", "2");
                }}
        };
    }

    private Map<String, Object> getSuccessResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 200);
        response.put("msg", "ok");
        response.put("timestamp", System.currentTimeMillis());

        Map<String, Object> data = new HashMap<>();
        data.put("total", 2);
        data.put("records", getRecords());
        data.put("orders", new String[]{});
        data.put("size", 10);
        data.put("current", 1);
        data.put("pages", 1);

        response.put("data", data);
        return response;
    }

    private Map<String, Object> getErrorResponse() {
        Map<String, Object> response = new HashMap<>();
        response.put("code", 2008);
        response.put("timestamp", System.currentTimeMillis());
        response.put("msg", "查询失败");
        response.put("data", null);
        return response;
    }

    private Object[] getRecords() {
        return new Object[]{
                new HashMap<String, Object>() {{
                    put("ext", new HashMap<>());
                    put("id", "252");
                    put("businessTenantId", "107,108");
                    put("deptId", "93");
                    put("deptName", "联通数科");
                    put("account", "zhangsan1234");
                    put("name", "张三");
                    put("phone", "13111111111");
                    put("email", "zhangsan1234@chinaunicom.cn");
                    put("enabled", 1);
                }},
                new HashMap<String, Object>() {{
                    put("ext", new HashMap<>());
                    put("id", "253");
                    put("deptId", "93");
                    put("deptName", "联通数科");
                    put("account", "lisi8899");
                    put("name", "李四");
                    put("phone", "13111111112");
                    put("email", "lisi8899@chinaunicom.cn");
                    put("enabled", 1);
                }}
        };
    }
}
