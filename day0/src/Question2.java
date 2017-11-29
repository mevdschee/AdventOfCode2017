
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Question2 {

	// Assignment: https://pastebin.com/BMd61PUv
	// Data: https://pastebin.com/wGmzZHeq
	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input")), "UTF-8");
		String[] sentences = input.split("\\s*Start\\s*");
		ArrayList<int[]> a = new ArrayList<>();
		ArrayList<int[]> b = new ArrayList<>();
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
					a.add(new int[] { x, y });
					break;
				case "B":
					b.add(new int[] { x, y });
					break;
				}
			}
		}
		for (int[] p1 : a) {
			for (int[] p2 : b) {
				longest = Math.max(longest, Math.abs(p2[0] - p1[0]) + Math.abs(p2[1] - p1[1]));

			}
		}
		System.out.println(longest);
	}

}
