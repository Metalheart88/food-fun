package presentation;

import domain.Delivery;
import repository.DeliveryRepository;

import java.util.Date;
import java.util.UUID;

public class TestDelivery {
    public static void main(String[] args) {
        DeliveryRepository dr = new DeliveryRepository();
        Delivery delivery = new Delivery(UUID.randomUUID().toString(), new Date().toString(), new Date().toString());
        //ADD ---- TEST OK
//        dr.create(delivery);
    }
}
