import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

public class Code {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] numbers = input.split("\\s+");
		int[] banks = new int[numbers.length];
		for (int i = 0; i < banks.length; i++) {
			banks[i] = Integer.valueOf(numbers[i]);
		}
		HashMap<String, Integer> states = new HashMap<>();
		int steps = 0;
		int result;
		while (true) {
			int highest = 0;
			for (int i = 1; i < banks.length; i++) {
				if (banks[i] > banks[highest]) {
					highest = i;
				}
			}
			int add = banks[highest];
			banks[highest] = 0;
			for (int i = 0; i < add; i++) {
				banks[(highest + 1 + i) % banks.length]++;
			}
			steps++;
			String key = Arrays.toString(banks);
			if (states.containsKey(key)) {
				result = steps - states.get(key);
				break;
			}
			states.put(key, steps);
		}
		System.out.println(result);
	}

}
