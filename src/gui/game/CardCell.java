package gui.game;

import card.Card;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class CardCell extends Pane {
	protected Card card;

	public CardCell(Card card) {
		this.card = card;
		this.prefHeight(115);
		this.prefWidth(82);
		this.setMinWidth(82);
		this.setMinHeight(115);
		this.setPadding(new Insets(8));
		this.setVisible(true);
		
		
		removeHighlightBoarderCard();
		setBackgroundCard();
	}
	
	private void setBackgroundCard() {
		BackgroundFill bgFill = new BackgroundFill(Color.MOCCASIN, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill[] bgFillA = { bgFill };
		BackgroundSize bgSize = new BackgroundSize(82, 115, false, false, false, false);
		BackgroundImage bgImg = new BackgroundImage(new Image(this.card.getUrl()), null, null, null, bgSize);
		BackgroundImage[] bgImgA = { bgImg };
		this.setBackground(new Background(bgFillA, bgImgA));
	}

	protected void removeHighlightBoarderCard() {
		this.setBorder(new Border(
				new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
	}

	protected void setHighlightBoarderCard() {
		this.setBorder(new Border(
				new BorderStroke(Color.DEEPSKYBLUE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));

	}

}
