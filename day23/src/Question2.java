public class Question2 {

	public static void input(String[] args) {
		int a = 1, b = 0, c = 0, d = 0, e = 0, f = 0, g = 0, h = 0;
		b = 67; // 1: set b 67
		c = b; // 2: set c b
		if (a != 0) { // 3: jnz a 2
			// 4: jnz 1 5
			b *= 100; // 5: mul b 100
			b += 100000; // 6: sub b -100000
			c = b; // 7: set c b
			c += 17000; // 8: sub c -17000
		}
		do {
			f = 1; // 9: set f 1
			d = 2; // 10: set d 2
			do {
				e = 2; // 11: set e 2
				do {
					g = d; // 12: set g d
					g *= e; // 13: mul g e
					g -= b; // 14: sub g b
					if (g == 0) { // 15: jnz g 2
						f = 0; // 16: set f 0
					}
					e++; // 17: sub e -1
					g = e; // 18: set g e
					g -= b; // 19: sub g b
				} while (g != 0); // 20: jnz g -8
				d++; // 21: sub d -1
				g = d; // 22: set g d
				g -= b; // 23: sub g b
			} while (g != 0); // 24: jnz g -13
			if (f == 0) { // 25: jnz f 2
				h++; // 26: sub h -1
			}
			g = b; // 27: set g b
			g -= c; // 28: sub g c
			if (g == 0) { // 29: jnz g 2
				break; // 30: jnz 1 3
			}
			b += 17; // 31: sub b -17
		} while (true); // 32: jnz 1 -23
		System.out.println(h);
	}

	public static void main(String[] args) {
		int h = 0;
		for (int b = 106700; b <= 123700; b += 17) {
			boolean f = false;
			for (int d = 2; d < b; d++) {
				// for (int e = 2; e < b; e++) if (d * e == b) f = true;
				if (b % d == 0) {
					f = true;
					break; // optimize
				}
			}
			if (f) {
				h++;
			}
		}
		System.out.println(h);
	}

}
