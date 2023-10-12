package angelomoreno.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Order {
    private long id;
    private String status;
    private LocalDate orderDate;
    private LocalDate deliveryDate;
    private List<Product> products;
    private Costumer customer;

    public Order(String status, LocalDate orderDate, LocalDate deliveryDate, List<Product> products, Costumer customer) {
        Random rndm = new Random();
        this.status = status;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.products = products;
        this.customer = customer;
        this.id = rndm.nextInt(2000, 3000);
    }

    public static Order Ordersetter(List<Product> arrayList, int n, Costumer costumer) {
        List<Product> app = new ArrayList<>();
        LocalDate today = LocalDate.now();
        Random rndm = new Random();

        for (int i = 0; i < n; i++) {
            app.add(arrayList.get(rndm.nextInt(0, arrayList.size())));
        }
        return new Order("ok", today, today.plusDays(3), app, costumer);
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", orderDate=" + orderDate +
                ", deliveryDate=" + deliveryDate +
                ", products=" + products +
                ", customer=" + customer +
                "}";
    }
}
