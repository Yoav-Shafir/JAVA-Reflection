package annotations.classAnnotations;

import annotations.Platform;
import annotations.Token;

@Token(name="Class", code=10)
@Platform(what="Unix")
public class MyClass {
	private String data;
	public MyClass() {
		// TODO Auto-generated constructor stub
	}
	public MyClass(String data) {
		this.data = data;
	}
	public String read() {
		return this.data;
	}
	public void write(String value, int num) {
		
	}
}
