package net.music.Dao;

import java.io.Serializable;

import net.music.player.MediaImp;

public class MusicDao implements Serializable{
	@Override
	public String toString() {
		return name + ":::" + artist + ":::" + length ;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;
	String url;
	int id;
	String desc;
	
	String artist;
	int length;
	
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getPicSrc() {
		return desc;
	}
	public void setPicSrc(String picSrc) {
		this.desc = picSrc;
	}
	public MusicDao(int id,String url,String name,String desc,int length,String artist) {
		this.length=length;
		this.artist=artist;
		this.id=id;
		this.url=url;
		this.name=name;
		this.desc=desc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public MediaImp toMediaImp() {
		try {
			return new MediaImp(url, name, desc, id,length,artist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
}
