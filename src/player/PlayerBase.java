package player;

import java.util.ArrayList;

import card.Card;

public abstract class PlayerBase {
	private boolean isPass = false;
	private boolean isWin = false;
	private String name;
	private ArrayList<Card> hand;

	public PlayerBase(String name, ArrayList<Card> hand) {
		this.name = name;
		this.hand = hand;

	}

	public String toString() {
		String spaceLine = "----------";
		String longSpaceLine = "";
		String cardLine = "";
		for (Card card : this.getHand()) {
			cardLine += card.getName() + "  ";
			longSpaceLine += spaceLine;
		}
		return this.getName() + "Play!!!!\n" + longSpaceLine + "\n" + cardLine + "\n" + longSpaceLine;
	}

	public abstract void play();

	public void removeCards(ArrayList<Card> cards) {
		for (Card card : cards) {
			for (int idx = 0; idx < this.getHand().size(); idx++) {
				if (this.getHand().get(idx).compareTo(card) == 0) {
					this.getHand().remove(idx);
				}
			}
		}

	}

	public boolean isHandEmpty() {
		return this.hand.isEmpty();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public boolean isPass() {
		return isPass;
	}

	public void setPass(boolean isPass) {
		this.isPass = isPass;
	}

	public boolean isWin() {
		return isWin;
	}

	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}

}
