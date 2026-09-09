package com.kryox.view.Customer;

import java.awt.Desktop;
import java.io.File;
import java.net.URI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

/**
 * AboutUsPage - Presentation of BuyNex, platform capabilities,
 * multi-role ecosystem, order workflow, team, and Core2Web acknowledgements.
 */
public class AboutUsPage {

    private final String userId;

    public AboutUsPage(String userId) {
        this.userId = userId;
    }

    public Scene getAboutUsScene(Runnable backCallback) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #F8F5F2;");

        // Top Navigation Bar
        HBox topBar = createTopBar(backCallback);
        root.setTop(topBar);

        // Main Scrollable Content Container
        VBox mainContent = new VBox(28);
        mainContent.setPadding(new Insets(30, 60, 50, 60));
        mainContent.setMaxWidth(1350);
        mainContent.setAlignment(Pos.TOP_CENTER);

        // 1. Hero / Intro Section
        VBox heroSection = createHeroSection();

        // 2. Vision & Pillars Section
        VBox visionSection = createVisionSection();

        // 3. Platform Capabilities Section
        VBox capabilitiesSection = createCapabilitiesSection();

        // 4. Multi-Role Ecosystem Section
        VBox ecosystemSection = createEcosystemSection();

        // 5. Smart BuyNex / AI Features
        VBox aiSection = createAISection();

        // 6. How BuyNex Works (Workflow Journey)
        VBox workflowSection = createWorkflowSection();

        // 7. Project Team
        VBox teamSection = createTeamSection();

        // 8. Acknowledgements & Gratitude (Shashi Sir, Mentors, Core2Web)
        VBox acknowledgementsSection = createAcknowledgementsSection();

        // Footer
        VBox footerSection = createFooter();

        mainContent.getChildren().addAll(
                heroSection,
                new Separator(),
                visionSection,
                new Separator(),
                capabilitiesSection,
                new Separator(),
                ecosystemSection,
                new Separator(),
                aiSection,
                new Separator(),
                workflowSection,
                new Separator(),
                teamSection,
                new Separator(),
                acknowledgementsSection,
                footerSection
        );

        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #F8F5F2; -fx-background-color: transparent;");
        root.setCenter(scrollPane);

