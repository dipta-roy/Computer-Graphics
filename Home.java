import java.util.Scanner;

public class Home {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Computer Graphics Programs");
		int ch = 0;
		do {
			System.out.println("1. DDA Line Drawing Algorithm");
			System.out.println("2. Mid Point Drawing Algorithm");
			System.out.println("3. Cohen-Sutherland Line Clipping");
			System.out.println("0. Exit");
			System.out.println("Enter choice");
			ch = sc.nextInt();
			switch(ch) {
			case 1:
				DDA_Line_Algorithm da = new DDA_Line_Algorithm();
				da.DDA_Line_Alg();
				break;
			case 2:
				MidPointDrawing md = new MidPointDrawing();
				md.MidPointDrawingAlg();
				break;

			case 3:
				CohenSutherlandLineClipping lineClipping = new CohenSutherlandLineClipping();
				lineClipping.lineClipping();
				break;
			case 0:
				System.out.println("Exiting");
				break;
			default:
				System.out.println("Invalid choice");
				break;
			}
			System.out.println("\n");
			sc.nextLine();
			
		} while(ch!=0);
		sc.close();
	}
}
