package com.kryox.view.Delivery;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class PastWeeksHistory {

    private static final String BG_COLOR = "#fbfbfe";
    private static final String BORDER_COLOR = "#f0edf2";

    // =========================================================================
    // DYNAMIC FIRESTORE-READY HISTORICAL LOG MODEL
    // =========================================================================
    public static class PastWeeksData {
        public String partnerName = "Alex Walker";
        public String overallAverageCompletion = "94.2%";
        public int totalHistoricalHours = 186;
        public double totalHistoricalEarnings = 34820.00;
        public List<WeekRecord> weekRecords = new ArrayList<>();

        public PastWeeksData() {
            weekRecords.add(new WeekRecord("WK-32 (03 Aug - 09 Aug 2026)", 28, 28, 6240.00, 1.00, "100% Completed", true));
            weekRecords.add(new WeekRecord("WK-31 (27 Jul - 02 Aug 2026)", 32, 30, 6850.00, 0.94, "94% Completed", true));
            weekRecords.add(new WeekRecord("WK-30 (20 Jul - 26 Jul 2026)", 24, 24, 5380.00, 1.00, "100% Completed", true));
            weekRecords.add(new WeekRecord("WK-29 (13 Jul - 19 Jul 2026)", 30, 26, 5720.00, 0.87, "87% Completed", true));
            weekRecords.add(new WeekRecord("WK-28 (06 Jul - 12 Jul 2026)", 26, 26, 5810.00, 1.00, "100% Completed", true));
            weekRecords.add(new WeekRecord("WK-27 (29 Jun - 05 Jul 2026)", 28, 24, 4820.00, 0.85, "85% Completed", false));
        }
    }

    public static class WeekRecord {
        public String weekRange;
        public int scheduledHours;
        public int completedHours;
        public double earnings;
        public double completionRate;
        public String statusText;
        public boolean isPayoutSettled;

        public WeekRecord(String weekRange, int scheduledHours, int completedHours, double earnings,
                          double completionRate, String statusText, boolean isPayoutSettled) {
            this.weekRange = weekRange;
            this.scheduledHours = scheduledHours;
            this.completedHours = completedHours;
            this.earnings = earnings;
            this.completionRate = completionRate;
            this.statusText = statusText;
            this.isPayoutSettled = isPayoutSettled;
        }
    }

    // =========================================================================
    // STATIC SCENE FACTORY METHODS
    // =========================================================================
    public static Scene pastWeeksHistoryScene() {
        return pastWeeksHistoryScene(new PastWeeksData());
    }

    public static Scene pastWeeksHistoryScene(PastWeeksData data) {
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: " + BG_COLOR + ";");

        root.setTop(createTopHeader());

        VBox mainContent = createMainContent(data);
        ScrollPane scrollPane = new ScrollPane(mainContent);
        scrollPane.setFitToWidth(true);
        scrollPane.setPannable(true);
        scrollPane.setStyle("-fx-background-color: transparent; -fx-background: " + BG_COLOR + "; -fx-border-color: transparent;");

        root.setCenter(scrollPane);

        Scene scene = new Scene(root, 1280, 720);
        scene.setFill(Color.web(BG_COLOR));
        return scene;
    }

    private static BorderPane createTopHeader() {
        BorderPane topBar = new BorderPane();
        topBar.setPrefHeight(60);
        topBar.setMinHeight(60);
        topBar.setMaxHeight(60);
        topBar.setStyle(
                "-fx-background-color: white;" +
                "-fx-border-color: " + BORDER_COLOR + ";" +
                "-fx-border-width: 0 0 1 0;" +
                "-fx-padding: 0 35 0 25;"
        );

        Button btnBack = new Button("←  Back to Availability");
        btnBack.setStyle(
                "-fx-background-color: #f8f8fb;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-border-radius: 8;" +
                "-fx-background-radius: 8;" +
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: #374151;" +
                "-fx-cursor: hand;" +
                "-fx-padding: 6 14 6 14;"
        );
        btnBack.setOnAction(e -> {
            if (Homepage.HomepageStage != null) {
                Homepage.HomepageStage.setScene(PartnerAvailability.availabilityScene());
            }
        });

        Text title = new Text("Past Weeks Shift History");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-fill: #111827;");

        HBox leftGroup = new HBox(16, btnBack, title);
        leftGroup.setAlignment(Pos.CENTER_LEFT);
        topBar.setLeft(leftGroup);

        return topBar;
    }

    private static VBox createMainContent(PastWeeksData data) {
        VBox content = new VBox(22);
        content.setPadding(new Insets(24, 40, 60, 40));
        content.setAlignment(Pos.TOP_CENTER);

        VBox wrapper = new VBox(20);
        wrapper.setMaxWidth(880);

        HBox topMetrics = new HBox(16);
        topMetrics.getChildren().addAll(
                createSummaryCard("Total Historical Hours", data.totalHistoricalHours + " hrs", "Logged across active shifts", "⏱"),
                createSummaryCard("Total Shift Earnings", "₹" + String.format("%,.2f", data.totalHistoricalEarnings), "Direct IMPS settled", "💵"),
                createSummaryCard("Avg. Completion Rate", data.overallAverageCompletion, "Shift reliability metric", "🎯")
        );
        topMetrics.getChildren().forEach(n -> HBox.setHgrow(n, Priority.ALWAYS));

        VBox recordsList = new VBox(14);
        for (WeekRecord record : data.weekRecords) {
            recordsList.getChildren().add(createWeekRecordCard(record));
        }

        wrapper.getChildren().addAll(topMetrics, recordsList);
        content.getChildren().add(wrapper);
        return content;
    }

    private static VBox createSummaryCard(String label, String value, String subtext, String icon) {
        VBox card = new VBox(6);
        card.setPadding(new Insets(18));
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: " + BORDER_COLOR + ";" +
                "-fx-border-width: 1;"
        );

        BorderPane top = new BorderPane();
        Label l = new Label(label);
        l.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280; -fx-font-weight: bold;");
        Label i = new Label(icon);
        i.setStyle("-fx-font-size: 14px;");
        top.setLeft(l);
        top.setRight(i);

        Label v = new Label(value);
        v.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label s = new Label(subtext);
        s.setStyle("-fx-font-size: 10px; -fx-text-fill: #9ca3af;");

        card.getChildren().addAll(top, v, s);
        return card;
    }

    private static VBox createWeekRecordCard(WeekRecord record) {
        VBox card = new VBox(12);
        card.setPadding(new Insets(18));
        card.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: " + BORDER_COLOR + ";" +
                "-fx-border-width: 1;"
        );

        BorderPane topRow = new BorderPane();
        VBox titleBox = new VBox(2);
        Label weekRange = new Label("📅  " + record.weekRange);
        weekRange.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label hoursInfo = new Label("Logged: " + record.completedHours + " hrs completed / " + record.scheduledHours + " hrs scheduled");
        hoursInfo.setStyle("-fx-font-size: 11px; -fx-text-fill: #6b7280;");
        titleBox.getChildren().addAll(weekRange, hoursInfo);
        topRow.setLeft(titleBox);

        VBox rightBox = new VBox(2);
        rightBox.setAlignment(Pos.TOP_RIGHT);
        Label earnVal = new Label("₹" + String.format("%,.2f", record.earnings));
        earnVal.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #15803d;");

        Label payoutBadge = new Label(record.isPayoutSettled ? "✓ Payout Settled" : "⏳ In Verification");
        payoutBadge.setStyle(
                "-fx-font-size: 9px; -fx-font-weight: bold; -fx-padding: 2 6 2 6; -fx-background-radius: 4; " +
                "-fx-background-color: " + (record.isPayoutSettled ? "#dcfce7;" : "#fef3c7;") +
                "-fx-text-fill: " + (record.isPayoutSettled ? "#15803d;" : "#b45309;")
        );
        rightBox.getChildren().addAll(earnVal, payoutBadge);
        topRow.setRight(rightBox);

        VBox progGroup = new VBox(4);
        BorderPane progMeta = new BorderPane();
        Label progTitle = new Label("Shift Completion Target");
        progTitle.setStyle("-fx-font-size: 10px; -fx-text-fill: #6b7280;");
        Label progVal = new Label(record.statusText);
        progVal.setStyle("-fx-font-size: 10px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        progMeta.setLeft(progTitle);
        progMeta.setRight(progVal);

        ProgressBar pb = new ProgressBar(record.completionRate);
        pb.setMaxWidth(Double.MAX_VALUE);
        pb.setPrefHeight(6);
        pb.setStyle("-fx-accent: " + (record.completionRate >= 0.90 ? "#16a34a;" : "#ea580c;"));
        progGroup.getChildren().addAll(progMeta, pb);

        card.getChildren().addAll(topRow, progGroup);
        return card;
    }
}