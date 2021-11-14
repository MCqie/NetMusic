package net.music;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

import com.sun.org.glassfish.gmbal.Description;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.util.Callback;
import net.music.Dao.MusicDao;
import net.music.Exception.Builder;
import net.music.Exception.ErrorType;
import net.music.Exception.ExceptionDialogs;
import net.music.player.MediaList;
import net.music.util.Dialog;
import net.music.util.SongListUtil;
import net.music.web.Download;
import net.music.web.WebInterface;

public class Util {
	public static void exchangetoplay() {
		Window.playorpause.setImage(Window.play);
	}

	public static void exchangetopause() {
		Window.playorpause.setImage(Window.pause);
	}

	public static String SectoStr(int x) {
		int m = x / 60;
		int s = x % 60;
		return m + "分" + s + "秒";
	}

	@Description("搜索页面的ListView样式")
	public static void uplist(ListView<MusicDao> searchresult, MediaList media) {
		ObservableList<MusicDao> obt = searchresult.getItems();

		searchresult.setCellFactory(new Callback<ListView<MusicDao>, ListCell<MusicDao>>() {

			@Override
			public ListCell<MusicDao> call(ListView<MusicDao> param) {

				ListCell<MusicDao> listcell = new ListCell<MusicDao>() {

					@Override
					protected void updateItem(MusicDao arg0, boolean arg1) {
						super.updateItem(arg0, arg1);

						if (arg0 != null) {

							String name = arg0.getName();
							String artist = arg0.getArtist();
							int length = (int) (Double.parseDouble(arg0.getLength() + "") / 1000);
							String sl = SectoStr(length);

							HBox hbox = new HBox();
							HBox h1 = new HBox();
							HBox h2 = new HBox();
							HBox h3 = new HBox();
							HBox h4 = new HBox();

							h1.setPrefWidth(275);
							h2.setPrefWidth(265);
							h3.setPrefWidth(240);
							h4.setPrefWidth(60);

							h1.setAlignment(Pos.CENTER_LEFT);
							h2.setAlignment(Pos.CENTER_LEFT);
							h3.setAlignment(Pos.CENTER_LEFT);
							h4.setAlignment(Pos.CENTER_LEFT);

							Label l1 = new Label(name);
							l1.setAlignment(Pos.CENTER_LEFT);
							Label l2 = new Label(artist);
							l2.setAlignment(Pos.CENTER_LEFT);
							Label l3 = new Label(sl);
							l3.setAlignment(Pos.CENTER_LEFT);

							ImageView add = new ImageView(Objects.requireNonNull(getClass().getClassLoader().getResource("sourse/add.png"))
									.toExternalForm());
							ImageView download = new ImageView(Objects.requireNonNull(getClass().getClassLoader()
									.getResource("sourse/download.png")).toExternalForm());

							add.setCursor(Cursor.HAND);
							add.setPickOnBounds(true);

							download.setCursor(Cursor.HAND);
							download.setPickOnBounds(true);

							add.setFitHeight(25);
							add.setFitWidth(25);
							AddListener_SaveToPlayList(add, arg0);

							download.setFitHeight(25);
							download.setFitWidth(25);
							AddListener_Download(download, arg0);
							hbox.getChildren().addAll(h1, h2, h3, h4);
							h1.getChildren().add(l1);
							h2.getChildren().add(l2);
							h3.getChildren().add(l3);
							h4.getChildren().addAll(add, download);

							h4.setSpacing(30);

							this.setGraphic(hbox);
						} else {
							this.setGraphic(null);
						}

					}

				};
				return listcell;
			}
		});
		Platform.runLater(() -> {
			obt.removeAll(obt);
			obt.addAll(media.getList());
		});

	}

