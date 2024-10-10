package com.hy.utils;

import com.hy.service.HttpMessageService;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;

@Component()
public class ElasticsearchExample {
    @Autowired
    private HttpMessageService messageService;

    private static final String INDEX_NAME = ".ds-packetbeat-8.15.0-2024.10.08-000003";
    private static final String DOCUMENT_ID = "MK6LcJIBMgwQbSCsQaqG";
    private static final String USERNAME = "elastic";
    private static final String PASSWORD = "qNnEH$u#UddNpShQpX^y";
    private static final String OUTPUT_FILE_PATH = "C:\\My_Work\\IdeaProjects\\MyGitProject\\hzbb\\parse-httpstream\\src\\main\\resources\\output_file\\output_file.png";

    public static void main(String[] args) {
        try {
            final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY,
                    new UsernamePasswordCredentials(USERNAME, PASSWORD));

            // 创建RestHighLevelClient实例
            try (RestHighLevelClient client = new RestHighLevelClient(
                    RestClient.builder(new HttpHost("192.168.27.251", 9200, "http"))
                            .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider)))) {

                GetRequest getRequest = new GetRequest(INDEX_NAME, DOCUMENT_ID);
                getRequest.fetchSourceContext(new FetchSourceContext(true, new String[]{"http.response.body.content"}, null));

                GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);

                if (response.isExists()) {
                    // byte[] content = (byte[]) response.getSourceAsMap().get("http.response.body.content");
                    Map<String, Object> sourceAsMap = response.getSourceAsMap();
                    Map<String, Object> httpResponseMap = (Map<String, Object>) sourceAsMap.get("http");
                    Map<String, Object> responseBodyMap = (Map<String, Object>) httpResponseMap.get("response");
                    Map<String, Object> bodyMap = (Map<String, Object>) responseBodyMap.get("body");
                    // 直接获取字节数组
                    // byte[] contentBytes = (byte[]) bodyMap.get("content");
                    // 获取字符串形式的文件内容
                    String contentString = (String) bodyMap.get("content");
                    // byte[] contentBytes = Base64.getDecoder().decode(contentString);
                    // 将字符串转换为字节数组
                    byte[] contentBytes = contentString.getBytes("ISO-8859-1"); // ISO-8859-1是单字节编码，不会改变原始字节内容
                    System.out.println("字节数组长度：" + contentBytes.length);
                    // 将字节数组解析成文件
                    try (FileOutputStream fos = new FileOutputStream(OUTPUT_FILE_PATH)) {
                        fos.write(contentBytes);
                        System.out.println("file saved to: " + OUTPUT_FILE_PATH);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // System.out.println("Content: " + Arrays.toString(contentBytes));
                } else {
                    System.out.println("Document not found.");
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}