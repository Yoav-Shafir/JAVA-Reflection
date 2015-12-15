package annotations.constructorParameterAnnotations;

import annotations.Platform;
import annotations.Token;

public class TheFinalClass {
	private String data;
	public TheFinalClass() {
	
	}
	public TheFinalClass(
		@Token(name="Constructor Parameter", code=50)
		@Platform(what="Unix")
		String data) {
		this.data = data;
	}
	public String read() {
		return this.data;
	}
	public void write() {
		
	}
}
