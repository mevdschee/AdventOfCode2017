public class Question2 {

	public static void input(String[] args) {
		int a=1,b=0,c=0,d=0,e=0,f=0,g=0,h=0;
		b=67; // set b 67
		c=b; // set c b
		if (a!=0){ // jnz a 2
		// jnz 1 5
		b*=100; // mul b 100
		b+=100000; // sub b -100000
		c=b; // set c b
		c+=17000; // sub c -17000
		} do { f=1; // set f 1
		d=2; // set d 2
		do { e=2; // set e 2
		do { g=d; // set g d
		g*=e; // mul g e
		g-=b; // sub g b
		if (g==0) { // jnz g 2
		f=0; // set f 0
		} e++; // sub e -1
		g=e; // set g e
		g-=b; // sub g b
		} while (g!=0); // jnz g -8
		d++; // sub d -1
		g=d; // set g d
		g-=b; // sub g b
		} while (g!=0); // jnz g -13
		if (f==0) { // jnz f 2
		h++; // sub h -1
		} g=b; // set g b
		g-=c; // sub g c
		if (g==0) { // jnz g 2
		break; // jnz 1 3
		} b+=17; // sub b -17
		} while (true); // jnz 1 -23
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
