import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;

public class Code {

	public static int findCorrectedValue(HashMap<String, Integer> weights, HashMap<String, Integer> childWeights) {
		HashMap<Integer, Integer> occurences = new HashMap<>();
		for (String key : childWeights.keySet()) {
			int childWeight = childWeights.get(key);
			if (!occurences.containsKey(childWeight)) {
				occurences.put(childWeight, 1);
			} else {
				occurences.put(childWeight, occurences.get(childWeight) + 1);
			}
		}
		int faultyWeight = -1;
		for (int weight : occurences.keySet()) {
			if (occurences.get(weight) == 1) {
				faultyWeight = weight;
			}
		}
		String correct = null;
		String faulty = null;
		for (String key : childWeights.keySet()) {
			if (childWeights.get(key) == faultyWeight) {
				faulty = key;
			} else {
				correct = key;
			}
		}
		int diff = childWeights.get(faulty) - childWeights.get(correct);
		return weights.get(faulty) - diff;
	}

	public static int find(HashMap<String, Integer> weights, HashMap<String, String[]> children, String current) {
		HashMap<String, Integer> childWeights = new HashMap<>();
		int sum = 0;
		for (String child : children.get(current)) {
			int weight = find(weights, children, child);
			childWeights.put(child, weight);
			sum += weight;
		}
		if (new HashSet<Integer>(childWeights.values()).size() > 1) {
			System.out.println(findCorrectedValue(weights, childWeights));
			System.exit(0);
		}
		return sum + weights.get(current);
	}

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
		find(weights, children, current);
	}

}
