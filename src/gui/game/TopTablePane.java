package gui.game;

import gui.menu.MenuPane;
import image.PreBackgroundImage;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.GameController;
import main.Main;
import sound.PreSound;

public class TopTablePane extends HBox {
	private Button mainMenuButton;
	private CharacterPane bot2Character = new CharacterPane(GameController.getBot2());
	private CharacterPane bot3Character = new CharacterPane(GameController.getBot3());

	public TopTablePane() {
		super();
		this.setPadding(new Insets(8));
		this.setSpacing(433);

		initMainMenuButton();
		initEventHandler();

		bot2Character.setBackgroundCharacter(PreBackgroundImage.bot2Image);
		bot3Character.setBackgroundCharacter(PreBackgroundImage.bot3Image);

		this.getChildren().add(bot2Character);
		this.getChildren().add(mainMenuButton);
		this.getChildren().add(bot3Character);
	}

	private void initMainMenuButton() {
		mainMenuButton = new Button("Main Menu");
		mainMenuButton.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 15));
		mainMenuButton.setAlignment(Pos.CENTER);
		mainMenuButton.setBackground(
				new Background(new BackgroundImage(PreBackgroundImage.menuButtonImage, null, null, null, null)));

	}

	private void initEventHandler() {
		mainMenuButton.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				ButtonType yesButton = new ButtonType("Yes");
				ButtonType noButton = new ButtonType("No");
				Alert alert = new Alert(Alert.AlertType.WARNING, "", yesButton, noButton);
				alert.setTitle("Warning");
				alert.setHeaderText("Are you sure?");
				alert.setContentText("The game won't be saved if you leave.");
				alert.showAndWait();

				if (alert.getResult().equals(yesButton)) {
					GameController.resetGame();

					alert.getButtonTypes().removeAll(yesButton, noButton);
					alert.setContentText("Please wait for a sec...");
					alert.setHeaderText(null);
					Thread thread = new Thread(() -> {
						try {
							// Wait for 3 secs
							Thread.sleep(3000);
							if (alert.isShowing()) {
								javafx.application.Platform.runLater(() -> alert.close());
								PreSound.backgroundGameSound.stop();
								PreSound.backgroundMenuSound.play();
							}
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
					thread.setDaemon(true);
					thread.start();
					alert.showAndWait();
					Main.gameController = new GameController();

					Main.menuPane = new MenuPane();
					Main.boardPane = new BoardPane();

					Main.mainMenu = new Scene(Main.menuPane);
					Main.gameMenu = new Scene(Main.boardPane);

					Main.mainStage.setScene(Main.mainMenu);

				}
			}
		});
	}

	public CharacterPane getBot2Character() {
		return bot2Character;
	}

	public void setBot2Character(CharacterPane bot2Character) {
		this.bot2Character = bot2Character;
	}

	public CharacterPane getBot3Character() {
		return bot3Character;
	}

	public void setBot3Character(CharacterPane bot3Character) {
		this.bot3Character = bot3Character;
	}

}
