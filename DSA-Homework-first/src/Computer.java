import java.util.Arrays;

public class Computer {
	private String type;
	private double procSpeed;
	private String[] files;

	// "да валидират по подходящ начин тези клас данни,
	// съобразен с контекста на задачата" по-точно?
	public Computer() {
		this.type = "None";
		this.procSpeed = 0.0;
		this.files = null;
	}

	public Computer(Computer another) {
		this.type = another.type;
		this.procSpeed = another.procSpeed;
		this.files = new String[another.files.length];
		System.arraycopy(another.files.length, 0, this.files, 0, another.files.length);
	}

	public Computer(String type, double procSpeed, String[] files) {
		super();
		this.type = type;
		this.procSpeed = procSpeed;
		this.files = files;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getProcSpeed() {
		return procSpeed;
	}

	public void setProcSpeed(double procSpeed) {
		this.procSpeed = procSpeed;
	}

	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "Computer [type=" + type + ", procSpeed=" + procSpeed + ", files=" + Arrays.toString(files) + "]";
	}

	public static void main(String[] args) {

	}

}
