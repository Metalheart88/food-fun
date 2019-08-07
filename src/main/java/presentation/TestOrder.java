package presentation;

import domain.Customer;
import domain.Delivery;
import domain.Food;
import domain.Order;
import repository.CustomerRepository;
import repository.FoodRepository;
import repository.OrderRepository;
import service.CustomerService;
import service.FoodService;
import service.OrderService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TestOrder {
    public static void main(String[] args) {
        CustomerRepository cr = new CustomerRepository();
        CustomerService cs = new CustomerService();
        FoodRepository fr = new FoodRepository();
        FoodService fs = new FoodService();

        Food food = fs.getById("0100f1b7-de46-43b1-a09e-ecb8f22d3ac1");
        Customer customer = cs.getById("e9130ac1-eb1c-413a-bc24-dd6c122af6e1");
        Delivery delivery = new Delivery(UUID.randomUUID().toString(), new Date().toString(), new Date().toString());
        Order order = new Order(UUID.randomUUID().toString(), new Date().toString(), delivery, customer,1, food.getPrice(), food);

        OrderRepository or = new OrderRepository();
        OrderService os = new OrderService();

        //ADD ---- TEST OK
//        os.add(order);

        //GET BY ID ---- TEST OK
//        Order order1 = or.getById("8ccfc94f-2a75-4ec4-b045-098eb676f413");
//        System.out.println(order1.getCustomer().getName());
        //GET ALL ---- TEST OK
//        List<Order> orders = or.getAll();
//        System.out.println(orders);
        //GET ALL FOR CUSTOMER ---- TEST OK
//        List<Order> orders = or.getAllForCustomer("24fdec73-bd52-47aa-b7ef-1b1342149ab7");
//        System.out.println(orders.size());
    }
}
