package service;

import Configuration.EsConnection;
import com.fasterxml.jackson.databind.JsonNode;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import play.mvc.Http;

import javax.inject.Inject;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProductEsService {
    @Inject
    private EsConnection esConnection;

    public void addProductInfo(Http.Request request) throws IOException {
        JsonNode body = request.body().asJson();

        String id = body.get("productid").asText();
        String name = body.get("productname").asText();
        String specId = body.get("specid").asText();
        String speDetails = body.get("specdetails").asText();

        Map<String,Object> productmap = new HashMap<>();
        productmap.put("productid",id);
        productmap.put("productname", name);
        productmap.put("specid", specId);
        IndexRequest indexRequest = new IndexRequest("product","_doc",id).source(productmap);
        IndexResponse response = esConnection.getRestHighLevelClient().index(indexRequest, RequestOptions.DEFAULT);

        Map<String,Object> specmap = new HashMap<>();
        specmap.put("specid",specId);
        specmap.put("specdetails", speDetails);
        IndexRequest indexRequest1 = new IndexRequest("productspec","_doc",id).source(specmap);
        IndexResponse response1 = esConnection.getRestHighLevelClient().index(indexRequest1, RequestOptions.DEFAULT);
    }
    public String getByProductId(String id) throws IOException {
        GetRequest request1 = new GetRequest("product","_doc",id);
        GetResponse response1 = esConnection.getRestHighLevelClient().get(request1, RequestOptions.DEFAULT);
        String result = response1.getSourceAsString();
        return result;
    }
    public String getByProductSpecId(String id) throws IOException {
        GetRequest request2 = new GetRequest("productspec", "_doc",id);
        GetResponse response2 = esConnection.getRestHighLevelClient().get(request2, RequestOptions.DEFAULT);
        String result = response2.getSourceAsString();
        return result;
    }
}
