import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

public class Question1 {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] numbers = input.split("\\s+");
		int[] banks = new int[numbers.length];
		for (int i = 0; i < numbers.length; i++) {
			banks[i] = Integer.valueOf(numbers[i]);
		}
		HashMap<String, Boolean> states = new HashMap<>();
		int steps = 0;
		while (true) {
			int highest = 0;
			for (int i = 1; i < numbers.length; i++) {
				if (banks[i] > banks[highest]) {
					highest = i;
				}
			}
			int add = (int) Math.ceil((float) banks[highest] / numbers.length);
			for (int i = 0; i < numbers.length; i++) {
				if (banks[highest] >= add) {
					banks[highest] -= add;
					banks[(highest + 1 + i) % numbers.length] += add;
				} else {
					int left = banks[highest];
					banks[highest] -= left;
					banks[(highest + 1 + i) % numbers.length] += left;
				}
			}
			steps++;
			String key = Arrays.toString(banks);
			if (states.containsKey(key)) {
				break;
			}
			states.put(key, true);
		}
		System.out.println(steps);
	}

}
