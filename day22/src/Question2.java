import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Question2 {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] lines = input.split("\\n");
		HashMap<String, Integer> infected = new HashMap<>();
		// initialize
		for (int i = 0; i < lines.length; i++) {
			for (int j = 0; j < lines[i].length(); j++) {
				String coords = j + "," + i;
				infected.put(coords, lines[i].charAt(j) == '#' ? 2 : 0);
			}
		}
		// execute
		String status = "CWIF";
		int x = lines[0].length() / 2;
		int y = lines.length / 2;
		String dirs = "URDL";
		int d = 0;
		int count = 0;
		for (int i = 0; i < 10000000; i++) {

			// for (int k = -3; k < 6; k++) {
			// for (int j = -3; j < 6; j++) {
			// String coords = j + "," + k;
			// int stat = infected.containsKey(coords) ? infected.get(coords) :
			// 0;
			// char c = status.charAt(stat);
			// if (c == 'I') {
			// c = '#';
			// } else if (c == 'C') {
			// c = '.';
			// }
			// String s = (x + "," + y).equals(coords) ? "[" + c + "]" : " " + c
			// + " ";
			// System.out.print(s);
			// }
			// System.out.println();
			// }
			// System.out.println();

			String coords = x + "," + y;
			int stat = infected.containsKey(coords) ? infected.get(coords) : 0;
			int t = 0;
			switch (status.charAt(stat)) {
			case 'C':
				t = 3;
				break;
			case 'W':
				t = 0;
				break;
			case 'I':
				t = 1;
				break;
			case 'F':
				t = 2;
				break;
			}
			d = (d + t) % 4;
			stat = (stat + 1) % 4;
			count += status.charAt(stat) == 'I' ? 1 : 0;
			infected.put(coords, stat);
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
