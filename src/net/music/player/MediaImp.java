package net.music.player;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.Track;
import javafx.util.Duration;
import net.music.web.WebInterface;

public class MediaImp {
	String MusicName;
	String desc;
	int id;
	Media media;
	String artist;
	int length;
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
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
	public Media getMedia() {
		return media;
	}
	public void setMedia(Media media) {
		this.media = media;
	}
	 public MediaImp(String Src,String Name,String desc,int id,int length,String artist) throws Exception{
		this.length=length;
		this.artist=artist;
		this.media=new Media(WebInterface.getRedirectUrl(Src));
		this.id=id;
		this.desc=desc;
		this.MusicName=Name;
	}
	 
	public String getMusicName() {
		return MusicName;
	}
	public void setMusicName(String musicName) {
		MusicName = musicName;
	}
	public String getPicSrc() {
		return desc;
	}
	public void setPicSrc(String picSrc) {
		this.desc = picSrc;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Duration getDuration() {
		return media.getDuration();
	}
	public ReadOnlyObjectProperty<Duration> durationProperty() {
		return media.durationProperty();
	}
	public boolean equals(Object obj) {
		return media.equals(obj);
	}
	public ReadOnlyObjectProperty<MediaException> errorProperty() {
		return media.errorProperty();
	}
	public final MediaException getError() {
		return media.getError();
	}
	public final int getHeight() {
		return media.getHeight();
	}
	public final ObservableMap<String, Duration> getMarkers() {
		return media.getMarkers();
	}
	public final ObservableMap<String, Object> getMetadata() {
		return media.getMetadata();
	}
	public final Runnable getOnError() {
		return media.getOnError();
	}
	public String getSource() {
		return media.getSource();
	}
	public final ObservableList<Track> getTracks() {
		return media.getTracks();
	}
	public final int getWidth() {
		return media.getWidth();
	}
	public int hashCode() {
		return media.hashCode();
	}
	public ReadOnlyIntegerProperty heightProperty() {
		return media.heightProperty();
	}
	public ObjectProperty<Runnable> onErrorProperty() {
		return media.onErrorProperty();
	}
	public final void setOnError(Runnable value) {
		media.setOnError(value);
	}
	public String toString() {
		return media.toString();
	}
	public ReadOnlyIntegerProperty widthProperty() {
		return media.widthProperty();
	}
}
