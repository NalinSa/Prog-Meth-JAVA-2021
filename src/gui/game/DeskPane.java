package gui.game;

import card.Card;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.GameController;

public class DeskPane extends HBox implements Updatable {
	private Label resetTableText;

	public DeskPane() {
		super();
		this.setPadding(new Insets(80));
		this.setAlignment(Pos.CENTER);
		this.setMaxHeight(100);

		initResetTableText();
	}

	private void initResetTableText() {
		resetTableText = new Label("Resetting the Table...");
		resetTableText.setTextFill(Color.ALICEBLUE);
		resetTableText.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 50));
		resetTableText.setAlignment(Pos.CENTER);
		resetTableText.setVisible(false);
	}

	@Override
	public void updateUI() {
		// Update card on Desk table
		this.getChildren().clear();
		for (Card card : GameController.getCardOnDesk()) {
			this.getChildren().add(new CardCell(card));
		}
	}

	public void setVisibleResetTableText(boolean visible) {
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				getChildren().clear();
				getChildren().add(resetTableText);
				resetTableText.setVisible(visible);
			}
		});

	}
}
