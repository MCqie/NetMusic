package net.music.player;

import javafx.scene.media.MediaPlayer;
import net.music.Window;
import net.music.lyric.LyticItem;
import net.music.util.PlayUtil;
import net.music.util.WaveUtil;

public class PlayerListener {
	public static int index=0;
	public static LyticItem u=null;
	public PlayerListener(Player p) {
		MediaPlayer media = p.getMediaplayer();
		media.setOnPlaying(() -> {
			System.out.println("歌曲播放");
			
		});
		media.setOnPaused(() -> {
			System.out.println("歌曲暂停");
		});
		media.setOnReady(() -> {
			Window.progress.setValue(0);
			Window.progress.setMax(media.getTotalDuration().toSeconds());
			
			Window.player.setRate(Window.speedrate);
			System.out.println("歌曲准备好了");
			
			// p.play();
		});
		media.setOnStopped(() -> {
			System.out.println("歌曲停止");
		});
		media.setOnError(() -> {
			System.out.println("歌曲错误");
		});
		media.setOnEndOfMedia(() -> {
			System.out.println("歌曲播放完毕");
			PlayUtil.MusicOverChange();

		});
		media.setOnRepeat(() -> {
			System.out.println("歌曲重复");
		});
		media.currentTimeProperty().addListener((observable, oldValue, newValue) -> {
			double ScrollWidth =Window.scrolllyc.getContent().getBoundsInLocal().getHeight()
		-Window.scrolllyc.getViewportBounds().getHeight();
			double x= (Window.PlayingLyric.getLyrics().get(index).getL().getHeight()/ScrollWidth);
			if (!Window.Mousestatus) {
				Window.progress.setValue(newValue.toSeconds());
			}
if(index<Window.PlayingLyric.getLyrics().size()-1){
			if(newValue.toSeconds()>Window.PlayingLyric.getLyrics().get(index).getTime()){
				Window.PlayingLyric.getLyrics().get(index).ToRed();
				if(!(Window.PlayingLyric.getLyrics().get(index).getL().getText().equals("- - - - - - - - - -"))) {
					Window.scrolllyc.setVvalue(Window.scrolllyc.getVvalue() + x);
				}
				if(u!=null){
					u.ToBlack();
				}
				u=	Window.PlayingLyric.getLyrics().get(index);
				index++;
			}}
		});
		media.setAudioSpectrumListener((a,b,c,d)-> WaveUtil.updateWave(c));
	}

}
