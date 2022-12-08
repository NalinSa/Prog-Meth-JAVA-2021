package gui.menu;

import image.PreBackgroundImage;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import logic.GameController;
import main.Main;
import sound.PreSound;

public class MenuPane extends VBox {
	private Button startButton;
	private Button ruleButton;
	private Button exitButton;
	private HBox exitPane;

	public MenuPane() {
		super();

		this.setPrefHeight(720);
		this.setPrefWidth(1280);
		this.setAlignment(Pos.BOTTOM_CENTER);
		this.setPadding(new Insets(10));
		this.setSpacing(35);
		this.setBackground(new Background(
				new BackgroundImage(PreBackgroundImage.mainMenuBackgroundImage, null, null, null, null)));

		initStartButton();
		initRuleButton();
		initExitButton();
		initExitPane();

		MenuPane.setMargin(exitPane, new Insets(75));
		this.getChildren().addAll(startButton, ruleButton, exitPane);

	}

	private void initStartButton() {
		startButton = new Button("Start Game!!!");
		startButton.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 80));
		startButton.setAlignment(Pos.CENTER);
		startButton.setBackground(
				new Background(new BackgroundImage(PreBackgroundImage.startButtonImage, null, null, null, null)));

		startButton.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				PreSound.backgroundMenuSound.stop();
				PreSound.setCycleAndVolume(PreSound.backgroundGameSound, AudioClip.INDEFINITE, 0.5);
				PreSound.backgroundGameSound.play();
				Main.mainStage.setScene(Main.gameMenu);
				GameController.setInGame(true);
				System.out.println("Round : " + GameController.getRound());
				Thread thread = new Thread(() -> {
					GameController.startTurn();
				});
				thread.start();

			}
		});
	}

	private void initRuleButton() {
		ruleButton = new Button("Rules");
		ruleButton.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 40));
		ruleButton.setAlignment(Pos.CENTER);
		ruleButton.setBackground(
				new Background(new BackgroundImage(PreBackgroundImage.ruleButtonImage, null, null, null, null)));

		ruleButton.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				onClickRuleHandler();

			}
		});
	}

	private void onClickRuleHandler() {
		Text rules = new Text("CARD POINTS\n" + "-----------------\n"
				+ "card points are divided into 2 parts depending on\n" + "	1. The card value\n"
				+ "The card values are ranked high to low in this order:\n"
				+ "2 > A > K > Q > J > 10 > 9 > 8 > 7 > 6 > 5 > 4 > 3\n" + "	2. The card suit \r\n"
				+ "The four suits are ranked high to low in this order:\n"
				+ "the suit of spades(♠), the suit of hearts(♥), the suit of diamonds(♦), and the suit of clubs(♣).\n"
				+ "\n" + "Cards are to be ranked first from higher to lower value, then shall the suit be considered.\n"
				+ "\n" + "\r\n" + "HOW TO PLAY\n" + "------------------\n"
				+ " • 3 cards that are of the same value can beat a card\n"
				+ " • 4 cards that are of the same value can beat 2 cards\n"
				+ " • If player A wishes to play a similar number of cards as player B (the previous player),\n"
				+ "player A can do so by choosing to play\n" + "a.) a card with superior value to player B’s card\n"
				+ "b.) a card equal in value of player B’s card, though from a superior card suit\r\n" + "\r\n" + "\r\n"
				+ "HOW TO PLAY THE CARDS\r\n" + "---------------------------------\n"
				+ "Card play in the game of President is sorted into 2 categories which are\n"
				+ "a.) Odd cards (playing a single or three cards)\n" + "b.) Even cards (playing two or four cards)\r\n"
				+ "**When a category of cards is chosen for the game, players have to play with the chosen\n"
				+ "category until the table is cleared (all players pass) before starting a new round.**\r\n" + "\r\n"
				+ "The first player of the round determines the category of cards to be played in that round.\n"
				+ "The card play is formatted as such:\r\n" + "\r\n"
				+ " • Playing a single card: This is when a player plays one card. The next player is to play\n"
				+ "a single card from a superior suit or play three cards with the same value.\r\n"
				+ " • Playing two cards: This is when a player plays two cards. The next player is to play\n"
				+ "two cards from a superior suit or play four cards with the same value.\r\n"
				+ " • Playing three cards: This is when a player plays three cards. The next player is to play\n"
				+ "only three cards from a superior value.\r\n"
				+ " • Playing four cards: This is when a player plays four cards. The next player is to play\n"
				+ "only four cards from a superior value.\r\n" + "\r\n" + "\r\n" + "HOW TO PLAY POORPRESIDENT GAME\r\n"
				+ "-----------------------------------------\n"
				+ "	1. Distribute a deck of cards to 4 players. Each player will receive 13 cards.\r\n"
				+ "	2. The player with 3 clubs starts the game by putting the 3 clubs into the desk\n"
				+ "(the player can start the game with a single card, two, three, or four cards–but\n"
				+ "the 3 clubs must be included in the play).\n" + "	3. Players take turns playing clockwise.\r\n"
				+ "	4. Players are to play the cards with superior value than the cards on the desk.\r\n"
				+ "	5. Players with no card to play or players who wish not to discard their cards in \n"
				+ "that round must make a pass, and can play again after the desk table is cleared for \n"
				+ "another round.\r\n"
				+ "	6. A round ends when players do not have cards that are superior to the cards on\n"
				+ "the desk. Take the cards from the desk desk. The last player to play starts\n"
				+ "the round. The game continues as such until players are left with their hands empty.\r\n"
				+ "	7. The game ends when 3 players are left with their hands empty. \r\n" + "");
		rules.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 20));
		FlowPane flowPane = new FlowPane(rules);
		ScrollPane scrollPane = new ScrollPane();
		flowPane.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
		scrollPane.setContent(flowPane);
		scrollPane.setFitToWidth(true);
		scrollPane.setPrefWidth(900);
		scrollPane.setPrefHeight(380);
		scrollPane.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
		ButtonType exitButton = new ButtonType("Exit");
		Alert alert = new Alert(AlertType.NONE, "", exitButton);
		alert.setTitle("Rules");
		alert.getDialogPane().setContent(scrollPane);
		alert.getDialogPane().setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
		alert.showAndWait();

	}

	private void initExitButton() {
		exitButton = new Button("Exit");
		exitButton.setFont(Font.font("Comic Sans MS", FontWeight.NORMAL, 20));
		exitButton.setAlignment(Pos.BOTTOM_RIGHT);
		exitButton.setBackground(
				new Background(new BackgroundImage(PreBackgroundImage.exitButtonImage, null, null, null, null)));
		exitButton.setTextFill(Color.WHITE);
		exitButton.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				// TODO Auto-generated method stub
				Main.mainStage.close();

			}
		});
	}

	private void initExitPane() {
		exitPane = new HBox();
		exitPane.getChildren().add(exitButton);
		exitPane.setAlignment(Pos.BOTTOM_RIGHT);
	}
}
