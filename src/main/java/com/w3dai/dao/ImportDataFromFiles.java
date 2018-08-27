package com.w3dai.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.searchbox.action.Action;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.client.JestResultHandler;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;
import org.apache.http.HttpHost;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.bulk.BulkProcessor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.Set;

public class ImportDataFromFiles {
    public void ImportData(String filePath) throws IOException{
        File file = new File(filePath);
        BufferedReader reader = null;
     /**   RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));
**/
        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9200));

        JestClient jestClient = new JestClient(){
            public <T extends JestResult> T execute(Action<T> action) throws IOException {
                return null;
            }

            public <T extends JestResult> void executeAsync(Action<T> action, JestResultHandler<? super T> jestResultHandler) {

            }

            public void shutdownClient() {

            }

            public void setServers(Set<String> set) {

            }

            public void close(){

            }
        };

        try{
            reader = new BufferedReader(new FileReader(file));
            String aLine = null;
            while((aLine = reader.readLine()) != null){
                JSONObject parseObject = JSON.parseObject(aLine);


                Bulk bulk = new Bulk.Builder()
                        .defaultIndex("papers")
                        .defaultType("article")
                        .addAction(Arrays.asList(
                                new Index.Builder( parseObject ).build()
                        )).build();
                JestResult result = jestClient.execute(bulk);
                if (result.isSucceeded()){
                    System.out.println("insert success!");
                }else{
                    System.out.println("insert failed");
                }



            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
        client.close();

    }
}
