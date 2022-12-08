package logic;

import java.util.ArrayList;
import java.util.Collections;
import card.Card;
import deck.PremadeDeck;
import gui.game.BoardPane;
import gui.game.Updatable;
import gui.menu.MenuPane;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import main.Main;
import player.Bot;
import player.Player;
import player.PlayerBase;
import sound.PreSound;

public class GameController {
	private static ArrayList<Card> cardOnDesk;
	private static ArrayList<PlayerBase> playersInGame;
	private static ArrayList<PlayerBase> winners;
	private static PlayerBase currentPlayer;
	private static PlayerBase recentPlayer;
	private static int round;
	private static Bot bot1;
	private static Bot bot2;
	private static Bot bot3;
	private static Player player;
	private static boolean isGameEnd;
	private static boolean isResettingDesk = false;
	private static boolean isInGame = false;

	public GameController() {
		System.out.println("Start Game");
		bot1 = new Bot("Bot1", null);
		bot2 = new Bot("Bot2", null);
		bot3 = new Bot("Bot3", null);
		player = new Player("Player", null);
		
		PremadeDeck.shuffle();
		PremadeDeck.spread();
		System.out.println(currentPlayer.getName() + " is the first player");
		
		GameController.cardOnDesk = new ArrayList<Card>();
		GameController.winners = new ArrayList<PlayerBase>();
		GameController.playersInGame = new ArrayList<PlayerBase>();
		
		bot1.setHand(PremadeDeck.hand1);
		bot2.setHand(PremadeDeck.hand2);
		bot3.setHand(PremadeDeck.hand3);
		player.setHand(PremadeDeck.hand4);
		GameController.playersInGame.add(bot1);
		GameController.playersInGame.add(bot2);
		GameController.playersInGame.add(bot3);
		GameController.playersInGame.add(player);
		GameController.isGameEnd = false;
		GameController.round = 1;
		
	}

	public static void resetGame() {
		System.out.println("Back to Menu");
		GameController.isInGame = false;
		PremadeDeck.resetHand();
	}

	public static void startTurn() {
		System.out.println("Call StartTurn");
		if (currentPlayer instanceof Bot) {
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (GameController.isInGame()) {
				currentPlayer.play();
			}
		}
		else {
			PreSound.playerSound.setVolume(0.1);
			PreSound.playerSound.play();
		}
	}

	public static void update() {
		// TODO Auto-generated method stub

		if (GameController.isInGame()) {
			System.out.println("Updating...");
			if (currentPlayer.isHandEmpty()) {
				winners.add(currentPlayer);
				currentPlayer.setWin(true);
				if (winners.size() != 3) {
					PreSound.winnerSound.play();
				}
			}
			if (winners.size() == 3) {
				PreSound.backgroundGameSound.stop();
				PreSound.gameEndSound.play();
				isGameEnd = true;
				updateAllUI();

			} else {
				findNextPlayer();
				while (currentPlayer.isPass() || currentPlayer.isWin()) {
					findNextPlayer();
				}
				updateAllUI();

				Thread thread = new Thread(() -> {
					GameController.startTurn();
				});
				thread.start();
			}
		}
	}

