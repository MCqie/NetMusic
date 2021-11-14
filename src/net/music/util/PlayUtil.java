package net.music.util;

import java.io.IOException;
import java.util.Random;

import jdk.internal.org.objectweb.asm.tree.analysis.Analyzer;
import net.music.Window;
import net.music.lyric.Lyric;
import net.music.player.MediaImp;
import net.music.player.Player;
import net.music.player.PlayerListener;
import net.music.web.DataAnalysis;
import net.music.web.WebInterface;

public class PlayUtil {
	@SuppressWarnings("deprecation")
	public static void AChangemusic(MediaImp mediaimp)  {
		Window.medianame.setText("正在播放:"+mediaimp.getMusicName()+"     "+mediaimp.getArtist());
		Window.Using.getSelectionModel().select(Window.PlayingList.getIndex());
		//Window.wsonglist.getSelectionModel().select(Window.PlayingList.getIndex());
		if (Window.player != null) {
			Window.player.changeMusic(mediaimp);
		} else {
			Window.player = new Player(mediaimp);
		}

		//歌词
		try {
			Window.PlayingLyric= DataAnalysis.analysisLyric(WebInterface.GetInterfaceText("http://music.163.com/api/song/media?id="+mediaimp.getId()));
			PlayerListener.index=0;
			//歌词复位
			Window.scrolllyc.setVvalue(0.0);
		}catch (Exception ignored){
			ignored.printStackTrace();
		}
	}
	public static void MusicOverChange()  {
		if(Window.playstatus==PlayStatus.DEFAULT) {
			PlayUtil.AChangemusic(Window.PlayingList.getNextMedia().toMediaImp());
			Window.player.play();
			}else if(Window.playstatus==PlayStatus.RANDOM) {
				int index=new Random().nextInt(Window.PlayingList.getList().size()-1);
				Window.PlayingList.setIndex(index);
				PlayUtil.AChangemusic(Window.PlayingList.getList().get(index).toMediaImp());
				Window.player.play();
			}else{
				PlayUtil.AChangemusic(Window.PlayingList.getList().get(Window.PlayingList.getIndex()).toMediaImp());
				Window.player.play();
			}
	}
	public static void MusicUClick()  {
		if(Window.playstatus==PlayStatus.DEFAULT) {
			PlayUtil.AChangemusic(Window.PlayingList.getprevMedia().toMediaImp());
			Window.player.play();
			}else if(Window.playstatus==PlayStatus.RANDOM) {
				int index=new Random().nextInt(Window.PlayingList.getList().size()-1);
				Window.PlayingList.setIndex(index);
				PlayUtil.AChangemusic(Window.PlayingList.getList().get(index).toMediaImp());
				Window.player.play();
			}else{
				PlayUtil.AChangemusic(Window.PlayingList.getList().get(Window.PlayingList.getIndex()).toMediaImp());
				Window.player.play();
			}
	}
}
