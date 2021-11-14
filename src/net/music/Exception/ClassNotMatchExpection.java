package net.music.Exception;

public class ClassNotMatchExpection extends Exception{


	private static final long serialVersionUID = 1L;
	
	public ClassNotMatchExpection(String reason) {
		super(reason);
		this.addSuppressed(new ClassCastException());
	}
}
