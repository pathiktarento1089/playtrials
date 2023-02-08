package controllers;

import elasticconfig.ElasticConfig;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import play.mvc.Controller;
import play.mvc.Result;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EsController extends Controller {
    public RestClient restClient;
    private RestHighLevelClient client = ElasticConfig.getClient();
    public Result add(String name, String address, String section) {
//            Map<String, Object>  jsonMap = new TreeMap<>();
        IndexRequest request = new IndexRequest(name, address, section);
        //request.source(jsonData, XContentType.JSON);
        try {
            IndexResponse response = client.index(request, RequestOptions.DEFAULT);
            return ok("Document indexed with id: " + response.getId());
            //client.index(request,null);
        } catch (IOException e) {
            e.printStackTrace();
            return internalServerError("Error indexing document");
        }
    }
}