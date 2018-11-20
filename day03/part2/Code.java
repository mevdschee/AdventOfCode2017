import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Code {

	private static void fill(int[][] field, int x, int y) {
		int sum = 0;
		for (int i = x - 1; i <= x + 1; i++) {
			for (int j = y - 1; j <= y + 1; j++) {
				if (i < 0 || i >= field.length) {
					continue;
				}
				if (j < 0 || j >= field[i].length) {
					continue;
				}
				if (i == x && j == y) {
					continue;
				}
				sum += field[i][j];
			}
		}
		if (sum == 0) {
			sum = 1;
		}
		field[x][y] = sum;
	}

	public static void main(String[] args) throws IOException {
		int input = Integer.parseInt(new String(Files.readAllBytes(Paths.get("input"))).trim());
		int size = (int) Math.ceil(Math.sqrt(input));
		if (size % 2 == 0) {
			size += 1;
		}
		int origin = size / 2;
		int x = origin;
		int y = origin;
		int[][] field = new int[size][size];
		for (int e = 1; e <= size; e += 2) {
			for (int i = 0; i < e - 2; i++) {
				fill(field, x, y);
				y -= 1;
			}
			for (int i = 0; i < e - 1; i++) {
				fill(field, x, y);
				x -= 1;
			}
			for (int i = 0; i < e - 1; i++) {
				fill(field, x, y);
				y += 1;
			}
			for (int i = 0; i < e; i++) {
				fill(field, x, y);
				x += 1;
			}
		}
		int min = Integer.MAX_VALUE;
		for (y = 0; y < size; y++) {
			for (x = 0; x < size; x++) {
				if (field[x][y] >= input) {
					min = Math.min(min, field[x][y]);
				}
			}
		}
		System.out.println(min);
	}

}
