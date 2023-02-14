package controllers;

import com.fasterxml.jackson.databind.JsonNode;
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

//    public Result connection() throws IOException {
//        System.out.println("connected to Elastic Search");
//        String json = "{\"id\":\"3\",\"name\":\"Jp\"}";
//        Request request = new Request("POST", "/employee/_doc/2");
//        request.setJsonEntity(json);
//        Response response = esClient.getRestClient().performRequest(request);
//        return ok(EntityUtils.toString(response.getEntity()));
//    }
    public Result addInfo(Http.Request request) throws IOException {
        JsonNode body = request.body().asJson();
        String id = body.get("id").asText();
        String name = body.get("name").asText();
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("id", id);
        jsonMap.put("name", name);
        IndexRequest indexRequest = new IndexRequest("employee","_doc","4").source(jsonMap);
        esClient.getRestClient().index(indexRequest,RequestOptions.DEFAULT);
        return ok("Data successfully inserted");

    }
}