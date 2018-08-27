package com.w3dai.dao;


import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.ActionRequestBuilder;
import org.elasticsearch.action.support.PlainActionFuture;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class CreateIndexFromData{
    public void CreateIndex() throws IOException {
        //create the high level client of the localhost
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));

        CreateIndexRequest request = new CreateIndexRequest("papers01");

        request.settings(Settings.builder() // <1>
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2)
        );

        request.mapping("article", // <1>
                "{\n" +
                        "  \"article\": {\n" +
                        "    \"properties\": {\n" +
                        "      \"引题\": {\n" +
                        "        \"type\": \"text\"\n" +
                        "        \"store\":\"no\"\n"+
                        "        \"term_analyzer\": \"with_positions_offsets\",\n" +
                        "         \"index_analyzer\": \"ik_max_word\",\n" +
                        "         \"search_analyzer\": \"ik_max_word\"\n" +
                        "      },\n" +
                        "      \"标题\": {\n" +
                        "        \"type\": \"text\"\n" +
                        "        \"store\":\"no\"\n"+
                        "        \"term_analyzer\": \"with_positions_offsets\",\n" +
                        "         \"index_analyzer\": \"ik_max_word\",\n" +
                        "         \"search_analyzer\": \"ik_max_word\"\n" +
                        "      },\n" +
                        "      \"副题\": {\n" +
                        "        \"type\": \"text\"\n" +
                        "        \"store\":\"no\"\n"+
                        "        \"term_analyzer\": \"with_positions_offsets\",\n" +
                        "         \"index_analyzer\": \"ik_max_word\",\n" +
                        "         \"search_analyzer\": \"ik_max_word\"\n" +
                        "      },\n" +
                        "      \"作者\": {\n" +
                        "        \"type\": \"text\"\n" +
                        "        \"index\":\"not_analyzed\"\n"+
                        "      },\n" +
                        "      \"责编\": {\n" +
                        "        \"type\": \"text\"\n" +
                        "        \"index\":\"not_analyzed\"\n"+
                        "      },\n" +
                        "      \"版面\": {\n" +
                        "        \"type\": \"text\"\n" +
                        "        \"index\":\"not_analyzed\"\n"+
                        "      },\n" +
                        "      \"分类\": {\n" +
                        "        \"type\": \"text\"\n" +
                        "        \"index\":\"not_analyzed\"\n"+
                        "      },\n" +
                        "      \"日期\": {\n" +
                        "        \"type\": \"date\"\n" +
                        "        \"format\":\"yyyymmdd\"\n"+
                        "      },\n" +
                        "      \"正文\": {\n" +
                        "        \"type\": \"text\"\n" +
                        "        \"term_analyzer\": \"with_positions_offsets\",\n" +
                        "         \"index_analyzer\": \"ik_max_word\",\n" +
                        "         \"search_analyzer\": \"ik_max_word\"\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }\n" +
                        "}",
                XContentType.JSON);

        CreateIndexResponse createIndexResponse = client.indices().create(request, RequestOptions.DEFAULT);

        boolean acknowledged = createIndexResponse.isAcknowledged();
        boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();

        System.out.println(acknowledged + "##" + shardsAcknowledged);


        /**
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
         **/
    }
}
