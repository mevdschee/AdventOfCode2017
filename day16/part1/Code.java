import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Code {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] words = input.split(",");
		int len = 16;
		// initialize
		int s = 0;
		char[] p = new char[len];
		for (int i = 0; i < len; i++) {
			p[i] = (char) ('a' + i);
		}

		Pattern pattern = Pattern.compile("([sxp])([a-z0-9]+)(/([a-z0-9]+))?");
		for (String word : words) {
			Matcher matcher = pattern.matcher(word);
			matcher.find();
			String op = matcher.group(1);
			String arg1 = matcher.group(2);
			String arg2 = matcher.group(4);
			switch (op.charAt(0)) {
			case 's':
				s -= Integer.parseInt(arg1);
				if (s < 0) {
					s += len;
				}
				break;
			case 'x':
				int p1 = (s + Integer.parseInt(arg1)) % len;
				int p2 = (s + Integer.parseInt(arg2)) % len;
				char tmp = p[p1];
				p[p1] = p[p2];
				p[p2] = tmp;
				break;
			case 'p':
				char c1 = arg1.charAt(0);
				char c2 = arg2.charAt(0);
				for (int i = 0; i < len; i++) {
					if (p[i] == c1) {
						p[i] = c2;
					} else if (p[i] == c2) {
						p[i] = c1;
					}
				}
				break;
			}
		}
		String str = new String(p);
		str = str.substring(s, len).concat(str.substring(0, s));
		System.out.println(str);
	}

}
