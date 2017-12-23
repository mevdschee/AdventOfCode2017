public class Question2 {

	public static void main(String[] args) {
		int h = 0;
		for (int b = 106700; b <= 123700; b += 17) {
			boolean f = false;
			for (int d = 2; d < b; d++) {
				// for (int e = 2; e < b; e++) if (d * e == b) f = true;
				if (b % d == 0) {
					f = true;
					break;
				}
			}
			if (f) {
				h++;
			}
		}
		System.out.println(h);
	}

}
