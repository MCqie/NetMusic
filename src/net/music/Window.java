package net.music;

import java.net.URL;
import java.util.Objects;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.music.Dao.MusicDao;
import net.music.Dao.SongList;
import net.music.config.DefaultConfig;
import net.music.lyric.Lyric;
import net.music.lyric.LyticItem;
import net.music.player.MediaList;
import net.music.player.Player;
import net.music.util.PlayStatus;

public class Window extends Application {
	
	public static DefaultConfig config;
	public static Player player;//播放器
	public static MediaList searchresult;//搜索结果
	public static SongList opensonglist;//正在使用的SongList
	public static ImageView playorpause;//暂停播放按钮
	public static Image play,pause;//播放 暂停图片
	public static Slider progress,volume;//进度条 ，音量条
	public static double volume_value;//默认音量
	public static boolean Mousestatus=false;//鼠标状态
	public static MediaList PlayingList;//正在播放的SongList
	public static ListView<MusicDao> listview,wsonglist,Dsonglist;//搜索页 歌单页 下载页
	public static ListView<String> musiclistlist;//所有歌单
	public static Label medianame;//歌曲名字
	public static Double[] ydv;//调音默认值
	public static PlayStatus playstatus;//播放状态
	public static ListView<?> Using;//正在使用的ListView

	public static int YDMax,YDMin;
	public static double speedrate=1.0;
	public static Stage stageI;
    public static HBox pinpu;
	public static VBox lyricopane;
	public static Lyric PlayingLyric;
	public static ScrollPane scrolllyc;

    double x1,y1,x_stage,y_stage;//窗口拖动值
	
	public static void main(String[] args) throws Throwable {
		config=DefaultConfig.LoadDefaultConfig();
		assert config != null;
		System.out.println(config.getConfig().toJSONString());
		ydv= config.getJSONArray("ydv").toJavaList(Double.class).toArray(new Double[0]);
		playstatus=PlayStatus.STPlayStatus(config.get("playstatus").toString());
		volume_value=config.getDoubleValue("volume_value");
		YDMax=config.getIntValue("YDMax");
		YDMin=config.getIntValue("YDMin");
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		URL fxml = getClass().getResource("NetPlayer.fxml");
		FXMLLoader loader = new FXMLLoader(fxml);
		BorderPane bp = (BorderPane) loader.load();

		play=new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("sourse/Pause.png")).toString());
		pause=new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("sourse/Begin.png")).toString());
		volume.setMax(1.0);
		volume.setValue(volume_value);
		progress.setMax(100);

		volume.valueProperty().addListener((a,b,c)->{
			volume_value=c.doubleValue();
		});
		Scene scene = new Scene(bp);
        scene.getStylesheets().add(
                this.getClass().getResource("/sourse/Listview.css").toExternalForm());
        
		 scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
		       @Override public void handle(MouseEvent m) {
		         //计算          
		         stage.setX(x_stage + m.getScreenX() - x1);
		         stage.setY(y_stage + m.getScreenY() - y1);
		         }                                                
		        });
		      scene.setOnDragEntered(null);
		      scene.setOnMousePressed(new EventHandler<MouseEvent>() {
		         
		         @Override public void handle(MouseEvent m) {
		         //按下鼠标后，记录当前鼠标的坐标         
		         x1 =m.getScreenX();
		         y1 =m.getScreenY();
		         x_stage = stage.getX();
		             y_stage = stage.getY();
		           }                
		        });
		      scene.setFill(null);
		stage.setScene(scene);
		stage.setTitle("NetMusicPlayer");
		stage.setResizable(false);
		stage.initStyle(StageStyle.TRANSPARENT);
		bp.requestFocus();
		System.out.println(System.getProperty("user.dir")+"\\sourse\\music.png");
		stage.getIcons().add(new Image(Objects.requireNonNull(getClass().getClassLoader().getResource("sourse/music.png")).toExternalForm()));
		stage.show();
		stageI=stage;
	}
}