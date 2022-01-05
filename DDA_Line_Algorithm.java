import java.util.Scanner;

public class DDA_Line_Algorithm {
	void DDA_Line_Alg() {
		Scanner sc = new Scanner(System.in);
		System.out.println("DDA Line Algorithm");
		System.out.println("Enter starting coordinate (Xo,Yo)");
		String[] start = sc.nextLine().split(",");
		System.out.println("Enter ending coordinate (Xn,Yn)");
		String[] end = sc.nextLine().split(",");
		float X0 = Integer.parseInt(start[0]);
		float Xn = Integer.parseInt(end[0]);
		float Y0 = Integer.parseInt(start[1]);
		float Yn = Integer.parseInt(end[1]);
		float slope = (Yn-Y0)/(Xn-X0);
		int numberOfSteps = (int) (Math.abs((Yn-Y0))>Math.abs((Xn-X0))?Math.abs((Yn-Y0)):Math.abs((Xn-X0)));
		System.out.println("Slope = "+ slope);
		System.out.println("Number of Steps = " + numberOfSteps);
		float xp,yp;
		float xpp = 0;
		float ypp = 0;
		xp = X0;
		yp = Y0;
		String progCase = (slope<1)?"\n\tslope<1\n\txp+1 = 1 + xp\n\typ+1 = slope + yp":(slope>1)?"\n\tslope>1\n\txp+1 = (1/slope) + xp\n\typ+1 = 1 + yp":"\n\tslope=1\t\nxp+1 = 1 + xp\n\typ+1 = 1 + yp";
		System.out.println("\nCase to be followed : " + progCase +"\n");
		System.out.print("Xp" + "\t|\t" + "Yp" + "\t|\t" + "Xp + 1" + "\t|\t" + "Yp+1" + "\t|\t" + "Round(xp+1,yp+1)" );
		for (int i = 0; i < numberOfSteps; i++) {
			if(slope<1) {
				xpp = 1 + xp;
				ypp = slope + yp;
			} else if (slope == 1) {
				xpp = 1 + xp;
				ypp = 1 + yp;
			} else if (slope > 1) {
				xpp = (1/slope) + xp;
				ypp = 1 + yp;
			}
			System.out.print("\n" + customRound(xp) + "\t|\t" + customRound(yp) + "\t|\t" + customRound(xpp) + "\t|\t" + customRound(ypp) +"\t|\t" + Math.round(xpp)+ "," + Math.round(ypp));
			xp=xpp;
			yp=ypp;
		}
		//sc.close();
	}
	static String customRound(float x) {
		return Double.toString(Math.round(x * 100.0) / 100.0);
	}
}