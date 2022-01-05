import java.util.Scanner;

public class MidPointDrawing {
	void MidPointDrawingAlg() {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter radius r value");
		int r = sc.nextInt();
		System.out.println("Enter origin x value");
		int x = sc.nextInt();
		System.out.println("k   pk    (x+1,y+1)");
		MidPointDrawing.coordinate(1-r, x, r, 0);
	}
	static void coordinate(int pk, int x, int y, int count) {
		if(x<y) {
			if(pk<0) {
				x++;
				System.out.println(count+"    "+ pk + "     (" + x + "," + y +")");
				pk = pk + (2*x) + 1;
			} else {
				x++;
				y--;
				System.out.println(count+"    "+ pk + "     (" + x + "," + y +")");
				pk = pk + (2*x) + 1 - (2 * y);
			}
			coordinate(pk,x,y, ++count);
		}
	}
}
