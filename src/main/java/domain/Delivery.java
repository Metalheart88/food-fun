package domain;

public class Delivery {
    private String deliveryId;
    private String deliveryTime;
    private String arrivalTime;
    private Customer customer;
    private Order order;

    public Delivery(String id, String deliveryTime, String arrivalTime, Customer customer, Order order) {
        this.deliveryId = id;
        this.deliveryTime = deliveryTime;
        this.arrivalTime = arrivalTime;
        this.customer = customer;
        this.order = order;
    }

    public Delivery(String id, String deliveryTime, String arrivalTime) {
        this.deliveryId = id;
        this.deliveryTime = deliveryTime;
        this.arrivalTime = arrivalTime;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public String getDeliveryTime() {
        return deliveryTime;
    }

    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getCustomerId() {
        return customer.getId();
    }

    public String getOrderId() {
        return order.getOrderId();
    }
}
