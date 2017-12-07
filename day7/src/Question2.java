import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class Question2 {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] rows = input.split("\\n");
		HashMap<String, String> parents = new HashMap<>();
		HashMap<String, Integer> weights = new HashMap<>();
		ArrayList<String> leafs = new ArrayList<>();
		for (int i = 0; i < rows.length; i++) {
			String[] sides = rows[i].trim().split("->");
			String parent[] = sides[0].trim().split("\\(");
			if (sides.length > 1) {
				String[] children = sides[1].trim().split(",\\s*");
				for (int j = 0; j < children.length; j++) {
					parents.put(children[j], parent[0].trim());
				}
			} else {
				leafs.add(parent[0].trim());
			}
			weights.put(parent[0].trim(), Integer.parseInt(parent[1].split("\\)")[0]));
		}
		while (true) {
			HashMap<String, HashMap<String, Integer>> childWeights = new HashMap<>();
			for (int i = 0; i < leafs.size(); i++) {
				String child = leafs.get(i);
				if (!parents.containsKey(child)) {
					System.out.println(child);
					System.exit(0);
				}
				String parent = parents.get(child);
				if (!childWeights.containsKey(parent)) {
					childWeights.put(parent, new HashMap<>());
				}
				HashMap<String, Integer> children = childWeights.get(parent);
				children.put(child, weights.get(child));
			}
			for (String parent : childWeights.keySet()) {
				HashMap<String, Integer> children = childWeights.get(parent);
				int sum = 0;
				for (int i : children.values()) {
					sum += i;
				}
				weights.put(parent, weights.get(parent) + sum);
				if (new HashSet<Integer>(children.values()).size() > 1) {
					System.out.println(Arrays.toString(children.values().toArray(new Integer[] {})));
					System.out.println(Arrays.toString(children.keySet().toArray(new String[] {})));
					System.exit(0);
				}
			}
			leafs = new ArrayList<String>(childWeights.keySet());
			System.out.println(Arrays.toString(leafs.toArray(new String[] {})));
		}
	}

}
