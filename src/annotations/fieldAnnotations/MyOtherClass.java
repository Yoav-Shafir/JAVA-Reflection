package annotations.fieldAnnotations;

import annotations.Platform;
import annotations.Token;

public class MyOtherClass {
	@Token(name="Field", code=20)
	@Platform(what="Unix")
	private String data;
	public MyOtherClass() {
		// TODO Auto-generated constructor stub
	}
	public MyOtherClass(String data) {
		this.data = data;
	}
	public String read() {
		return this.data;
	}
	public void write(String value, int num) {
		
	}
	
}
