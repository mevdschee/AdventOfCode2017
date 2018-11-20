import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Code {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] lines = input.split("\\n");
		HashMap<Integer, HashMap<Integer, Boolean>> connections = new HashMap<>();
		HashMap<Integer, Boolean> numbers = new HashMap<>();
		// initialize
		for (String line : lines) {
			String[] parts = line.split("<->");
			int source = Integer.valueOf(parts[0].trim());
			parts = parts[1].trim().split(",");
			for (String part : parts) {
				int target = Integer.valueOf(part.trim());
				if (!connections.containsKey(source)) {
					connections.put(source, new HashMap<>());
				}
				connections.get(source).put(target, true);
				numbers.put(source, true);
				if (!connections.containsKey(target)) {
					connections.put(target, new HashMap<>());
				}
				connections.get(target).put(source, true);
				numbers.put(target, true);
			}
		}
		int groups = 0;
		while (true) {
			HashMap<Integer, Boolean> connected = new HashMap<>();
			connected.put(numbers.keySet().iterator().next(), true);
			// execute
			int size = 0;
			while (true) {
				for (int source : connections.keySet()) {
					if (connected.containsKey(source)) {
						for (int target : connections.get(source).keySet()) {
							connected.put(target, true);
						}
					}
				}
				// no more additions
				if (connected.keySet().size() == size) {
					break;
				}
				size = connected.keySet().size();
			}
			// remove numbers that are in this group
			for (int number : connected.keySet()) {
				numbers.remove(number);
			}
			groups++;
			if (numbers.size() == 0) {
				break;
			}
		}
		System.out.println(groups);
	}

}
