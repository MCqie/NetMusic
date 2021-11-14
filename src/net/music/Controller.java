package net.music;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXSlider;
import com.sun.org.glassfish.gmbal.Description;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.AudioEqualizer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.*;
import javafx.util.Duration;
import net.music.Dao.MusicDao;
import net.music.Dao.SongList;
import net.music.listener.SLListener;
import net.music.player.MediaList;
import net.music.player.PlayerListener;
import net.music.util.*;
import net.music.util.Dialog;

public class Controller implements Initializable {
    public JFXButton min;
	public JFXButton addmusicList;
	public Tooltip tip11,tip21,tip31,tip41,tip51,tip61,tip71,tip81,tip91,tip101;
	public JFXColorPicker zhutise,gecibeijing,wavejb2,wavejb1;
	public JFXColorPicker shezhi;
	public JFXColorPicker tiaoyin;
	public HBox setting;
	int n = 0;
	public ScrollPane scrolllyc;
	@FXML
    VBox lyricpane;
    @FXML
	HBox pinpu;
	@FXML
	TextField searchfiled,songlistname;
	@FXML
	ListView<MusicDao> searchresult,wsonglist,DsongList;
	@FXML
	ListView<String> musiclistlist;
	@FXML
	ImageView before,next,playorpause,search,quit,sound,status;
	@FXML
	JFXSlider progress,volume;
	@FXML
	Label medianame;
	@FXML
	Slider yd1,yd2,yd3,yd4,yd5,yd6,yd7,yd8,yd9,yd10;
	@FXML
	ChoiceBox<String> speedrate; 
	@FXML
	BorderPane background;

	public static  Stop stop1,stop2;
	@Description("搜索按钮点击")
	public void searchclick(MouseEvent click) {
		new Thread(() -> {
			String name = searchfiled.getText();
			MediaList medialist = null;
			try {
				medialist = MusicDataUtil.SearchByName(name);
				Window.searchresult = medialist;
			} catch (Exception ignored) {
				ignored.printStackTrace();
			}

			Util.uplist(searchresult, medialist);
		}).start();
	}
	@Description("搜索热键")
	public void hotkeysearch(KeyEvent e) {
		if (e.getCode().getName().equals(KeyCode.ENTER.getName())&& searchfiled.isFocused()) {
			searchclick(null);
		}
	}
	
	public void clicktomin(MouseEvent e) {
		Window.stageI.setIconified(true);
	}
	
	@Description("播放按钮点击")
	public void playclick(MouseEvent e) {
		try {
			if (Window.player.getStatus().equals(Status.PLAYING)) {
				Window.player.pause();
			} else if (Window.player.getStatus().equals(Status.PAUSED) || Window.player.getStatus().equals(Status.READY)) {
				Window.player.play();

				System.out.println(Window.PlayingList.getList().get(1).getId());

			}
		}catch (NullPointerException a){
			Dialog d=new Dialog("错误", "播放任务为空");

		}
	}
	
