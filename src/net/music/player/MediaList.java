package net.music.player;

import java.io.Serializable;
import java.util.ArrayList;

import net.music.Dao.MusicDao;

public class MediaList implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<MusicDao> list=new ArrayList<>();
	private int index =0;
	public MediaList() {
	}
	public void addMedia(MusicDao media) {
		list.add(media);
	}
	public void removeMedia(MusicDao media) {
		list.remove(media);
	}
	public void removeMedia(int index) {
		list.remove(index);
	}
	public ArrayList<MusicDao> getList() {
		return list;
	}
	public void setList(ArrayList<MusicDao> list) {
		this.list = list;
	}
	public MusicDao getNextMedia() {
		index++;
		if(index==list.size()) {
			index=0;
			return list.get(index);
		}else {
			return list.get(index);
		}
	}
	public MusicDao getprevMedia() {
		index--;
		if(index==-1) {
			index=list.size()-1;
			return list.get(index);
		}else {
			return list.get(index);
		}
	}
	public void setIndex(int index) {
		this.index=index;
	}
	public void initIndex() {
		setIndex(0);
	}
	public int getIndex() {
		return index;
	}
	
}
