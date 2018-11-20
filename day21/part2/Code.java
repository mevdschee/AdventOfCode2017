import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Code {

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

	private static String[] extract(String[] prev, int x, int y, int sp) {
		String[] cell = new String[sp];
		for (int j = 0; j < sp; j++) {
			cell[j] = prev[y * sp + j].substring(x * sp, (x + 1) * sp);
		}
		return cell;
	}

	private static void append(String[] next, int x, int y, String[] cell) {
		int sn = cell.length;
		for (int j = 0; j < sn; j++) {
			if (next[y * sn + j] == null) {
				next[y * sn + j] = "";
			}
			next[y * sn + j] += cell[j];
		}
	}

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] lines = input.split("\\n");
		HashMap<Integer, HashMap<String, String>> rules = new HashMap<>();
		rules.put(2, new HashMap<>());
		rules.put(3, new HashMap<>());
		// initialize
		for (int i = 0; i < lines.length; i++) {
			String[] patterns = lines[i].split(" => ");
			String[] p0 = patterns[0].split("/");
			String p1 = patterns[1];
			int sp = p0.length;
			if (sp == 2) {
				HashMap<String, String> rsp = rules.get(sp);
				for (int r = 0; r < 4; r++) {
					rsp.put(String.join("/", new String[] { p0[0], p0[1] }), p1);
					rsp.put(String.join("/", new String[] { reverse(p0[0]), reverse(p0[1]) }), p1);
					rsp.put(String.join("/", new String[] { p0[1], p0[0] }), p1);
					rsp.put(String.join("/", new String[] { reverse(p0[1]), reverse(p0[0]) }), p1);
					p0 = rotate(p0);
				}
			} else if (sp == 3) {
				HashMap<String, String> rsp = rules.get(sp);
				for (int r = 0; r < 4; r++) {
					rsp.put(String.join("/", new String[] { p0[0], p0[1], p0[2] }), p1);
					rsp.put(String.join("/", new String[] { reverse(p0[0]), reverse(p0[1]), reverse(p0[2]) }), p1);
					rsp.put(String.join("/", new String[] { p0[2], p0[1], p0[0] }), p1);
					rsp.put(String.join("/", new String[] { reverse(p0[2]), reverse(p0[1]), reverse(p0[0]) }), p1);
					p0 = rotate(p0);
				}
			}
		}
		// execute
		String[] prev = new String[] { ".#.", "..#", "###" };
		int iterations = 18;
		String[] next = new String[] {};
		for (int i = 0; i < iterations; i++) {
			int sp, cp = 1;
			for (sp = 2; sp <= 3; sp++) {
				cp = prev.length / sp;
				if (prev.length % sp == 0) {
					break;
				}
			}
			HashMap<String, String> rsp = rules.get(sp);
			next = new String[(sp + 1) * cp];
			for (int y = 0; y < cp; y++) {
				for (int x = 0; x < cp; x++) {
					String[] ccell = extract(prev, x, y, sp);
					String[] ncell = rsp.get(String.join("/", ccell)).split("/");
					append(next, x, y, ncell);
				}
			}
			prev = next;
		}
		int count = 0;
		for (int i = 0; i < next.length; i++) {
			for (int j = 0; j < next[i].length(); j++) {
				if (next[i].charAt(j) == '#')
					count++;
			}
		}
		System.out.println(count);
	}

}