	@Description("歌单页面的ListView样式")
	public static void musiclist(ListView<String> listview) {
		listview.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

			@Override
			public ListCell<String> call(ListView<String> param) {
				ListCell<String> cell = new ListCell<String>() {

					@Override
					protected void updateItem(String item, boolean empty) {
						super.updateItem(item, empty);
						if (item != null) {
							Label l = new Label(item);
							l.setAlignment(Pos.CENTER_LEFT);
							Font f = Font.font("FangSong", FontPosture.ITALIC, 20.0);
							l.setStyle("-fx-text-fill:#FF00FF");
							l.setFont(f);
							HBox box = new HBox();
							HBox b1 = new HBox();
							HBox b2 = new HBox();

							b1.setAlignment(Pos.CENTER_LEFT);
							b1.setPrefWidth(140);

							ImageView iv = new ImageView(getClass().getClassLoader().getResource("sourse/delete.png")
									.toExternalForm().toString());
							iv.setFitHeight(30);
							iv.setFitWidth(30);
							iv.setCursor(Cursor.HAND);
							iv.setPickOnBounds(true);
							AddListener_DeleteSongList(iv, item);


							box.getChildren().addAll(b1, b2);
							b1.getChildren().add(l);
							b2.getChildren().addAll(iv);

							this.setGraphic(box);
						} else {
							this.setGraphic(null);
						}
					}

				};
				return cell;
			}
		});

	}

	@Description("歌单详情页面的ListView样式")
	public static void initsonglistlist(ListView<MusicDao> listview) {

		listview.setCellFactory((t) -> {
			ListCell<MusicDao> listcell = new ListCell<MusicDao>() {
				@Override
				protected void updateItem(MusicDao arg0, boolean arg1) {
					super.updateItem(arg0, arg1);
					if (arg1 == false) {
						String name = arg0.getName();
						String artist = arg0.getArtist();
						int length = (int) (Double.parseDouble(arg0.getLength() + "") / 1000);
						String sl = SectoStr(length);

						HBox hbox = new HBox();
						HBox h1 = new HBox();
						HBox h2 = new HBox();
						HBox h3 = new HBox();
						HBox h4 = new HBox();

						h1.setPrefWidth(275);
						h2.setPrefWidth(275);
						h3.setPrefWidth(245);
						h4.setPrefWidth(75);

						h1.setAlignment(Pos.CENTER_LEFT);
						h2.setAlignment(Pos.CENTER_LEFT);
						h3.setAlignment(Pos.CENTER_LEFT);
						h4.setAlignment(Pos.CENTER_LEFT);

						Label l1 = new Label(name);
						l1.setAlignment(Pos.CENTER_LEFT);
						Label l2 = new Label(artist);
						l2.setAlignment(Pos.CENTER_LEFT);
						Label l3 = new Label(sl);
						l3.setAlignment(Pos.CENTER_LEFT);

						ImageView iv = new ImageView(Objects.requireNonNull(getClass().getClassLoader().getResource("sourse/delete.png"))
								.toExternalForm());
						iv.setFitHeight(25);
						iv.setFitWidth(25);
						iv.setCursor(Cursor.HAND);
						iv.setPickOnBounds(true);

					
						ImageView iv2=new ImageView(Objects.requireNonNull(getClass().getClassLoader().getResource("sourse/download.png"))
								.toExternalForm());
						iv2.setFitHeight(23);
						iv2.setFitWidth(23);
						iv2.setCursor(Cursor.HAND);
						iv2.setPickOnBounds(true);
						
						h4.setSpacing(20);

						hbox.getChildren().addAll(h1, h2, h3, h4);
						h1.getChildren().add(l1);
						h2.getChildren().add(l2);
						h3.getChildren().add(l3);
						h4.getChildren().addAll(iv,iv2);
						AddListener_DeleteSongListMusic(iv, arg0);
						AddListener_Download(iv2, arg0);
						this.setGraphic(hbox);
					} else {
						this.setGraphic(null);
					}

				}
			};

			return listcell;

		});

	}
	
	@Description("已下载歌曲页面的ListView样式")
	public static void initDownloadedList(ListView<MusicDao> listview) {

		listview.setCellFactory((t) -> {
			ListCell<MusicDao> listcell = new ListCell<MusicDao>() {
				@Override
				protected void updateItem(MusicDao arg0, boolean arg1) {
					super.updateItem(arg0, arg1);
					if (!arg1) {
						String name = arg0.getName();
						String artist = arg0.getArtist();
						int length = (int) (Double.parseDouble(arg0.getLength() + "") / 1000);
						String sl = SectoStr(length);

						HBox hbox = new HBox();
						HBox h1 = new HBox();
						HBox h2 = new HBox();
						HBox h3 = new HBox();
						HBox h4 = new HBox();

						h1.setPrefWidth(275);
						h2.setPrefWidth(275);
						h3.setPrefWidth(245);
						h4.setPrefWidth(75);

						h1.setAlignment(Pos.CENTER_LEFT);
						h2.setAlignment(Pos.CENTER_LEFT);
						h3.setAlignment(Pos.CENTER_LEFT);
						h4.setAlignment(Pos.CENTER_LEFT);

						Label l1 = new Label(name);
						l1.setAlignment(Pos.CENTER_LEFT);
						Label l2 = new Label(artist);
						l2.setAlignment(Pos.CENTER_LEFT);
						Label l3 = new Label(sl);
						l3.setAlignment(Pos.CENTER_LEFT);

						ImageView iv = new ImageView(Objects.requireNonNull(getClass().getClassLoader().getResource("sourse/delete.png"))
								.toExternalForm());
						iv.setFitHeight(25);
						iv.setFitWidth(25);
						iv.setCursor(Cursor.HAND);
						iv.setPickOnBounds(true);

						AddListener_DeleteDownloadListMusic(iv, arg0);

						hbox.getChildren().addAll(h1, h2, h3, h4);
						h1.getChildren().add(l1);
						h2.getChildren().add(l2);
						h3.getChildren().add(l3);
						h4.getChildren().add(iv);

						this.setGraphic(hbox);
					} else {
						this.setGraphic(null);
					}

				}
			};

			return listcell;

		});

	}

	public static void AddListener_SaveToPlayList(ImageView add, MusicDao arg0) {



		add.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			if (Window.opensonglist != null) {
				Window.opensonglist.addMedia(arg0);
				try {
					SongListUtil.SaveWithName(Window.opensonglist, Window.opensonglist.getListName());
				} catch (IOException e1) {
					e1.printStackTrace();
				}

				ObservableList<MusicDao> obt = Window.wsonglist.getItems();
				try {
					Window.opensonglist = SongListUtil
							.LoadDefultList(Window.musiclistlist.getSelectionModel().getSelectedItem());
				} catch (ClassNotFoundException | IOException e2) {
					e2.printStackTrace();
				}

				Platform.runLater(() -> {
					try {
						obt.removeAll(obt);
						obt.addAll(Window.opensonglist.getMedialist().getList());
					} catch (Exception ignored) {
					}
				});
			} else {
				ExceptionDialogs.show(new ErrorType("错误","请打开歌单"), Builder.OK);
			}
		});

	}

	public static void AddListener_DeleteSongList(ImageView iv, String item) {

		iv.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			if (e.getButton() == MouseButton.SECONDARY) {
				new File(System.getProperty("user.dir") + "/SongList/" + item).delete();
				SongListUtil.upgradeSL();
			}

		});
	}

	public static void AddListener_DeleteSongListMusic(ImageView iv, MusicDao arg0) {
		iv.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			if (e.getButton() == MouseButton.SECONDARY) {
				Window.opensonglist.removeMedia(arg0);
				try {
					SongListUtil.SaveWithName(Window.opensonglist, Window.opensonglist.getListName());
					SongListUtil.LoadDefultList(Window.opensonglist.getListName());
				} catch (IOException | ClassNotFoundException e1) {

					e1.printStackTrace();
				}
				Platform.runLater(() -> {
					try {
						ObservableList<MusicDao> obt = Window.wsonglist.getItems();
						obt.removeAll(obt);
						obt.addAll(Window.opensonglist.getMedialist().getList());
					} catch (Exception ignored) {

					}
				});
			}
		});
	}
	public static void AddListener_DeleteDownloadListMusic(ImageView iv, MusicDao arg0) {
		iv.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			if (e.getButton() == MouseButton.SECONDARY) {
				System.out.println(arg0.getUrl());
			try {
				System.out.println(new File(new URL(arg0.getUrl()).getFile()).delete());
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				SongListUtil.upgradeDL();
			}
		});
	}
	public static void AddListener_Download(ImageView download,MusicDao arg0) {
		download.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
			try {
				Download.DownloadFile(WebInterface.getRedirectUrl(arg0.getUrl()), System.getProperty("user.dir")
						+ "/Download/" + arg0.getName() + " --  " + arg0.getArtist() + ".mp3");
				SongListUtil.upgradeDL();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
	}
}
