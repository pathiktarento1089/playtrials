import com.google.inject.Provides;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
public class ElasticSearchConfig {
    @Provides
    public RestClient restClient(){
        RestClient client = RestClient.builder(
                new HttpHost("localhost", 9200, "http")
        ).build();
        return client;
    }


}

