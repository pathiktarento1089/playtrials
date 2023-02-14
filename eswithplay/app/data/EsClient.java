package data;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

public class EsClient {
    private RestHighLevelClient restClient;
    public EsClient() {
        restClient =new RestHighLevelClient(RestClient.builder(new HttpHost("localhost",9200,"http")));

    }

    public static IndexResponse index(IndexRequest request, RequestOptions aDefault) {
        return null;
    }

    public RestHighLevelClient getRestClient(){
        return restClient;
    }
}
