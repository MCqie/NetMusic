package net.music.Exception;

import net.music.util.Dialog;

public class ExceptionDialogs {
    public static void show(ErrorType type,Builder builder){
        Dialog d=new Dialog(type.title, type.msg);
        builder.Build(d);
        d.show();
    }
    public static void show(ErrorType type,Builder... builder){
        Dialog d=new Dialog(type.title, type.msg);
        for (Builder b:builder){
            b.Build(d);
        }
        d.show();
    }

}
