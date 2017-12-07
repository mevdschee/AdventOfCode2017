import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Question1 {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] rows = input.split("\\n");
		HashMap<String, String> parents = new HashMap<>();
		HashMap<String, String[]> children = new HashMap<>();
		HashMap<String, Integer> weights = new HashMap<>();
		for (int i = 0; i < rows.length; i++) {
			String[] sides = rows[i].trim().split("->");
			String parts[] = sides[0].trim().split("\\(");
			String parent = parts[0].trim();
			int weight = Integer.parseInt(parts[1].split("\\)")[0]);
			weights.put(parent, weight);
			if (sides.length > 1) {
				String[] nodes = sides[1].trim().split(",\\s*");
				for (int j = 0; j < nodes.length; j++) {
					parents.put(nodes[j], parts[0].trim());
				}
				children.put(parent, nodes);
			} else {
				children.put(parent, new String[] {});
			}
		}
		String current = parents.keySet().iterator().next();
		while (parents.containsKey(current)) {
			current = parents.get(current);
		}
		System.out.println(current);
	}

}
