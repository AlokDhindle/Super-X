package com.kryox.view.Customer;

import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class EarningsReport extends Application {

    private final String ORANGE = "#FF873D";
    private final String BG = "#F8F7FB";

    private VBox card() {

        VBox box = new VBox(15);

        box.setPadding(new Insets(18));

        box.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:12;" +
                "-fx-effect:dropshadow(gaussian,rgba(0,0,0,0.08),18,0,0,4);"
        );

        return box;
    }

    private Label label(String text,double size,String color,boolean bold){

        Label l=new Label(text);

        l.setStyle(
                "-fx-font-family:'Montserrat';"+
                "-fx-font-size:"+size+"px;"+
                "-fx-text-fill:"+color+";"+
                (bold?"-fx-font-weight:bold;":"")
        );

        return l;
    }

    private HBox navItem(String icon,String text,boolean active){

        Label i=label(icon,14,active?"white":"#777",false);
        Label t=label(text,12,active?"white":"#777",active);

        HBox box=new HBox(12,i,t);

        box.setAlignment(Pos.CENTER_LEFT);

        box.setPadding(new Insets(10,15,10,15));

        box.setPrefWidth(160);

        if(active){

            box.setStyle(
                    "-fx-background-color:"+ORANGE+";" +
                    "-fx-background-radius:10;"
            );

        }else{

            box.setStyle(
                    "-fx-background-color:transparent;"
            );
        }

        return box;
    }

    @Override
    public void start(Stage stage){

        //======================
        // SIDEBAR
        //======================

        VBox sidebar=new VBox(12);

        sidebar.setPrefWidth(185);

        sidebar.setPadding(new Insets(25,12,20,12));

        sidebar.setStyle(
                "-fx-background-color:white;" +
                "-fx-border-color:#ECECEC;" +
                "-fx-border-width:0 1 0 0;"
        );

        StackPane avatar=new StackPane();

        Circle c=new Circle(22);

        c.setFill(Color.web("#FFE0CC"));

        avatar.getChildren().addAll(
                c,
                label("AW",11,ORANGE,true)
        );

        VBox profileText=new VBox(3);

        profileText.getChildren().addAll(

                label("Alex Walker",13,ORANGE,true),

                label("Elite Partner",10,"#888",false)
        );

        HBox profile=new HBox(12,avatar,profileText);

        profile.setAlignment(Pos.CENTER_LEFT);

        Button online=new Button("Go Online");

        online.setPrefWidth(155);

        online.setPrefHeight(38);

        online.setStyle(
                "-fx-background-color:linear-gradient(to right,#FF873D,#FFB07C);" +
                "-fx-text-fill:white;" +
                "-fx-font-size:12;" +
                "-fx-font-weight:bold;" +
                "-fx-background-radius:10;"
        );

        VBox menu=new VBox(6);

        menu.getChildren().addAll(

                navItem("⌂","Dashboard",false),

                navItem("🚚","Deliveries",false),

                navItem("📍","Navigation",false),

                navItem("💰","Earnings",true),

                navItem("📅","Availability",false)
        );

        Region space=new Region();

        VBox.setVgrow(space,Priority.ALWAYS);

        sidebar.getChildren().addAll(

                profile,

                online,

                new Separator(),

                menu,

                space,

                navItem("⚙","Settings",false),

                navItem("↩","Logout",false)

        );

        //======================
        // MAIN AREA
        //======================

        VBox main=new VBox(25);

        main.setPadding(new Insets(30));

        main.setStyle("-fx-background-color:"+BG+";");

        //======================
        // TOP BAR
        //======================

        HBox topBar=new HBox();

        topBar.setAlignment(Pos.CENTER_LEFT);

        TextField search=new TextField();

        search.setPromptText("Search transactions...");

        search.setPrefWidth(430);

        search.setPrefHeight(40);

        search.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:20;" +
                "-fx-font-size:12;" +
                "-fx-border-width:0;"
        );

        Region topSpace=new Region();

        HBox.setHgrow(topSpace,Priority.ALWAYS);

        HBox icons=new HBox(
                18,
                label("🔔",15,"#777",false),
                label("✉",15,"#777",false),
                label("?",15,"#777",true),
                new Circle(12,Color.web("#E6C8B6"))
        );

        icons.setAlignment(Pos.CENTER);

        topBar.getChildren().addAll(
                search,
                topSpace,
                icons
        );

        //======================
        // PAGE TITLE
        //======================

        VBox titleBox=new VBox(4);

        titleBox.getChildren().addAll(

                label("Earnings Report",28,"#333",true),

                label("Track your income and payout history.",12,"#888",false)

        );
                //====================================================
        // ACTION BUTTONS
        //====================================================

        Region titleSpacer = new Region();
        HBox.setHgrow(titleSpacer, Priority.ALWAYS);

        Button monthBtn = new Button("This Month ▼");
        monthBtn.setPrefSize(120,36);

        monthBtn.setStyle(
                "-fx-background-color:white;" +
                "-fx-background-radius:10;" +
                "-fx-border-color:#E7E7E7;" +
                "-fx-border-radius:10;" +
                "-fx-font-size:12;"
        );

        Button exportBtn = new Button("Export Data");

        exportBtn.setPrefSize(130,36);

        exportBtn.setStyle(
                "-fx-background-color:"+ORANGE+";" +
                "-fx-text-fill:white;" +
                "-fx-background-radius:10;" +
                "-fx-font-weight:bold;"
        );

        HBox heading=new HBox(
                titleBox,
                titleSpacer,
                monthBtn,
                exportBtn
        );

        heading.setAlignment(Pos.CENTER_LEFT);

        //====================================================
        // SUMMARY CARDS
        //====================================================

        HBox summaryCards=new HBox(20);

        VBox weeklyCard=card();
        weeklyCard.setPrefSize(250,140);

        weeklyCard.getChildren().addAll(

                label("Weekly Earnings",13,"#777",false),

                label("$1,248.60",28,"#333",true),

                label("+12.4% than last week",11,"#45B36B",false)

        );

        VBox monthlyCard=card();
        monthlyCard.setPrefSize(250,140);

        monthlyCard.getChildren().addAll(

                label("Monthly Total",13,"#777",false),

                label("$5,846.20",28,"#333",true),

                label("+8.7% than last month",11,"#45B36B",false)

        );

        VBox dailyCard=card();
        dailyCard.setPrefSize(250,140);

        dailyCard.getChildren().addAll(

                label("Daily Average",13,"#777",false),

                label("$208.45",28,"#333",true),

                label("Average of 30 days",11,"#888",false)

        );

        summaryCards.getChildren().addAll(
                weeklyCard,
                monthlyCard,
                dailyCard
        );

        //====================================================
        // EARNINGS OVERVIEW CARD
        //====================================================

        VBox chartCard=card();

        chartCard.setPrefSize(1020,330);

        HBox chartHeader=new HBox();

        chartHeader.setAlignment(Pos.CENTER_LEFT);

        Label chartTitle=label(
                "Earnings Overview",
                16,
                "#333",
                true
        );

        Region chartSpacer=new Region();
        HBox.setHgrow(chartSpacer,Priority.ALWAYS);

        ComboBox<String> filter=new ComboBox<>();

        filter.getItems().addAll(
                "Weekly",
                "Monthly",
                "Yearly"
        );

        filter.setValue("Monthly");

        chartHeader.getChildren().addAll(
                chartTitle,
                chartSpacer,
                filter
        );

        //====================================================
        // GRAPH PLACEHOLDER
        //====================================================

        Pane graph=new Pane();

        graph.setPrefSize(980,220);

        graph.setStyle(
                "-fx-background-color:#FCFCFC;" +
                "-fx-border-color:#EEEEEE;" +
                "-fx-border-radius:10;" +
                "-fx-background-radius:10;"
        );

        Label graphText=label(
                "Line Chart will be added here",
                16,
                "#BBBBBB",
                false
        );

        graphText.setLayoutX(340);
        graphText.setLayoutY(95);

        graph.getChildren().add(graphText);

        chartCard.getChildren().addAll(
                chartHeader,
                graph
        );

        //====================================================
        // ADD TO MAIN
        //====================================================

        main.getChildren().addAll(

                topBar,

                heading,

                summaryCards,

                chartCard

        );
        //====================================================
        // TRANSACTION HISTORY
        //====================================================

        VBox transactionCard = card();
        transactionCard.setPrefSize(1020,300);

        Label transactionTitle = label(
                "Transaction History",
                16,
                "#333333",
                true
        );

        TableView<String> table = new TableView<>();

        table.setPrefHeight(240);

        TableColumn<String,String> orderCol =
                new TableColumn<>("Order ID");

        TableColumn<String,String> dateCol =
                new TableColumn<>("Date");

        TableColumn<String,String> amountCol =
                new TableColumn<>("Amount");

        TableColumn<String,String> commissionCol =
                new TableColumn<>("Commission");

        TableColumn<String,String> statusCol =
                new TableColumn<>("Status");

        TableColumn<String,String> actionCol =
                new TableColumn<>("Action");

        orderCol.setPrefWidth(150);
        dateCol.setPrefWidth(140);
        amountCol.setPrefWidth(140);
        commissionCol.setPrefWidth(150);
        statusCol.setPrefWidth(150);
        actionCol.setPrefWidth(120);

        table.getColumns().addAll(
                orderCol,
                dateCol,
                amountCol,
                commissionCol,
                statusCol,
                actionCol
        );

        transactionCard.getChildren().addAll(
                transactionTitle,
                table
        );

        //====================================================
        // MAIN
        //====================================================

        main.getChildren().add(transactionCard);

        //====================================================
        // ROOT
        //====================================================

        BorderPane root = new BorderPane();

        root.setLeft(sidebar);

        root.setCenter(main);

        Scene scene = new Scene(root,1450,850);

        stage.setTitle("Earnings Report");

        stage.setScene(scene);

        stage.setMinWidth(1450);

        stage.setMinHeight(850);

        stage.show();
    }
}
