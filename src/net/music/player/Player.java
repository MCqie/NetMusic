package net.music.player;

import com.sun.media.jfxmedia.control.VideoDataBuffer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.media.AudioEqualizer;
import javafx.scene.media.AudioSpectrumListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaMarkerEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import net.music.Util;
import net.music.Window;

public class Player{
	public MediaPlayer mediaplayer;
	@SuppressWarnings("deprecation")
	public VideoDataBuffer impl_getLatestFrame() {
		return mediaplayer.impl_getLatestFrame();
	}
	public final void setRate(double value) {
		mediaplayer.setRate(value);
	}
	public final void setStartTime(Duration value) {
		mediaplayer.setStartTime(value);
	}
	public final void setStopTime(Duration value) {
		mediaplayer.setStopTime(value);
	}
	public final void setVolume(double value) {
		mediaplayer.setVolume(value);
	}
	public Player(MediaImp m) {
		mediaplayer=new MediaPlayer(m.getMedia());
		System.out.println(mediaplayer.getTotalDuration().toSeconds());
		upgrade();
	}
	public DoubleProperty audioSpectrumIntervalProperty() {
		return mediaplayer.audioSpectrumIntervalProperty();
	}
	public ObjectProperty<AudioSpectrumListener> audioSpectrumListenerProperty() {
		return mediaplayer.audioSpectrumListenerProperty();
	}
	public IntegerProperty audioSpectrumNumBandsProperty() {
		return mediaplayer.audioSpectrumNumBandsProperty();
	}
	public IntegerProperty audioSpectrumThresholdProperty() {
		return mediaplayer.audioSpectrumThresholdProperty();
	}
	public BooleanProperty autoPlayProperty() {
		return mediaplayer.autoPlayProperty();
	}
	public DoubleProperty balanceProperty() {
		return mediaplayer.balanceProperty();
	}
	public ReadOnlyObjectProperty<Duration> bufferProgressTimeProperty() {
		return mediaplayer.bufferProgressTimeProperty();
	}
	public ReadOnlyIntegerProperty currentCountProperty() {
		return mediaplayer.currentCountProperty();
	}
	public ReadOnlyDoubleProperty currentRateProperty() {
		return mediaplayer.currentRateProperty();
	}
	public ReadOnlyObjectProperty<Duration> currentTimeProperty() {
		return mediaplayer.currentTimeProperty();
	}
	public IntegerProperty cycleCountProperty() {
		return mediaplayer.cycleCountProperty();
	}
	public ReadOnlyObjectProperty<Duration> cycleDurationProperty() {
		return mediaplayer.cycleDurationProperty();
	}
	public void dispose() {
		mediaplayer.dispose();
	}
	public boolean equals(Object obj) {
		return mediaplayer.equals(obj);
	}
	public ReadOnlyObjectProperty<MediaException> errorProperty() {
		return mediaplayer.errorProperty();
	}
	public final AudioEqualizer getAudioEqualizer() {
		return mediaplayer.getAudioEqualizer();
	}
	public final double getAudioSpectrumInterval() {
		return mediaplayer.getAudioSpectrumInterval();
	}
	public final AudioSpectrumListener getAudioSpectrumListener() {
		return mediaplayer.getAudioSpectrumListener();
	}
	public final int getAudioSpectrumNumBands() {
		return mediaplayer.getAudioSpectrumNumBands();
	}
	public final int getAudioSpectrumThreshold() {
		return mediaplayer.getAudioSpectrumThreshold();
	}
	public final double getBalance() {
		return mediaplayer.getBalance();
	}
	public final Duration getBufferProgressTime() {
		return mediaplayer.getBufferProgressTime();
	}
	public final int getCurrentCount() {
		return mediaplayer.getCurrentCount();
	}
	public final double getCurrentRate() {
		return mediaplayer.getCurrentRate();
	}
	public final Duration getCurrentTime() {
		return mediaplayer.getCurrentTime();
	}
	public final int getCycleCount() {
		return mediaplayer.getCycleCount();
	}
	public final Duration getCycleDuration() {
		return mediaplayer.getCycleDuration();
	}
	public final MediaException getError() {
		return mediaplayer.getError();
	}
	public final Media getMedia() {
		return mediaplayer.getMedia();
	}
	public final Runnable getOnEndOfMedia() {
		return mediaplayer.getOnEndOfMedia();
	}
	public final Runnable getOnError() {
		return mediaplayer.getOnError();
	}
	public final Runnable getOnHalted() {
		return mediaplayer.getOnHalted();
	}
	public final EventHandler<MediaMarkerEvent> getOnMarker() {
		return mediaplayer.getOnMarker();
	}
	public final Runnable getOnPaused() {
		return mediaplayer.getOnPaused();
	}
	public final Runnable getOnPlaying() {
		return mediaplayer.getOnPlaying();
	}
	public final Runnable getOnReady() {
		return mediaplayer.getOnReady();
	}
	public final Runnable getOnRepeat() {
		return mediaplayer.getOnRepeat();
	}
	public final Runnable getOnStalled() {
		return mediaplayer.getOnStalled();
	}
	public final Runnable getOnStopped() {
		return mediaplayer.getOnStopped();
	}
	public final double getRate() {
		return mediaplayer.getRate();
	}
	public final Duration getStartTime() {
		return mediaplayer.getStartTime();
	}
	public final Status getStatus() {
		return mediaplayer.getStatus();
	}
	public final Duration getStopTime() {
		return mediaplayer.getStopTime();
	}
	public final Duration getTotalDuration() {
		return mediaplayer.getTotalDuration();
	}
	public final double getVolume() {
		return mediaplayer.getVolume();
	}
	public int hashCode() {
		return mediaplayer.hashCode();
	}
	public final boolean isAutoPlay() {
		return mediaplayer.isAutoPlay();
	}
	public final boolean isMute() {
		return mediaplayer.isMute();
	}
	public BooleanProperty muteProperty() {
		return mediaplayer.muteProperty();
	}
	public ObjectProperty<Runnable> onEndOfMediaProperty() {
		return mediaplayer.onEndOfMediaProperty();
	}
	public ObjectProperty<Runnable> onErrorProperty() {
		return mediaplayer.onErrorProperty();
	}
	public ObjectProperty<Runnable> onHaltedProperty() {
		return mediaplayer.onHaltedProperty();
	}
	public ObjectProperty<EventHandler<MediaMarkerEvent>> onMarkerProperty() {
		return mediaplayer.onMarkerProperty();
	}
	public ObjectProperty<Runnable> onPausedProperty() {
		return mediaplayer.onPausedProperty();
	}
	public ObjectProperty<Runnable> onPlayingProperty() {
		return mediaplayer.onPlayingProperty();
	}
	public ObjectProperty<Runnable> onReadyProperty(Function0<Unit> function) {
		return mediaplayer.onReadyProperty();
	}
	public ObjectProperty<Runnable> onRepeatProperty() {
		return mediaplayer.onRepeatProperty();
	}
	public ObjectProperty<Runnable> onStalledProperty() {
		return mediaplayer.onStalledProperty();
	}
	public ObjectProperty<Runnable> onStoppedProperty() {
		return mediaplayer.onStoppedProperty();
	}
	public DoubleProperty rateProperty() {
		return mediaplayer.rateProperty();
	}
	public void seek(Duration seekTime) {
		mediaplayer.seek(seekTime);
	}
	public final void setAudioSpectrumInterval(double value) {
		mediaplayer.setAudioSpectrumInterval(value);
	}
	public final void setAudioSpectrumListener(AudioSpectrumListener listener) {
		mediaplayer.setAudioSpectrumListener(listener);
	}
	public final void setAudioSpectrumNumBands(int value) {
		mediaplayer.setAudioSpectrumNumBands(value);
	}
	public final void setAudioSpectrumThreshold(int value) {
		mediaplayer.setAudioSpectrumThreshold(value);
	}
	public final void setAutoPlay(boolean value) {
		mediaplayer.setAutoPlay(value);
	}
	public final void setBalance(double value) {
		mediaplayer.setBalance(value);
	}
	public final void setCycleCount(int value) {
		mediaplayer.setCycleCount(value);
	}
	public final void setMute(boolean value) {
		mediaplayer.setMute(value);
	}
	public ObjectProperty<Duration> startTimeProperty() {
		return mediaplayer.startTimeProperty();
	}
	public ReadOnlyObjectProperty<Status> statusProperty() {
		return mediaplayer.statusProperty();
	}
	public ObjectProperty<Duration> stopTimeProperty() {
		return mediaplayer.stopTimeProperty();
	}
	public String toString() {
		return mediaplayer.toString();
	}
	public ReadOnlyObjectProperty<Duration> totalDurationProperty() {
		return mediaplayer.totalDurationProperty();
	}
	public DoubleProperty volumeProperty() {
		return mediaplayer.volumeProperty();
	}
	public void play() {
		Util.exchangetoplay();
		mediaplayer.play();
	}
	public void stop() {
		mediaplayer.stop();
	}
	public MediaPlayer getMediaplayer() {
		return mediaplayer;
	}
	public void setMediaplayer(MediaPlayer mediaplayer) {
		this.mediaplayer = mediaplayer;
	}
	public void upgrade() {
		Window.volume.setValue(Window.volume_value);
		this.mediaplayer.volumeProperty().bind(Window.volume.valueProperty());
		new PlayerListener(this);
		for(int i=0;i<10;i++) {
		this.mediaplayer.getAudioEqualizer().getBands().get(i).setGain(Window.ydv[i]);
		}
		
		
	}
	@Deprecated
	public void changeMusic(MediaImp media) {
		//this.mediaplayer.stop();
		this.mediaplayer.dispose();
		this.mediaplayer=new MediaPlayer(media.getMedia());
		upgrade();
	}
	public void pause() {
		Util.exchangetopause();
		this.mediaplayer.pause();
	}
}
