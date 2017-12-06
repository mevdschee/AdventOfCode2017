import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyze {

	public static void main(String[] args) throws IOException {
		int[] years = { 2015, 2016, 2017 };
		int days = 25;
		int positions = 100;
		int stars = 2;
		System.out.println("year,day,stars,position,seconds");
		for (int year : years) {
			for (int day = 1; day <= days; day++) {
				String filename = "input/" + year + "_day_" + day + ".html";
				if (!Paths.get(filename).toFile().exists()) {
					continue;
				}
				String input = new String(Files.readAllBytes(Paths.get(filename))).trim();
				Pattern p = Pattern.compile("position\">([ 0-9]+)\\)<.*?time\">[a-zA-Z]+ [0-9]+  ([0-9:]+)<");
				Matcher m = p.matcher(input);
				int i = 0;
				while (m.find()) {
					int position = i % positions + 1;
					if (position != Integer.parseInt(m.group(1).trim())) {
						System.out.println("Parse error pos" + m.group());
						System.exit(1);
					}
					int star = 2 - i / positions;
					String[] parts = m.group(2).split(":");
					if (parts.length != 3) {
						System.out.println("Parse error time");
						System.exit(1);
					}
					int seconds = Integer.parseInt(parts[0]) * 3600;
					seconds += Integer.parseInt(parts[1]) * 60;
					seconds += Integer.parseInt(parts[2]);
					System.out.println(year + "," + day + "," + star + "," + position + "," + seconds);
					i++;
				}
				if (i != positions * stars) {
					System.out.println("Parse error stars");
					System.exit(1);
				}
			}
		}
	}

}
