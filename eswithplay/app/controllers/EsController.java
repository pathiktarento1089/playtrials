package controllers;

import data.EsClient;
import elasticconfig.ElasticConfig;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.*;
import org.elasticsearch.common.xcontent.XContentType;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import scala.util.parsing.json.JSONObject;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EsController extends Controller {
//    public RestClient restClient;
    @Inject
    private EsClient esClient;

    public Result connection() throws IOException {
        System.out.println("connected to Elastic Search");
        String json = "{\"id\":\"3\",\"name\":\"Jp\"}";
        Request request = new Request("POST", "/employee/_doc/2");
        request.setJsonEntity(json);
        Response response = esClient.getRestClient().performRequest(request);
        return ok(EntityUtils.toString(response.getEntity()));
    }

    public Result addInfo(int id, String name) {
//            Map<String, Object>  jsonMap = new TreeMap<>();
        Request request = new Request("POST", "/employee/_doc/2");
//        request.setJsonEntity("{\"json\":\"text\"}");
        request.setJsonEntity(String.valueOf(new StringEntity(String.valueOf(request), ContentType.APPLICATION_JSON)));
        try {
            Response response = esClient.getRestClient().performRequest(request);
            return ok(EntityUtils.toString(response.getEntity()));
            //client.index(request,null);
        } catch (IOException e) {
            e.printStackTrace();
            return internalServerError("Error indexing document");
        }
    }


//    public Result addinfo(String indexName, String typeName, String id, JSONObject data)
//    {
//        IndexRequest request = new IndexRequest(indexName, typeName, id);
//        request.source(data.toString(), XContentType.JSON);
//        IndexResponse response = EsClient.index(request, RequestOptions.DEFAULT);
//    }
}