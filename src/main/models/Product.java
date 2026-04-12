package main.models;

import main.enums.ProductCategory;

public record Product(
        String id,
        String name,
        double price,
        ProductCategory category,
        int stock
) {}