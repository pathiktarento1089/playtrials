package service;

import Configuration.MysqlConnection;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.mvc.Http;

import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Service {
    @Inject
    public MysqlConnection mysqlConnection;
    HashMap<Integer, String> productMap = new HashMap<>();
    public void addProduct(Http.Request request) throws SQLException {
        JsonNode jsonNode = request.body().asJson();
        int id= jsonNode.get("id").asInt();
        String name= jsonNode.get("name").asText();
        int specId = jsonNode.get("specId").asInt();
        String productSpec = jsonNode.get("productSpec").asText();

        PreparedStatement statement = mysqlConnection.getConnection().prepareStatement("insert into products (id, name,specId) values (?,?,?);");
        statement.setInt(1, id);
        statement.setString(2, name);
        statement.setInt(3,specId);
        statement.executeUpdate();

        PreparedStatement statement1 = mysqlConnection.getConnection().prepareStatement("insert into ProductSpec (specId, productSpec) values (?,?);");
        statement1.setInt(1, specId);
        statement1.setString(2, productSpec);
        statement1.executeUpdate();
    }
    public String getProductById(int id) throws SQLException {
        PreparedStatement statement = mysqlConnection.getConnection().prepareStatement("select * from products where id=?");
        statement.setInt(1,id);
        ResultSet resultSet = statement.executeQuery();
        ObjectNode result = Json.newObject();
        while (resultSet.next()){
            int productId = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int specId = resultSet.getInt("specId");
            result.put("id",productId);
            result.put("name",name);
            result.put("specId", specId);
            //productMap.put(specId,null);
        }
        return result.toString();
    }
    public String getProductSpecById(int specId) throws SQLException {
        PreparedStatement statement = mysqlConnection.getConnection().prepareStatement("select * from ProductSpec where specId=?");
        statement.setInt(1,specId);
        ResultSet resultSet = statement.executeQuery();
        ObjectNode result = Json.newObject();
        while (resultSet.next()){
            int idSpec = resultSet.getInt("specId");
            String productSpec = resultSet.getString("productSpec");
            result.put("specId",idSpec);
            result.put("productSpec",productSpec);
        }
        return result.toString();
    }
    public HashMap<Integer, String> getProductMap() {
        return productMap;
    }
}
