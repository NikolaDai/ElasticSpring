package com.w3dai.dao;


import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class CreateIndexFromData{
    public void IndexGenerator() throws Exception{

        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new TransportAddress(InetAddress.getByName("localhost"), 9300));
        IndicesAdminClient indicesAdminClient = client.admin().indices();

        Map<String, Object> typesName = new HashMap<String, Object>();
        typesName.put("引题", "type=text");
        typesName.put("正题", "type=text");
        typesName.put("副题", "type=text");
        typesName.put("作者", "type=text");
        typesName.put("日期", "type=text");
        typesName.put("版面", "type=text");
        typesName.put("分类", "type=text");
        typesName.put("体裁", "type=text");

        indicesAdminClient.prepareCreate("paper10")
                .setSettings(Settings.builder()
                        .put("index.number_of_shards", 3)
                        .put("index.number_of_replicas", 2)
                ).addMapping("article", typesName)
                .get();
    }
}
