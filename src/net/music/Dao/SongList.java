package net.music.Dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javafx.scene.image.Image;
import net.music.player.MediaList;

public class SongList implements Serializable{
	/**
	 * @apiNote 可被序列化
	 */
	private static final long serialVersionUID = 1L;
	public void addMedia(MusicDao media) {
		medialist.addMedia(media);
	}
	public void removeMedia(MusicDao media) {
		medialist.removeMedia(media);
	}
	public void removeMedia(int index) {
		medialist.removeMedia(index);
	}
	private String ListName=null;
	private String desc=null;
	private Image image=null;
	private MediaList medialist=new MediaList();
	public SongList(String listname) {
		this.ListName=listname;
	}
	public SongList(String listname,String desc) {
		this.ListName=listname;
		this.desc=desc;
	}
	
	public String getListName() {
		return ListName;
	}
	public void setListName(String listName) {
		ListName = listName;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	public MediaList getMedialist() {
		return medialist;
	}
	public void setMedialist(MediaList medialist) {
		this.medialist = medialist;
	}
	public void Save(String Path) throws IOException {
		File f=new File(Path);
		if(f.exists()) {f.delete();}
		f.createNewFile();
		FileOutputStream fo=new FileOutputStream(f);
		ObjectOutputStream oow= new ObjectOutputStream(fo);
		oow.writeObject(this);
		oow.close();
		fo.close();
	}
	public static SongList Load(String Path) throws IOException, ClassNotFoundException {
		File f=new File(Path);
		FileInputStream fi=new FileInputStream(f);
		ObjectInputStream oow= new ObjectInputStream(fi);
		Object obj= oow.readObject();
		oow.close();
		fi.close();
		if(obj instanceof SongList) {
			SongList sl=(SongList)obj;
			//sl.getMedialist().initIndex();
			return sl;
		}else {
			throw new ClassNotFoundException("此路径下的文件并不是有效的对象");
		}
	}
}
