import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Code {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		int sum = 0;
		for (int i = 0; i < input.length(); i++) {
			int n = (i + 1) % input.length();
			if (input.charAt(n) == input.charAt(i)) {
				sum += input.charAt(i) - '0';
			}
		}
		System.out.println(sum);
	}

}
