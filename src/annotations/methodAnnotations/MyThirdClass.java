package annotations.methodAnnotations;

import annotations.Platform;
import annotations.Token;

public class MyThirdClass {
	private String data;
	public MyThirdClass() {
		// TODO Auto-generated constructor stub
	}
	public MyThirdClass(String data) {
		this.data = data;
	}
	@Token(name="Void Method", code=30)
	public String read() {
		return this.data;
	}
	@Token(name="Method", code=30)
	@Platform(what="Unix")
	public void write(String value, int num) {
		
	}
	
}