import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Question1 {

	public static void main(String[] args) throws IOException {
		String[] rows = new String(Files.readAllBytes(Paths.get("input"))).trim().split("\\n");
		int sum = 0;
		for (int i = 0; i < rows.length; i++) {
			int smallest = Integer.MAX_VALUE;
			int largest = Integer.MIN_VALUE;
			String[] words = rows[i].trim().split("\\s+");
			for (int j = 0; j < words.length; j++) {
				int n = Integer.valueOf(words[j]);
				smallest = Math.min(smallest, n);
				largest = Math.max(largest, n);
			}
			sum += largest - smallest;
		}
		System.out.println(sum);
	}

}
