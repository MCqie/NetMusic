package net.music.util;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import net.music.Window;
import net.music.Dao.MusicDao;
import net.music.Dao.SongList;

public class SongListUtil {
	static Double time2;
	static ObservableList<MusicDao> obt;
	static MusicDao md = null;
	static MediaPlayer mp;
	static Media m = null;

	public static SongList LoadDefultList(String MusicListName) throws ClassNotFoundException, IOException {
		return SongList.Load(System.getProperty("user.dir") + "/SongList/" + MusicListName);
	}

	public static File[] GetAllSongList() {
		File f = new File(System.getProperty("user.dir") + "/SongList");
		if (!f.exists()) {
			f.mkdir();
		}
		return f.listFiles();
	}

	public static void SaveWithName(SongList song, String name) throws IOException {
		song.Save(System.getProperty("user.dir") + "/SongList/" + name);

	}

	public static void upgradeSL() {
		Platform.runLater(() -> {
			ObservableList<String> obt = Window.musiclistlist.getItems();
			obt.removeAll(obt);
		});

		Platform.runLater(() -> {
			ObservableList<String> obt = Window.musiclistlist.getItems();
			for (File f : GetAllSongList()) {
				obt.add(f.getName());
			}
		});
	}

	public static File[] GetAllDownlaodList() {
		File f = new File(System.getProperty("user.dir") + "/Download");
		if (!f.exists()) {
			f.mkdir();
		}
		return f.listFiles();
	}

	@SuppressWarnings("deprecation")
	public static void upgradeDL() {
		obt = Window.Dsonglist.getItems();
		obt.removeAll(obt);
		Platform.runLater(() -> {
			for (File f : GetAllDownlaodList()) {
				String[] sa = f.getName().split(" -- ");
				try {
					m = new Media(f.toURI().toURL().toString());
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
				mp = new MediaPlayer(m);
				mp.setOnReady(() -> {
					time2 = mp.getTotalDuration().toSeconds();
					try {
						md = new MusicDao(0, f.toURL().toExternalForm(), sa[0], "", (int) (time2 * 1000),
								sa[1]);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
					obt.add(md);
					mp=null;
					m=null;
					System.gc();
				});

			}
		});
	}
}
