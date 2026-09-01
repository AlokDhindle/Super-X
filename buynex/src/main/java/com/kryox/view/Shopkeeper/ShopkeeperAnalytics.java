package com.kryox.view.Shopkeeper;

import com.kryox.Main;
import com.kryox.view.Customer.Homepage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Polyline;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ShopkeeperAnalytics {

        public static Scene analyticsScene() {

                // ============================================================
                // BORDER PANE
                // ============================================================

                BorderPane borderPane = new BorderPane();

                // ================================================================
                // MAIN BORDER PANE
                // ================================================================

                HBox headerMainBox = ViewConstants.header();

                // Header background
                headerMainBox.setStyle(
                                "-fx-background-color: #EBCCB7;");

                borderPane.setTop(headerMainBox);

                // ============================================================
                // SIDEBAR
                // ============================================================

                VBox sidebar = createSidebar();
                borderPane.setRight(sidebar);

                // ============================================================
                // ANALYTICS CENTER
                // ============================================================

                VBox analyticsMain = new VBox(18);

                analyticsMain.setPadding(
                                new Insets(20, 24, 25, 24));

                analyticsMain.setStyle(
                                "-fx-background-color: #EEE5DF;");

                // ============================================================
                // TITLE AREA
                // ============================================================

                Text analyticsTitle = new Text("Business Analytics");

                analyticsTitle.setStyle(
                                "-fx-font-size: 27px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #202126;" +
                                                "-fx-font-family: 'Arial';");

                Text analyticsSubtitle = new Text(
                                "AI-driven insights for your store performance");

                analyticsSubtitle.setStyle(
                                "-fx-font-size: 14px;" +
                                                "-fx-fill: #72594D;" +
                                                "-fx-font-family: 'Arial';");

                VBox titleBox = new VBox(
                                5,
                                analyticsTitle,
                                analyticsSubtitle);

                Button exportButton = new Button();

                Text exportIcon = new Text("⇩");

                exportIcon.setStyle(
                                "-fx-font-size: 19px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #252525;");

                Text exportText = new Text("Export Report");

                exportText.setStyle(
                                "-fx-font-size: 13px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #333333;");

                HBox exportContent = new HBox(
                                9,
                                exportIcon,
                                exportText);

                exportContent.setAlignment(Pos.CENTER);

                exportButton.setGraphic(exportContent);

                exportButton.setPrefWidth(145);
                exportButton.setPrefHeight(42);

                exportButton.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-border-color: #DCD8E0;" +
                                                "-fx-border-width: 1px;" +
                                                "-fx-border-radius: 8px;" +
                                                "-fx-background-radius: 8px;" +
                                                "-fx-cursor: hand;");

                exportButton.setOnMouseEntered(e -> {
                        exportButton.setStyle(
                                        "-fx-background-color: #FFF4EB;" +
                                                        "-fx-border-color: #FF6900;" +
                                                        "-fx-border-width: 1px;" +
                                                        "-fx-border-radius: 8px;" +
                                                        "-fx-background-radius: 8px;" +
                                                        "-fx-cursor: hand;");
                });

                exportButton.setOnMouseExited(e -> {
                        exportButton.setStyle(
                                        "-fx-background-color: white;" +
                                                        "-fx-border-color: #DCD8E0;" +
                                                        "-fx-border-width: 1px;" +
                                                        "-fx-border-radius: 8px;" +
                                                        "-fx-background-radius: 8px;" +
                                                        "-fx-cursor: hand;");
                });

                HBox titleRow = new HBox(
                                20,
                                titleBox,
                                exportButton);

                titleRow.setAlignment(Pos.CENTER_LEFT);

                HBox.setHgrow(
                                titleBox,
                                Priority.ALWAYS);

                analyticsMain.getChildren().add(titleRow);

                // ============================================================
                // KPI CARDS
                // ============================================================

                VBox revenueCard = new VBox(7);

                revenueCard.setPadding(
                                new Insets(16, 15, 14, 15));

                revenueCard.setPrefWidth(145);
                revenueCard.setPrefHeight(125);

                revenueCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 10px;" +
                                                "-fx-border-color: #ECE9EE;" +
                                                "-fx-border-radius: 10px;");

                Text revenueIcon = new Text("▣");

                revenueIcon.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-fill: #B44B0A;");

                Text revenueLabel = new Text("Total\nRevenue");

                revenueLabel.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #68564E;");

                HBox revenueHeader = new HBox(
                                8,
                                revenueIcon,
                                revenueLabel);

                Text revenueValue = new Text("\u20B942.8k");

                revenueValue.setStyle(
                                "-fx-font-size: 25px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #202126;");

                Text revenueGrowth = new Text("↗ +15% vs last\nmonth");

                revenueGrowth.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #B44B0A;");

                revenueCard.getChildren().addAll(
                                revenueHeader,
                                revenueValue,
                                revenueGrowth);

                // ------------------------------------------------------------

                VBox ordersCard = new VBox(7);

                ordersCard.setPadding(
                                new Insets(16, 15, 14, 15));

                ordersCard.setPrefWidth(145);
                ordersCard.setPrefHeight(125);

                ordersCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 10px;" +
                                                "-fx-border-color: #ECE9EE;" +
                                                "-fx-border-radius: 10px;");

                Text ordersCardIcon = new Text("▢");

                ordersCardIcon.setStyle(
                                "-fx-font-size: 19px;" +
                                                "-fx-fill: #77716D;");

                Text ordersCardLabel = new Text("Orders");

                ordersCardLabel.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #68564E;");

                HBox ordersCardHeader = new HBox(
                                8,
                                ordersCardIcon,
                                ordersCardLabel);

                Text ordersValue = new Text("1,240");

                ordersValue.setStyle(
                                "-fx-font-size: 25px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #202126;");

                Text ordersGrowth = new Text("↗ +5% vs last\nmonth");

                ordersGrowth.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #B44B0A;");

                ordersCard.getChildren().addAll(
                                ordersCardHeader,
                                ordersValue,
                                ordersGrowth);

                // ------------------------------------------------------------

                VBox aovCard = new VBox(7);

                aovCard.setPadding(
                                new Insets(16, 15, 14, 15));

                aovCard.setPrefWidth(145);
                aovCard.setPrefHeight(125);

                aovCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 10px;" +
                                                "-fx-border-color: #ECE9EE;" +
                                                "-fx-border-radius: 10px;");

                Text aovIcon = new Text("▤");

                aovIcon.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-fill: #77716D;");

                Text aovLabel = new Text("Avg Order\nValue");

                aovLabel.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #68564E;");

                HBox aovHeader = new HBox(
                                8,
                                aovIcon,
                                aovLabel);

                Text aovValue = new Text("\u20B934.50");

                aovValue.setStyle(
                                "-fx-font-size: 25px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #202126;");

                Text aovGrowth = new Text("→ +0.2% vs last\nmonth");

                aovGrowth.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #6E6A67;");

                aovCard.getChildren().addAll(
                                aovHeader,
                                aovValue,
                                aovGrowth);

                // ------------------------------------------------------------

                VBox retentionCard = new VBox(7);

                retentionCard.setPadding(
                                new Insets(16, 15, 14, 15));

                retentionCard.setPrefWidth(145);
                retentionCard.setPrefHeight(125);

                retentionCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 10px;" +
                                                "-fx-border-color: #ECE9EE;" +
                                                "-fx-border-radius: 10px;");

                Text retentionIcon = new Text("♡");

                retentionIcon.setStyle(
                                "-fx-font-size: 22px;" +
                                                "-fx-fill: #B44B0A;");

                Text retentionLabel = new Text("Retention");

                retentionLabel.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #68564E;");

                HBox retentionHeader = new HBox(
                                8,
                                retentionIcon,
                                retentionLabel);

                Text retentionValue = new Text("72%");

                retentionValue.setStyle(
                                "-fx-font-size: 25px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #202126;");

                Text retentionGrowth = new Text("↗ +2% vs last\nmonth");

                retentionGrowth.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #B44B0A;");

                retentionCard.getChildren().addAll(
                                retentionHeader,
                                retentionValue,
                                retentionGrowth);

                HBox kpiRow = new HBox(
                                12,
                                revenueCard,
                                ordersCard,
                                aovCard,
                                retentionCard);

                kpiRow.setAlignment(Pos.CENTER_LEFT);

                analyticsMain.getChildren().add(kpiRow);

                // ============================================================
                // SECOND ROW
                // ============================================================

                HBox secondRow = new HBox(16);

                // ============================================================
                // SALES & GROWTH TREND CARD
                // ============================================================

                VBox salesCard = new VBox(10);

                salesCard.setPadding(
                                new Insets(18, 18, 15, 18));

                salesCard.setPrefWidth(570);
                salesCard.setMinHeight(270);

                salesCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 11px;" +
                                                "-fx-border-color: #ECE9EE;" +
                                                "-fx-border-radius: 11px;");

                Text salesTitle = new Text("Sales & Growth Trends");

                salesTitle.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #202126;");

                Text salesSubtitle = new Text(
                                "Actual Revenue vs AI-Predicted Baseline");

                salesSubtitle.setStyle(
                                "-fx-font-size: 12px;" +
                                                "-fx-fill: #72594D;");

                Text actualLegend = new Text("● Actual");

                actualLegend.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-fill: #A94A08;" +
                                                "-fx-font-weight: bold;");

                Text predictedLegend = new Text("◌ Predicted");

                predictedLegend.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-fill: #A87958;" +
                                                "-fx-font-weight: bold;");

                HBox legends = new HBox(
                                12,
                                actualLegend,
                                predictedLegend);

                legends.setAlignment(Pos.CENTER_RIGHT);

                HBox salesHeading = new HBox(
                                10,
                                new VBox(
                                                3,
                                                salesTitle,
                                                salesSubtitle),
                                legends);

                salesHeading.setAlignment(Pos.CENTER_LEFT);

                HBox.setHgrow(
                                salesHeading.getChildren().get(0),
                                Priority.ALWAYS);

                // ============================================================
                // CHART
                // ============================================================

                Pane chartPane = new Pane();

                chartPane.setPrefSize(530, 190);
                chartPane.setMinSize(530, 190);

                double chartLeft = 30;
                double chartRight = 515;
                double chartBottom = 155;

                // horizontal grid lines
                for (int i = 0; i < 4; i++) {

                        double y = 35 + (i * 40);

                        Line gridLine = new Line(
                                        chartLeft,
                                        y,
                                        chartRight,
                                        y);

                        gridLine.setStroke(
                                        Color.web("#F0EDF0"));

                        gridLine.setStrokeWidth(1);

                        chartPane.getChildren().add(gridLine);
                }

                // Actual sales area
                Polygon actualArea = new Polygon(
                                30.0, 125.0,
                                95.0, 118.0,
                                160.0, 120.0,
                                225.0, 95.0,
                                290.0, 60.0,
                                355.0, 82.0,
                                420.0, 66.0,
                                480.0, 38.0,
                                515.0, 65.0,
                                515.0, 155.0,
                                30.0, 155.0);

                actualArea.setFill(
                                Color.web("#FFF0E4"));

                chartPane.getChildren().add(actualArea);

                // Predicted line
                Polyline predictedLine = new Polyline(
                                30.0, 132.0,
                                95.0, 126.0,
                                160.0, 126.0,
                                225.0, 105.0,
                                290.0, 80.0,
                                355.0, 91.0,
                                420.0, 88.0,
                                480.0, 67.0,
                                515.0, 82.0);

                predictedLine.setStroke(
                                Color.web("#F2A06D"));

                predictedLine.setStrokeWidth(2);
                predictedLine.getStrokeDashArray().addAll(
                                5.0,
                                5.0);

                predictedLine.setFill(
                                Color.TRANSPARENT);

                chartPane.getChildren().add(
                                predictedLine);

                // Actual line
                Polyline actualLine = new Polyline(
                                30.0, 125.0,
                                95.0, 118.0,
                                160.0, 120.0,
                                225.0, 95.0,
                                290.0, 60.0,
                                355.0, 82.0,
                                420.0, 66.0,
                                480.0, 38.0,
                                515.0, 65.0);

                actualLine.setStroke(
                                Color.web("#FF6900"));

                actualLine.setStrokeWidth(3);

                actualLine.setFill(
                                Color.TRANSPARENT);

                chartPane.getChildren().add(
                                actualLine);

                // chart labels
                String[] days = {
                                "Mon",
                                "Tue",
                                "Wed",
                                "Thu",
                                "Fri",
                                "Sat",
                                "Sun"
                };

                double[] dayPositions = {
                                30,
                                110,
                                190,
                                270,
                                350,
                                430,
                                505
                };

                for (int i = 0; i < days.length; i++) {

                        Text dayText = new Text(
                                        days[i]);

                        dayText.setStyle(
                                        "-fx-font-size: 9px;" +
                                                        "-fx-fill: #77716D;");

                        dayText.setLayoutX(
                                        dayPositions[i]);

                        dayText.setLayoutY(
                                        175);

                        chartPane.getChildren().add(
                                        dayText);
                }

                salesCard.getChildren().addAll(
                                salesHeading,
                                chartPane);

                // ============================================================
                // RIGHT SIDE ANALYTICS CARDS
                // ============================================================

                VBox rightAnalytics = new VBox(15);

                rightAnalytics.setPrefWidth(285);

                // ============================================================
                // PREDICTIVE FORECAST
                // ============================================================

                VBox forecastCard = new VBox(10);

                forecastCard.setPadding(
                                new Insets(16));

                forecastCard.setPrefHeight(150);

                forecastCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 11px;" +
                                                "-fx-border-color: #E4DDE1;" +
                                                "-fx-border-width: 2px 0 0 0;" +
                                                "-fx-border-radius: 11px;");

                Text forecastIcon = new Text("⌁");

                forecastIcon.setStyle(
                                "-fx-font-size: 25px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #B44B0A;");

                Text forecastTitle = new Text(
                                "Predictive\nForecast");

                forecastTitle.setStyle(
                                "-fx-font-size: 19px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #202126;");

                HBox forecastHeader = new HBox(
                                12,
                                forecastIcon,
                                forecastTitle);

                Text forecastDescription = new Text(
                                "Estimated revenue for the next 7\n" +
                                                "days based on upcoming local\n" +
                                                "weekend festival.");

                forecastDescription.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-fill: #72594D;");

                HBox forecastValueBox = new HBox();

                forecastValueBox.setAlignment(
                                Pos.CENTER_LEFT);

                forecastValueBox.setPadding(
                                new Insets(10));

                forecastValueBox.setStyle(
                                "-fx-background-color: #F0EEF4;" +
                                                "-fx-background-radius: 8px;");

                VBox forecastValueText = new VBox(2);

                Text forecastLabel = new Text(
                                "EST. REVENUE");

                forecastLabel.setStyle(
                                "-fx-font-size: 9px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #72594D;");

                Text forecastValue = new Text(
                                "\u20B912.4k");

                forecastValue.setStyle(
                                "-fx-font-size: 22px;" +
                                                "-fx-fill: #29272B;");

                forecastValueText.getChildren().addAll(
                                forecastLabel,
                                forecastValue);

                Text forecastGrowth = new Text(
                                "↑ 18%");

                forecastGrowth.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #B44B0A;" +
                                                "-fx-background-color: #F1E1D8;" +
                                                "-fx-background-radius: 7px;");

                HBox.setHgrow(
                                forecastValueText,
                                Priority.ALWAYS);

                forecastValueBox.getChildren().addAll(
                                forecastValueText,
                                forecastGrowth);

                forecastCard.getChildren().addAll(
                                forecastHeader,
                                forecastDescription,
                                forecastValueBox);

                // ============================================================
                // AI RESTOCK ALERTS
                // ============================================================

                VBox restockCard = new VBox(10);

                restockCard.setPadding(
                                new Insets(16));

                restockCard.setPrefHeight(205);

                restockCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 11px;" +
                                                "-fx-border-color: #ECE9EE;" +
                                                "-fx-border-radius: 11px;");

                Text restockIcon = new Text("▣");

                restockIcon.setStyle(
                                "-fx-font-size: 20px;" +
                                                "-fx-fill: #D64040;");

                Text restockTitle = new Text(
                                "AI Restock\nAlerts");

                restockTitle.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #202126;");

                HBox restockHeader = new HBox(
                                12,
                                restockIcon,
                                restockTitle);

                // Whole Milk
                Rectangle milkImage = new Rectangle(
                                30,
                                38);

                milkImage.setArcWidth(6);
                milkImage.setArcHeight(6);

                milkImage.setFill(
                                Color.web("#E8E6E3"));

                Text milkIconText = new Text("🥛");

                milkIconText.setStyle(
                                "-fx-font-size: 15px;");

                StackPane milkBox = new StackPane(
                                milkImage,
                                milkIconText);

                Text milkName = new Text(
                                "Whole Milk (1\nGal)");

                milkName.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #454148;");

                Text milkAlert = new Text(
                                "Depleting in ~8\nhours");

                milkAlert.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-fill: #D83A3A;" +
                                                "-fx-font-weight: bold;");

                VBox milkText = new VBox(
                                3,
                                milkName,
                                milkAlert);

                HBox milkRow = new HBox(
                                12,
                                milkBox,
                                milkText);

                milkRow.setAlignment(
                                Pos.CENTER_LEFT);

                // Bread
                Rectangle breadImage = new Rectangle(
                                30,
                                38);

                breadImage.setArcWidth(6);
                breadImage.setArcHeight(6);

                breadImage.setFill(
                                Color.web("#E8D4BC"));

                Text breadIconText = new Text("🍞");

                breadIconText.setStyle(
                                "-fx-font-size: 15px;");

                StackPane breadBox = new StackPane(
                                breadImage,
                                breadIconText);

                Text breadName = new Text(
                                "Fresh Bread\nAssortment");

                breadName.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #454148;");

                Text breadAlert = new Text(
                                "Depleting in ~12\nhours");

                breadAlert.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-fill: #D83A3A;" +
                                                "-fx-font-weight: bold;");

                VBox breadText = new VBox(
                                3,
                                breadName,
                                breadAlert);

                HBox breadRow = new HBox(
                                12,
                                breadBox,
                                breadText);

                breadRow.setAlignment(
                                Pos.CENTER_LEFT);

                restockCard.getChildren().addAll(
                                restockHeader,
                                milkRow,
                                breadRow);

                rightAnalytics.getChildren().addAll(
                                forecastCard,
                                restockCard);

                secondRow.getChildren().addAll(
                                salesCard,
                                rightAnalytics);

                analyticsMain.getChildren().add(
                                secondRow);

                // ============================================================
                // THIRD ROW
                // ============================================================

                HBox thirdRow = new HBox(16);

                // ============================================================
                // TOP SELLING CARD
                // ============================================================

                VBox topSellingCard = new VBox(12);

                topSellingCard.setPadding(
                                new Insets(16));

                topSellingCard.setPrefWidth(285);
                topSellingCard.setPrefHeight(190);

                topSellingCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 11px;" +
                                                "-fx-border-color: #ECE9EE;" +
                                                "-fx-border-radius: 11px;");

                Text topSellingTitle = new Text(
                                "Top Selling");

                topSellingTitle.setStyle(
                                "-fx-font-size: 19px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #202126;");

                // Product 1
                Text sourdoughImage = new Text("🥖");

                sourdoughImage.setStyle(
                                "-fx-font-size: 22px;");

                Text sourdoughName = new Text(
                                "Artisan\nSourdough");

                sourdoughName.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #454148;");

                Text sourdoughUnits = new Text(
                                "240\nunits");

                sourdoughUnits.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-fill: #72594D;");

                VBox sourdoughInfo = new VBox(
                                3,
                                sourdoughName,
                                sourdoughUnits);

                HBox.setHgrow(
                                sourdoughInfo,
                                Priority.ALWAYS);

                HBox sourdoughRow = new HBox(
                                9,
                                sourdoughImage,
                                sourdoughInfo);

                sourdoughRow.setAlignment(
                                Pos.CENTER_LEFT);

                Rectangle sourdoughBarBackground = new Rectangle(185, 7);

                sourdoughBarBackground.setArcWidth(7);
                sourdoughBarBackground.setArcHeight(7);

                sourdoughBarBackground.setFill(
                                Color.web("#EAE7EA"));

                Rectangle sourdoughBar = new Rectangle(145, 7);

                sourdoughBar.setArcWidth(7);
                sourdoughBar.setArcHeight(7);

                sourdoughBar.setFill(
                                Color.web("#B44B0A"));

                StackPane sourdoughProgress = new StackPane(
                                sourdoughBarBackground,
                                sourdoughBar);

                sourdoughProgress.setAlignment(
                                Pos.CENTER_LEFT);

                // Product 2
                Text bananaImage = new Text("🍌");

                bananaImage.setStyle(
                                "-fx-font-size: 21px;");

                Text bananaName = new Text(
                                "Organic Bananas");

                bananaName.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #454148;");

                Text bananaUnits = new Text(
                                "185 units");

                bananaUnits.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-fill: #72594D;");

                VBox bananaInfo = new VBox(
                                4,
                                bananaName,
                                bananaUnits);

                HBox bananaRow = new HBox(
                                9,
                                bananaImage,
                                bananaInfo);

                bananaRow.setAlignment(
                                Pos.CENTER_LEFT);

                Rectangle bananaBarBackground = new Rectangle(185, 7);

                bananaBarBackground.setArcWidth(7);
                bananaBarBackground.setArcHeight(7);

                bananaBarBackground.setFill(
                                Color.web("#EAE7EA"));

                Rectangle bananaBar = new Rectangle(115, 7);

                bananaBar.setArcWidth(7);
                bananaBar.setArcHeight(7);

                bananaBar.setFill(
                                Color.web("#CC7A49"));

                StackPane bananaProgress = new StackPane(
                                bananaBarBackground,
                                bananaBar);

                bananaProgress.setAlignment(
                                Pos.CENTER_LEFT);

                // Product 3
                Text coffeeImage = new Text("☕");

                coffeeImage.setStyle(
                                "-fx-font-size: 21px;");

                Text coffeeName = new Text(
                                "Whole Bean Coffee");

                coffeeName.setStyle(
                                "-fx-font-size: 11px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #454148;");

                Text coffeeUnits = new Text(
                                "120 units");

                coffeeUnits.setStyle(
                                "-fx-font-size: 10px;" +
                                                "-fx-fill: #72594D;");

                VBox coffeeInfo = new VBox(
                                4,
                                coffeeName,
                                coffeeUnits);

                HBox coffeeRow = new HBox(
                                9,
                                coffeeImage,
                                coffeeInfo);

                coffeeRow.setAlignment(
                                Pos.CENTER_LEFT);

                Rectangle coffeeBarBackground = new Rectangle(185, 7);

                coffeeBarBackground.setArcWidth(7);
                coffeeBarBackground.setArcHeight(7);

                coffeeBarBackground.setFill(
                                Color.web("#EAE7EA"));

                Rectangle coffeeBar = new Rectangle(90, 7);

                coffeeBar.setArcWidth(7);
                coffeeBar.setArcHeight(7);

                coffeeBar.setFill(
                                Color.web("#D09672"));

                StackPane coffeeProgress = new StackPane(
                                coffeeBarBackground,
                                coffeeBar);

                coffeeProgress.setAlignment(
                                Pos.CENTER_LEFT);

                topSellingCard.getChildren().addAll(
                                topSellingTitle,
                                sourdoughRow,
                                sourdoughProgress,
                                bananaRow,
                                bananaProgress,
                                coffeeRow,
                                coffeeProgress);

                // ============================================================
                // PEAK HOURS CARD
                // ============================================================

                VBox peakCard = new VBox(12);

                peakCard.setPadding(
                                new Insets(16));

                peakCard.setPrefWidth(285);
                peakCard.setPrefHeight(190);

                peakCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 11px;" +
                                                "-fx-border-color: #ECE9EE;" +
                                                "-fx-border-radius: 11px;");

                Text peakTitle = new Text(
                                "Peak Hours");

                peakTitle.setStyle(
                                "-fx-font-size: 19px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #202126;");

                GridPane heatMap = new GridPane();

                heatMap.setHgap(4);
                heatMap.setVgap(4);

                double[][] heatValues = {
                                { 0.20, 0.30, 0.45, 0.65, 0.30 },
                                { 0.35, 0.85, 0.55, 0.90, 0.50 },
                                { 0.70, 0.95, 0.40, 0.75, 0.20 }
                };

                for (int row = 0; row < 3; row++) {

                        for (int col = 0; col < 5; col++) {

                                Rectangle heatCell = new Rectangle(48, 28);

                                heatCell.setArcWidth(3);
                                heatCell.setArcHeight(3);

                                double value = heatValues[row][col];

                                String cellColor;

                                if (value > 0.80) {
                                        cellColor = "#A94408";
                                } else if (value > 0.60) {
                                        cellColor = "#C7794A";
                                } else if (value > 0.40) {
                                        cellColor = "#D8A888";
                                } else {
                                        cellColor = "#E5C8B5";
                                }

                                heatCell.setFill(
                                                Color.web(cellColor));

                                heatMap.add(
                                                heatCell,
                                                col,
                                                row);
                        }
                }

                HBox peakLabels = new HBox(
                                38,
                                new Text("9A"),
                                new Text("12P"),
                                new Text("3P"),
                                new Text("6P"),
                                new Text("9P"));

                for (javafx.scene.Node node : peakLabels.getChildren()) {

                        ((Text) node).setStyle(
                                        "-fx-font-size: 9px;" +
                                                        "-fx-fill: #72594D;");
                }

                peakCard.getChildren().addAll(
                                peakTitle,
                                heatMap,
                                peakLabels);

                // ============================================================
                // CUSTOMER SENTIMENT CARD
                // ============================================================

                VBox sentimentCard = new VBox(9);

                sentimentCard.setPadding(
                                new Insets(16));

                sentimentCard.setPrefWidth(285);
                sentimentCard.setPrefHeight(190);

                sentimentCard.setStyle(
                                "-fx-background-color: white;" +
                                                "-fx-background-radius: 11px;" +
                                                "-fx-border-color: #ECE9EE;" +
                                                "-fx-border-radius: 11px;");

                Text sentimentTitle = new Text(
                                "Customer\nSentiment");

                sentimentTitle.setStyle(
                                "-fx-font-size: 18px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #202126;");

                Text sentimentScore = new Text(
                                "4.8");

                sentimentScore.setStyle(
                                "-fx-font-size: 27px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #202126;");

                Text stars = new Text(
                                "★★★★★");

                stars.setStyle(
                                "-fx-font-size: 17px;" +
                                                "-fx-fill: #B44B0A;" +
                                                "-fx-font-weight: bold;");

                HBox scoreRow = new HBox(
                                10,
                                sentimentScore,
                                stars);

                scoreRow.setAlignment(
                                Pos.CENTER_LEFT);

                Text sentimentBased = new Text(
                                "Based on 142 reviews this week");

                sentimentBased.setStyle(
                                "-fx-font-size: 9px;" +
                                                "-fx-fill: #77716D;");

                Text themesTitle = new Text(
                                "KEY THEMES");

                themesTitle.setStyle(
                                "-fx-font-size: 9px;" +
                                                "-fx-font-weight: bold;" +
                                                "-fx-fill: #72594D;");

                Text theme1 = new Text(
                                "⚡ Fast Delivery");

                theme1.setStyle(
                                "-fx-font-size: 9px;" +
                                                "-fx-fill: #5C4C45;" +
                                                "-fx-background-color: #F1EDF2;" +
                                                "-fx-background-radius: 8px;");

                Text theme2 = new Text(
                                "⊙ Quality Produce");

                theme2.setStyle(
                                "-fx-font-size: 9px;" +
                                                "-fx-fill: #5C4C45;" +
                                                "-fx-background-color: #F1EDF2;" +
                                                "-fx-background-radius: 8px;");

                Text theme3 = new Text(
                                "♙ Friendly Staff");

                theme3.setStyle(
                                "-fx-font-size: 9px;" +
                                                "-fx-fill: #5C4C45;" +
                                                "-fx-background-color: #F1EDF2;" +
                                                "-fx-background-radius: 8px;");

                VBox themes = new VBox(
                                4,
                                theme1,
                                theme2,
                                theme3);

                sentimentCard.getChildren().addAll(
                                sentimentTitle,
                                scoreRow,
                                sentimentBased,
                                themesTitle,
                                themes);

                thirdRow.getChildren().addAll(
                                topSellingCard,
                                peakCard,
                                sentimentCard);

                analyticsMain.getChildren().add(
                                thirdRow);

                // ============================================================
                // CENTER SCROLL PANE
                // ============================================================

                ScrollPane centerScroll = new ScrollPane(
                                analyticsMain);

                centerScroll.setFitToWidth(true);
                centerScroll.setPannable(true);

                centerScroll.setHbarPolicy(
                                ScrollPane.ScrollBarPolicy.NEVER);

                centerScroll.setVbarPolicy(
                                ScrollPane.ScrollBarPolicy.AS_NEEDED);

                centerScroll.setStyle(
                                "-fx-background-color: #EEE5DF;" +
                                                "-fx-border-color: transparent;");

                borderPane.setCenter(
                                centerScroll);

                // ============================================================
                // EXPORT REPORT ACTION
                // ============================================================

                exportButton.setOnAction(e -> {

                        System.out.println(
                                        "Analytics report exported successfully.");

                        exportText.setText(
                                        "Report Exported");

                        exportButton.setStyle(
                                        "-fx-background-color: #FFF0E4;" +
                                                        "-fx-border-color: #B44B0A;" +
                                                        "-fx-border-width: 1px;" +
                                                        "-fx-border-radius: 8px;" +
                                                        "-fx-background-radius: 8px;" +
                                                        "-fx-cursor: hand;");
                });

                // ================================================================
                // FOOTER
                // ================================================================

                VBox footerBox = ViewConstants.footer();

                borderPane.setBottom(
                                footerBox);

                // ============================================================
                // SCENE
                // ============================================================

                Scene analyticsScene = new Scene(
                                borderPane,
                               1550,850
                        );

                analyticsScene.setFill(
                                Color.web("#EEE5DF"));

                return analyticsScene;
        }
        private static VBox createSidebar() {

                VBox sidebar = new VBox();

                sidebar.setMinWidth(
                                ViewConstants.SIDEBAR_WIDTH);

                sidebar.setMaxWidth(
                                ViewConstants.SIDEBAR_WIDTH);

                sidebar.setStyle(
                                "-fx-background-color: #EBCCB7;" +
                                                "-fx-border-color: #E3C7BA;" +
                                                "-fx-border-width: 0 1px 0 0;");

                HBox profileBox = ViewConstants.letfProfileBox();

                profileBox.setAlignment(
                                Pos.CENTER_LEFT);

                profileBox.setPadding(
                                new Insets(
                                                30,
                                                20,
                                                30,
                                                20));

                Button dashboardButton = ViewConstants.createDashboardButton(
                                "★",
                                "Dashboard",
                                false);

                Button ordersButton = ViewConstants.createDashboardButton(
                                "🛒",
                                "Orders",
                                false);

                Button inventoryButton = ViewConstants.createDashboardButton(
                                "📋",
                                "Inventory",
                                false);

                Button offersButton = ViewConstants.createDashboardButton(
                                "🎁",
                                "Offers",
                                false);

                Button analyticsButton = ViewConstants.createDashboardButton(
                                "📊",
                                "Analytics",
                                true);

                Button settingsButton = ViewConstants.createDashboardButton(
                                "⚙",
                                "Settings",
                                false);

                Button supportButton = ViewConstants.createDashboardButton(
                                "?",
                                "Support",
                                false);

                VBox menu = new VBox(
                                5,
                                dashboardButton,
                                ordersButton,
                                inventoryButton,
                                offersButton,
                                analyticsButton,
                                settingsButton,
                                supportButton);

                menu.setPadding(
                                new Insets(
                                                0,
                                                8,
                                                0,
                                                8));

                // VBox logout =
                // ViewConstants.logoutBox();

                VBox.setVgrow(
                                menu,
                                Priority.ALWAYS);

                sidebar.getChildren().addAll(
                                profileBox,
                                menu
                // logout
                );

                dashboardButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperDashboard
                                                                .dashboardScene()));
                inventoryButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperInventory
                                                                .inventoryScene()));

                ordersButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperOrderReady
                                                                .ordersScene()));

                offersButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperOffers
                                                                .offersScene()));


                settingsButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperSettings
                                                                .settingsScene()));

                supportButton.setOnAction(
                                event -> Homepage.HomepageStage.setScene(
                                                ShopkeeperSupport
                                                                .supportScene()));

                return sidebar;
        }

}