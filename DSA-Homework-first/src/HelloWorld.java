
import java.util.Scanner;

public class HelloWorld {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String answer = new String();
		
		while (!answer.equals("q")) {
			if (answer != "") System.out.println(String.format("Hello %s!", answer));
			System.out.println("Please enter a name: ");
			answer = scanner.next();
			
		} 
		System.out.println("Bye!");
	}

}
