package angelomoreno;

import angelomoreno.entities.Costumer;
import angelomoreno.entities.Order;
import angelomoreno.entities.Product;

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
        List<Order> orders = new ArrayList<>();
        orders.add(Order.Ordersetter(products, 20, costumer1));
        orders.add(Order.Ordersetter(products, 10, costumer1));
        orders.add(Order.Ordersetter(products, 3, costumer1));
        orders.add(Order.Ordersetter(products, 22, costumer2));
        orders.add(Order.Ordersetter(products, 18, costumer2));
        orders.add(Order.Ordersetter(products, 20, costumer3));
        orders.add(Order.Ordersetter(products, 30, costumer3));
        orders.add(Order.Ordersetter(products, 15, costumer3));
        orders.add(Order.Ordersetter(products, 40, costumer4));
        orders.add(Order.Ordersetter(products, 5, costumer5));

        primoEs(orders);
    }
    public static List<Product> createProduct() {
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

    public static void primoEs(List<Order> orders) {
        orders.stream().collect(Collectors.groupingBy(Order::getCustomer)).forEach(((costumer, ordini) -> System.out.println("Costumer " + costumer + ": " + ordini)));
    }
}