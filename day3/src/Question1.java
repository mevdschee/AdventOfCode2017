import java.io.IOException;

public class Question1 {

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
		for (y = 0; y < size; y++) {
			for (x = 0; x < size; x++) {
				if (field[x][y] == input) {
					System.out.println(Math.abs(x - cx) + Math.abs(y - cy));
				}
			}
		}
	}

}
