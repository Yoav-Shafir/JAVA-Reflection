package annotations.constructorAnnotations;

import annotations.Platform;
import annotations.Token;

public class MyLastClass {
	private String data;
	@Token(name="Default Constructor", code=40)
	@Platform(what="Unix")
	public MyLastClass() {
	
	}
	@Token(name="Constructor", code=40)
	public MyLastClass(String data) {
		this.data = data;
	}
	public String read() {
		return this.data;
	}
	public void write(String value, int num) {
		
	}
}
