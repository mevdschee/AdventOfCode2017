import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Code {

	public static void main(String[] args) throws IOException {
		int step = Integer.parseInt(new String(Files.readAllBytes(Paths.get("input"))).trim());
		ArrayList<Integer> buffer = new ArrayList<>();
		buffer.add(0);
		int p = 0;
		for (int i = 1; i <= 2017; i++) {
			p = (p + step) % buffer.size() + 1;
			buffer.add(p, i);
		}
		System.out.println(buffer.get((p + 1)% buffer.size()));
	}

}
