package com.kryox.view.Customer;

import com.kryox.view.Customer.Homepage;

public class CustomerNavigation {

    public static void navigateToDashboard(String userId) {
        if (userId == null || userId.isBlank()) userId = "guest";
        Dashbord d = new Dashbord(userId);
        Homepage.HomepageStage.setScene(d.getDashbordScene());
    }

    public static void navigateToNearbyShops(String userId) {
        if (userId == null || userId.isBlank()) userId = "guest";
        final String uid = userId;
        neaby_shope ns = new neaby_shope(uid);
        Homepage.HomepageStage.setScene(ns.getNearby_shopes(() -> navigateToDashboard(uid)));
    }

    public static void navigateToDeals(String userId) {
        if (userId == null || userId.isBlank()) userId = "guest";
        final String uid = userId;
        DealsDB d = new DealsDB(uid);
        Homepage.HomepageStage.setScene(d.getDealScene(() -> navigateToDashboard(uid)));
    }

    public static void navigateToOrders(String userId) {
        if (userId == null || userId.isBlank()) userId = "guest";
        My_orderAllorder moa = new My_orderAllorder(userId);
        Homepage.HomepageStage.setScene(moa.getAllorderScene());
    }

    public static void navigateToAnalytics(String userId) {
        if (userId == null || userId.isBlank()) userId = "guest";
        final String uid = userId;
        Analytics an = new Analytics(uid);
        Homepage.HomepageStage.setScene(an.getAnalyticscene(() -> navigateToDashboard(uid)));
    }

    public static void navigateToCart(String userId) {
        if (userId == null || userId.isBlank()) userId = "guest";
        ShoppingCartUI cart = new ShoppingCartUI(userId);
        Homepage.HomepageStage.setScene(cart.getaddcartScene());
    }

    public static void navigateToSettings(String userId) {
        if (userId == null || userId.isBlank()) userId = "guest";
        final String uid = userId;
        Seting se = new Seting(uid);
        Homepage.HomepageStage.setScene(se.getSetingscene(() -> navigateToDashboard(uid)));
    }

    public static void navigateToHelp(String userId) {
        if (userId == null || userId.isBlank()) userId = "guest";
        final String uid = userId;
        Helppage hp = new Helppage(uid);
        Homepage.HomepageStage.setScene(hp.getHelpScene(() -> navigateToDashboard(uid)));
    }

    public static void navigateToAboutUs(String userId) {
        if (userId == null || userId.isBlank()) userId = "guest";
        final String uid = userId;
        AboutUsPage about = new AboutUsPage(uid);
        Homepage.HomepageStage.setScene(about.getAboutUsScene(() -> navigateToSettings(uid)));
    }

    public static void navigateToNotifications(String userId) {
        if (userId == null || userId.isBlank()) userId = "guest";
        final String uid = userId;
        Notifications n = new Notifications(uid);
        Homepage.HomepageStage.setScene(n.getNotificationscene(() -> navigateToDashboard(uid)));
    }

    public static void navigateToGroceries(String userId) {
        if (userId == null || userId.isBlank()) userId = "guest";
        final String uid = userId;
        Grocries g = new Grocries(uid);
        Homepage.HomepageStage.setScene(g.getGrocriescene(() -> navigateToDashboard(uid)));
    }

    public static void navigateToElectronics(String userId) {
        if (userId == null || userId.isBlank()) userId = "guest";
        final String uid = userId;
        Electronics e = new Electronics(uid);
        Homepage.HomepageStage.setScene(e.getElectrScene(() -> navigateToDashboard(uid)));
    }

    public static void navigateToPlans(String userId) {
        if (userId == null || userId.isBlank()) userId = "guest";
        final String uid = userId;
        CustomerManagePlan cmp = new CustomerManagePlan(uid);
        Homepage.HomepageStage.setScene(cmp.getManagePlanScene(() -> navigateToDashboard(uid)));
    }

    public static void performSearch(String userId, String query, Runnable returnCallback) {
        if (userId == null || userId.isBlank()) userId = "guest";
        final String uid = userId;
        final Runnable ret = (returnCallback != null) ? returnCallback : () -> navigateToDashboard(uid);
        String q = (query != null) ? query.trim() : "";
        Electronics searchView = new Electronics(uid, "Search Results", q);
        Homepage.HomepageStage.setScene(searchView.getElectrScene(ret));
    }

    public static void navigateToLogin() {
        CustomerLogin.loggedInUserId = null;
        CustomerLogin cl = new CustomerLogin();
        if (Homepage.HomepageStage != null) {
            Homepage.HomepageStage.setScene(cl.getLoginScene());
        }
    }
}

