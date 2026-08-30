package com.kryox.controller.Shopkeeper;

import java.util.ArrayList;

import com.kryox.dao.Shopkeeper.ProductDAO;
import com.kryox.model.Shopkeeper.ProductModel;

public class ProductController {

    private static ProductDAO productDAO;

    // ============================================================
    // CONSTRUCTOR
    // ============================================================

    public ProductController() {

        productDAO = new ProductDAO();

    }

    // ============================================================
    // FETCH PRODUCTS
    // ============================================================

    public static ArrayList<ProductModel> fetchProducts() {

        System.out.println("========================================");
        System.out.println("PRODUCT CONTROLLER");
        System.out.println("FETCHING PRODUCTS");
        System.out.println("========================================");

        ArrayList<ProductModel> products =
                new ArrayList<>();

        try {

            // DAO se products fetch
            products = productDAO.getProducts();

            if (products == null) {

                products = new ArrayList<>();

            }

            System.out.println(
                    "Products fetched: "
                            + products.size()
            );

            // Debug
            for (ProductModel product : products) {

                if (product != null) {

                    System.out.println(
                            "Product: "
                                    + product.getProductName()
                    );
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "ProductController fetch error."
            );

            e.printStackTrace();
        }

        return products;
    }

    // ============================================================
    // ADD PRODUCT
    // ============================================================

    public static void addProduct(
            ProductModel productModel) {

        if (productModel == null) {

            System.out.println(
                    "ProductModel is null."
            );

            return;
        }

        try {

            productDAO.addProduct(
                    productModel
            );

            System.out.println(
                    "Product added successfully."
            );

        } catch (Exception e) {

            System.out.println(
                    "ProductController add error."
            );

            e.printStackTrace();
        }
    }

    // ============================================================
    // UPDATE PRODUCT
    // ============================================================

    public static void updateProduct(
            ProductModel productModel) {

        if (productModel == null) {

            System.out.println(
                    "ProductModel is null."
            );

            return;
        }

        try {

            productDAO.updateProduct(
                    productModel
            );

            System.out.println(
                    "Product updated successfully."
            );

        } catch (Exception e) {

            System.out.println(
                    "ProductController update error."
            );

            e.printStackTrace();
        }
    }

    // ============================================================
    // DELETE PRODUCT
    // ============================================================

    public static void deleteProduct(
            String productId) {

        if (productId == null ||
                productId.trim().isEmpty()) {

            System.out.println(
                    "Product ID is missing."
            );

            return;
        }

        try {

            productDAO.deleteProduct(
                    productId
            );

            System.out.println(
                    "Product deleted successfully."
            );

        } catch (Exception e) {

            System.out.println(
                    "ProductController delete error."
            );

            e.printStackTrace();
        }
    }
}