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
		HashMap<String, Long> registers = new HashMap<>();
		Pattern pattern = Pattern.compile("([a-z][a-z][a-z]) (([a-z])|([0-9-]+))( (([a-z])|([0-9-]+)))?");
		// initialize
		for (int i = 0; i < lines.length; i++) {
			Matcher matcher = pattern.matcher(lines[i].trim());
			matcher.find();
			String reg1 = matcher.group(3);
			String reg2 = matcher.group(7);
			if (reg1 != null) {
				registers.put(reg1, 0l);
			}
			if (reg2 != null) {
				registers.put(reg2, 0l);
			}
		}
		registers.put("a", 0l);
		// execute
		long count = 0;
		int i = 0;
		while (true) {
			Matcher matcher = pattern.matcher(lines[i].trim());
			matcher.find();
			String operation = matcher.group(1);
			String reg1 = matcher.group(3);
			long val1 = reg1 == null ? Integer.parseInt(matcher.group(4)) : registers.get(reg1);
			String reg2 = null;
			long val2 = 0;
			if (matcher.group(7) != null || matcher.group(8) != null) {
				reg2 = matcher.group(7);
				val2 = reg2 == null ? Integer.parseInt(matcher.group(8)) : registers.get(reg2);
			}
			//System.out.println(i + ":" + operation + "," + reg1 + "," + val2);
			if (operation.equals("rcv") && val1 != 0) {
				break;
			}
			switch (operation) {
			case "set":
				registers.put(reg1, val2);
				break;
			case "sub":
				registers.put(reg1, val1 - val2);
				break;
			case "mul":
				count++;
				registers.put(reg1, val1 * val2);
				break;
			case "jnz":
				if (val1 != 0) {
					i += val2 - 1;
				}
				break;
			}
			i++;
			if (i >= lines.length) {
				break;
			}
		}
		System.out.println(count);
	}

}
