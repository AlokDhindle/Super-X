package com.kryox.controller.Shopkeeper;

import com.kryox.dao.Shopkeeper.ShopkeeperDAO;
import com.kryox.view.Shopkeeper.ViewConstants;

public class ShopkeeperDetailsController {

    public static void updateShopkeeperDetails(String shopNameValue, String ownerNameValue, String mobileValue, String panValue, String gstValue,
            String categoryValue, String addressValue, String stateValue, String cityValue,
            String pinValue, String licenseValue) {
                ShopkeeperDAO shopkeeperDAO = new ShopkeeperDAO();
                shopkeeperDAO.updateShopkeeperDetails(
                        shopNameValue,
                        ownerNameValue,
                        mobileValue,
                        panValue,
                        gstValue,
                        categoryValue,
                        addressValue,
                        stateValue,
                        cityValue,
                        pinValue,
                        licenseValue
                );

                ViewConstants.shopkeeperModel = shopkeeperDAO.getShopDetails(ShopkeeperLogController.getShopkeeperUid());
    }
    
}
