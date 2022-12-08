package deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import card.Card;
import logic.GameController;
import player.PlayerBase;

public class PremadeDeck {
	static Card C3 = new Card("3 Club", 3, 0, "img/3_C.jpg");
	static Card D3 = new Card("3 Diamond", 3, 1, "img/3_D.jpg");
	static Card H3 = new Card("3 Heart", 3, 2, "img/3_H.jpg");
	static Card S3 = new Card("3 Spade", 3, 3, "img/3_S.jpg");

	static Card C4 = new Card("4 Club", 4, 0, "img/4_C.jpg");
	static Card D4 = new Card("4 Diamond", 4, 1, "img/4_D.jpg");
	static Card H4 = new Card("4 Heart", 4, 2, "img/4_H.jpg");
	static Card S4 = new Card("4 Spade", 4, 3, "img/4_S.jpg");

	static Card C5 = new Card("5 Club", 5, 0, "img/5_C.jpg");
	static Card D5 = new Card("5 Diamond", 5, 1, "img/5_D.jpg");
	static Card H5 = new Card("5 Heart", 5, 2, "img/5_H.jpg");
	static Card S5 = new Card("5 Spade", 5, 3, "img/5_S.jpg");

	static Card C6 = new Card("6 Club", 6, 0, "img/6_C.jpg");
	static Card D6 = new Card("6 Diamond", 6, 1, "img/6_D.jpg");
	static Card H6 = new Card("6 Heart", 6, 2, "img/6_H.jpg");
	static Card S6 = new Card("6 Spade", 6, 3, "img/6_S.jpg");

	static Card C7 = new Card("7 Club", 7, 0, "img/7_C.jpg");
	static Card D7 = new Card("7 Diamond", 7, 1, "img/7_D.jpg");
	static Card H7 = new Card("7 Heart", 7, 2, "img/7_H.jpg");
	static Card S7 = new Card("7 Spade", 7, 3, "img/7_S.jpg");

	static Card C8 = new Card("8 Club", 8, 0, "img/8_C.jpg");
	static Card D8 = new Card("8 Diamond", 8, 1, "img/8_D.jpg");
	static Card H8 = new Card("8 Heart", 8, 2, "img/8_H.jpg");
	static Card S8 = new Card("8 Spade", 8, 3, "img/8_S.jpg");

	static Card C9 = new Card("9 Club", 9, 0, "img/9_C.jpg");
	static Card D9 = new Card("9 Diamond", 9, 1, "img/9_D.jpg");
	static Card H9 = new Card("9 Heart", 9, 2, "img/9_H.jpg");
	static Card S9 = new Card("9 Spade", 9, 3, "img/9_S.jpg");

	static Card C10 = new Card("10 Club", 10, 0, "img/10_C.jpg");
	static Card D10 = new Card("10 Diamond", 10, 1, "img/10_D.jpg");
	static Card H10 = new Card("10 Heart", 10, 2, "img/10_H.jpg");
	static Card S10 = new Card("10 Spade", 10, 3, "img/10_S.jpg");

	static Card CJ = new Card("Jack Club", 11, 0, "img/J_C.jpg");
	static Card DJ = new Card("Jack Diamond", 11, 1, "img/J_D.jpg");
	static Card HJ = new Card("Jack Heart", 11, 2, "img/J_H.jpg");
	static Card SJ = new Card("Jack Spade", 11, 3, "img/J_S.jpg");

	static Card CQ = new Card("Queen Club", 12, 0, "img/Q_C.jpg");
	static Card DQ = new Card("Queen Diamond", 12, 1, "img/Q_D.jpg");
	static Card HQ = new Card("Queen Heart", 12, 2, "img/Q_H.jpg");
	static Card SQ = new Card("Queen Spade", 12, 3, "img/Q_S.jpg");

	static Card CK = new Card("King Club", 13, 0, "img/K_C.jpg");
	static Card DK = new Card("King Diamond", 13, 1, "img/K_D.jpg");
	static Card HK = new Card("King Heart", 13, 2, "img/K_H.jpg");
	static Card SK = new Card("King Spade", 13, 3, "img/K_S.jpg");

	static Card CA = new Card("Ace Club", 14, 0, "img/A_C.jpg");
	static Card DA = new Card("Ace Diamond", 14, 1, "img/A_D.jpg");
	static Card HA = new Card("Ace Heart", 14, 2, "img/A_H.jpg");
	static Card SA = new Card("Ace Spade", 14, 3, "img/A_S.jpg");

	static Card C2 = new Card("2 Club", 15, 0, "img/2_C.jpg");
	static Card D2 = new Card("2 Diamond", 15, 1, "img/2_D.jpg");
	static Card H2 = new Card("2 Heart", 15, 2, "img/2_H.jpg");
	static Card S2 = new Card("2 Spade", 15, 3, "img/2_S.jpg");
	public static ArrayList<Card> hand1 = new ArrayList<Card>();
	public static ArrayList<Card> hand2 = new ArrayList<Card>();
	public static ArrayList<Card> hand3 = new ArrayList<Card>();
	public static ArrayList<Card> hand4 = new ArrayList<Card>();

	public static List<Card> initialCardDeck = new ArrayList<Card>(List.of(C3, D3, H3, S3, C4, D4, H4, S4, C5, D5, H5,
			S5, C6, D6, H6, S6, C7, D7, H7, S7, C8, D8, H8, S8, C9, D9, H9, S9, C10, D10, H10, S10, CJ, DJ, HJ, SJ, CQ,
			DQ, HQ, SQ, CK, DK, HK, SK, CA, DA, HA, SA, C2, D2, H2, S2));

	public static void setStartPlayer(int idx, PlayerBase player) {
		if (initialCardDeck.get(idx).compareTo(C3) == 0) {
			GameController.setCurrentPlayer(player);
		}
	}

	public static void shuffle() {
		Collections.shuffle(initialCardDeck);
	}

	public static void spread() {
		for (int idx = 0; idx < initialCardDeck.size(); idx++) {
			int queue = idx % 4;

			switch (queue) {
			case 0:
				hand1.add(initialCardDeck.get(idx));
				setStartPlayer(idx, GameController.getBot1());
				break;
			case 1:
				hand2.add(initialCardDeck.get(idx));
				setStartPlayer(idx, GameController.getBot2());
				break;
			case 2:
				hand3.add(initialCardDeck.get(idx));
				setStartPlayer(idx, GameController.getBot3());
				break;
			case 3:
				hand4.add(initialCardDeck.get(idx));
				setStartPlayer(idx, GameController.getPlayer());
				break;
			}

		}
		Collections.sort(hand1);
		Collections.sort(hand2);
		Collections.sort(hand3);
		Collections.sort(hand4);
	}

	public static void resetHand() {
		hand1 = new ArrayList<Card>();
		hand2 = new ArrayList<Card>();
		hand3 = new ArrayList<Card>();
		hand4 = new ArrayList<Card>();
	}

}
