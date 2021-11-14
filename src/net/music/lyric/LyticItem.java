package net.music.lyric;


import javafx.scene.control.Label;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

public class LyticItem {
    Label l=new Label();
    Double Time;
    public LyticItem(String Lrc,Double Time){
        this.l=new Label(Lrc);
        Font f = Font.font("System", FontPosture.ITALIC, 18.0);
        l.setFont(f);
        this.Time=Time;
    }
    public void ToRed(){
      l.setTextFill(Paint.valueOf("#f00000"));
        Font f = Font.font("System", FontPosture.ITALIC, 25.0);
        l.setFont(f);
    }
    public void ToBlack(){
        Font f = Font.font("System", FontPosture.ITALIC, 18.0);
        l.setFont(f);
        l.setTextFill(Paint.valueOf("#000000"));
    }


    public Double getTime() {
        return Time;
    }

    public Label getL() {
        return l;
    }

    public void setL(Label l) {
        this.l = l;
    }

    public void setTime(Double time) {
        Time = time;
    }
}
