import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Question2 {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input")));
		String[] lines = input.split("\\n");
		int width = lines[0].length();
		int height = lines.length;
		char field[][] = new char[width][height];
		// initialize
		for (int y = 0; y < lines.length; y++) {
			for (int x = 0; x < lines[y].length(); x++) {
				field[x][y] = lines[y].charAt(x);
			}
		}
		// execute
		int cx = lines[0].indexOf('|');
		int cy = 0;
		int px = 0;
		int py = 0;
		int dx = 0;
		int dy = 1;
		boolean line = true;
		int steps = 1;
		while (true) {
			char c = field[cx][cy];
			if (c == '+') {
				if (field[cx][cy - 1] == '|' && !(cx == px && cy - 1 == py)) {
					dx = 0;
					dy = -1;
				}
				if (field[cx][cy + 1] == '|' && !(cx == px && cy + 1 == py)) {
					dx = 0;
					dy = 1;
				}
				if (field[cx - 1][cy] == '-' && !(cx - 1 == px && cy == py)) {
					dx = -1;
					dy = 0;
				}
				if (field[cx + 1][cy] == '-' && !(cx + 1 == px && cy == py)) {
					dx = 1;
					dy = 0;
				}
			}
			if (c == '-' || c == '|') {
				line = true;
			} else if (line == false) {
				break;
			} else {
				line = false;
			}
			px = cx;
			py = cy;
			cx += dx;
			cy += dy;
			steps += 1;
		}
		System.out.println(steps - 1);
	}

}