	public static void findNextPlayer() {
		System.out.println("Find next player");
		for (int idx = 0; idx < playersInGame.size(); idx++) {
			if (playersInGame.get(idx).equals(currentPlayer)) {
				currentPlayer = playersInGame.get((idx + 1) % playersInGame.size());
				break;
			}
		}
		if (currentPlayer.equals(recentPlayer)) {
			updateAllUI();
			try {
				Thread.sleep(1000);
				PreSound.resetingDeskSound.play();
				GameController.isResettingDesk = true;
				Main.boardPane.getDeskPane().setVisibleResetTableText(true);
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Main.boardPane.getDeskPane().setVisibleResetTableText(false);
			GameController.isResettingDesk = false;
			if (GameController.isInGame()) {
				resetDesk();
				round++;
				System.out.println("Round : " + GameController.getRound());
			}
		}
	}

	public static void updateAllUI() {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				for (Updatable u : Main.boardPane.getUpdatablePane()) {
					u.updateUI();
				}

				if (GameController.isGameEnd()) {
					showEndGame();				}
			}
		});
	}
	private static void showEndGame() {
		ButtonType menuButton = new ButtonType("Back to Menu");
		ButtonType exitButton = new ButtonType("Exit");
		Alert alert = new Alert(Alert.AlertType.INFORMATION, "", menuButton, exitButton);
		alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
		alert.setTitle("Game end!!!");
		alert.setHeaderText("We found the winners!!!");
		String winnersText = "";
		for (int idx = 1; idx < 4; idx++) {
			winnersText += Integer.toString(idx) + " : "
					+ GameController.getWinners().get(idx - 1).getName() + "\n";
		}
		alert.setContentText(winnersText);
		alert.showAndWait();

		if (alert.getResult().equals(menuButton)) {
			GameController.resetGame();

			PreSound.backgroundMenuSound.play();
			Main.gameController = new GameController();

			Main.menuPane = new MenuPane();
			Main.boardPane = new BoardPane();

			Main.mainMenu = new Scene(Main.menuPane);
			Main.gameMenu = new Scene(Main.boardPane);

			Main.mainStage.setScene(Main.mainMenu);

		} else if (alert.getResult().equals(exitButton)) {
			Main.mainStage.close();
		}
	}
	public static void resetDesk() {
		// TODO Auto-generated method stub
		System.out.println("reset desk");
		cardOnDesk.clear();
		for (PlayerBase player : playersInGame) {
			player.setPass(false);
		}
	}

	public static ArrayList<PlayerBase> getPlayersInGame() {
		return playersInGame;
	}

	public static void setPlayersInGame(ArrayList<PlayerBase> playersInGame) {
		GameController.playersInGame = playersInGame;
	}

	public static PlayerBase getCurrentPlayer() {
		return currentPlayer;
	}

	public static void setCurrentPlayer(PlayerBase currentPlayer) {
		GameController.currentPlayer = currentPlayer;
	}

	public static ArrayList<Card> getCardOnDesk() {
		return cardOnDesk;
	}

	public static void setCardOnDesk(ArrayList<Card> cardOnDesk) {
		GameController.cardOnDesk = cardOnDesk;
		Collections.sort(cardOnDesk);
	}

	public static int getRound() {
		return round;
	}

	public static void setRound(int round) {
		GameController.round = round;
	}

	public static PlayerBase getRecentPlayer() {
		return recentPlayer;
	}

	public static void setRecentPlayer(PlayerBase recentPlayer) {
		GameController.recentPlayer = recentPlayer;
	}

	public static ArrayList<PlayerBase> getWinners() {
		return winners;
	}

	public static void setWinners(ArrayList<PlayerBase> winners) {
		GameController.winners = winners;
	}

	public static boolean isGameEnd() {
		return isGameEnd;
	}

	public static void setGameEnd(boolean isGameEnd) {
		GameController.isGameEnd = isGameEnd;
	}

	public static Bot getBot1() {
		return bot1;
	}

	public static void setBot1(Bot bot1) {
		GameController.bot1 = bot1;
	}

	public static Bot getBot2() {
		return bot2;
	}

	public static void setBot2(Bot bot2) {
		GameController.bot2 = bot2;
	}

	public static Bot getBot3() {
		return bot3;
	}

	public static void setBot3(Bot bot3) {
		GameController.bot3 = bot3;
	}

	public static Player getPlayer() {
		return player;
	}

	public static void setPlayer(Player player) {
		GameController.player = player;
	}

	public static boolean isResettingDesk() {
		return isResettingDesk;
	}

	public static void setResettingDesk(boolean isResettingDesk) {
		GameController.isResettingDesk = isResettingDesk;
	}

	public static boolean isInGame() {
		return isInGame;
	}

	public static void setInGame(boolean isInGame) {
		GameController.isInGame = isInGame;
	}

}
