import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Question1 {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] lines = input.split("\\n");
		long[][][] p = new long[3][lines.length][3];
		boolean[] removed = new boolean[lines.length];
		// parse
		for (int i = 0; i < lines.length; i++) {
			String[] values = lines[i].split(">,");
			for (int j = 0; j < values.length; j++) {
				String[] values2 = values[j].split(",");
				for (int k = 0; k < values2.length; k++) {
					p[j][i][k] = Integer.parseInt(values2[k].replaceAll("[^0-9-]", ""));
				}
			}
			removed[i] = false;
		}
		// execute
		int minp = -1;
		long max = Integer.MAX_VALUE;
		while (true) {
			int c = 0;
			for (int i = 0; i < p[0].length; i++) {
				if (removed[i]) {
					continue;
				}
				// increase velocity
				for (int j = 0; j < 3; j++) {
					p[1][i][j] += p[2][i][j];
				}
				// increase position
				long d = 0;
				for (int j = 0; j < 3; j++) {
					p[0][i][j] += p[1][i][j];
					d += Math.abs(p[0][i][j]);
				}
				if (d > max) {
					removed[i] = true;
				}
				c++;
				minp = i;
			}
			if (c == 1) {
				break;
			}
		}
		System.out.println(minp);
	}

}
