package gui.game;

import java.util.ArrayList;

import image.PreBackgroundImage;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BorderPane;

public class BoardPane extends BorderPane {
	private TopTablePane topTablePane;
	private LeftTable leftTablePane;
	private RightTable rightTablePane;
	private DeskPane deskPane;
	private PlayerCardPane playerCardPane;
	private ArrayList<Updatable> updatablePane;

	public BoardPane() {
		super();
		this.setPrefHeight(720);
		this.setPrefWidth(1280);

		topTablePane = new TopTablePane();
		leftTablePane = new LeftTable();
		rightTablePane = new RightTable();
		deskPane = new DeskPane();
		playerCardPane = new PlayerCardPane();

		topTablePane.setPrefHeight(150);
		leftTablePane.setPrefWidth(150);
		rightTablePane.setPrefWidth(150);
		deskPane.setMinWidth(250);
		playerCardPane.setPrefHeight(130);

		this.setTop(topTablePane);
		this.setLeft(leftTablePane);
		this.setRight(rightTablePane);
		this.setCenter(deskPane);
		this.setBottom(playerCardPane);

		initUpdatablePane();

		BorderPane.setAlignment(topTablePane, Pos.CENTER);
		BorderPane.setAlignment(leftTablePane, Pos.BOTTOM_LEFT);
		BorderPane.setAlignment(rightTablePane, Pos.CENTER);
		BorderPane.setAlignment(deskPane, Pos.TOP_CENTER);
		BorderPane.setAlignment(playerCardPane, Pos.CENTER);

		this.setBackground(
				new Background(new BackgroundImage(PreBackgroundImage.tableBackgroundImage, null, null, null, null)));
	}
	
	private void initUpdatablePane() {
		updatablePane = new ArrayList<Updatable>();
		updatablePane.add(deskPane);
		updatablePane.add(playerCardPane);
		updatablePane.add(topTablePane.getBot2Character());
		updatablePane.add(topTablePane.getBot3Character());
		updatablePane.add(leftTablePane.getBot1Character());
		updatablePane.add(rightTablePane.getPlayerCharacter());
	}

	public ArrayList<Updatable> getUpdatablePane() {
		return updatablePane;
	}

	public void setUpdatablePane(ArrayList<Updatable> updatablePane) {
		this.updatablePane = updatablePane;
	}

	public TopTablePane getTopTablePane() {
		return topTablePane;
	}

	public void setTopTablePane(TopTablePane topTablePane) {
		this.topTablePane = topTablePane;
	}

	public LeftTable getLeftTablePane() {
		return leftTablePane;
	}

	public void setLeftTablePane(LeftTable leftTablePane) {
		this.leftTablePane = leftTablePane;
	}

	public RightTable getRightTablePane() {
		return rightTablePane;
	}

	public void setRightTablePane(RightTable rightTablePane) {
		this.rightTablePane = rightTablePane;
	}

	public DeskPane getDeskPane() {
		return deskPane;
	}

	public void setDeskPane(DeskPane deskPane) {
		this.deskPane = deskPane;
	}

	public PlayerCardPane getPlayerCardPane() {
		return playerCardPane;
	}

	public void setPlayerCardPane(PlayerCardPane playerCardPane) {
		this.playerCardPane = playerCardPane;
	}

}
