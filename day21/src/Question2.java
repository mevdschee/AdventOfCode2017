import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Question2 {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] lines = input.split("\\n");
		ArrayList<HashMap<String, Long>> registers = new ArrayList<>();
		registers.add(new HashMap<>());
		registers.add(new HashMap<>());
		Pattern pattern = Pattern.compile("([a-z][a-z][a-z]) (([a-z])|([0-9-]+))( (([a-z])|([0-9-]+)))?");
		int programs = 2;
		// initialize
		for (int p = 0; p < programs; p++) {
			for (int i = 0; i < lines.length; i++) {
				Matcher matcher = pattern.matcher(lines[i].trim());
				matcher.find();
				String reg1 = matcher.group(3);
				String reg2 = matcher.group(7);
				if (reg1 != null) {
					registers.get(p).put(reg1, 0l);
				}
				if (reg2 != null) {
					registers.get(p).put(reg2, 0l);
				}
			}
			registers.get(p).put("p", (long) p);
		}
		// execute
		long count = 0;
		ArrayList<LinkedList<Long>> queue = new ArrayList<>();
		queue.add(new LinkedList<Long>());
		queue.add(new LinkedList<Long>());
		int[] pc = new int[] { 0, 0 };
		int p = 0;
		while (true) {
			int o = (p + 1) % programs;
			while (true) {
				Matcher matcher = pattern.matcher(lines[pc[p]].trim());
				matcher.find();
				String operation = matcher.group(1);
				String reg1 = matcher.group(3);
				long val1 = reg1 == null ? Integer.parseInt(matcher.group(4)) : registers.get(p).get(reg1);
				String reg2 = null;
				long val2 = 0;
				if (matcher.group(7) != null || matcher.group(8) != null) {
					reg2 = matcher.group(7);
					val2 = reg2 == null ? Integer.parseInt(matcher.group(8)) : registers.get(p).get(reg2);
				}
				if (operation.equals("rcv") && queue.get(p).size() == 0) {
					break;
				}
				switch (operation) {
				case "snd":
					queue.get(o).add(val1);
					count += (p == 1 ? 1 : 0);
					break;
				case "set":
					registers.get(p).put(reg1, val2);
					break;
				case "add":
					registers.get(p).put(reg1, val1 + val2);
					break;
				case "mul":
					registers.get(p).put(reg1, val1 * val2);
					break;
				case "mod":
					registers.get(p).put(reg1, val1 % val2);
					break;
				case "rcv":
					registers.get(p).put(reg1, queue.get(p).removeFirst());
					break;
				case "jgz":
					if (val1 > 0) {
						pc[p] += val2 - 1;
					}
					break;
				}
				pc[p]++;
				if (pc[p] >= lines.length) {
					break;
				}
			}
			if (queue.get(p).size() == 0 && queue.get(o).size() == 0) {
				break;
			}
			p = (p + 1) % programs;
		}
		System.out.println(count);
	}

}
