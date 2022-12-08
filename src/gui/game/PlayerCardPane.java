package gui.game;

import image.PreBackgroundImage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import logic.GameController;

public class PlayerCardPane extends HBox implements Updatable {

	public PlayerCardPane() {
		// TODO Auto-generated constructor stub
		this.setAlignment(Pos.CENTER_LEFT);
		this.setPadding(new Insets(8));
		this.setPrefHeight(150);
		this.setPrefWidth(1280);
		this.setSpacing(15);
		this.setBorder(new Border(
				new BorderStroke(Color.GRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		this.setBackground(new Background(
				new BackgroundImage(PreBackgroundImage.playerCardPaneBackgroundImage, null, null, null, null)));
		updateUI();
	}

	@Override
	public void updateUI() {
		// update cards show in PlayerCardPane
		this.getChildren().clear();
		for (int idx = 0; idx < GameController.getPlayer().getHand().size(); idx++) {
			this.getChildren().add(new PlayerCardCell(GameController.getPlayer().getHand().get(idx)));
		}

	}
}
