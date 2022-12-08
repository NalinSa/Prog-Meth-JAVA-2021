package gui.game;

import card.Card;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import logic.GameController;

public class PlayerCardCell extends CardCell {

	public PlayerCardCell(Card card) {
		super(card);

		this.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				if (GameController.getCurrentPlayer().equals(GameController.getPlayer())) {
					if (!GameController.isResettingDesk()) {
						onClickHandler();
					}
				}

			}
		});
	}

	private void onClickHandler() {
		if (GameController.getPlayer().isCurrentChoose(card)) {
			GameController.getPlayer().getCurrentChoose().remove(card);
			removeHighlightBoarderCard();
		} else {
			GameController.getPlayer().getCurrentChoose().add(card);
			setHighlightBoarderCard();
		}
	}

}
