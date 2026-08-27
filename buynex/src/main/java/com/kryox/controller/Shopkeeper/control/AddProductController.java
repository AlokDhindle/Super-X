package com.kryox.control;

import com.kryox.model.ProductModel;
import com.kryox.dao.ShopkeeperDAO;

public class AddProductController {

    public static void addNewProduct(ProductModel productModel) {
        System.out.println("Add new product");
        ShopkeeperDAO shopkeeperDAO = new ShopkeeperDAO();
        shopkeeperDAO.addProduct(productModel);
    }
    
}
