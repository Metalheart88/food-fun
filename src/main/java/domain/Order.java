package domain;

public class Order {

    private String orderId;
    private String orderDate;
    private Delivery delivery;
    private Customer customer;
    private int orderQty;
    private float totalPrice;
    private Food food;
//    private Payment payment;

    public Order(String orderId, String orderDate, Delivery delivery, Customer customer, int orderQty, float totalPrice, Food food) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.delivery = delivery;
        this.customer = customer;
        this.orderQty = orderQty;
        this.totalPrice = totalPrice;
        this.food = food;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getOrderQty() {
        return orderQty;
    }

    public void setOrderQty(int orderQty) {
        this.orderQty = orderQty;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public Food getFood() {
        return food;
    }
}
