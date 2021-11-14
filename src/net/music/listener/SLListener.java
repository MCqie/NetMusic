package net.music.listener;

import java.io.IOException;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import net.music.Window;
import net.music.Dao.MusicDao;
import net.music.Dao.SongList;
import net.music.player.MediaImp;
import net.music.util.PlayUtil;
import net.music.util.SongListUtil;

public class SLListener {
	public static ListView<MusicDao> listview;
	public static ListView<String> musiclistlist;
	public static ListView<MusicDao> wsonglist;
	public static ListView<MusicDao> Dsonglist;
	public static SongList songList=null;
	public SLListener(ListView<MusicDao> listview,ListView<String> musiclistlist,ListView<MusicDao> wsonglist,ListView<MusicDao> Dsonglist) {
		SLListener.listview = listview;
		SLListener.musiclistlist=musiclistlist;
		SLListener.wsonglist=wsonglist;
		SLListener.Dsonglist=Dsonglist;
		init();
	}

	public static void init() {
		//搜索页的列表监听
		listview.addEventHandler(MouseEvent.MOUSE_CLICKED, (t) -> {
			if (t.getClickCount() == 2 && t.getButton().name().equals("PRIMARY")) {
				MediaImp mi = Window.searchresult.getList().get(listview.getSelectionModel().getSelectedIndex())
						.toMediaImp();
				Window.searchresult.setIndex(listview.getSelectionModel().getSelectedIndex());
				Window.PlayingList=Window.searchresult;
				Window.Using=listview;
				PlayUtil.AChangemusic(mi);
				Window.player.play();
				
			}
			
		});
	//歌单页的列表监听
		musiclistlist.addEventHandler(MouseEvent.MOUSE_CLICKED, (t) -> {
			if (t.getClickCount() == 2 && t.getButton().name().equals("PRIMARY")) {
				ObservableList<MusicDao> obt=	Window.wsonglist.getItems();
//				SongList songList=null;
				try {
					songList=SongListUtil.LoadDefultList(musiclistlist.getSelectionModel().getSelectedItem());
					Window.opensonglist=songList;
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
				}
				Platform.runLater(()->{
					try {
					obt.removeAll(obt);
					for(MusicDao music:songList.getMedialist().getList()) {
						obt.add(music);
					}}catch (Exception e) {
						// TODO: handle exception
					}});
			
				}
	});
		//歌单详情页监听
		wsonglist.addEventHandler(MouseEvent.MOUSE_CLICKED, (t) -> {
			if (t.getClickCount() == 2 && t.getButton().name().equals("PRIMARY")) {
				MediaImp mi = Window.opensonglist.getMedialist().getList().get(wsonglist.getSelectionModel().getSelectedIndex())
						.toMediaImp();
				Window.opensonglist.getMedialist().setIndex(wsonglist.getSelectionModel().getSelectedIndex());
				Window.PlayingList=Window.opensonglist.getMedialist();
				Window.Using=wsonglist;
				
				PlayUtil.AChangemusic(mi);
				Window.Using=wsonglist;
				Window.player.play();
				}
	});
	
	}}
		