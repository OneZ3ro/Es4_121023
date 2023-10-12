package angelomoreno;

import angelomoreno.entities.Costumer;
import angelomoreno.entities.Order;
import angelomoreno.entities.Product;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import angelomoreno.entities.StringMod;
import com.github.javafaker.Faker;
import org.apache.commons.io.FileUtils;

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

        StringMod wrapper = s -> "------------------------------ " + s + " ------------------------------";
        System.out.println(wrapper.modify("Es1"));
        primoEs(orders);
        System.out.println();

//        System.out.println(wrapper.modify("Es2"));
//        secondoEs(orders);

        System.out.println(wrapper.modify("Es3"));
        terzoEs(products);

//        System.out.println(wrapper.modify("Es2"));
//        secondoEs(orders);

        System.out.println(wrapper.modify("Es5"));
        quintoEs(products);

        System.out.println(wrapper.modify("Es6"));
        sestoEs(products);
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

//    public static void secondoEs(List<Order> orders) {
//        orders.stream().collect(Collectors.groupingBy(order -> order.getCustomer())).forEach(((costumer, ordini) -> System.out.println("Costumer " + costumer + "\nTot: " + ordini.stream().mapToDouble(order -> order.getProducts().stream().forEach(Product::getPrice)).sum())));
//    }

    public static void terzoEs(List<Product> products) {
//        DoubleSummaryStatistics priceStatistic = products.stream().collect(Collectors.summarizingDouble(product -> product.getPrice()));
//        System.out.println("Il prodotto più costoso ha un prezzo di: " + priceStatistic.getMax() + "€");
        List<Product> moreThan250 = new ArrayList<>(products.stream().filter(product -> product.getPrice() > 250).sorted(Comparator.comparing(Product::getPrice)).toList());
        System.out.println("------------ Prodotti costosi (più di 250€) ------------");
        moreThan250.forEach(System.out::println);
    }

    public static void quintoEs(List<Product> products) {
        products.stream().collect(Collectors.groupingBy(product -> product.getCategory())).forEach(((category, prodotti) -> System.out.println("Categoria: " + category + ": " + prodotti.stream().map(product -> product.getPrice()).reduce(0.0, (partialSum, currentElem) -> partialSum + currentElem))));
    }

    public static void sestoEs(List<Product> products) {
        File file = new File("src/output.text");
        try {
            for (int i = 0; i < products.size(); i++) {
                FileUtils.writeStringToFile(file, products.get(i).getName() + "@" + products.get(i).getCategory() + "@" + products.get(i).getPrice() + "#", StandardCharsets.UTF_8, true);
            }
            String contenuto = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.getMessage();
        }
    }
}