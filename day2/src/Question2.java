import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Question2 {

	public static void main(String[] args) throws IOException {
		String[] rows = new String(Files.readAllBytes(Paths.get("input"))).trim().split("\\n");
		int sum = 0;
		for (int i = 0; i < rows.length; i++) {
			String[] words = rows[i].trim().split("\\s+");
			for (int j = 0; j < words.length; j++) {
				int n = Integer.valueOf(words[j]);
				for (int k = j + 1; k < words.length; k++) {
					int m = Integer.valueOf(words[k]);
					if (n % m == 0) {
						sum += n / m;
					}
					if (m % n == 0) {
						sum += m / n;
					}
				}
			}
		}
		System.out.println(sum);
	}

}
