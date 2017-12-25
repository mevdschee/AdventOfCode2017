import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Question1 {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] lines = input.split("\\n");
		// initialize
		char start = lines[0].trim().split(" ")[3].charAt(0);
		int checksum = Integer.parseInt(lines[1].trim().split(" ")[5]);
		HashMap<String, Integer> writes = new HashMap<>();
		HashMap<String, Integer> moves = new HashMap<>();
		HashMap<String, Integer> states = new HashMap<>();
		for (int i = 2; i < lines.length; i += 10) {
			char state = lines[i + 1].trim().split(" ")[2].charAt(0);
			for (int j = 0; j < 2; j++) {
				String key = state + String.valueOf(j);
				writes.put(key, lines[i + 3 + j * 4].trim().split(" ")[4].charAt(0) - '0');
				moves.put(key, lines[i + 4 + j * 4].trim().split(" ")[6].charAt(0) == 'l' ? -1 : 1);
				states.put(key, lines[i + 5 + j * 4].trim().split(" ")[4].charAt(0) - 'A');
			}
		}
		// run
		HashMap<Integer, Integer> slots = new HashMap<>();
		char state = start;
		int position = 0;
		for (int i = 0; i < checksum; i++) {
			if (!slots.containsKey(position)) {
				slots.put(position, 0);
			}
			int current = slots.get(position);
			String key = state + String.valueOf(current);
			slots.put(position, writes.get(key));
			position += moves.get(key);
			state = (char) ('A' + states.get(key));
		}
		// count
		int count = 0;
		for (int pos : slots.keySet()) {
			if (slots.get(pos) == 1) {
				count++;
			}
		}
		System.out.println(count);
	}

}
