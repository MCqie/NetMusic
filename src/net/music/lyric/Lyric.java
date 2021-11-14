package net.music.lyric;

import javafx.scene.layout.Pane;
import net.music.Window;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Lyric {
    ArrayList<LyticItem> lyrics=new ArrayList<>();
    public Lyric(String lyricString){
            String[] s = lyricString.split("\\n");
        lyrics.add(new LyticItem("- - - - - - - - - -", 0.0));
            for (String sa : s) {
                try {
                String time = getSubString(sa, "[","]");
                //= sa.substring(1, 9);
                String[] timestr = time.split(":");
//time.split(":");
                Double timechick = Double.parseDouble(timestr[0]) * 60 + Double.parseDouble(timestr[1]);
                String Lyr = sa.replace("["+time+"]", "");
                System.out.println(sa + "-----" + time + "-----" + Lyr);
                lyrics.add(new LyticItem(Lyr, timechick));
                LoadToPane();

                }catch (Exception ignored){
                }
            }

       }

    public static String getSubString(String text, String left, String right) {
        String result = "";
        int zLen;
        if (left == null || left.isEmpty()) {
            zLen = 0;
        } else {
            zLen = text.indexOf(left);
            if (zLen > -1) {
                zLen += left.length();
            } else {
                zLen = 0;
            }
        }
        int yLen = text.indexOf(right, zLen);
        if (yLen < 0 || right == null || right.isEmpty()) {
            yLen = text.length();
        }
        result = text.substring(zLen, yLen);
        return result;
    }

    public void setLyrics(ArrayList<LyticItem> lyrics) {
        this.lyrics = lyrics;
    }

    public ArrayList<LyticItem> getLyrics() {
        return lyrics;
    }

    public void LoadToPane(){
        Window.lyricopane.getChildren().clear();
        for(LyticItem l:this.getLyrics()){
            Window.lyricopane.getChildren().add(l.getL());
        }
    }
}
