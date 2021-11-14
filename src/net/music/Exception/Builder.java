package net.music.Exception;

import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import net.music.util.Dialog;

import javax.swing.*;

public interface Builder {
   public void Build(Dialog d);



 final static Builder OK= d -> {
     Button b = d.addButton("OK2");
     b.addEventHandler(MouseEvent.MOUSE_CLICKED, (t) -> {
         d.getT().close();
     });
 };



}
