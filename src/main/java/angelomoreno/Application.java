package angelomoreno;

import angelomoreno.entities.Costumer;
import angelomoreno.entities.Order;
import angelomoreno.entities.Product;

import java.util.*;
import java.util.function.Supplier;
import com.github.javafaker.Faker;
public class Application {

    public static void main(String[] args) {
        List<Product> products = new ArrayList<>(createProduct());
        Faker faker = new Faker(Locale.ITALY);
        Costumer costumer1 = new Costumer(faker.name().firstName());
        Costumer costumer2 = new Costumer(faker.name().firstName());
        Costumer costumer3 = new Costumer(faker.name().firstName());
        Costumer costumer4 = new Costumer(faker.name().firstName());
        Costumer costumer5 = new Costumer(faker.name().firstName());
        List<Order> order = new ArrayList<>();
        order.add(Order.Ordersetter(products, 20, costumer1));
        order.add(Order.Ordersetter(products, 30, costumer2));
        order.add(Order.Ordersetter(products, 15, costumer3));
        order.add(Order.Ordersetter(products, 40, costumer4));
        order.add(Order.Ordersetter(products, 5, costumer5));
    }
    public static List<Product> createProduct () {
        String[] strArr = {"Books", "Baby", "Boys"};
        Supplier<Product> productSupplier = () -> {
            Faker faker = new Faker(Locale.ITALY);
            Random rnmd = new Random();
            return new Product(faker.book().title(), strArr[rnmd.nextInt(0, strArr.length)], rnmd.nextDouble(0, 300));
        };
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            products.add(productSupplier.get());
        }
        return products;
    }
}