package sound;

import javafx.scene.media.AudioClip;

public class PreSound {
	public static AudioClip cardSound = new AudioClip(
			ClassLoader.getSystemResource("sound/CardSound.wav").toString());
	public static AudioClip passingSound = new AudioClip(ClassLoader.getSystemResource("sound/PassingSound.wav").toString());
	public static AudioClip resetingDeskSound = new AudioClip(
			ClassLoader.getSystemResource("sound/ResetingDeskSound.wav").toString());
	public static AudioClip winnerSound = new AudioClip(
			ClassLoader.getSystemResource("sound/WinnerSound.wav").toString());
	public static AudioClip backgroundMenuSound = new AudioClip(
			ClassLoader.getSystemResource("sound/BackgroundMenuSound.wav").toString());
	public static AudioClip gameEndSound = new AudioClip(
			ClassLoader.getSystemResource("sound/GameEndSound.wav").toString());
	public static AudioClip backgroundGameSound = new AudioClip(
			ClassLoader.getSystemResource("sound/BackgroundGameSound.wav").toString());
	public static AudioClip playerSound = new AudioClip(
			ClassLoader.getSystemResource("sound/PlayerSound.wav").toString());

	public static void setCycleAndVolume(AudioClip sound, int cycle, double volume) {
		sound.setCycleCount(cycle);
		sound.setVolume(volume);
	}
}
