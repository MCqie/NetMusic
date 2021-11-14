package net.music.util;

public enum PlayStatus {
	CYCLE("CYCLE"),RANDOM("RANDOM"),DEFAULT("DEFAULT");
	PlayStatus(String string) {
		
	}
	public static PlayStatus STPlayStatus(String s) {
		if("CYCLE".equals(s)) {
			return PlayStatus.CYCLE;
		}else if("RANDOM".equals(s)) {
			return PlayStatus.RANDOM;
		}else if("DEFAULT".equals(s)) {
			return PlayStatus.DEFAULT;
		}
		return null;

	}

}