        return new Scene(root, 1550, 850);
    }

    private HBox createTopBar(Runnable backCallback) {
        HBox topBar = new HBox(16);
        topBar.setPrefHeight(65);
        topBar.setPadding(new Insets(0, 30, 0, 30));
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setStyle("-fx-background-color: #ebccb7; -fx-border-color: #ddb89f; -fx-border-width: 0 0 1 0;");

        Button backBtn = new Button("← Back");
        backBtn.setStyle(
                "-fx-background-color: #a83f00; -fx-text-fill: white; -fx-font-size: 13px; " +
                "-fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 8 18 8 18; -fx-cursor: hand;"
        );
        backBtn.setOnAction(e -> {
            if (backCallback != null) {
                backCallback.run();
            } else {
                CustomerNavigation.navigateToSettings(userId);
            }
        });

        Label brandLabel = new Label("BuyNex  |  About Platform & Team");
        brandLabel.setFont(Font.font("System", FontWeight.BOLD, 17));
        brandLabel.setTextFill(Color.web("#a83f00"));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        Button openWebBtn = new Button("🌐 Open Web View in Browser");
        openWebBtn.setStyle(
                "-fx-background-color: #ffffff; -fx-text-fill: #a83f00; -fx-border-color: #a83f00; " +
                "-fx-border-radius: 20; -fx-background-radius: 20; -fx-font-size: 12px; -fx-font-weight: bold; " +
                "-fx-padding: 6 16 6 16; -fx-cursor: hand;"
        );
        openWebBtn.setOnAction(e -> openWebPage());

        topBar.getChildren().addAll(backBtn, brandLabel, spacer, openWebBtn);
        return topBar;
    }

    private void openWebPage() {
        try {
            File htmlFile = new File("about.html");
            if (htmlFile.exists() && Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(htmlFile.toURI());
            } else if (Desktop.isDesktopSupported()) {
                File subHtml = new File("c:/BuyNex/Super-X/about.html");
                if (subHtml.exists()) {
                    Desktop.getDesktop().browse(subHtml.toURI());
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private VBox createHeroSection() {
        VBox box = new VBox(12);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(10, 0, 10, 0));

        Label badge = new Label("⚡  NEXT-GENERATION LOCAL RETAIL ECOSYSTEM");
        badge.setStyle(
                "-fx-background-color: #ffe4d6; -fx-text-fill: #a83f00; -fx-font-size: 11px; " +
                "-fx-font-weight: bold; -fx-padding: 5 14 5 14; -fx-background-radius: 20;"
        );

        Label title = new Label("Empowering Neighborhood Commerce with Smart Technology");
        title.setFont(Font.font("System", FontWeight.BOLD, 26));
        title.setTextFill(Color.web("#222222"));
        title.setTextAlignment(TextAlignment.CENTER);
        title.setWrapText(true);

        Label subtitle = new Label("BuyNex bridges local brick-and-mortar storefronts with digital speed and intelligence.");
        subtitle.setFont(Font.font("System", 15));
        subtitle.setTextFill(Color.web("#555555"));

        // One-Line Definition Box
        VBox defBox = new VBox(8);
        defBox.setPadding(new Insets(18, 24, 18, 24));
        defBox.setMaxWidth(920);
        defBox.setStyle(
                "-fx-background-color: #ffffff; -fx-border-color: #a83f00; -fx-border-width: 1.5; " +
                "-fx-border-radius: 12; -fx-background-radius: 12; -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.06), 10, 0, 0, 4);"
        );

        Label defTag = new Label("★  CORE PLATFORM DEFINITION");
        defTag.setStyle("-fx-text-fill: #a83f00; -fx-font-size: 11px; -fx-font-weight: bold;");

        Label defText = new Label(
                "“BuyNex is a smart local-commerce platform that digitizes the complete retail journey—from product discovery and ordering to inventory management, payment, delivery, campaigns, and AI-powered assistance.”"
        );
        defText.setFont(Font.font("System", FontWeight.BOLD, 15));
        defText.setTextFill(Color.web("#1e293b"));
        defText.setWrapText(true);

        defBox.getChildren().addAll(defTag, defText);
        box.getChildren().addAll(badge, title, subtitle, defBox);
        return box;
    }

    private VBox createVisionSection() {
        VBox box = new VBox(14);
        box.setAlignment(Pos.CENTER_LEFT);

        Label secTitle = new Label("About BuyNex & Platform Vision");
        secTitle.setFont(Font.font("System", FontWeight.BOLD, 20));
        secTitle.setTextFill(Color.web("#a83f00"));

        Label desc = new Label(
                "BuyNex connects Customers, Shopkeepers, Delivery Partners, and Platform Admins into one harmonious network. " +
                "Our mission is to empower physical stores with modern digital retail tools while delivering an effortless, reliable shopping and delivery experience for buyers."
        );
        desc.setFont(Font.font("System", 14));
        desc.setTextFill(Color.web("#444444"));
        desc.setWrapText(true);

        HBox pillarsBox = new HBox(16);
        pillarsBox.setAlignment(Pos.CENTER);

        pillarsBox.getChildren().addAll(
                createCard("🌐 Unified Ecosystem", "Connects customer orders, store inventory, courier delivery, and central admin monitoring."),
                createCard("⚡ AI & Barcodes", "Real-time camera barcode lookups and smart conversational assistants for customers and admins."),
                createCard("🏪 Merchant Growth", "Enables local shopkeepers to manage catalogs, track daily sales, and join promotional campaigns.")
        );

        box.getChildren().addAll(secTitle, desc, pillarsBox);
        return box;
    }

    private VBox createCapabilitiesSection() {
        VBox box = new VBox(14);
        box.setAlignment(Pos.CENTER_LEFT);

        Label secTitle = new Label("What BuyNex Does (Platform Capabilities)");
        secTitle.setFont(Font.font("System", FontWeight.BOLD, 20));
        secTitle.setTextFill(Color.web("#a83f00"));

        HBox row1 = new HBox(16);
        row1.getChildren().addAll(
                createMiniCard("🔍 Search & Discovery", "Browse categorized inventories across nearby shops with live stock info."),
                createMiniCard("▦ Barcode Scanning", "Scan product barcodes to inspect items or populate missing catalog data."),
                createMiniCard("💳 Cart & Secure Checkout", "Seamless cart additions, coupon discounts, and secure transaction workflows.")
        );

        HBox row2 = new HBox(16);
        row2.getChildren().addAll(
                createMiniCard("📍 Real-Time Tracking", "Live delivery status milestones keeping customers and merchants updated."),
                createMiniCard("★ Verified Reviews", "Transparent community ratings that recognize trusted stores and couriers."),
                createMiniCard("🤖 AI & Notifications", "Always-on conversational support and targeted alerts for deals & orders.")
        );

        box.getChildren().addAll(secTitle, row1, row2);
        return box;
    }

    private VBox createEcosystemSection() {
        VBox box = new VBox(16);
        box.setAlignment(Pos.CENTER_LEFT);

        Label secTitle = new Label("Multi-Role Ecosystem");
        secTitle.setFont(Font.font("System", FontWeight.BOLD, 20));
        secTitle.setTextFill(Color.web("#a83f00"));

        HBox rolesBox = new HBox(16);
        rolesBox.setAlignment(Pos.CENTER);

        rolesBox.getChildren().addAll(
                createRoleBox("Customer", "#10b981", new String[]{
                        "Search and discover local products",
                        "Scan barcodes for instant info",
                        "Place orders & track deliveries",
                        "Submit ratings & reviews",
                        "AI Chatbot shopping assistance"
                }),
                createRoleBox("Shopkeeper", "#f59e0b", new String[]{
                        "Manage store catalog and stock",
                        "Add, edit, or delete items",
                        "Receive and prepare orders",
                        "Request campaign participation",
                        "View revenue and sales insights"
                }),
                createRoleBox("Delivery Partner", "#3b82f6", new String[]{
                        "Receive assigned orders",
                        "Accept & manage deliveries",
                        "Update live transit statuses",
                        "Review completed deliveries",
                        "Streamlined delivery workflow"
                }),
                createRoleBox("Admin", "#8b5cf6", new String[]{
                        "Manage all platform users",
                        "Approve campaigns & deals",
                        "Monitor real-time transactions",
                        "AI Smart Assistant analytics",
                        "Maintain catalog taxonomy"
                })
        );

        box.getChildren().addAll(secTitle, rolesBox);
        return box;
    }

    private VBox createAISection() {
        VBox box = new VBox(12);
        box.setPadding(new Insets(18, 22, 18, 22));
        box.setStyle(
                "-fx-background-color: #ffffff; -fx-border-color: #0284c7; -fx-border-width: 1; " +
                "-fx-border-radius: 12; -fx-background-radius: 12;"
        );

        Label aiBadge = new Label("INTELLIGENCE BY DESIGN");
        aiBadge.setStyle("-fx-text-fill: #0284c7; -fx-font-weight: bold; -fx-font-size: 11px;");

        Label aiTitle = new Label("Smart BuyNex: AI Chatbot & Barcode Intelligence");
        aiTitle.setFont(Font.font("System", FontWeight.BOLD, 17));
        aiTitle.setTextFill(Color.web("#0f172a"));

        Label aiDesc = new Label(
                "• AI Assistant: Serves shoppers with 24/7 conversational assistance and equips platform administrators with a smart assistant to surface operational metrics.\n" +
                "• Barcode Scanning: Identifies products rapidly via camera and retrieves missing product specifications when items are not yet listed in a shopkeeper's inventory."
        );
        aiDesc.setFont(Font.font("System", 13.5));
        aiDesc.setTextFill(Color.web("#334155"));
        aiDesc.setWrapText(true);

        box.getChildren().addAll(aiBadge, aiTitle, aiDesc);
        return box;
    }

    private VBox createWorkflowSection() {
        VBox box = new VBox(12);
        box.setAlignment(Pos.CENTER_LEFT);

        Label secTitle = new Label("How BuyNex Works (The Order Journey)");
        secTitle.setFont(Font.font("System", FontWeight.BOLD, 20));
        secTitle.setTextFill(Color.web("#a83f00"));

        HBox stepsBox = new HBox(12);
        stepsBox.setAlignment(Pos.CENTER);

        stepsBox.getChildren().addAll(
                createStepCard("1", "Customer", "Places order & completes payment"),
                createStepCard("2", "Shopkeeper", "Receives & prepares order package"),
                createStepCard("3", "Admin / Engine", "Coordinates and assigns courier"),
                createStepCard("4", "Delivery Partner", "Picks up and navigates to customer"),
                createStepCard("5", "Customer", "Receives delivery & leaves review")
        );

        box.getChildren().addAll(secTitle, stepsBox);
        return box;
    }

    private VBox createTeamSection() {
        VBox box = new VBox(12);
        box.setAlignment(Pos.CENTER_LEFT);

        Label secTitle = new Label("Project Team");
        secTitle.setFont(Font.font("System", FontWeight.BOLD, 20));
        secTitle.setTextFill(Color.web("#a83f00"));

        HBox teamGrid = new HBox(14);
        teamGrid.setAlignment(Pos.CENTER);

        teamGrid.getChildren().addAll(
                createTeamMemberCard("[Team Member Name]", "[Full-Stack Architect]", "[Core Architecture & Lifecycle]"),
                createTeamMemberCard("[Team Member Name]", "[UI/UX & Frontend]", "[User Interface & Application Flow]"),
                createTeamMemberCard("[Team Member Name]", "[Backend & Database]", "[Data Persistence & Order Pipeline]"),
                createTeamMemberCard("[Team Member Name]", "[AI & Workflows]", "[Barcode & AI Assistant Integration]")
        );

        box.getChildren().addAll(secTitle, teamGrid);
        return box;
    }

    private VBox createAcknowledgementsSection() {
        VBox box = new VBox(18);
        box.setAlignment(Pos.CENTER_LEFT);

        Label secTitle = new Label("Acknowledgements & Sincere Gratitude");
        secTitle.setFont(Font.font("System", FontWeight.BOLD, 22));
        secTitle.setTextFill(Color.web("#a83f00"));

        // Dedicated Spotlight Card for Shashi Sir & Core2Web
        HBox shashiCard = new HBox(24);
        shashiCard.setPadding(new Insets(24));
        shashiCard.setAlignment(Pos.CENTER_LEFT);
        shashiCard.setStyle(
                "-fx-background-color: #ffffff; -fx-border-color: #a83f00; -fx-border-width: 1.5; " +
                "-fx-border-radius: 14; -fx-background-radius: 14; " +
                "-fx-effect: dropshadow(gaussian, rgba(168, 63, 0, 0.12), 14, 0, 0, 5);"
        );

        // Photo / Profile Area for Shashi Sir
        VBox photoBox = new VBox(8);
        photoBox.setPrefSize(140, 150);
        photoBox.setAlignment(Pos.CENTER);
        photoBox.setStyle(
                "-fx-background-color: #fff4ed; -fx-border-color: #a83f00; -fx-border-width: 1.5; " +
                "-fx-border-radius: 10; -fx-background-radius: 10;"
        );
        Label photoIcon = new Label("👨‍🏫");
        photoIcon.setStyle("-fx-font-size: 38px;");
        Label photoText = new Label("[Shashi Sir Photo]");
        photoText.setStyle("-fx-font-size: 11px; -fx-font-weight: bold; -fx-text-fill: #a83f00;");
        photoBox.getChildren().addAll(photoIcon, photoText);

        VBox shashiText = new VBox(6);
        Label honTag = new Label("HONORABLE MENTOR & GUIDE");
        honTag.setStyle("-fx-text-fill: #a83f00; -fx-font-size: 11px; -fx-font-weight: bold;");

        Label shashiTitle = new Label("Shashi Sir & Core2Web");
        shashiTitle.setFont(Font.font("System", FontWeight.BOLD, 20));
        shashiTitle.setTextFill(Color.web("#111827"));

        Label quote = new Label(
                "“Our heartfelt gratitude to Shashi Sir and Core2Web for cultivating an environment of technical rigor, " +
                "disciplined engineering, and continuous learning. Your mentorship, deep insights, and personal encouragement " +
                "laid the philosophical and technical foundation of BuyNex. Thank you for inspiring us to look beyond code, " +
                "build solutions that solve real-world problems, and pursue engineering excellence with dedication.”"
        );
        quote.setFont(Font.font("System", 13.5));
        quote.setStyle("-fx-font-style: italic;");
        quote.setTextFill(Color.web("#374151"));
        quote.setWrapText(true);

        shashiText.getChildren().addAll(honTag, shashiTitle, quote);
        HBox.setHgrow(shashiText, Priority.ALWAYS);
        shashiCard.getChildren().addAll(photoBox, shashiText);

        // Mentors & Instructors 2-column layout
        HBox mentorRow = new HBox(16);

        // Instructors
        VBox instructorsBox = new VBox(10);
        instructorsBox.setPadding(new Insets(16));
        instructorsBox.setPrefWidth(500);
        instructorsBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #e2e8f0; -fx-border-radius: 10; -fx-background-radius: 10;");
        Label instTitle = new Label("Our Respected Instructors");
        instTitle.setFont(Font.font("System", FontWeight.BOLD, 15));
        instTitle.setTextFill(Color.web("#a83f00"));
        Label instDesc = new Label(
                "• Sachin Sir — For conceptual clarity, technical direction, and foundational guidance.\n" +
                "• Pramod Sir — For practical insights, problem-solving methodologies, and technical review.\n" +
                "• Akshay Sir — For engineering support, practical guidance, and disciplined code practices."
        );
        instDesc.setFont(Font.font("System", 13));
        instDesc.setTextFill(Color.web("#475569"));
        instDesc.setWrapText(true);
        instructorsBox.getChildren().addAll(instTitle, instDesc);

        // Super Mentors
        VBox superBox = new VBox(10);
        superBox.setPadding(new Insets(16));
        superBox.setPrefWidth(500);
        superBox.setStyle("-fx-background-color: #ffffff; -fx-border-color: #e2e8f0; -fx-border-radius: 10; -fx-background-radius: 10;");
        Label superTitle = new Label("Our Super Mentors");
        superTitle.setFont(Font.font("System", FontWeight.BOLD, 15));
        superTitle.setTextFill(Color.web("#a83f00"));
        Label superDesc = new Label(
                "• Shiv Sir — For invaluable architectural reviews, consistent motivation, and project direction.\n" +
                "• Subodh Sir — For strategic mentorship, critical milestone feedback, and technical problem-solving assistance."
        );
        superDesc.setFont(Font.font("System", 13));
        superDesc.setTextFill(Color.web("#475569"));
        superDesc.setWrapText(true);
        superBox.getChildren().addAll(superTitle, superDesc);

        mentorRow.getChildren().addAll(instructorsBox, superBox);

        // Mentors & Team Leads Banner
        VBox leadsBanner = new VBox(6);
        leadsBanner.setPadding(new Insets(16, 20, 16, 20));
        leadsBanner.setStyle("-fx-background-color: #ffffff; -fx-border-color: #e2e8f0; -fx-border-radius: 10; -fx-background-radius: 10;");
        Label leadsTitle = new Label("To All Mentors & Team Leads");
        leadsTitle.setFont(Font.font("System", FontWeight.BOLD, 14));
        leadsTitle.setTextFill(Color.web("#1e293b"));
        Label leadsText = new Label(
                "We extend our sincere thanks to all our Mentors and Team Leads who dedicated their time to review our progress, " +
                "test modules, and share actionable feedback throughout every sprint. Your day-to-day guidance, patience, technical insights, " +
                "and constant encouragement were indispensable in shaping BuyNex."
        );
        leadsText.setFont(Font.font("System", 13));
        leadsText.setTextFill(Color.web("#475569"));
        leadsText.setWrapText(true);
        leadsBanner.getChildren().addAll(leadsTitle, leadsText);

        box.getChildren().addAll(secTitle, shashiCard, mentorRow, leadsBanner);
        return box;
    }

    private VBox createFooter() {
        VBox footer = new VBox(6);
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(20, 0, 10, 0));

        Label brand = new Label("BuyNex Platform");
        brand.setFont(Font.font("System", FontWeight.BOLD, 15));
        brand.setTextFill(Color.web("#a83f00"));

        Label tagline = new Label("Bridging Stores. Empowering Communities. Smarter Retail.");
        tagline.setFont(Font.font("System", 12));
        tagline.setTextFill(Color.web("#666666"));

        Label copy = new Label("© 2026 BuyNex. Built with passion and guidance from Core2Web.");
        copy.setFont(Font.font("System", 11));
        copy.setTextFill(Color.web("#999999"));

        footer.getChildren().addAll(brand, tagline, copy);
        return footer;
    }

    // Helper UI card generators
    private VBox createCard(String title, String text) {
        VBox card = new VBox(6);
        card.setPadding(new Insets(14));
        card.setPrefWidth(320);
        card.setStyle("-fx-background-color: #ffffff; -fx-border-color: #e2e8f0; -fx-border-radius: 8; -fx-background-radius: 8;");
        Label t = new Label(title);
        t.setFont(Font.font("System", FontWeight.BOLD, 14));
        t.setTextFill(Color.web("#1e293b"));
        Label d = new Label(text);
        d.setFont(Font.font("System", 12.5));
        d.setTextFill(Color.web("#64748b"));
        d.setWrapText(true);
        card.getChildren().addAll(t, d);
        return card;
    }

    private VBox createMiniCard(String title, String text) {
        VBox card = new VBox(5);
        card.setPadding(new Insets(12));
        card.setPrefWidth(320);
        card.setStyle("-fx-background-color: #ffffff; -fx-border-color: #e2e8f0; -fx-border-radius: 8; -fx-background-radius: 8;");
        Label t = new Label(title);
        t.setFont(Font.font("System", FontWeight.BOLD, 13));
        t.setTextFill(Color.web("#1e293b"));
        Label d = new Label(text);
        d.setFont(Font.font("System", 12));
        d.setTextFill(Color.web("#64748b"));
        d.setWrapText(true);
        card.getChildren().addAll(t, d);
        return card;
    }

    private VBox createRoleBox(String role, String accentColor, String[] points) {
        VBox box = new VBox(8);
        box.setPadding(new Insets(14));
        box.setPrefWidth(240);
        box.setStyle("-fx-background-color: #ffffff; -fx-border-color: " + accentColor + "; -fx-border-radius: 10; -fx-background-radius: 10;");

        Label name = new Label(role);
        name.setFont(Font.font("System", FontWeight.BOLD, 15));
        name.setTextFill(Color.web(accentColor));

        VBox list = new VBox(4);
        for (String p : points) {
            Label pt = new Label("• " + p);
            pt.setFont(Font.font("System", 11.5));
            pt.setTextFill(Color.web("#334155"));
            pt.setWrapText(true);
            list.getChildren().add(pt);
        }

        box.getChildren().addAll(name, list);
        return box;
    }

    private VBox createStepCard(String num, String actor, String action) {
        VBox box = new VBox(4);
        box.setPadding(new Insets(12));
        box.setPrefWidth(185);
        box.setAlignment(Pos.TOP_LEFT);
        box.setStyle("-fx-background-color: #ffffff; -fx-border-color: #cbd5e1; -fx-border-radius: 8; -fx-background-radius: 8;");

        Label n = new Label("0" + num);
        n.setFont(Font.font("System", FontWeight.BOLD, 16));
        n.setTextFill(Color.web("#a83f00"));

        Label a = new Label(actor);
        a.setFont(Font.font("System", FontWeight.BOLD, 12.5));
        a.setTextFill(Color.web("#1e293b"));

        Label desc = new Label(action);
        desc.setFont(Font.font("System", 11));
        desc.setTextFill(Color.web("#64748b"));
        desc.setWrapText(true);

        box.getChildren().addAll(n, a, desc);
        return box;
    }

    private VBox createTeamMemberCard(String name, String role, String focus) {
        VBox box = new VBox(6);
        box.setPadding(new Insets(16));
        box.setPrefWidth(240);
        box.setAlignment(Pos.CENTER);
        box.setStyle("-fx-background-color: #ffffff; -fx-border-color: #e2e8f0; -fx-border-radius: 10; -fx-background-radius: 10;");

        Label avatar = new Label("👤");
        avatar.setStyle("-fx-font-size: 32px;");

        Label n = new Label(name);
        n.setFont(Font.font("System", FontWeight.BOLD, 14));
        n.setTextFill(Color.web("#1e293b"));

        Label r = new Label(role);
        r.setStyle("-fx-background-color: #ffe4d6; -fx-text-fill: #a83f00; -fx-font-size: 10.5px; -fx-padding: 3 8 3 8; -fx-background-radius: 12;");

        Label f = new Label(focus);
        f.setFont(Font.font("System", 11));
        f.setTextFill(Color.web("#64748b"));
        f.setWrapText(true);
        f.setTextAlignment(TextAlignment.CENTER);

        box.getChildren().addAll(avatar, n, r, f);
        return box;
    }
}
