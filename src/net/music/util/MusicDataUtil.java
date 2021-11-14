package net.music.util;

import java.io.IOException;
import java.net.URLEncoder;

import net.music.Dao.MusicDao;
import net.music.lyric.Lyric;
import net.music.player.MediaImp;
import net.music.player.MediaList;
import net.music.web.DataAnalysis;
import net.music.web.WebInterface;

public class MusicDataUtil {
	protected MusicDataUtil() {}
	

	@Deprecated
	public static MediaImp PackMusic(MusicDao music) throws Exception {
		return new MediaImp(music.getUrl(), music.getName(), music.getPicSrc(), music.getId(),music.getLength(),music.getArtist());
	}

	public static MediaList SearchByName(String name) throws IOException {
	String ana=	WebInterface.GetInterfaceText(
			"http://music.163.com/api/search/get/web?csrf_token=hlpretag=&hlposttag=&s="
					+URLEncoder.encode(name,"UTF-8")
					+"&type=1&offset=0&total=true&limit=20");
		return DataAnalysis.analysis(ana);
	}


}
