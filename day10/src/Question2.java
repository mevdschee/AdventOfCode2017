import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Question2 {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		int[] add = new int[] { 17, 31, 73, 47, 23 };
		int[] lengths = new int[input.length() + add.length];
		for (int i = 0; i < lengths.length; i++) {
			if (i < input.length()) {
				lengths[i] = Integer.valueOf(input.charAt(i));
			} else {
				lengths[i] = Integer.valueOf(add[i - input.length()]);
			}
		}
		int size = 256;
		int[] circle = new int[size];
		for (int i = 0; i < size; i++) {
			circle[i] = i;
		}
		int rounds = 64;
		int pos = 0;
		int skip = 0;
		for (int r = 0; r < rounds; r++) {
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
		}
		int[] dense = new int[circle.length / 16];
		for (int i = 0; i < dense.length; i++) {
			for (int j = 0; j < 16; j++) {
				dense[i] ^= circle[i * 16 + j];
			}
		}
		for (int i = 0; i < dense.length; i++) {
			System.out.print(String.format("%02x", dense[i]));
		}
	}

}
