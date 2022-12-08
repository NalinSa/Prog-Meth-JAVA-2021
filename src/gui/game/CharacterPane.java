package gui.game;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import logic.GameController;
import player.PlayerBase;

public class CharacterPane extends VBox implements Updatable {
	private Label numCardCharacter;
	private PlayerBase character;
	private Label statusText;

	public CharacterPane(PlayerBase character) {
		super();
		this.character = character;
		this.setPrefHeight(150);
		this.setPrefWidth(150);
		this.setMinHeight(150);
		this.setMinWidth(150);
		this.setPadding(new Insets(8));
		this.setSpacing(30);
		this.setAlignment(Pos.TOP_CENTER);
		this.setBorder(null);
		this.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));

		initNumCardCharacter();
		initStatusText();
		updateUI();

		this.getChildren().add(numCardCharacter);
		this.getChildren().add(statusText);

	}

	private void setBorderHighlight(Color color) {
		this.setBorder(new Border(new BorderStroke(color, BorderStrokeStyle.SOLID, null, new BorderWidths(7))));
	}

	private void initNumCardCharacter() {
		numCardCharacter = new Label();
		numCardCharacter.setTextFill(Color.BLACK);
		numCardCharacter.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
		numCardCharacter.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 15));
		numCardCharacter.setAlignment(Pos.CENTER);
	}

	private void initStatusText() {
		statusText = new Label("PASS");
		statusText.setTextFill(Color.RED);
		statusText.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 25));
		statusText.setAlignment(Pos.CENTER);
		statusText.setVisible(false);
	}

	public void setBackgroundCharacter(Image image) {
		// set background image
		BackgroundFill bgFill = new BackgroundFill(Color.BEIGE, CornerRadii.EMPTY, Insets.EMPTY);
		BackgroundFill[] bgFillA = { bgFill };
		BackgroundSize bgSize = new BackgroundSize(150, 150, false, false, false, false);
		BackgroundImage bgImg = new BackgroundImage(image, null, null, null, bgSize);
		BackgroundImage[] bgImgA = { bgImg };
		this.setBackground(new Background(bgFillA, bgImgA));

	}

	@Override
	public void updateUI() {
		// Update number of cards show in CharacterPane
		numCardCharacter.setText(" CARDS : " + character.getHand().size() + " ");

		if (character.isWin()) {
			setBorderHighlight(Color.CORNFLOWERBLUE);
			statusText.setText("WIN");
			statusText.setTextFill(Color.CORNFLOWERBLUE);
			statusText.setVisible(true);
		} else if (GameController.getCurrentPlayer().equals(character)) {
			statusText.setVisible(false);
			setBorderHighlight(Color.LAWNGREEN);

		} else if (character.isPass()) {
			setBorderHighlight(Color.RED);
			statusText.setVisible(true);

		} else {
			setBorderHighlight(Color.BEIGE);
			statusText.setVisible(false);
		}

	}

	public PlayerBase getCharacter() {
		return character;
	}

	public void setCharacter(PlayerBase character) {
		this.character = character;
	}

}
