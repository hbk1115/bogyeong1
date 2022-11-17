package watersort;

public class Clear {
	static int clear = 0;
	
	public Clear() {
	}
	public void run(int i) {
		if(clear < i) {
			clear = i;
		}
		
	}

}