import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Question1 {

	// Assignment: https://pastebin.com/BMd61PUv
	// Data: https://pastebin.com/wGmzZHeq
	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] sentences = input.split("\\s*Start\\s*");
		int longest = -1;
		for (String sentence : sentences) {
			int x = 0;
			int y = 0;
			String[] words = sentence.split(",\\s*");
			for (String word : words) {
				switch (word) {
				case "Up":
					y -= 1;
					break;
				case "Down":
					y += 1;
					break;
				case "Left":
					x -= 1;
					break;
				case "Right":
					x += 1;
					break;
				case "A":
					longest = Math.max(longest, Math.abs(x) + Math.abs(y));
					break;
				case "B":
					longest = Math.max(longest, Math.abs(x) + Math.abs(y));
					break;
				}
			}
		}
		System.out.println(longest);
	}

}
