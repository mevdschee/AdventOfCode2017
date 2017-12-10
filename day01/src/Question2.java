import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Question2 {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		int plus = input.length() / 2;
		int sum = 0;
		for (int i = 0; i < input.length(); i++) {
			int n = (i + plus) % input.length();
			if (input.charAt(n) == input.charAt(i)) {
				sum += input.charAt(i) - '0';
			}
		}
		System.out.println(sum);
	}

}
