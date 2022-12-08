package player;

import java.util.ArrayList;
import java.util.Collections;

import card.Card;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logic.GameController;
import sound.PreSound;

public class Player extends PlayerBase {
	private ArrayList<Card> currentChoose = new ArrayList<Card>();

	public Player(String name, ArrayList<Card> hand) {
		super(name, hand);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		if (isPlayable()) {
			ArrayList<Card> newDesk = (ArrayList<Card>) currentChoose.clone();
			PreSound.cardSound.play();
			GameController.setCardOnDesk(newDesk);
			removeCards(currentChoose);
			System.out.println(this.toString());
			currentChoose.clear();
			GameController.setRecentPlayer(this);

			Thread thread = new Thread(() -> {
				GameController.update();
			});
			thread.start();

		} else {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setHeaderText(null);
					alert.setTitle("Wrong Cards");
					alert.setContentText("Choose the correct cards or pass.");
					alert.showAndWait();
				}
			});

		}
	}

	public void pass() {
		if (GameController.getCardOnDesk().isEmpty()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText(null);
			alert.setTitle("Error");
			alert.setContentText("You need to play some cards.");
			alert.showAndWait();
		} else {
			Thread thread = new Thread(() -> {
				PreSound.passingSound.play();
				setPass(true);
				currentChoose.clear();
				System.out.println("PlayerPass!");
				GameController.update();
			});
			thread.start();

		}

	}

	public boolean isCurrentChoose(Card c) {
		for (int index = 0; index < currentChoose.size(); index++) {
			if (currentChoose.get(index).compareTo(c) == 0) {
				return true;
			}
		}
		return false;
	}

	private boolean isPlayable() {
		int deskSize = GameController.getCardOnDesk().size();

		if (currentChoose.size() == 0) {
			return false;
		}

		int value = currentChoose.get(0).getValue();

		for (Card c : currentChoose) {
			if (c.getValue() != value) {
				return false;
			}
		}
		Collections.sort(currentChoose);

		switch (deskSize) {
		case 0:
			if (GameController.getRound() != 1) {
				return true;
			}

			else if (currentChoose.get(0).compareTo(new Card("3 Club", 3, 0, "img/3_C.jpg")) == 0) {
				return true;
			}

			return false;

		case 1:
			if (currentChoose.size() == 3) {
				return true;
			}

			else if (currentChoose.size() == 1) {
				if (currentChoose.get(0).compareTo(GameController.getCardOnDesk().get(0)) == 1) {
					return true;
				}
			}

			return false;

		case 2:
			if (currentChoose.size() == 4) {
				return true;
			}

			else if (currentChoose.size() == 2) {
				if (currentChoose.get(1).compareTo(GameController.getCardOnDesk().get(1)) == 1) {
					return true;
				}
			}

			return false;

		case 3:
			if (currentChoose.size() != 3) {
				return false;
			}

			else if (currentChoose.get(0).compareTo(GameController.getCardOnDesk().get(0)) == 1) {
				return true;
			}

			return false;
		case 4:
			if (currentChoose.size() != 4) {
				return false;
			}

			else if (currentChoose.get(0).compareTo(GameController.getCardOnDesk().get(0)) == 1) {
				return true;
			}

			return false;
		}
		return false;

	}

	public ArrayList<Card> getCurrentChoose() {
		return currentChoose;
	}

	public void setCurrentChoose(ArrayList<Card> currentChoose) {
		this.currentChoose = currentChoose;
	}

}
