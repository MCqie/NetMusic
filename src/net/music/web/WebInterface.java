package net.music.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class WebInterface {


	public static String GetInterfaceText(String url) throws IOException {
		URL webURL = new URL(url);
		URLConnection uc = webURL.openConnection();
		HttpURLConnection huc = (HttpURLConnection) uc;
		huc.setRequestMethod("GET");
		huc.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		InputStream is = huc.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, StandardCharsets.UTF_8);
		BufferedReader br = new BufferedReader(isr);
		StringBuilder SUM = new StringBuilder();
		while (true) {
			String s = br.readLine();
			if (s == null) {
				break;
			}
			SUM.append(s);
		}

		return SUM.toString();
	}



	public static String getRedirectUrl(String path) throws Exception {
		try {
			URL serverUrl = new URL(path);
			HttpURLConnection conn = (HttpURLConnection) serverUrl.openConnection();
			conn.setRequestMethod("GET");
			// 必须设置false，否则会自动redirect到Location的地址
			conn.setInstanceFollowRedirects(false);

			conn.addRequestProperty("Accept-Charset", "UTF-8;");
			conn.addRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
			conn.connect();
			String location = conn.getHeaderField("Location");
			serverUrl = new URL(location);
			conn = (HttpURLConnection) serverUrl.openConnection();
			conn.setRequestMethod("GET");
			conn.addRequestProperty("Accept-Charset", "UTF-8;");
			conn.addRequestProperty("User-Agent",
					"Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.8) Firefox/3.6.8");
			conn.connect();
			return location;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
