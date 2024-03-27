package com.hy.test;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.*;

/**
 * Description: 修改用户中心菜单， 增加顶层目录
 * Author: yhong
 * Date: 2024/3/26
 */


public class JsonProcessor {

    public static void updateParent(String inputFilePath, String outputFilePath, String newParentId, String newParentCode) {
        try {
            // 读取输入文件
            FileReader reader = new FileReader(inputFilePath);
            BufferedReader bufferedReader = new BufferedReader(reader);
            StringBuilder jsonData = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                jsonData.append(line);
            }
            bufferedReader.close();

            // 解析JSON数据
            JSONArray jsonArray = JSONArray.parseArray(jsonData.toString());
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                if (item.getInteger("parentId") == 0) {
                    item.put("parentId", newParentId);
                    item.put("parentCode", newParentCode);
                }
            }
            // menuCode添加 _ln后缀
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                String menuCode = item.getString("menuCode");
                if (menuCode != null && !menuCode.isEmpty()) {
                    item.put("menuCode", menuCode + "_ln");
                }
            }
            // 创建新的JSON对象并添加到数组中
            JSONObject newItem = new JSONObject();
            newItem.put("moduleCode", "workflow");
            newItem.put("title", "your_title");
            newItem.put("path", newItem.getString("moduleCode") + "/top");
            newItem.put("component", newItem.getString("moduleCode") + "/top");
            newItem.put("parentCode", "root");
            newItem.put("parentId", 0);
            newItem.put("id", newParentId);
            newItem.put("menuCode", newParentCode);
            newItem.put("permissionCode", newItem.getString("moduleCode") + "Top");
            jsonArray.add(newItem);

            // 将修改后的JSON数据写入输出文件
            FileWriter writer = new FileWriter(outputFilePath);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(jsonArray.toJSONString());
            bufferedWriter.close();

            System.out.println("文件处理成功。");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 示例用法
    public static void main(String[] args) {
        String inputFilePath = "C:\\Users\\洪岩\\Desktop\\LN-Portal\\OriginMenuJson\\workflow.json";  // 输入文件路径
        String outputFilePath = "C:\\Users\\洪岩\\Desktop\\LN-Portal\\LnMenuJson\\workflow.json";  // 输出文件路径

        String newParentId = "20000";  // 新的parentId
        String newParentCode = "20000_ln";  // 新的parentCode

        updateParent(inputFilePath, outputFilePath, newParentId, newParentCode);
    }
}

