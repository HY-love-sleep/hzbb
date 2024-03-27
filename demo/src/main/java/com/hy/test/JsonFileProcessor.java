package com.hy.test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JsonFileProcessor {

    public static void processJsonFiles(String directoryPath) {
        try {
            // 获取目录下所有文件
            Files.walk(Paths.get(directoryPath))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".json"))
                    .forEach(JsonFileProcessor::processJsonFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static void processJsonFile(Path filePath) {
        try {
            // 读取 JSON 文件
            StringBuilder jsonData = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()));
            String line;
            while ((line = reader.readLine()) != null) {
                jsonData.append(line);
            }
            reader.close();

            JSONArray jsonArray = JSONArray.parseArray(jsonData.toString());

            // 遍历 JSON 数组中的每个 JSON 对象
            for (int i = 0; i < jsonArray.size(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // 添加 subModuleCode 字段为之前的 moduleCode 字段
                String moduleCode = jsonObject.getString("moduleCode");
                jsonObject.put("subModuleCode", moduleCode);

                // 修改 moduleCode 字段为 ln_security
                jsonObject.put("moduleCode", "liaoning_anquan");
            }

            // 写入修改后的 JSON 数据回文件
            FileWriter writer = new FileWriter(filePath.toFile());
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            bufferedWriter.write(jsonArray.toJSONString());
            bufferedWriter.close();

            System.out.println("Processed file: " + filePath.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 指定目录路径
        String directoryPath = "C:\\Users\\洪岩\\Desktop\\LN-Portal\\LnMenuJson\\ln\\fixModuleCode";
        processJsonFiles(directoryPath);
    }
}
