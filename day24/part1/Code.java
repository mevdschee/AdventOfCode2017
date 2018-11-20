import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Code {

	private static int step(int port, int strength, HashMap<String, Boolean> components) {
		int max = strength;
		for (String component : components.keySet()) {
			String[] parts = component.split("/");
			int left = Integer.parseInt(parts[0]);
			int right = Integer.parseInt(parts[1]);
			if (left == port || right == port) {
				HashMap<String, Boolean> next = new HashMap<>(components);
				next.remove(component);
				if (left == port) {
					max = Math.max(max, step(right, strength + left + right, next));
				} else {
					max = Math.max(max, step(left, strength + left + right, next));
				}
			}
		}
		return max;
	}

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] lines = input.split("\\n");
		HashMap<String, Boolean> components = new HashMap<>();
		// initialize
		for (int i = 0; i < lines.length; i++) {
			String[] parts = lines[i].split("/");
			int left = Integer.parseInt(parts[0]);
			int right = Integer.parseInt(parts[1]);
			if (left < right) {
				components.put(left + "/" + right, true);
			} else {
				components.put(right + "/" + left, true);
			}
		}
		// execute
		System.out.println(step(0, 0, components));
	}

}
