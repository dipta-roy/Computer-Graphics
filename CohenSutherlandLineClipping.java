import java.util.Arrays;
import java.util.Scanner;
 class points{
	int x, y;
	int[] regionCode;
	boolean isOutside = false;
	public points(String[] point) {
		this.x = Integer.parseInt(point[0]);
		this.y = Integer.parseInt(point[1]);
	}
	public int[] getRegionCode() {
		return regionCode;
	}
	public void setRegionCode(int[] regionCode) {
		this.regionCode = regionCode;
	}
}

public class CohenSutherlandLineClipping {
	final int[] INSIDE = {0,0,0,0}; 
	final int[] LEFT = {0,0,0,1}; 
	final int[] RIGHT = {0,0,1,0}; 
	final int[] BOTTOM = {0,1,0,0}; 
	final int[] TOP = {1,0,0,0}; 
	void lineClipping() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Cohen Sutherland Line Clipping Algorithm");
		System.out.println("Enter Rectrangular Window Boundary");
		System.out.println("Enter starting coordinate (X,Y)");
		String[] min = sc.nextLine().split(",");
		System.out.println("Enter ending coordinate (X,Y)");
		String[] max = sc.nextLine().split(",");
		float XWmin = Integer.parseInt(min[0]);
		float XWmax = Integer.parseInt(max[0]);
		float YWmin = Integer.parseInt(min[1]);
		float YWmax = Integer.parseInt(max[1]);
		System.out.println("Enter Line Information");
		System.out.println("Enter starting coordinate of A (Ax, Ay)");
		String[] start = sc.nextLine().split(",");
		System.out.println("Enter ending coordinate of B (Bx, By)");
		String[] end = sc.nextLine().split(",");
		points p1 = new points(start);
		points p2 = new points(end);
	
		System.out.println("Region Code for Point 1 ("+p1.x+", "+p1.y+")");
		p1.setRegionCode(calculateRegionCode(p1.x, p1.y, XWmin, XWmax, YWmin, YWmax));
		printRegionCode("Region Code of Line 1", p1.getRegionCode());
		
		System.out.println("Region Code for Point 2 ("+p2.x+", "+p2.y+")");
		p2.setRegionCode(calculateRegionCode(p2.x, p2.y, XWmin, XWmax, YWmin, YWmax));	
		printRegionCode("Region Code of Line 2", p2.getRegionCode());
		
		if(Arrays.equals(INSIDE, p1.getRegionCode()) && Arrays.equals(INSIDE, p2.getRegionCode())) {
			System.out.println("Both endpoints have region code 0000 hence the line is inside clipping window.");
		} else if (!logicalAND(p1.getRegionCode(),p2.getRegionCode())) {
				System.out.println("AND operations on 2 region codes are not zero, which means the line is outside clipping area");
		} else {
			System.out.println("Value of Logical AND is 0000 hence clipping is required");
			intersectionSide(p1, p2,  XWmin, XWmax, YWmin, YWmax);	
		}
	}

	
	public int[] calculateRegionCode(float X, float Y, float XWmin,float XWmax,float YWmin,float YWmax) {
		int [] bits = {0,0,0,0};
		
		System.out.print("Bit 1: X - XWmin -> " + X + " - " + XWmin);
		if((X - XWmin) < 0 ) {
			System.out.print(" < 0 -> 1\n");
			bits[3] = 1;
		} else {
			System.out.print(" > 0 -> 0\n");
		}
		
		System.out.print("Bit 2: XWmax - X -> " + XWmax + " - " + X);
		if((XWmax - X) < 0 ) {
			System.out.print(" < 0 -> 1\n");
			bits[2] = 1;
		} else {
			System.out.print(" > 0 -> 0\n");
		}
		
		System.out.print("Bit 3: Y - YWmin -> " + Y + " - " + YWmin);
		if((Y - YWmin) < 0 ) {
			System.out.print(" < 0 -> 1\n");
			bits[1] = 1;
		 }else {
			System.out.print(" > 0 -> 0\n");
		}
		
		System.out.print("Bit 4: YWmax - Y -> " + YWmax + " - " + Y);
		if((YWmax - Y) < 0 ) {
			System.out.print(" < 0 -> 1\n");
			bits[0] = 1;
		} else {
			System.out.print(" > 0 -> 0\n");
		}
		return bits;
	}

	public boolean logicalAND(int[] array1, int[] array2) {
		for(int i=0;i<4;i++) {
			if((array1[i] * array2[i]) == 1) {
				return false;
			}
		}
		return true;
	}
	
	public void printRegionCode(String message, int[] array) {
		System.out.print(message + "   -> ");
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+ " ");
		}
		System.out.println("\n");
	}
	
	public void intersectionSide(points p1, points p2, float XWmin, float XWmax, float YWmin, float YWmax) {
        float x=0, y=0;
		points out;
		points in;
		if(!Arrays.equals(INSIDE, p1.getRegionCode())) {
			out = p1;
			in = p2;
		} else {
			out = p2;
			in = p1;
		}
	
		if(!logicalAND(out.regionCode,TOP )) {
			 System.out.println("Intersection Point at Horizontal Top\nUsing formula : x = x1 + (1 / slope) * (y - y1)");
			 System.out.println("x = " + p1.x +" + ( " + p2.x +" - " + p1.x+ ") * (" + YWmax +" - " + p1.y + ") / (" + p2.y+" - "+ p1.y +")");
			 x = p1.x + (p2.x - p1.x) * (YWmax - p1.y) / (p2.y - p1.y);
             y = YWmax;
		} else if(!logicalAND(out.regionCode,BOTTOM)) {
			System.out.println("Intersection Point at Horizontal Bottom\nUsing formula : x = x1 + (1 / slope) * (y - y1)");
			System.out.println("x = " + p1.x + " + ("+ p2.x+" - "+p1.x+") * ("+YWmin+" - "+p1.y+") / ("+p2.y+" - "+p1.y+")");
			 x = p1.x + (p2.x - p1.x) * (YWmin - p1.y) / (p2.y - p1.y);
             y = YWmin;
		} else if(!logicalAND(out.regionCode,RIGHT)) {
			System.out.println("Intersection Point at Vertical Right\nUsing formula : y = y1 + slope * (x - x1)");
			System.out.println("y = "+ p1.y +" + ("+p2.y+" - "+p1.y+") * ("+XWmax+" - "+p1.x+") / ("+p2.x+" - "+p1.x+")");
			 y = p1.y + (p2.y - p1.y) * (XWmax - p1.x) / (p2.x - p1.x);
             x = XWmax;
		} else if(!logicalAND(out.regionCode,LEFT)) {
			System.out.println("Intersection Point at Vertical Left\nUsing formula : y = y1 + slope * (x - x1)");
			System.out.println("y = "+ p1.y+" + ("+p2.y+" - "+p1.y+") * ("+XWmin+" - "+p1.x+") / ("+p2.x+" - "+p1.x+")");
			 y = p1.y + (p2.y - p1.y) * (XWmin - p1.x) / (p2.x - p1.x);
             x = XWmin;
		}
		System.out.println("Clipped part of line is from -> (" + in.x + " , " + in.y + ") to (" + customRound(x) +" , "+ customRound(y)+")");
	}
	static String customRound(float x) {
		return Double.toString(Math.round(x * 100.0) / 100.0);
	}
}