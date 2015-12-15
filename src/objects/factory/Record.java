package objects.factory;

public class Record {
	private int num;
	
	public Record() {
		this.num = 0;
	}
	
	public Record(int num) {
		this.num = num;
	}
	
	public final int getNum() {
		return this.num;
	}
}
