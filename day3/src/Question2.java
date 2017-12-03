import java.io.IOException;

public class Question2 {

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
				sum += field[i][j];
			}
		}
		if (sum == 0) {
			sum = 1;
		}
		field[x][y] = sum;
	}

	public static void main(String[] args) throws IOException {
		int input = 312051;
		int size = (int) Math.ceil(Math.sqrt(input));
		if (size % 2 == 0) {
			size += 1;
		}
		int cx = size / 2;
		int cy = size / 2;
		int x = cx;
		int y = cy;
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
