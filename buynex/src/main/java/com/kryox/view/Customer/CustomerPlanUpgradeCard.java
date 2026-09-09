package com.kryox.view.Customer;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

/**
 * Dynamic Upgrade Card displayed in the customer sidebar.
 * Automatically reflects whether the user is on Free, Gold, or Platinum VIP plan.
 */
public class CustomerPlanUpgradeCard {

    public static VBox createUpgradeCard(String userId) {
        String plan = CustomerManagePlan.CustomerPlanState.getCurrentPlan(userId);

        VBox upgradeCard = new VBox(7);
        upgradeCard.setPrefWidth(205);
        upgradeCard.setMinWidth(205);
        upgradeCard.setMaxWidth(205);
        upgradeCard.setPrefHeight(112);
        upgradeCard.setPadding(new Insets(15));
        upgradeCard.setAlignment(Pos.CENTER_LEFT);

        LinearGradient upgradeGradient;
        String borderColor;
        String titleText;
        String descText;
        String btnText;
        String btnStyle;

        if ("Platinum".equalsIgnoreCase(plan)) {
            // Platinum VIP State
            upgradeGradient = new LinearGradient(
                    0, 0, 1, 1, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, Color.web("#1B1728")),
                    new Stop(1, Color.web("#372B4D"))
            );
            borderColor = "#8B5CF6";
            titleText = "💎 Platinum VIP Active";
            descText = "VIP Butler & Express Delivery";
            btnText = "Manage VIP Plan";
            btnStyle = "-fx-background-color: linear-gradient(to right, #8B5CF6, #C084FC);" +
                       "-fx-text-fill: white;" +
                       "-fx-font-size: 10px;" +
                       "-fx-font-weight: bold;" +
                       "-fx-background-radius: 10;" +
                       "-fx-cursor: hand;";
        } else if ("Gold".equalsIgnoreCase(plan)) {
            // Gold Member State
            upgradeGradient = new LinearGradient(
                    0, 0, 1, 1, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, Color.web("#261E11")),
                    new Stop(1, Color.web("#4A3716"))
            );
            borderColor = "#F59E0B";
            titleText = "👑 Gold Member Active";
            descText = "15% OFF & Free 15-Min Delivery";
            btnText = "Upgrade to Platinum";
            btnStyle = "-fx-background-color: linear-gradient(to right, #F59E0B, #FBBF24);" +
                       "-fx-text-fill: #221400;" +
                       "-fx-font-size: 10px;" +
                       "-fx-font-weight: bold;" +
                       "-fx-background-radius: 10;" +
                       "-fx-cursor: hand;";
        } else {
            // Free / Standard Tier (Matches exact initial UI)
            upgradeGradient = new LinearGradient(
                    0, 0, 1, 1, true,
                    CycleMethod.NO_CYCLE,
                    new Stop(0, Color.web("#25262A")),
                    new Stop(1, Color.web("#45474D"))
            );
            borderColor = "transparent";
            titleText = "Unlock Gold";
            descText = "Smarter deals & exclusive rewards";
            btnText = "Upgrade to Gold";
            btnStyle = "-fx-background-color: linear-gradient(to right, #FF6900, #FF9B5C);" +
                       "-fx-text-fill: white;" +
                       "-fx-font-size: 10px;" +
                       "-fx-font-weight: bold;" +
                       "-fx-background-radius: 10;" +
                       "-fx-cursor: hand;";
        }

        upgradeCard.setBackground(
                new Background(
                        new BackgroundFill(
                                upgradeGradient,
                                new CornerRadii(17),
                                Insets.EMPTY
                        )
                )
        );

        if (!"transparent".equals(borderColor)) {
            upgradeCard.setStyle(
                    "-fx-border-color: " + borderColor + ";" +
                    "-fx-border-width: 1.2;" +
                    "-fx-border-radius: 17;"
            );
        }

        Label upgradeTitle = new Label(titleText);
        upgradeTitle.setStyle(
                "-fx-font-size: 12px;" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: white;"
        );

        Label upgradeText = new Label(descText);
        upgradeText.setStyle(
                "-fx-font-size: 8px;" +
                "-fx-text-fill: #BEBFC3;"
        );

        Button upgradeGold = new Button(btnText);
        upgradeGold.setPrefWidth(175);
        upgradeGold.setPrefHeight(30);
        upgradeGold.setStyle(btnStyle);
        upgradeGold.setOnAction(e -> CustomerNavigation.navigateToPlans(userId));

        upgradeCard.getChildren().addAll(upgradeTitle, upgradeText, upgradeGold);
        return upgradeCard;
    }
}
