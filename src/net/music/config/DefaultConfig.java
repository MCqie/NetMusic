package net.music.config;

import java.io.File;
import java.io.IOException;

public class DefaultConfig extends Config {
	public DefaultConfig(){
		super();
	}
	@Override
	@Deprecated
	public void Save(String path) throws IOException {
		// TODO Auto-generated method stub
		super.Save(path);
	}

	public void SaveDefaultConfig() {
		try {
			Save(System.getProperty("user.dir") + "\\Config.cfg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static DefaultConfig LoadDefaultConfig() throws IOException {
		new DefaultConfig().initDefaultConfig();
		try {
			return toDefaultConfig( Load(System.getProperty("user.dir") + "\\Config.cfg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static DefaultConfig toDefaultConfig(Config cfg) {
		DefaultConfig dcfg = new DefaultConfig();
		dcfg.config = cfg.config;
		return dcfg;
	}
	public void initDefaultConfig() throws IOException {
		File f=new File(System.getProperty("user.dir") + "\\Config.cfg");
		if (!f.exists()) {f.createNewFile();
		config=DefaultValue;
		SaveDefaultConfig();
		}
	}

}
