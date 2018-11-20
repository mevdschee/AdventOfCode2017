import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Code {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] lines = input.split("\\n");
		HashMap<String, Integer> registers = new HashMap<>();
		Pattern pattern = Pattern.compile("([a-z]+) (inc|dec) ([0-9-]+) if ([a-z]+) ([><=!]+) ([0-9-]+)");
		// initialize
		for (int i = 0; i < lines.length; i++) {
			Matcher matcher = pattern.matcher(lines[i].trim());
			matcher.find();
			String reg1 = matcher.group(1);
			String reg2 = matcher.group(4);
			registers.put(reg1, 0);
			registers.put(reg2, 0);
		}
		// execute
		for (int i = 0; i < lines.length; i++) {
			Matcher matcher = pattern.matcher(lines[i].trim());
			matcher.find();
			String reg1 = matcher.group(1);
			int reg1v = registers.get(reg1);
			String operation = matcher.group(2);
			int num1 = Integer.parseInt(matcher.group(3));
			int reg2v = registers.get(matcher.group(4));
			String comparator = matcher.group(5);
			int num2 = Integer.parseInt(matcher.group(6));
			boolean condition;
			switch (comparator) {
			case ">":
				condition = reg2v > num2;
				break;
			case ">=":
				condition = reg2v >= num2;
				break;
			case "<":
				condition = reg2v < num2;
				break;
			case "<=":
				condition = reg2v <= num2;
				break;
			case "==":
				condition = reg2v == num2;
				break;
			case "!=":
				condition = reg2v != num2;
				break;
			default:
				condition = false;
			}
			if (condition) {
				switch (operation) {
				case "inc":
					reg1v += num1;
					break;
				case "dec":
					reg1v -= num1;
					break;
				}
				registers.put(reg1, reg1v);
			}
		}
		// largest
		int max = Integer.MIN_VALUE;
		for (String reg : registers.keySet()) {
			max = Math.max(max, registers.get(reg));
		}
		System.out.println(max);
	}

}
