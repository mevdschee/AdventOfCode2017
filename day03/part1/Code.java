import java.io.IOException;

public class Code {

	public static void main(String[] args) throws IOException {
		int input = 312051;
		int size = (int) Math.ceil(Math.sqrt(input));
		if (size % 2 == 0) {
			size += 1;
		}
		int origin = size / 2;
		int x = origin;
		int y = origin;
		int[][] field = new int[size][size];
		int n = 1;
		for (int e = 1; e <= size; e += 2) {
			for (int i = 0; i < e - 2; i++) {
				field[x][y] = n++;
				y -= 1;
			}
			for (int i = 0; i < e - 1; i++) {
				field[x][y] = n++;
				x -= 1;
			}
			for (int i = 0; i < e - 1; i++) {
				field[x][y] = n++;
				y += 1;
			}
			for (int i = 0; i < e; i++) {
				field[x][y] = n++;
				x += 1;
			}
		}
		int val = 0;
		for (y = 0; y < size; y++) {
			for (x = 0; x < size; x++) {
				if (field[x][y] == input) {
					val = Math.abs(x - origin) + Math.abs(y - origin);
				}
			}
		}
		System.out.println(val);
	}

}
