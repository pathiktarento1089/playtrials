package controllers;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.ProductEsService;

import javax.inject.Inject;
import java.io.IOException;

public class ProductEsController extends Controller {
    @Inject
    private ProductEsService esService;
    public Result addData(Http.Request request) throws IOException {
        esService.addProductInfo(request);
        return ok("Data added successfully");
    }
    public Result getProductData(String id) throws IOException {
        String result = esService.getByProductId(id);
        return ok(result);
    }

    public Result getProductSpecData(String id) throws IOException {
        String result = esService.getByProductSpecId(id);
        return ok(result);
    }
}
