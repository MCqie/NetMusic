
package net.music.web;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class Download {
	@SuppressWarnings("resource")
	public static void DownloadFile(String url,String Savepath) {
		try(InputStream in=new URL(url).openStream()){
			Path target=Paths.get(Savepath);
			Files.createDirectories(target.getParent());
			Files.copy(in,target,StandardCopyOption.REPLACE_EXISTING);
		}catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	    
}
