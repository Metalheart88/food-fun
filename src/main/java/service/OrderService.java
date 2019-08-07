package service;

import domain.Order;
import exception.EntityNotFoundException;
import repository.OrderRepository;

import java.util.List;

public class OrderService {

    private OrderRepository orderRepository;

    public OrderService() {
        orderRepository = new OrderRepository();
    }

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void add (Order order) {
        orderRepository.add(order);
    }

    public void delete (Order order) {
        Order orderDB = orderRepository.getById(order.getOrderId());

        if (orderDB != null) {
            orderRepository.delete(order);
        } else {
            throw new EntityNotFoundException("Order with id " + order.getOrderId() + " was not found!");
        }
    }

    public void update (String order_id) {
        Order orderDB = orderRepository.getById(order_id);

        if (orderDB != null) {
            orderRepository.update(order_id);
        } else {
            throw new EntityNotFoundException("Order with id " + order_id + " was not found!");
        }
    }

    public Order getById (String order_id) {
        Order orderDB = orderRepository.getById(order_id);

        if (orderDB == null) throw new EntityNotFoundException("Order with id " + order_id + " was not found!");
        return orderDB;
    }

    public List<Order> getAll () {
        return orderRepository.getAll();
    }

    public List<Order> getAllForCustomer (String customerId) {
        return orderRepository.getAllForCustomer(customerId);
    }

}
