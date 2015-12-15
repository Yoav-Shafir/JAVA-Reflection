package objects.factory;

public class MyDBase implements IDBase {
	
	public MyDBase() {

	}
	
	@Override
	public void write(Record obj) {
		System.out.println("MyDBase: write");
	}

	@Override
	public Record read() {
		System.out.println("MyDBase: read");
		return new Record();
	}

}
