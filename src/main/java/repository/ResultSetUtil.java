package repository;

import domain.Customer;
import domain.Delivery;
import domain.Food;
import domain.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class ResultSetUtil {

    public static Customer buildCustomer(ResultSet resultSet) throws SQLException {

        String id = resultSet.getString("id");
        String password = resultSet.getString("password");
        String name = resultSet.getString("name");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        String country = resultSet.getString("country");
        String province = resultSet.getString("province");
        String streetName = resultSet.getString("street_name");
        int streetNumber = resultSet.getInt("street_number");
        int suiteNumber = resultSet.getInt("suite_number");
        String postalCode = resultSet.getString("postal_code");
        int poBox = resultSet.getInt("po_box");

        return new Customer(id, password, name, email, phone, country, province, streetName,
                streetNumber, suiteNumber, postalCode, poBox);
    }

    public static Food buildFood(ResultSet resultSet) throws SQLException {

        String id = resultSet.getString("food_id");
        String name = resultSet.getString("food_name");
        String recipe = resultSet.getString("recipe");
        float price = resultSet.getFloat("price");
        String ingredientId = resultSet.getString("ingredient_id");
        String ingredient = resultSet.getString("ingredient_name");
        int ingredientQty = resultSet.getInt("qty");
        String image = resultSet.getString("food_image");

        return new Food(id, name, recipe, price, ingredientId, ingredient, ingredientQty, image);
    }

    public static Delivery buildDelivery(ResultSet resultSet) throws SQLException {
        String id = resultSet.getString("delivery_id");
        String deliveryTime = resultSet.getString("delivery_time");
        String arrivalTime = resultSet.getString("arrival_time");

        return new Delivery(id, deliveryTime, arrivalTime);
    }

    public static Order buildOrder(ResultSet resultSet) throws SQLException {

        String id = resultSet.getString("order_id");
        String orderDate = resultSet.getString("date");
        Delivery delivery = buildDelivery(resultSet);
        Customer customer = buildCustomer(resultSet);
        int orderQty = resultSet.getInt("quantity");
        float totalPrice = resultSet.getFloat("price");
        Food food = buildFood(resultSet);

        return new Order(id, orderDate, delivery, customer, orderQty, totalPrice, food);
    }

}
