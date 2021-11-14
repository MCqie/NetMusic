package net.music.web;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import net.music.Dao.MusicDao;
import net.music.lyric.Lyric;
import net.music.player.MediaList;

public class DataAnalysis {
	public static MediaList analysis(String analData) {
		JSONObject Json = JSONObject.parseObject(analData);

		// System.out.println(Json);
		JSONArray ja = (JSONArray) ((JSONObject) Json.get("result")).get("songs");
		MediaList mediaList = new MediaList();

		for (Object a : ja) {
			StringBuilder Artists= new StringBuilder();
			JSONObject SONG = (JSONObject) a;
			// System.out.println((JSONObject)T);
			int id = SONG.getIntValue("id");
			
			String url = null;
			String desc = null;
			int fee = SONG.getInteger("fee");
			try {
				url = "http://music.163.com/song/media/outer/url?id=" + id + ".mp3";
//				if (SONG.getJSONObject("ar").getString("alias") != null) {
//					System.out.println(SONG.getJSONObject("album"));
//					desc = SONG.getJSONObject("album").getJSONArray("alia").getString(0);
//				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			for(Object artista:SONG.getJSONArray("artists")) {
				JSONObject artist=(JSONObject)artista;
				Artists.append(artist.getString("name")).append(" - ");
			}
			
			if (fee != 1) {
				MusicDao music = new MusicDao(id, url, SONG.getString("name"), desc,SONG.getIntValue("duration"), Artists.toString());
				mediaList.addMedia(music);
				// System.out.println(mediaList.getList().size());
			}
		}

		return mediaList;
	}

	public static Lyric analysisLyric(String analData){
		JSONObject json=JSONObject.parseObject(analData);
		return new Lyric(json.getString("lyric"));
	}
}
