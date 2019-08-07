package repository;

import domain.Delivery;
import exception.InfrastructureException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class DeliveryRepository extends DatabaseConnection {

    public void create(Delivery delivery) {
        Connection connection = openConnection();

        try {
            String sql = "INSERT INTO deliveries(delivery_id, delivery_time, arrival_time) VALUES(?,?,?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, delivery.getDeliveryId());
            pstmt.setString(2, delivery.getDeliveryTime());
            pstmt.setString(3, delivery.getArrivalTime());

            pstmt.executeUpdate();

            System.out.println("Delivery was created!");
        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "create"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "create"),e);
        } finally {
            closeConnection(connection);
        }
    }

    public void update(String delivery_id) {

    }

    public void delete(Delivery delivery) {

    }

    public Delivery get(String delivery_id) {
        return null;
    }

    public List<Delivery> getAll() {
        return null;
    }

}
