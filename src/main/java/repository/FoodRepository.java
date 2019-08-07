package repository;

import domain.Food;
import com.mysql.cj.protocol.Resultset;
import exception.InfrastructureException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static repository.ResultSetUtil.buildFood;

public class FoodRepository extends DatabaseConnection {

    public void add(Food food) {

        Connection connection = openConnection();

        try {
            String sql = "INSERT INTO food(food_id, food_name, recipe, price, food_image) VALUES(?,?,?,?,?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, food.getId());
            pstmt.setString(2, food.getName());
            pstmt.setString(3, food.getRecipe());
            pstmt.setFloat(4, food.getPrice());
            pstmt.setString(5, food.getImage());

            pstmt.executeUpdate();

            String sqlIngr = "INSERT INTO ingredients(ingredient_id, ingredient_name) VALUES(?,?)";

            PreparedStatement pstmtIngr = connection.prepareStatement(sqlIngr);

            pstmtIngr.setString(1, food.getIngredientId());
            pstmtIngr.setString(2, food.getIngredient());

            pstmtIngr.executeUpdate();

            String sqlQty = "INSERT INTO food_ingredients(food_id, ingredient_id, qty) VALUES(?,?,?)";

            PreparedStatement pstmtQty = connection.prepareStatement(sqlQty);

            pstmtQty.setString(1, food.getId());
            pstmtQty.setString(2, food.getIngredientId());
            pstmtQty.setInt(3, food.getIngredientQty());

            pstmtQty.executeUpdate();

            System.out.println("Food added!");

        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "add"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "add"),e);
        } finally {
            closeConnection(connection);
        }
    }

    public void update(Food food) {

        Connection connection = openConnection();

        try {
            String sql = "UPDATE food SET food_name = ?, recipe = ?, price = ?, food_image = ? WHERE food_id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, food.getName());
            pstmt.setString(2, food.getRecipe());
            pstmt.setFloat(3, food.getPrice());
            pstmt.setString(4, food.getId());
            pstmt.setString(5, food.getImage());

            pstmt.executeUpdate();

            String sqlIngr = "UPDATE ingredients SET ingredient_name = ? WHERE ingredient_id = ?";

            PreparedStatement pstmtIngr = connection.prepareStatement(sqlIngr);

            pstmtIngr.setString(1, food.getIngredient());
            pstmtIngr.setString(2, food.getIngredientId());

            pstmtIngr.executeUpdate();

            String sqlQty = "UPDATE food_ingredients SET qty = ? WHERE food_id = ?";

            PreparedStatement pstmtQty = connection.prepareStatement(sqlQty);

            pstmtQty.setInt(1, food.getIngredientQty());
            pstmtQty.setString(2, food.getId());

            pstmtQty.executeUpdate();

            System.out.println("Food info successfully updated!");
        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "update"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "update"),e);
        } finally {
            closeConnection(connection);
        }
    }

    public void delete(Food food) {

        Connection connection = openConnection();

        try {
            String sqlQty = "DELETE FROM food_ingredients WHERE food_id = ?";

            PreparedStatement pstmtQ = connection.prepareStatement(sqlQty);
            pstmtQ.setString(1, food.getId());
            pstmtQ.executeUpdate();

            String sql = "DELETE FROM food WHERE food_id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, food.getId());
            pstmt.executeUpdate();

            String sqlIngr = "DELETE FROM ingredients WHERE ingredient_id = ?";

            PreparedStatement pstmtIngr = connection.prepareStatement(sqlIngr);
            pstmtIngr.setString(1, food.getIngredientId());
            pstmtIngr.executeUpdate();

            System.out.println("Food deleted!");

        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "delete"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "delete"),e);
        } finally {
            closeConnection(connection);
        }
    }

    public Food getById(String food_id) {

        Connection connection = openConnection();

        try {
            String sql = "SELECT \n" +
                    "    f.food_id,\n" +
                    "    f.food_name,\n" +
                    "    f.recipe,\n" +
                    "    f.price,\n" +
                    "    f.food_image,\n" +
                    "    i.ingredient_id,\n" +
                    "    i.ingredient_name,\n" +
                    "    fi.qty\n" +
                    "FROM\n" +
                    "    food f,\n" +
                    "    ingredients i,\n" +
                    "    food_ingredients fi\n" +
                    "WHERE\n" +
                    "    f.food_id = fi.food_id\n" +
                    "        AND i.ingredient_id = fi.ingredient_id\n" +
                    "        AND f.food_id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, food_id);
            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                return buildFood(resultSet);
            }
        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "getById"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "getById"),e);
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public List<Food> getAll() {
        List<Food> food = new ArrayList<>();

        Connection connection = openConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT f.food_id, f.food_name, f.recipe, f.price, f.food_image, i.ingredient_id, i.ingredient_name, fi.qty\n" +
                            "FROM food f, ingredients i, food_ingredients fi\n" +
                            "WHERE f.food_id = fi.food_id AND i.ingredient_id = fi.ingredient_id");
            while (resultSet.next()) {
                food.add(buildFood(resultSet));
            }
            System.out.println("Finished fetching all customers!");
        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "getAll"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "getAll"),e);
        } finally {
            closeConnection(connection);
        }
        return food;
    }
}
