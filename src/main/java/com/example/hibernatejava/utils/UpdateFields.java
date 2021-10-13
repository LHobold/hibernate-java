package com.example.hibernatejava.utils;

import com.example.hibernatejava.entity.Product;

public class UpdateFields {

    public static void updateProductFields(Product fieldsToUpdate, Product productToUpdate) {

        String updatedName = fieldsToUpdate.getName() == null ? productToUpdate.getName() : fieldsToUpdate.getName();

        String updatedCategory = fieldsToUpdate.getCategory() == null ? productToUpdate.getCategory()
                : fieldsToUpdate.getCategory();

        Double updatedPrice = fieldsToUpdate.getPrice() == null ? productToUpdate.getPrice()
                : fieldsToUpdate.getPrice();

        productToUpdate.setName(updatedName);
        productToUpdate.setCategory(updatedCategory);
        productToUpdate.setPrice(updatedPrice);

    }

}
