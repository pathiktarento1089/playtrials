package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.JsonArray;
import data.MysqlClient;
import org.elasticsearch.client.ml.dataframe.evaluation.regression.HuberMetric;
import play.mvc.Controller;
import play.mvc.Result;

import javax.inject.Inject;
import java.util.Map;

public class MysqlController extends Controller {
    @Inject
    private MysqlClient mysqlClient;

    public Result getData() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json= objectMapper.writeValueAsString(mysqlClient.getCountryMap());
        //return ok(mysqlClient.getCountryMap().toString());
        return ok(json);
    }

}
