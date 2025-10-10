class Product {
    private String name;
    private double price;
    private String category;

    // Constructor
    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return name + " (₹" + price + ")";
    }
}



import java.util.*;
import java.util.stream.*;
import java.util.Map.Entry;

public class ProductStreamDemo {
    public static void main(String[] args) {
        List<Product> products = Arrays.asList(
                new Product("Laptop", 55000, "Electronics"),
                new Product("Smartphone", 30000, "Electronics"),
                new Product("Headphones", 3000, "Electronics"),
                new Product("Shirt", 1200, "Clothing"),
                new Product("Jacket", 3500, "Clothing"),
                new Product("Blender", 2200, "Home Appliances"),
                new Product("Microwave", 7000, "Home Appliances")
        );

        // 1. Group by category
        System.out.println("\n🔹 Grouped by Category:");
        Map<String, List<Product>> grouped = products.stream()
                .collect(Collectors.groupingBy(Product::getCategory));
        grouped.forEach((category, list) -> System.out.println(category + " -> " + list));

        // 2. Most expensive product in each category
        System.out.println("\n🔹 Most Expensive Product in Each Category:");
        Map<String, Optional<Product>> maxPriceByCategory = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.maxBy(Comparator.comparingDouble(Product::getPrice))
                ));
        maxPriceByCategory.forEach((category, product) -> 
                System.out.println(category + " -> " + product.get().getName() + " (₹" + product.get().getPrice() + ")"));

        // 3. Average price of all products
        double avgPrice = products.stream()
                .collect(Collectors.averagingDouble(Product::getPrice));
        System.out.println("\n🔹 Average Price of All Products: ₹" + avgPrice);
    }
}

