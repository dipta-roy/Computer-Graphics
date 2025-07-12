import java.util.Scanner;

public class MidPointDrawing {
    
    void MidPointDrawingAlg() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter radius (r): ");
        int r = sc.nextInt();
        
        System.out.print("Enter origin (x0, y0): ");
        int x0 = sc.nextInt();
        int y0 = sc.nextInt();

        // Starting point
        int x = 0;
        int y = r;

        // Initial decision parameter
        int pk = 1 - r;
        int step = 0;

        System.out.println("\nk\tpk\t(x, y)");
        System.out.printf("%d\t%d\t(%d, %d)\n", step, pk, x + x0, y + y0);
        plotCirclePoints(x0, y0, x, y);

        // Iterate over one octant and reflect for the full circle
        while (x < y) {
            step++;
            x++;
            if (pk < 0) {
                pk += 2 * x + 1;
            } else {
                y--;
                pk += 2 * x + 1 - 2 * y;
            }
            System.out.printf("%d\t%d\t(%d, %d)\n", step, pk, x + x0, y + y0);
            plotCirclePoints(x0, y0, x, y);
        }
    }

    // Uses 8-way symmetry to print full circle coordinates
    void plotCirclePoints(int xc, int yc, int x, int y) {
        System.out.println("Plot: (" + ( xc + x) + ", " + (yc + y) + ")");
        System.out.println("      (" + ( xc - x) + ", " + (yc + y) + ")");
        System.out.println("      (" + ( xc + x) + ", " + (yc - y) + ")");
        System.out.println("      (" + ( xc - x) + ", " + (yc - y) + ")");
        System.out.println("      (" + ( xc + y) + ", " + (yc + x) + ")");
        System.out.println("      (" + ( xc - y) + ", " + (yc + x) + ")");
        System.out.println("      (" + ( xc + y) + ", " + (yc - x) + ")");
        System.out.println("      (" + ( xc - y) + ", " + (yc - x) + ")");
    }
}
