import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Code {

	public static void main(String[] args) throws IOException {
		int step = Integer.parseInt(new String(Files.readAllBytes(Paths.get("input"))).trim());
		int buffer = 0;
		int p = 0;
		for (int i = 1; i <= 50000000; i++) {
			p = (p + step) % i + 1;
			if (p == 1) {
				buffer = i;
			}
		}
		System.out.println(buffer);
	}

}
