import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Question2 {

	private static int dist(int x0, int y0, int x1, int y1) {
		int dx = x1 - x0;
		int dy = y1 - y0;

		if (Math.signum(dx) == Math.signum(dy)) {
			return Math.abs(dx + dy);
		} else {
			return Math.max(Math.abs(dx), Math.abs(dy));
		}
	}

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] directions = input.split(",");
		int x = 0;
		int y = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < directions.length; i++) {
			switch (directions[i]) {
			case "n":
				y += 1;
				break;
			case "nw":
				x -= 1;
				y += 1;
				break;
			case "sw":
				x -= 1;
				break;
			case "s":
				y -= 1;
				break;
			case "se":
				x += 1;
				y -= 1;
				break;
			case "ne":
				x += 1;
				break;
			}
			max = Math.max(max, dist(0, 0, x, y));
		}
		System.out.println(max);
	}

}
