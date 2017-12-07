import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Question1 {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] rows = input.split("\\n");
		HashMap<String, String> parents = new HashMap<>();
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
		}
		String current = leafs.get(0);
		while (parents.containsKey(current)) {
			current = parents.get(current);
		}
		System.out.println(current);
	}

}
