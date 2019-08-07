package repository;

import domain.Customer;
import exception.InfrastructureException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static repository.ResultSetUtil.buildCustomer;

public class CustomerRepository extends DatabaseConnection {

    private static final String LOG_ERROR_MSG = "Error during the user %s";

    public void add(Customer customer) {
        Connection connection = openConnection();

        try {

            String sql = "INSERT INTO customers(id, password, name, email, phone) VALUES(?,?,?,?,?)";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customer.getId());
            pstmt.setString(2, customer.getPassword());
            pstmt.setString(3, customer.getName());
            pstmt.setString(4, customer.getEmail());
            pstmt.setString(5, customer.getPhone());

            pstmt.executeUpdate();

            String sqlAddress = "INSERT INTO customer_addresses(customer_id, country, province, street_name, street_number," +
                    "suite_number, postal_code, po_box) VALUES(?,?,?,?,?,?,?,?)";

            PreparedStatement pstmt1 = connection.prepareStatement(sqlAddress);
            pstmt1.setString(1, customer.getId());
            pstmt1.setString(2, customer.getCountry());
            pstmt1.setString(3, customer.getProvince());
            pstmt1.setString(4, customer.getStreetName());
            pstmt1.setInt(5, customer.getStreetNumber());
            pstmt1.setInt(6, customer.getSuiteNumber());
            pstmt1.setString(7, customer.getPostalCode());
            pstmt1.setInt(8, customer.getPoBox());

            pstmt1.executeUpdate();

            System.out.println("Customer added!");

        } catch(SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "insert"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "insert"),e);
        } finally {
            closeConnection(connection);
        }
    }

    public void update(Customer customer) {

        Connection connection = openConnection();

        try {
            String sql = "UPDATE customers SET name = ?, password = ?, email = ?, phone = ? WHERE id = ?";

            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customer.getName());
            pstmt.setString(2, customer.getPassword());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getPhone());
            pstmt.setString(5, customer.getId());

            pstmt.executeUpdate();

            String sqlAddress = "UPDATE customer_addresses SET country = ?, province = ?, street_name = ?, " +
                    "street_number = ?, suite_number = ?, postal_code = ?, po_box = ? WHERE customer_id = ?";

            PreparedStatement pstmtAddress = connection.prepareStatement(sqlAddress);
            pstmtAddress.setString(1, customer.getCountry());
            pstmtAddress.setString(2, customer.getProvince());
            pstmtAddress.setString(3, customer.getStreetName());
            pstmtAddress.setInt(4, customer.getStreetNumber());
            pstmtAddress.setInt(5, customer.getSuiteNumber());
            pstmtAddress.setString(6, customer.getPostalCode());
            pstmtAddress.setInt(7, customer.getPoBox());
            pstmtAddress.setString(8, customer.getId());

            pstmtAddress.executeUpdate();

            System.out.println("Customer info successfully updated!");

        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "update"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "update"),e);
        } finally {
            closeConnection(connection);
        }

    }

    public void delete(Customer customer) {

        Connection connection = openConnection();

        try {

            String sqlAddress = "DELETE FROM customer_addresses WHERE customer_id = ?";
            PreparedStatement pstmtA = connection.prepareStatement(sqlAddress);
            pstmtA.setString(1, customer.getId());
            pstmtA.executeUpdate();

            String sql = "DELETE FROM customers WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customer.getId());
            pstmt.executeUpdate();

            System.out.println("Customer deleted!");

        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "delete"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "delete"),e);
        } finally {
            closeConnection(connection);
        }
    }

    public Customer getByEmail(String email) {
        Connection connection = openConnection();

        try {
            String sql = "SELECT c.id, c.password, c.name, c.email, c.phone, a.country, a.province, a.street_name, " +
                    "a.street_number, a.suite_number, a.postal_code, a.po_box \n" +
                    "FROM customers c, customer_addresses a \n" +
                    "WHERE c.id = a.customer_id \n" +
                    "AND c.email = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, email);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
                return buildCustomer(resultSet);
            }
        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "getByEmail"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "getByEmail"),e);
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public Customer getById(String customerId) {

        Connection connection = openConnection();

        try {
            String sql = "SELECT c.id, c.password, c.name, c.email, c.phone, a.country, a.province, a.street_name, " +
                    "a.street_number, a.suite_number, a.postal_code, a.po_box \n" +
                    "FROM customers c, customer_addresses a \n" +
                    "WHERE c.id = a.customer_id \n" +
                    "AND c.id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, customerId);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet.next()) {
               return buildCustomer(resultSet);
            }
        } catch (SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "getById"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "getById"),e);
        } finally {
            closeConnection(connection);
        }
        return null;
    }

    public List<Customer> getAll() {
        List<Customer> customers = new ArrayList<>();

        Connection connection = openConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM customers t1 " +
                            "INNER JOIN " +
                            "customer_addresses t2 ON t1.id = t2.customer_id;");
            while (resultSet.next()) {
                customers.add(buildCustomer(resultSet));
            }
            System.out.println("Finished fetching all customers!");
        } catch(SQLException e) {
            logger.error(String.format(LOG_ERROR_MSG, "getAll"), e);
            throw new InfrastructureException(String.format(LOG_ERROR_MSG, "getAll"),e);
        } finally {
            closeConnection(connection);
        }
        return customers;
    }

}
