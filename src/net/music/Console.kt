package net.music;

import javafx.application.Application
import javafx.scene.media.MediaPlayer
import javafx.stage.Stage
import net.music.player.MediaList
import net.music.player.Player
import net.music.util.MusicDataUtil
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.Exception

fun main() {
    val a=Console()

    a.runit()
}
class Console :Application() {
companion object {
    var i: Int = 0
    lateinit var medias: MediaList;
    var aw = aa(null)
}
    fun runit() {
        launch()
}

    override fun start(primaryStage: Stage?) {
        val ipr = InputStreamReader(System.`in`, Charsets.UTF_8)
        val br = BufferedReader(ipr)
        try{
        while (true) {
            val a = br.readLine();
            val t = a.split(" ");
            when (t[0]) {
                "Search" -> {
                    medias = MusicDataUtil.SearchByName(t[1])
                    medias.list.forEach {
                        println("$i#${it.name}#${it.artist}")
                        i++;
                    }
                    i = 0
                }
                "Play"->{
                    if(t.size==2){
                      aw.p  = MediaPlayer(medias.list[t[1].toInt()].toMediaImp().media)
                         aw.p!!.play()
                    }
                    if(t.size==1){
                        aw.p?.play()
                    }
                }
            }
        }
    }catch (e:Exception){
            e.printStackTrace()
        }}
}

data class aa(
        var p: MediaPlayer?
)