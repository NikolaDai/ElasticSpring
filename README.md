# ElasticSpring
The purpose of ElasticSpring is to implement elasticsearch functions using Spring. 


Preparations:
In order to make everything run as expected, you need intall the softwares in the URL:http://www.cnblogs.com//hts-technology/p/8477258.html
IK plugin installation:https://github.com/medcl/elasticsearch-analysis-ik


about mapping:https://blog.csdn.net/napoay/article/details/73100110?locationNum=9&fps=1#324-termvector
code of backup:

 request.mapping("article", // <1>
                "{\n" +
                        "  \"article\": {\n" +
                        "    \"properties\": {\n" +
                        "      \"引题\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"store\":\"no\",\n"+
                        "        \"term_analyzer\": \"with_positions_offsets\",\n" +
                        "         \"analyzer\": \"ik_max_word\",\n" +
                        "         \"search_analyzer\": \"ik_max_word\"\n" +
                        "      },\n" +
                        "      \"标题\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"store\":\"no\",\n"+
                        "        \"term_analyzer\": \"with_positions_offsets\",\n" +
                        "         \"analyzer\": \"ik_max_word\",\n" +
                        "         \"search_analyzer\": \"ik_max_word\"\n" +
                        "      },\n" +
                        "      \"副题\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"store\":\"no\",\n"+
                        "        \"term_analyzer\": \"with_positions_offsets\",\n" +
                        "         \"analyzer\": \"ik_max_word\",\n" +
                        "         \"search_analyzer\": \"ik_max_word\"\n" +
                        "      },\n" +
                        "      \"作者\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"index\":\"not_analyzed\"\n"+
                        "      },\n" +
                        "      \"责编\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"index\":\"not_analyzed\"\n"+
                        "      },\n" +
                        "      \"版面\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"index\":\"not_analyzed\"\n"+
                        "      },\n" +
                        "      \"分类\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"index\":\"not_analyzed\"\n"+
                        "      },\n" +
                        "      \"日期\": {\n" +
                        "        \"type\": \"date\",\n" +
                        "        \"format\":\"yyyymmdd\"\n"+
                        "      },\n" +
                        "      \"正文\": {\n" +
                        "        \"type\": \"text\",\n" +
                        "        \"term_analyzer\": \"with_positions_offsets\",\n" +
                        "         \"analyzer\": \"ik_max_word\",\n" +
                        "         \"search_analyzer\": \"ik_max_word\"\n" +
                        "      }\n" +
                        "    }\n" +
                        "  }\n" +
                        "}",
                XContentType.JSON);