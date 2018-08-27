package com.w3dai.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

        UpdateRequest request = new UpdateRequest(
                "papers",
                "article",
                "1");

        IndexRequest indexRequest = new IndexRequest("papers", "article");

        System.out.print(request);
        try{
            reader = new BufferedReader(new FileReader(file));
            String aLine = null;
            while((aLine = reader.readLine()) != null){
                JSONObject parseObject = JSON.parseObject(aLine);


                IndexResponse response = client.prepareIndex("papers", "article", "1").setSource(parseObject).get();



            }

        }
        catch(IOException e){
            e.printStackTrace();
        }
        client.close();

    }
}
