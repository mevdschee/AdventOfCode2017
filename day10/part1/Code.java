import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Code {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] words = input.split(",");
		int[] lengths = new int[words.length];
		for (int i = 0; i < words.length; i++) {
			lengths[i] = Integer.valueOf(words[i]);
		}
		int size = 256;
		int[] circle = new int[size];
		for (int i = 0; i < size; i++) {
			circle[i] = i;
		}
		int pos = 0;
		int skip = 0;
		for (int i = 0; i < lengths.length; i++) {
			int length = lengths[i];
			for (int j = 0; j < length / 2; j++) {
				int p1 = (pos + j) % circle.length;
				int p2 = (pos + length - 1 - j) % circle.length;
				int tmp = circle[p1];
				circle[p1] = circle[p2];
				circle[p2] = tmp;
			}
			pos = (pos + length + skip) % circle.length;
			skip++;
		}
		System.out.println(circle[0] * circle[1]);
	}

}
