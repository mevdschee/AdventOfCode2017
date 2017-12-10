import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Question1 {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] lines = input.split("\\n");
		for (String line : lines) {
			int depth = 0;
			int count = 0;
			int i = 0;
			boolean garbage = false;
			while (i < line.length()) {
				if (!garbage) {
					switch (line.charAt(i)) {
					case '{':
						depth++;
						break;
					case '<':
						garbage = true;
						break;
					case '}':
						count += depth;
						depth--;
						break;
					}
				} else {
					switch (line.charAt(i)) {
					case '!':
						i++;
						break;
					case '>':
						garbage = false;
					}
				}
				i++;
			}
			System.out.println(count);
		}
	}

}
