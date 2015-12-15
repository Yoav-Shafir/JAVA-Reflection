package annotations.methodParameterAnnotations;

import annotations.Platform;
import annotations.Token;

public class YetAnotherClass {
	private String data;
	public YetAnotherClass() {
	
	}
	public YetAnotherClass(String data) {
		this.data = data;
	}
	public String read() {
		return this.data;
	}
	public void write(
		@Token(name="Method Parameter", code=50) 
		@Platform(what="Unix")
		String value, 
		@Token(name="Method Parameter", code=50) 
		int num) {
		
	}
}
