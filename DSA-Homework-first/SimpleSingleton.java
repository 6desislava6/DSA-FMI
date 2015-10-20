public class SimpleSingleton {
	private static SimpleSingleton instance = null;
	private SimpleSingleton(){
		
	}
	public static SimpleSingleton getInstance(){
		if (instance == null){
			instance = new SimpleSingleton();
		}
		return instance;
	}
}
