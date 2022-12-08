package gui.game;

import image.PreBackgroundImage;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.GameController;

public class RightTable extends VBox {
	private CharacterPane playerCharacter = new CharacterPane(GameController.getPlayer());
	private Button playButton;

	public RightTable() {
		super();
		this.setPadding(new Insets(8));
		this.setSpacing(100);
		this.setAlignment(Pos.BOTTOM_CENTER);

		initPlayButton();
		playerCharacter.setBackgroundCharacter(PreBackgroundImage.playerImage);

		this.getChildren().add(playButton);
		this.getChildren().add(playerCharacter);

	}

	private void initPlayButton() {
		playButton = new Button("Play");
		playButton.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 25));
		playButton.setTextFill(Color.WHITE);
		playButton.setAlignment(Pos.CENTER);
		playButton.setBackground(
				new Background(new BackgroundImage(PreBackgroundImage.playButtonImage, null, null, null, null)));

		playButton.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				if (GameController.getCurrentPlayer().equals(GameController.getPlayer())) {
					if (!GameController.isResettingDesk()) {
						GameController.getPlayer().play();
					} else {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setTitle("Error");
						alert.setHeaderText("Resetting the table");
						alert.setContentText("Please wait...");
						alert.showAndWait();
					}
				} else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Wait for your turn");
					alert.setHeaderText(null);
					alert.setContentText("It's not your turn.");
					alert.showAndWait();
				}
			}
		});

	}

	public CharacterPane getPlayerCharacter() {
		return playerCharacter;
	}

	public void setPlayerCharacter(CharacterPane playerCharacter) {
		this.playerCharacter = playerCharacter;
	}

}
