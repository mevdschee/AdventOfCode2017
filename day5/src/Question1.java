import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Question1 {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] lines = input.split("\\n");
		int[] instructions = new int[lines.length];
		int jumps = 0;
		for (int i = 0; i < lines.length; i++) {
			instructions[i] = Integer.valueOf(lines[i]);
		}
		int pos = 0;
		while (true) {
			int offset = instructions[pos];
			instructions[pos]++;
			jumps++;
			pos += offset;
			if (pos < 0 || pos >= instructions.length) {
				break;
			}
		}
		System.out.println(jumps);
	}

}
