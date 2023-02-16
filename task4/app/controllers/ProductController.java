package controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.Service;

import javax.inject.Inject;
import java.sql.SQLException;

public class ProductController extends Controller {
    @Inject
    public Service service;
    public Result addProductInfo(Http.Request request) throws SQLException {
        service.addProduct(request);
        return ok("Data inserted");
    }
    public Result getProductInfo(Integer id) throws JsonProcessingException, SQLException {
       String result = service.getProductById(id);
       return ok(result);
    }
    public Result getProductSpecById(Integer id) throws JsonProcessingException, SQLException {
        String result = service.getProductSpecById(id);
        return ok(result);
    }
}
