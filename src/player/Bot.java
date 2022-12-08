package player;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import card.Card;
import logic.GameController;
import sound.PreSound;

public class Bot extends PlayerBase {
	private TreeMap<Integer, Integer> countValue = new TreeMap<Integer, Integer>();
	private ArrayList<Card> listCard = new ArrayList<Card>();

	public Bot(String name, ArrayList<Card> hand) {
		super(name, hand);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void play() {
		// TODO Auto-generated method stub
		listCard.clear();
		addListCard();
		if (listCard.isEmpty()) {
			PreSound.passingSound.play();
			System.out.println(this.getName() + "Pass!!!!");
			this.setPass(true);
		} else {
			PreSound.cardSound.play();
			GameController.setCardOnDesk(listCard);
			removeCards(listCard);
			System.out.println(this.toString());
			GameController.setRecentPlayer(this);
		}
		Thread thread = new Thread(() -> {
			GameController.update();
		});
		thread.start();

	}

	private void addListCard() {
		int deskSize = GameController.getCardOnDesk().size();
		switch (deskSize) {
		case 0:
			if (GameController.getRound() == 1) {
				for (int idx = 0; idx < countValue.get(3); idx++) {
					listCard.add(this.getHand().get(idx));
				}
				countValue.put(this.getHand().get(0).getValue(), 0);
			} else {
				for (Map.Entry<Integer, Integer> entry : countValue.entrySet()) {
					if (entry.getValue() == 1) {
						listCard = chooseCard(1);
						break;
					}
					if (entry.getValue() == 2) {
						listCard = chooseCard(2);
						break;
					}
				}
				if (listCard.isEmpty()) {
					listCard = chooseCard(3);
				}
				if (listCard.isEmpty()) {
					listCard = chooseCard(4);
				}
			}
			break;
		case 1:
			listCard = chooseCard(1);
			if (listCard.isEmpty()) {
				listCard = chooseCard(3);
			}
			break;
		case 2:
			listCard = chooseCard(2);
			if (listCard.isEmpty()) {
				listCard = chooseCard(4);
			}
			break;
		case 3:
			listCard = chooseCard(3);
			break;
		case 4:
			listCard = chooseCard(4);
			break;
		}
	}

	private ArrayList<Card> chooseCard(int numcard) {
		ArrayList<Card> cardChosen = new ArrayList<Card>();
		ArrayList<Card> botHand = this.getHand();
		int deskSize = GameController.getCardOnDesk().size();
		int numValue;
		switch (numcard) {
		case 1:
			for (int idx = 0; idx < botHand.size(); idx++) {
				numValue = botHand.get(idx).getValue();
				if (countValue.get(numValue) == 1) {
					if (deskSize == 0 || botHand.get(idx).compareTo(GameController.getCardOnDesk().get(0)) == 1) {
						cardChosen.add(botHand.get(idx));
						countValue.put(numValue, 0);
						return cardChosen;
					}
				}
			}
			return cardChosen;

		case 2:
			int idx = 0;
			while (idx < botHand.size()) {
				numValue = botHand.get(idx).getValue();
				if (countValue.get(numValue) == 2) {
					idx++;
					if (deskSize == 0 || botHand.get(idx).compareTo(GameController.getCardOnDesk().get(1)) == 1) {
						cardChosen.add(botHand.get(idx - 1));
						cardChosen.add(botHand.get(idx));
						countValue.put(numValue, 0);
						return cardChosen;
					}
				}
				idx++;
			}
			return cardChosen;

		case 3:
			for (int idx1 = 0; idx1 < botHand.size(); idx1++) {
				numValue = botHand.get(idx1).getValue();
				if (countValue.get(numValue) == 3) {
					if (deskSize <= 1 || botHand.get(idx1).compareTo(GameController.getCardOnDesk().get(0)) == 1) {
						cardChosen.add(botHand.get(idx1));
						cardChosen.add(botHand.get(idx1 + 1));
						cardChosen.add(botHand.get(idx1 + 2));
						countValue.put(numValue, 0);
						return cardChosen;
					}
				}
			}
			return cardChosen;

		case 4:
			for (int idx1 = 0; idx1 < botHand.size(); idx1++) {
				numValue = botHand.get(idx1).getValue();
				if (countValue.get(numValue) == 4) {
					if (deskSize <= 2 || botHand.get(idx1).compareTo(GameController.getCardOnDesk().get(0)) == 1) {
						cardChosen.add(botHand.get(idx1));
						cardChosen.add(botHand.get(idx1 + 1));
						cardChosen.add(botHand.get(idx1 + 2));
						cardChosen.add(botHand.get(idx1 + 3));
						countValue.put(numValue, 0);
						return cardChosen;
					}
				}
			}
			return cardChosen;

		}
		return cardChosen;
	}

	public void setHand(ArrayList<Card> hand) {
		super.setHand(hand);
		for (Card card : hand) {
			int count = countValue.containsKey(card.getValue()) ? countValue.get(card.getValue()) : 0;
			countValue.put(card.getValue(), count + 1);
		}
		// print map in bot's hand
		System.out.println("add" + this.getName());
		for (Map.Entry<Integer, Integer> entry : countValue.entrySet()) {
			System.out.print(entry.getKey() + ":" + entry.getValue() + "  ");
		}
		System.out.println("");
	}
}
