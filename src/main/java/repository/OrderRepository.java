package repository;

import domain.Food;
import domain.Order;
import exception.InfrastructureException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static repository.ResultSetUtil.buildFood;
import static repository.ResultSetUtil.buildOrder;

public class OrderRepository extends DatabaseConnection{

    public void add(Order order) {
        Connection connection = openConnection();

        try {
            String sql = "INSERT INTO orders(order_id, date, delivery_id, customer_id, address_id) VALUES(?,?,?,?,?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, order.getOrderId());
            pstmt.setString(2, order.getOrderDate());
            pstmt.setString(3, order.getDelivery().getDeliveryId());
            pstmt.setString(4, order.getCustomer().getId());
            pstmt.setString(5, order.getCustomer().getId());

            pstmt.executeUpdate();

            String sqlOrder = "INSERT INTO order_food(order_id, food_id, quantity, price) VALUES(?,?,?,?)";

            PreparedStatement pstmtOrder = connection.prepareStatement(sqlOrder);
            pstmtOrder.setString(1, order.getOrderId());
            pstmtOrder.setString(2, order.getFood().getId());
            pstmtOrder.setInt(3, order.getOrderQty());
            pstmtOrder.setFloat(4, order.getTotalPrice());

            pstmtOrder.executeUpdate();

            String sqlDelivery = "INSERT INTO deliveries(delivery_id, delivery_time, arrival_time) VALUES(?,?,?)";

            PreparedStatement pstmtDelivery = connection.prepareStatement(sqlDelivery);
            pstmtDelivery.setString(1, order.getDelivery().getDeliveryId());
            pstmtDelivery.setString(2, order.getDelivery().getDeliveryTime());
            pstmtDelivery.setString(3, order.getDelivery().getArrivalTime());

            pstmtDelivery.executeUpdate();

            System.out.println("Order was created!");

        } catch(SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "add"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "add"),e);
        } finally {
            closeConnection(connection);
        }
    }

    public void update(String order_id) {

    }

    public void delete(Order order) {

    }

    public Order getById(String id) {

        Connection connection = openConnection();

        try {
            String sql = "SELECT \n" +
                    "    *\n" +
                    "FROM\n" +
                    "    food,\n" +
                    "    food_ingredients,\n" +
                    "    ingredients,\n" +
                    "    customers,\n" +
                    "    customer_addresses,\n" +
                    "    orders,\n" +
                    "    order_food,\n" +
                    "    deliveries\n" +
                    "WHERE\n" +
                    "    order_food.food_id = food.food_id\n" +
                    "\t\tAND food_ingredients.food_id = food.food_id\n" +
                    "        AND food_ingredients.ingredient_id = ingredients.ingredient_id\n" +
                    "        AND order_food.order_id = orders.order_id\n" +
                    "        AND orders.customer_id = customers.id\n" +
                    "        AND deliveries.delivery_id = orders.delivery_id\n" +
                    "        AND customer_addresses.customer_id = orders.address_id\n" +
                    "        AND orders.order_id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, id);

            ResultSet resultSet = pstmt.executeQuery();

            if(resultSet.next()) {
                return buildOrder(resultSet);
            }
        } catch(SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "getById"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "getById"),e);
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();

        Connection connection = openConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT \n" +
                            "    *\n" +
                            "FROM\n" +
                            "    food,\n" +
                            "    food_ingredients,\n" +
                            "    ingredients,\n" +
                            "    customers,\n" +
                            "    customer_addresses,\n" +
                            "    orders,\n" +
                            "    order_food,\n" +
                            "    deliveries\n" +
                            "WHERE\n" +
                            "    order_food.food_id = food.food_id\n" +
                            "\t\tAND food_ingredients.food_id = food.food_id\n" +
                            "        AND food_ingredients.ingredient_id = ingredients.ingredient_id\n" +
                            "        AND order_food.order_id = orders.order_id\n" +
                            "        AND orders.customer_id = customers.id\n" +
                            "        AND deliveries.delivery_id = orders.delivery_id\n" +
                            "        AND customer_addresses.customer_id = orders.address_id");

            while (resultSet.next()) {
                orders.add(buildOrder(resultSet));
            }
            System.out.println("Finished fetching all orders!");
        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "getAll"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "getAll"),e);
        } finally {
            closeConnection(connection);
        }
        return orders;
    }

    public List<Order> getAllForCustomer(String id) {
        Connection connection = openConnection();

        List<Order> orders = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT \n" +
                            "    orders.date AS date,\n" +
                            "    orders.order_id AS order_id,\n" +
                            "    order_food.price AS price,\n" +
                            "    deliveries.delivery_id,\n" +
                            "    deliveries.delivery_time AS delivery_time,\n" +
                            "    deliveries.arrival_time,\n" +
                            "    order_food.quantity AS quantity,\n" +
                            "    food.food_name AS food_name,\n" +
                            "    ingredients.ingredient_name AS ingredient_name,\n" +
                            "    food_ingredients.qty AS qty,\n" +
                            "    customers.id AS id,\n" +
                            "        customers.password,\n" +
                            "        customers.name,\n" +
                            "        customers.email,\n" +
                            "        customers.phone,\n" +
                            "        customer_addresses.country,\n" +
                            "        customer_addresses.province,\n" +
                            "        customer_addresses.street_name,\n" +
                            "        customer_addresses.street_number,\n" +
                            "        customer_addresses.suite_number,\n" +
                            "        customer_addresses.postal_code,\n" +
                            "        customer_addresses.po_box,\n" +
                            "        food.food_id,\n" +
                            "        food.food_name,\n" +
                            "        food.recipe,\n" +
                            "        food.price,\n" +
                            "        food.food_image,\n" +
                            "        ingredients.ingredient_id,\n" +
                            "        ingredients.ingredient_name\n" +
                            "FROM\n" +
                            "    food,\n" +
                            "    food_ingredients,\n" +
                            "    ingredients,\n" +
                            "    customers,\n" +
                            "    customer_addresses,\n" +
                            "    orders,\n" +
                            "    order_food,\n" +
                            "    deliveries\n" +
                            "WHERE\n" +
                            "    order_food.food_id = food.food_id\n" +
                            "        AND food_ingredients.food_id = food.food_id\n" +
                            "        AND food_ingredients.ingredient_id = ingredients.ingredient_id\n" +
                            "        AND order_food.order_id = orders.order_id\n" +
                            "        AND orders.customer_id = customers.id\n" +
                            "        AND deliveries.delivery_id = orders.delivery_id\n" +
                            "        AND customer_addresses.customer_id = orders.address_id\n" +
                            "        AND orders.customer_id = "+"'"+id+"' " +
                            "ORDER BY orders.date DESC;");

            while (resultSet.next()) {
                orders.add(buildOrder(resultSet));
            }
            System.out.println("Finished fetching all orders for the customer!");
        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "getAllForCustomer"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "getAllForCustomer"),e);
        } finally {
            closeConnection(connection);
        }
        return orders;
    }
}
