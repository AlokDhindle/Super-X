package com.kryox.controller.Shopkeeper;

import java.util.ArrayList;

import com.kryox.dao.Shopkeeper.ProductDAO;
import com.kryox.model.Shopkeeper.ProductModel;

public class ProductController {
    private static ProductDAO productDAO = new ProductDAO();

    public static void addNewProduct(ProductModel productModel) {

        new Thread() {
            @Override
            public void run() {
                System.out.println("Add new product");
                productDAO.addProduct(productModel);
            }
        }.start();
    }

    public static ArrayList<ProductModel> getProducts() {
        System.out.println("Get products");
        ArrayList<ProductModel> products = productDAO.getProducts();
        System.out.println("Products retrieved");
        return products;
    }
    
    public static void updateProduct(ProductModel productModel) {
        new Thread() {
            @Override
            public void run() {
                System.out.println("Update product");
                productDAO.updateProduct(productModel);
            }
        }.start();
    }

    public static void deleteProduct(String productId) {        
        new Thread() {
            @Override
            public void run() {
                System.out.println("Delete product");
                productDAO.deleteProduct(productId);
            }
        }.start();
    }
}
