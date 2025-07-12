import java.util.Arrays;
import java.util.Scanner;

// Class to represent a point with region code
class Point {
    int x, y;
    int[] regionCode;
    boolean isOutside = false;

    public Point(String[] coordinates) {
        this.x = Integer.parseInt(coordinates[0]);
        this.y = Integer.parseInt(coordinates[1]);
    }

    public int[] getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(int[] regionCode) {
        this.regionCode = regionCode;
    }
}

public class CohenSutherlandLineClipping {

    // Region code constants: TOP, BOTTOM, RIGHT, LEFT (bit order: TOP, BOTTOM, RIGHT, LEFT)
    private final int[] INSIDE = {0, 0, 0, 0};
    private final int[] TOP    = {1, 0, 0, 0};
    private final int[] BOTTOM = {0, 1, 0, 0};
    private final int[] RIGHT  = {0, 0, 1, 0};
    private final int[] LEFT   = {0, 0, 0, 1};

    public void lineClipping() {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Cohen-Sutherland Line Clipping Algorithm ===");

        // Input: Clipping Window Coordinates
        System.out.println("Enter rectangular window boundary:");
        System.out.print("Enter starting coordinate (Xmin,Ymin): ");
        String[] min = sc.nextLine().split(",");
        System.out.print("Enter ending coordinate (Xmax,Ymax): ");
        String[] max = sc.nextLine().split(",");

        float XWmin = Integer.parseInt(min[0]);
        float YWmin = Integer.parseInt(min[1]);
        float XWmax = Integer.parseInt(max[0]);
        float YWmax = Integer.parseInt(max[1]);

        // Input: Line coordinates
        System.out.println("Enter line coordinates:");
        System.out.print("Point A (Ax, Ay): ");
        Point p1 = new Point(sc.nextLine().split(","));
        System.out.print("Point B (Bx, By): ");
        Point p2 = new Point(sc.nextLine().split(","));

        // Calculate region codes
        p1.setRegionCode(calculateRegionCode(p1.x, p1.y, XWmin, XWmax, YWmin, YWmax));
        p2.setRegionCode(calculateRegionCode(p2.x, p2.y, XWmin, XWmax, YWmin, YWmax));

        // Print region codes
        printRegionCode("Region code for Point A", p1.getRegionCode());
        printRegionCode("Region code for Point B", p2.getRegionCode());

        // Determine visibility of the line
        if (Arrays.equals(p1.getRegionCode(), INSIDE) && Arrays.equals(p2.getRegionCode(), INSIDE)) {
            System.out.println("Line is completely inside the clipping window.");
        } else if (!logicalAND(p1.getRegionCode(), p2.getRegionCode())) {
            System.out.println("Line is completely outside the clipping window.");
        } else {
            System.out.println("Line is partially inside. Clipping required.");
            clipLine(p1, p2, XWmin, XWmax, YWmin, YWmax);
        }
    }

    // Calculates region code for a point based on the window boundaries
    private int[] calculateRegionCode(float x, float y, float XWmin, float XWmax, float YWmin, float YWmax) {
        int[] code = {0, 0, 0, 0}; // TOP, BOTTOM, RIGHT, LEFT

        // Each bit corresponds to a boundary
        code[0] = (y > YWmax) ? 1 : 0; // TOP
        code[1] = (y < YWmin) ? 1 : 0; // BOTTOM
        code[2] = (x > XWmax) ? 1 : 0; // RIGHT
        code[3] = (x < XWmin) ? 1 : 0; // LEFT

        return code;
    }

    // Logical AND operation between two region codes
    private boolean logicalAND(int[] code1, int[] code2) {
        for (int i = 0; i < 4; i++) {
            if ((code1[i] & code2[i]) == 1) return false;
        }
        return true;
    }

    // Print region code
    private void printRegionCode(String message, int[] code) {
        System.out.print(message + ": ");
        for (int bit : code) System.out.print(bit);
        System.out.println();
    }

    // Calculates and displays clipped line coordinates
    private void clipLine(Point p1, Point p2, float XWmin, float XWmax, float YWmin, float YWmax) {
        float x = 0, y = 0;
        Point outside = !Arrays.equals(p1.getRegionCode(), INSIDE) ? p1 : p2;
        Point inside = (outside == p1) ? p2 : p1;

        float dx = p2.x - p1.x;
        float dy = p2.y - p1.y;

        if (!logicalAND(outside.regionCode, TOP)) {
            y = YWmax;
            x = p1.x + dx * (y - p1.y) / dy;
            System.out.println("Clipped at TOP: (" + customRound(x) + ", " + customRound(y) + ")");
        } else if (!logicalAND(outside.regionCode, BOTTOM)) {
            y = YWmin;
            x = p1.x + dx * (y - p1.y) / dy;
            System.out.println("Clipped at BOTTOM: (" + customRound(x) + ", " + customRound(y) + ")");
        } else if (!logicalAND(outside.regionCode, RIGHT)) {
            x = XWmax;
            y = p1.y + dy * (x - p1.x) / dx;
            System.out.println("Clipped at RIGHT: (" + customRound(x) + ", " + customRound(y) + ")");
        } else if (!logicalAND(outside.regionCode, LEFT)) {
            x = XWmin;
            y = p1.y + dy * (x - p1.x) / dx;
            System.out.println("Clipped at LEFT: (" + customRound(x) + ", " + customRound(y) + ")");
        }

        System.out.println("Visible Line Segment: (" + inside.x + ", " + inside.y + ") to (" + customRound(x) + ", " + customRound(y) + ")");
    }

    // Utility to round to 2 decimal places
    private static String customRound(float value) {
        return String.format("%.2f", value);
    }
}
