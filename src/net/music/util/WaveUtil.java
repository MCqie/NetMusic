package net.music.util;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import net.music.Window;

import java.util.ArrayList;

public class WaveUtil {
  public static  ArrayList<Rectangle> WaveItems;
  public static ArrayList<Integer> items=new ArrayList<Integer>();
    public static LinearGradient linearGradient1 = new LinearGradient(0, 0, 0, 1, true, CycleMethod.NO_CYCLE,
            new Stop(0, Color.rgb(31,161,255)),
            new Stop(1, Color.rgb(255,0,243)));
    public static void initWave(int start,int num,int length){
        int a=start;
        int preflength=1200/num;
      WaveItems=new ArrayList<Rectangle>();
        for(int i=0;i<num;i++){
            Rectangle l = new Rectangle();
            l.setWidth(preflength);
            l.setHeight(0);
            items.add(a);
            a=a+length;
//            *<LinearGradient endX="0.4611650485436893" endY="1.0">
//             *                    <stops>
//              *                      <Stop color="#1fa1ff" />
//               *                     <Stop color="#ff00f3" offset="1.0" />
//                *                 </stops>
//                 *             </LinearGradient>

         l.setFill(linearGradient1);
            WaveItems.add(l);
            Window.pinpu.getChildren().add(l);
        }


    }
    public static void updateWave(float[] data){
//        Window.pinpu.getChildren().clear();
            for (int x=0;x<items.size();x++){
                WaveItems.get(x).setHeight(60-Math.abs(data[items.get(x)]));
//                Window.pinpu.getChildren().add( WaveItems.get(x));
            }
    }
}
