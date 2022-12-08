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

public class LeftTable extends VBox {
	private CharacterPane bot1Character = new CharacterPane(GameController.getBot1());
	private Button passButton;

	public LeftTable() {
		super();
		this.setPadding(new Insets(8));
		this.setSpacing(100);
		this.setAlignment(Pos.BOTTOM_CENTER);

		initPassButton();
		bot1Character.setBackgroundCharacter(PreBackgroundImage.bot1Image);

		this.getChildren().add(passButton);
		this.getChildren().add(bot1Character);

	}

	private void initPassButton() {
		passButton = new Button("Pass");
		passButton.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 25));
		passButton.setTextFill(Color.WHITE);
		passButton.setAlignment(Pos.CENTER);
		passButton.setBackground(
				new Background(new BackgroundImage(PreBackgroundImage.passButtonImage, null, null, null, null)));

		passButton.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				if (GameController.getCurrentPlayer().equals(GameController.getPlayer())) {
					if (!GameController.isResettingDesk()) {
						GameController.getPlayer().pass();
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

	public CharacterPane getBot1Character() {
		return bot1Character;
	}

	public void setBot1Character(CharacterPane bot1Character) {
		this.bot1Character = bot1Character;
	}

}
