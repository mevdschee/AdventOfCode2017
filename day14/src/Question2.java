import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Question2 {

	private static int[] knotHash(String input) throws IOException {
		int[] add = new int[] { 17, 31, 73, 47, 23 };
		int[] lengths = new int[input.length() + add.length];
		for (int i = 0; i < lengths.length; i++) {
			if (i < input.length()) {
				lengths[i] = Integer.valueOf(input.charAt(i));
			} else {
				lengths[i] = Integer.valueOf(add[i - input.length()]);
			}
		}
		int size = 256;
		int[] circle = new int[size];
		for (int i = 0; i < size; i++) {
			circle[i] = i;
		}
		int rounds = 64;
		int pos = 0;
		int skip = 0;
		for (int r = 0; r < rounds; r++) {
			for (int i = 0; i < lengths.length; i++) {
				int length = lengths[i];
				for (int j = 0; j < length / 2; j++) {
					int p1 = (pos + j) % circle.length;
					int p2 = (pos + length - 1 - j) % circle.length;
					int tmp = circle[p1];
					circle[p1] = circle[p2];
					circle[p2] = tmp;
				}
				pos = (pos + length + skip) % circle.length;
				skip++;
			}
		}
		int[] dense = new int[circle.length / 16];
		for (int i = 0; i < dense.length; i++) {
			for (int j = 0; j < 16; j++) {
				dense[i] ^= circle[i * 16 + j];
			}
		}
		return dense;
	}

	private static void remove(HashMap<Integer, HashMap<Integer, Boolean>> coords, int x, int y) {
		// remove
		coords.get(y).remove(x);
		if (coords.get(y).size() == 0) {
			coords.remove(y);
		}
		// remove neighbors
		ArrayList<int[]> n = new ArrayList<>();
		n.add(new int[] { x - 1, y });
		n.add(new int[] { x + 1, y });
		n.add(new int[] { x, y - 1 });
		n.add(new int[] { x, y + 1 });
		for (int i = 0; i < n.size(); i++) {
			int x2 = n.get(i)[0];
			int y2 = n.get(i)[1];
			if (coords.containsKey(y2)) {
				if (coords.get(y2).containsKey(x2)) {
					remove(coords, x2, y2);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		int lines = 128;
		// initialize
		HashMap<Integer, HashMap<Integer, Boolean>> coords = new HashMap<>();
		for (int i = 0; i < lines; i++) {
			int[] hash = knotHash(input + "-" + i);
			for (int j = 0; j < hash.length; j++) {
				for (int b = 0; b < 8; b++) {
					if ((hash[j] & (1 << b)) != 0) {
						int y = i;
						int x = j * 8 + 7 - b;
						if (!coords.containsKey(y)) {
							coords.put(y, new HashMap<>());
						}
						coords.get(y).put(x, true);
					}
				}
			}
		}
		// loop
		int regions = 0;
		while (coords.keySet().size() > 0) {
			int y = coords.keySet().iterator().next();
			int x = coords.get(y).keySet().iterator().next();
			remove(coords, x, y);
			regions++;
		}
		System.out.println(regions);
	}

}
