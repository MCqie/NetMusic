package net.music;

import java.io.IOException;
import com.sun.javafx.application.PlatformImpl;
import net.music.Dao.SongList;
import net.music.player.MediaImp;
import net.music.player.MediaList;
import net.music.player.Player;

public class SongListDemo {
	static Player p;
	public static void main(String[] args) throws IOException, InterruptedException {
		PlatformImpl.startup(() -> {
		
//			 储存
//			 SongList s=new SongList("Test");
//			 s.setMedialist(mediaList);
//			 try {
//				s.Save("a.obj");
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			
//			读取
		MediaList mediaList = null;
		try {
			SongList so=SongList.Load("a.obj");
			System.out.println(so.getListName());
			mediaList=so.getMedialist();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(mediaList.getList().size());
		MediaImp mediaImp= mediaList.getList().get(0).toMediaImp();
		System.out.println(mediaImp.getDuration());
		 p=new Player(mediaImp);
		 p.play();
		});
		
	}

}
