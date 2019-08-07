package repository;

import exception.InfrastructureException;
import org.apache.log4j.Logger;

import java.sql.*;

public abstract class DatabaseConnection {

    final static Logger logger = Logger.getLogger(DatabaseConnection.class);
    protected static final String LOG_ERROR_MSG = "Error during the user %s";

    protected Connection openConnection() {
        Connection connection;

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_fun?serverTimezone=UTC", "root", "G******!");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return connection;
    }

    protected void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            logger.error(("closeConnection"), e);
            throw new InfrastructureException(("closeConnection"),e);
        }
    }

}
