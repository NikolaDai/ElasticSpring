package com.w3dai.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.JestResult;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class ImportDataFromFiles {
    public void ImportData(String filePath) throws IOException{
        File file = new File(filePath);
        BufferedReader reader = null;

        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder("http://localhost:9200")
                .multiThreaded(true)
                //默认情况下，在给定的路由上创建不超过2个并发连接
                .defaultMaxTotalConnectionPerRoute(2)
                //总共不超过20个连接
                .maxTotalConnection(20)
                      .build());
        JestClient jestClient = factory.getObject();

        int lineNum = 0;
        try {
                reader = new BufferedReader(new FileReader(file));
                String aLine;
                while ((aLine = reader.readLine()) != null) {
                    System.out.println("\n\n\n######################################" + (lineNum++) + "######################################\n\n\n" );
                    JSONObject parseObject = JSON.parseObject(aLine);
                    Index index = new Index.Builder(parseObject).index("papers").type("article").build();
                    JestResult result = jestClient.execute(index);
                    if (result != null && !result.isSucceeded())
                        throw new RuntimeException(result.getErrorMessage() + "插入更新索引失败!");
                }
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}
