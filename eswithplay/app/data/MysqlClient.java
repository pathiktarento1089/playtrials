package data;

import java.sql.*;
import java.util.HashMap;
public class MysqlClient {
    private Connection connection;
    private HashMap<Integer,String> countryMap;
    public MysqlClient() throws SQLException{
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/country","root","MyNewPass");
        System.out.println("Successfully connected");
        HashMap<Integer,String> countryMap = new HashMap<>();
        PreparedStatement statement = connection.prepareStatement("select * from country_list ");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            int country_id = resultSet.getInt("country_id");
            String country_name = resultSet.getString("country_name");
            countryMap.put(country_id,country_name);
    }
        this.countryMap = countryMap;
    }
    public HashMap<Integer, String> getCountryMap() {
        return countryMap;
    }
}
