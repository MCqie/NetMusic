package net.music.util;


import java.io.IOException;
import java.net.URL;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Dialog {
	Scene c;
	Stage t;
	String title, msg;
	BorderPane bp ;
	double x1,y1,x_stage,y_stage;
	public Dialog(String title,String msg) {
		URL fxml = getClass().getResource("Dialog.fxml");
		FXMLLoader loader = new FXMLLoader(fxml);
		try {
			bp = loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c=new Scene(bp);
		t=new Stage();
		t.initStyle(StageStyle.TRANSPARENT);
		t.setScene(c);
		this.title=title;
		this.msg=msg;
	}
	
	public Button addButton(String name) {
		Button b=new Button(name);
		b.setStyle("-fx-background-color:#ccff88;-fx-background-radius: 20");
		ToolBar toolBar= (ToolBar) c.lookup("#tools");
		toolBar.getItems().add(b);
		return b;
	}
	public void show() {
		t.show();
		Button close= (Button)bp.lookup("#close");
		Label titlel= (Label)bp.lookup("#title");
		TextArea msgl= (TextArea)bp.lookup("#msg");
		titlel.setText(title);
		msgl.setText(msg);
		close.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)-> t.close());
		 c.setOnMouseDragged(m -> {
		   //计算
		   t.setX(x_stage + m.getScreenX() - x1);
		   t.setY(y_stage + m.getScreenY() - y1);
		   });
		      c.setOnDragEntered(null);
		      c.setOnMousePressed(m -> {
			  //按下鼠标后，记录当前鼠标的坐标
			  x1 =m.getScreenX();
			  y1 =m.getScreenY();
			  x_stage = t.getX();
				  y_stage = t.getY();
				});
	}

	public Scene getC() {
		return c;
	}

	public void setC(Scene c) {
		this.c = c;
	}

	public Stage getT() {
		return t;
	}

	public void setT(Stage t) {
		this.t = t;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public BorderPane getBp() {
		return bp;
	}

	public void setBp(BorderPane bp) {
		this.bp = bp;
	}
	
}
