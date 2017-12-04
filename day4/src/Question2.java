import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

public class Question2 {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] lines = input.split("\\n");
		int invalid = 0;
		for (int i = 0; i < lines.length; i++) {
			String[] words = lines[i].trim().split("\\s+");
			HashMap<String, Boolean> unique = new HashMap<>();
			for (String word : words) {
				char[] chars = word.toCharArray();
				Arrays.sort(chars);
				word = new String(chars);
				if (unique.containsKey(word)) {
					invalid++;
					break;
				}
				unique.put(word, true);
			}
		}
		System.out.println(lines.length - invalid);
	}

}