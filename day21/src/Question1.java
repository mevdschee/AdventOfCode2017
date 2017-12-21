import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Question1 {

	private static String reverse(String s) {
		return new StringBuilder(s).reverse().toString();
	}

	private static String[] rotate(String[] prev) {
		String[] next = new String[prev.length];
		int n = prev.length;
		for (int x = n - 1; x >= 0; x--) {
			next[x] = "";
			for (int y = 0; y < n; y++) {
				next[x] += prev[y].charAt(x);
			}
		}
		return next;
	}

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] lines = input.split("\\n");
		HashMap<Integer, HashMap<String[], String[]>> rules = new HashMap<>();
		rules.put(0, new HashMap<>());
		rules.put(1, new HashMap<>());
		// initialize
		for (int i = 0; i < lines.length; i++) {
			String[] patterns = lines[i].split(" => ");
			String[] p0 = patterns[0].split("/");
			String[] p1 = patterns[1].split("/");
			if (p0.length == 2 && p1.length == 3) {
				String[] v = new String[] { p1[0], p1[1], p1[2] };
				for (int r = 0; r < 4; r++) {
					rules.get(0).put(new String[] { p0[0], p0[1] }, v);
					rules.get(0).put(new String[] { reverse(p0[0]), reverse(p0[1]) }, v);
					rules.get(0).put(new String[] { p0[1], p0[0] }, v);
					rules.get(0).put(new String[] { reverse(p0[1]), reverse(p0[0]) }, v);
					p0 = rotate(p0);
				}
			} else if (p0.length == 2 && p1.length == 3) {
				String[] v = new String[] { p1[0], p1[1], p1[2], p1[3] };
				for (int r = 0; r < 4; r++) {
					rules.get(1).put(new String[] { p0[0], p0[1], p0[2] }, v);
					rules.get(1).put(new String[] { reverse(p0[0]), reverse(p0[1]), reverse(p0[2]) }, v);
					rules.get(1).put(new String[] { p0[2], p0[1], p0[0] }, v);
					rules.get(1).put(new String[] { reverse(p0[2]), reverse(p0[1]), reverse(p0[0]) }, v);
					p0 = rotate(p0);
				}
			}
		}
		// execute
		String[] prev = new String[] { ".#.", "..#", "###" };
		String[] next;
		for (int i = 0; i < 5; i++) {
			int sp = (i % 2 == 0 ? 3 : 2);
			int cp = i + 1;
			int sn = (i % 2 == 0 ? 2 : 3);
			int cn = i + 2;
			next = new String[sn * cn];
			for (int x = 0; x < cp; x++) {
				for (int y = 0; y < cp; y++) {
					String[] cell = new String[sp];
					for (int j = 0; j < sp; j++) {
						cell[j] = prev[y * sp + j].substring(x * sp, (x + 1) * sp);
					}
					// append(next, match(prev));
				}
			}
		}
		// System.out.println(sound);
	}

}
