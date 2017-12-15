import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Question2 {

	public static void main(String[] args) throws IOException {
		String input = new String(Files.readAllBytes(Paths.get("input"))).trim();
		String[] lines = input.split("\\n");
		long valueA = Long.parseLong(lines[0].split(" ")[4]);
		long valueB = Long.parseLong(lines[1].split(" ")[4]);
		long mulA = 16807;
		long mulB = 48271;
		long modA = 4;
		long modB = 8;
		long mod = 2147483647;
		long mask = 0xffff;
		long count = 5 * 1000 * 1000;
		long matches = 0;
		for (long i = 0; i < count; i++) {
			do {
				valueA = (valueA * mulA) % mod;
			} while (valueA % modA != 0);
			do {
				valueB = (valueB * mulB) % mod;
			} while (valueB % modB != 0);
			if (((valueA ^ valueB) & mask) == 0) {
				matches++;
			}
		}
		System.out.println(matches);
	}

}