	@Description("进度条按下")
	public void progresspress(MouseEvent e) {
		Window.Mousestatus = true;
	}
	@Description("进度条释放")
	public void progressreleased(MouseEvent e) {
		Window.player.seek(Duration.seconds(Window.progress.getValue()));
		Window.Mousestatus = false;
		PlayerListener.index=0;
		Window.scrolllyc.setVvalue(0);
	}
	@Description("上一曲")
	public void beforeclick(MouseEvent e) {
		PlayUtil.MusicUClick();
	}
	@Description("下 一曲")
	public void nextclick(MouseEvent e) {
		PlayUtil.MusicOverChange();
	}
	@Description("退出")
	public void quitclick(MouseEvent e) {
		Platform.exit();
	}
	@Description("添加歌单")
	public void addmusiclistclick() throws IOException {
		String name=songlistname.getText();
		songlistname.setText("");
		SongList songlist=new SongList(name);
		SongListUtil.SaveWithName(songlist, name);
		SongListUtil.upgradeSL();
	}
	@Description("音量调节")
	public void soundchanged(MouseEvent e) {
		if(volume.getValue()>0.6) {
			n=2;
		}else if(volume.getValue()<0.3) {
			n=0;
		}else {
			n=1;
		}
		sound.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("sourse/sound/" + n + ".png")).toString()));
	}
	@Description("切换状态")
	public void changestatus(MouseEvent e) {
		 if(Window.playstatus==PlayStatus.DEFAULT) {
			 Window.playstatus=PlayStatus.RANDOM;
			 status.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("sourse/random.png")).toString()));
			 status.setFitHeight(25);
			 status.setFitWidth(25);
		 }else if(Window.playstatus==PlayStatus.RANDOM) {
			 Window.playstatus=PlayStatus.CYCLE;
			 Image im=new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("sourse/cycle.png")).toString());
			 status.setImage(im);
			 status.setFitHeight(30);
			 status.setFitWidth(30);
		 }else {
			 Window.playstatus=PlayStatus.DEFAULT;
			 status.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("sourse/deaflt.png")).toString()));
			 status.setFitHeight(35);
			 status.setFitWidth(35);
		 }
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Util.musiclist(musiclistlist);
		Util.initsonglistlist(wsonglist);
		Util.initDownloadedList(DsongList);
		RegistToWindow();
		initImage();
		initlistener2();
		SLListener slListener = new SLListener(this.searchresult, this.musiclistlist, this.wsonglist, this.DsongList);
		WaveUtil.initWave(1,100,1);
		SongListUtil.upgradeSL();
		SongListUtil.upgradeDL();
		initother();
