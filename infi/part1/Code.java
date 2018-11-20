import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Code {

	public static void main(String[] args) throws IOException {
		String inputs = new String(Files.readAllBytes(Paths.get("input"))).trim();
		int split = inputs.indexOf("](");
		ArrayList<int[]> robots = new ArrayList<>();
		for (String input : inputs.substring(1, split).split("\\]\\[")) {
			String[] coords = input.split(",");
			robots.add(new int[] { Integer.parseInt(coords[0]), Integer.parseInt(coords[1]) });
		}
		ArrayList<ArrayList<int[]>> moves = new ArrayList<>();
		moves.add(new ArrayList<>());
		for (String input : inputs.substring(split + 2, inputs.length() - 1).split("\\)\\(")) {
			String[] coords = input.split(",");
			ArrayList<int[]> last = moves.get(moves.size() - 1);
			if (last.size() == robots.size()) {
				last = new ArrayList<>();
				moves.add(last);
			}
			last.add(new int[] { Integer.parseInt(coords[0]), Integer.parseInt(coords[1]) });
		}
		HashMap<String, Boolean> collisions = new HashMap<>();
		for (int m = 0; m < moves.size(); m++) {
			for (int r = 0; r < robots.size(); r++) {
				robots.get(r)[0] += moves.get(m).get(r)[0];
				robots.get(r)[1] += moves.get(m).get(r)[1];
			}
			for (int r = 0; r < robots.size(); r++) {
				for (int r2 = r - 1; r2 >= 0; r2--) {
					if (robots.get(r)[0] == robots.get(r2)[0] && robots.get(r)[1] == robots.get(r2)[1]) {
						collisions.put(robots.get(r)[0] + "," + robots.get(r)[1], true);
					}
				}
			}
		}
		System.out.println(collisions.keySet().size());
	}

}
