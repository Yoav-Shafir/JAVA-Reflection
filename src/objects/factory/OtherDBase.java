package objects.factory;

public class OtherDBase implements IDBase {
	
	public OtherDBase() {
		
	}
	
	@Override
	public void write(Record obj) {
		System.out.println("OtherDBase: write");
	}

	@Override
	public Record read() {
		System.out.println("OtherDBase: read");
		return new Record();
	}
			
}
