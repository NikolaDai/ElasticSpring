package com.w3dai.dao;


import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class CreateIndexFromData{
    public void CreateIndex() throws IOException {
        //create the high level client of the localhost
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));

        CreateIndexRequest request = new CreateIndexRequest("papers");

        request.settings(Settings.builder() // <1>
                .put("index.number_of_shards", 3)
                .put("index.number_of_replicas", 2)
        );

        request.mapping("article", // <1>
                "{\n" +
                        "  \"article\": {\n" +
                        "    \"properties\": {\n" +
                        "      \"引题\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"store\":\"false\",\n"+
                        "        \"term_vector\": \"with_positions_offsets\",\n" +
                        "         \"analyzer\": \"ik_max_word\",\n" +
                        "         \"search_analyzer\": \"ik_max_word\"\n" +
                        "      },\n" +
                        "      \"标题\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"store\":\"false\",\n"+
                        "        \"term_vector\": \"with_positions_offsets\",\n" +
                        "         \"analyzer\": \"ik_max_word\",\n" +
                        "         \"search_analyzer\": \"ik_max_word\"\n" +
                        "      },\n" +
                        "      \"副题\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"store\":\"false\",\n"+
                        "        \"term_vector\": \"with_positions_offsets\",\n" +
                        "         \"analyzer\": \"ik_max_word\",\n" +
                        "         \"search_analyzer\": \"ik_max_word\"\n" +
                        "      },\n" +
                        "      \"作者\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"index\":\"false\"\n"+
                        "      },\n" +
                        "      \"责编\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"index\":\"false\"\n"+
                        "      },\n" +
                        "      \"版面\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"index\":\"false\"\n"+
                        "      },\n" +
                        "      \"分类\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"index\":\"false\"\n"+
                        "      },\n" +
                        "      \"日期\": {\n" +
                        "        \"type\": \"date\",\n" +
                        "        \"format\":\"yyyymmdd\"\n"+
                        "      },\n" +
                        "      \"正文\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"term_vector\": \"with_positions_offsets\",\n" +
                        "         \"analyzer\": \"ik_max_word\",\n" +
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

    }
}
