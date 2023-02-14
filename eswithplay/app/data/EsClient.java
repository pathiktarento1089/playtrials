package data;

import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;

public class EsClient {
    private RestClient restClient;
    public EsClient() {
        restClient = RestClient.builder(
                new HttpHost("localhost",9200,"http")
        ).build();
    }

    public static IndexResponse index(IndexRequest request, RequestOptions aDefault) {
        return null;
    }

    public RestClient getRestClient(){
        return restClient;
    }
}
