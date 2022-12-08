package card;

public class Card implements Comparable<Card> {
	private String name;
	private int value;
	private int suit;
	private String url;

	public Card(String name, int value, int suit, String url) {
		super();
		this.name = name;
		this.value = value;
		this.suit = suit;
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public int getSuit() {
		return suit;
	}

	public void setSuit(int suit) {
		this.suit = suit;
	}

	@Override
	public int compareTo(Card o) {
		// TODO Auto-generated method stub
		int result = 0;
		if (getValue() == o.getValue()) {
			if (getSuit() > o.getSuit()) {
				result = 1;
			}
			if (getSuit() < o.getSuit()) {
				result = -1;
			}
		} else {
			if (getValue() > o.getValue()) {
				result = 1;
			}
			if (getValue() < o.getValue()) {
				result = -1;
			}
		}

		return result;
	}

}