ColorSet();

	}
	public void ColorSet(){

		zhutise.setValue(Color.rgb(11, 255, 255));
		zhutise.valueProperty().addListener(observable -> {
			background.setBackground(new Background(new BackgroundFill(zhutise.getValue(),null,null)));
		});
		//歌词
		gecibeijing.setValue(Color.rgb(255,255,255));
		gecibeijing.valueProperty().addListener(observable -> {
			lyricpane.setBackground(new Background(new BackgroundFill(gecibeijing.getValue(),null,null)));
		});
		//设置
		shezhi.setValue(Color.rgb(255,255,255));
		shezhi.valueProperty().addListener(observable -> {
			setting.setBackground(new Background(new BackgroundFill(shezhi.getValue(),null,null)));
		});
		//波形
		wavejb1.setValue(Color.rgb(31,161,255));
		wavejb1.valueProperty().addListener(observable -> {
			stop1=new Stop(0, wavejb1.getValue());
			WaveUtil.WaveItems.forEach((a)->{
				a.setFill(innerMethcod.mklg());
			});
		innerMethcod.mklg();
		});
		wavejb2.setValue(Color.rgb(255,0,243));
		wavejb2.valueProperty().addListener(observable -> {
			stop2=new Stop(1, wavejb2.getValue());
			WaveUtil.WaveItems.forEach((a)->{
				a.setFill(innerMethcod.mklg());
			});
		});

	}
	private static class innerMethcod{
		public static LinearGradient mklg(){
			 return new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,stop1,stop2);
		}
	}


	public void RegistToWindow() {
		Window.playorpause = this.playorpause;
		Window.progress = this.progress;
		Window.volume = this.volume;
		Window.medianame=medianame;
		Window.musiclistlist=musiclistlist;
		Window.wsonglist=wsonglist;
		Window.Dsonglist=DsongList;
		Window.pinpu=pinpu;
		Window.listview=searchresult;
		Window.lyricopane=lyricpane;
		Window.scrolllyc=scrolllyc;

	}
	public void initImage() {
		before.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("sourse/Before.png")).toString()));
		playorpause.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("sourse/Begin.png")).toString()));
		next.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("sourse/Next.png")).toString()));
		search.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("sourse/search.png")).toString()));
		quit.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("sourse/quit.png")).toString()));
		sound.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("sourse/sound/1.png")).toString()));
		status.setImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("sourse/deaflt.png")).toString()));
		background.setBackground(new Background(new BackgroundImage(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("sourse/bg.jpg")).toString()),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,  BackgroundPosition.CENTER, 
				new BackgroundSize(background.getWidth(),background.getHeight(), true,true, true, false))));
		status.setFitHeight(40);
		status.setFitWidth(40);
		search.setFitHeight(28);
		search.setFitWidth(28);
	}
	public void initListener(Slider slider,int l) {
		slider.valueChangingProperty().addListener((a,b,c)->{
			try {
			AudioEqualizer ae= Window.player.mediaplayer.getAudioEqualizer();
			ae.getBands().get(l).setGain(slider.getValue());
			System.out.println(slider.getId()+"|"+slider.getValue());
			Window.ydv[l]=slider.getValue();
			slider.setTooltip(new Tooltip(new java.text.DecimalFormat("#.00").format(slider.getValue())+"dB"));
			}catch (NullPointerException e) {
				e.printStackTrace();
			}
		});
	}
	public void initlistener2() {
		yd1.setMax(Window.YDMax);
		yd2.setMax(Window.YDMax);
		yd3.setMax(Window.YDMax);
		yd4.setMax(Window.YDMax);
		yd5.setMax(Window.YDMax);
		yd6.setMax(Window.YDMax);
		yd7.setMax(Window.YDMax);
		yd8.setMax(Window.YDMax);
		yd9.setMax(Window.YDMax);
		yd10.setMax(Window.YDMax);
		yd1.setMin(Window.YDMin);
		yd2.setMin(Window.YDMin);
		yd3.setMin(Window.YDMin);
		yd4.setMin(Window.YDMin);
		yd5.setMin(Window.YDMin);
		yd6.setMin(Window.YDMin);
		yd7.setMin(Window.YDMin);
		yd8.setMin(Window.YDMin);
		yd9.setMin(Window.YDMin);
		yd10.setMin(Window.YDMin);
		if(Window.YDMax>10||Window.YDMin<-10){
			Dialog d=new Dialog("Warning", "音调的最大值和最小值应设置为 0±10dB 过高或过低都会影响程序的正常使用");
			d.addButton("忽略").addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
				d.getT().close();
			});
			d.addButton("关闭程序").addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
				System.exit(0);
			});
			d.show();
		}
		if(!(Window.YDMax>0&&Window.YDMin<0)){
			Dialog d=new Dialog("Warning", "音调的最大值应大于0 音调的最小值应小于0");
			d.addButton("忽略").addEventHandler(MouseEvent.MOUSE_CLICKED, (e)-> d.getT().close());
			d.addButton("关闭程序").addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
				System.exit(0);
			});
			d.show();
		}
		if(Window.YDMax<=Window.YDMin){
			Dialog d=new Dialog("Warning", "音调的最大值应大于最小值");
			d.addButton("忽略").addEventHandler(MouseEvent.MOUSE_CLICKED, (e)-> d.getT().close());
			d.addButton("关闭程序").addEventHandler(MouseEvent.MOUSE_CLICKED, (e)-> System.exit(0));
			d.show();
		}
		initListener(yd1, 0);
		initListener(yd2, 1);
		initListener(yd3, 2);
		initListener(yd4, 3);
		initListener(yd5, 4);
		initListener(yd6, 5);
		initListener(yd7, 6);
		initListener(yd8, 7);
		initListener(yd9, 8);
		initListener(yd10,9);
		
		searchfiled.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				hotkeysearch(event);

			}
		});
	}
	
	public void initother() {
		
		this.speedrate.getItems().addAll("0.5","0.8","1.0","1.2","1.5","1.8","2.0");
		this.speedrate.setValue("1.0");
		this.speedrate.getSelectionModel().selectedItemProperty().addListener((a,b,c)->{
			if(Window.player!=null) {
				Window.speedrate=Double.parseDouble(c);
			Window.player.setRate(Window.speedrate);
			}
			
		});
		this.speedrate.setTooltip(new Tooltip("关于播放速率的设置"));
	}
	

}
