import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Code {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] lines = input.split("\\n");
		HashMap<String, Boolean> infected = new HashMap<>();
		// initialize
		for (int i = 0; i < lines.length; i++) {
			for (int j = 0; j < lines[i].length(); j++) {
				String coords = j + "," + i;
				infected.put(coords, lines[i].charAt(j) == '#');
			}
		}
		// execute
		int x = lines[0].length() / 2;
		int y = lines.length / 2;
		String dirs = "URDL";
		int d = 0;
		int count = 0;
		for (int i = 0; i < 10000; i++) {

			// for (int k = -3; k < 6; k++) {
			// for (int j = -3; j < 6; j++) {
			// String coords = j + "," + k;
			// char c = infected.containsKey(coords) && infected.get(coords) ?
			// '#' : '.';
			// String s = (x + "," + y).equals(coords) ? "[" + c + "]" : " " + c
			// + " ";
			// System.out.print(s);
			// }
			// System.out.println();
			// }
			// System.out.println();

			String coords = x + "," + y;
			boolean inf = infected.containsKey(coords) && infected.get(coords);
			int t = inf ? 1 : 3;
			d = (d + t) % 4;
			inf = !inf;
			count += inf ? 1 : 0;
			infected.put(coords, inf);
			switch (dirs.charAt(d)) {
			case 'U':
				y -= 1;
				break;
			case 'R':
				x += 1;
				break;
			case 'D':
				y += 1;
				break;
			case 'L':
				x -= 1;
				break;
			}

		}
		System.out.println(count);
	}

}
