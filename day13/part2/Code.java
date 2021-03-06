import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Code {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] lines = input.split("\\n");
		HashMap<Integer, HashMap<String, Integer>> firewalls = new HashMap<>();
		int max = Integer.MIN_VALUE;
		// initialize
		for (String line : lines) {
			String[] parts = line.split(":");
			int i = Integer.valueOf(parts[0].trim());
			int depth = Integer.valueOf(parts[1].trim());
			HashMap<String, Integer> firewall = new HashMap<>();
			firewall.put("depth", depth);
			firewall.put("position", 0);
			firewall.put("step", 1);
			firewalls.put(i, firewall);
			max = Math.max(max, i + 1);
		}
		HashMap<Integer, Integer> tokens = new HashMap<>();
		int delay = 0;
		while (true) {
			tokens.put(delay, 0);
			ArrayList<Integer> remove = new ArrayList<>();
			for (int i : tokens.keySet()) {
				int j = tokens.get(i);
				if (firewalls.containsKey(j)) {
					HashMap<String, Integer> firewall = firewalls.get(j);
					if (firewall.get("position") == 0) {
						remove.add(i);
					}
				}
			}
			for (int i : remove) {
				tokens.remove(i);
			}
			for (int j = 0; j < max; j++) {
				if (firewalls.containsKey(j)) {
					HashMap<String, Integer> firewall = firewalls.get(j);
					int depth = firewall.get("depth");
					int position = firewall.get("position");
					int step = firewall.get("step");
					position += step;
					firewall.put("position", position);
					if (position == depth - 1 || position == 0) {
						firewall.put("step", step * -1);
					}
				}
			}
			// delay one
			HashMap<Integer, Integer> copy = new HashMap<>();
			for (int i : tokens.keySet()) {
				int position = tokens.get(i) + 1;
				if (position < max) {
					copy.put(i, position);
				} else {
					System.out.println(i);
					System.exit(0);
				}
			}
			tokens = copy;
			delay++;
		}

	}

}
