package main;

import gui.game.BoardPane;
import gui.menu.MenuPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.GameController;
import sound.PreSound;
import javafx.scene.media.AudioClip;

public class Main extends Application {
	public static Stage mainStage;
	public static MenuPane menuPane;
	public static BoardPane boardPane;
	public static Scene mainMenu, gameMenu;
	public static GameController gameController;

	@Override
	public void start(Stage stage) {
		// TODO Auto-generated method stub
		PreSound.setCycleAndVolume(PreSound.backgroundMenuSound, AudioClip.INDEFINITE, 0.05);
		PreSound.backgroundMenuSound.play();
		Main.mainStage = stage;

		gameController = new GameController();
		menuPane = new MenuPane();
		boardPane = new BoardPane();

		Main.mainMenu = new Scene(menuPane);
		Main.gameMenu = new Scene(boardPane);

		Main.mainStage.setScene(mainMenu);
		Main.mainStage.setTitle("Poor President Card Game");
		Main.mainStage.setResizable(false);
		Main.mainStage.show();

	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
