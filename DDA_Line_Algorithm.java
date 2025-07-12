import java.util.Scanner;

public class DDA_Line_Algorithm {

    // Entry point for the DDA Line Drawing algorithm
    void DDA_Line_Alg() {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== DDA Line Drawing Algorithm ===");

        // Input start and end coordinates
        System.out.print("Enter starting coordinate (X0,Y0): ");
        String[] start = sc.nextLine().split(",");
        System.out.print("Enter ending coordinate (Xn,Yn): ");
        String[] end = sc.nextLine().split(",");

        // Parse inputs
        float X0 = Integer.parseInt(start[0]);
        float Y0 = Integer.parseInt(start[1]);
        float Xn = Integer.parseInt(end[0]);
        float Yn = Integer.parseInt(end[1]);

        // Calculate dx, dy and slope
        float dx = Xn - X0;
        float dy = Yn - Y0;
        float slope = dy / dx;

        // Determine number of steps (max of dx or dy)
        int numberOfSteps = (int) Math.max(Math.abs(dx), Math.abs(dy));

        System.out.println("Slope = " + slope);
        System.out.println("Number of Steps = " + numberOfSteps);

        // Determine how x and y should increment each step
        float xIncrement = dx / numberOfSteps;
        float yIncrement = dy / numberOfSteps;

        System.out.println("\nIncrements:");
        System.out.println("Δx per step = " + customRound(xIncrement));
        System.out.println("Δy per step = " + customRound(yIncrement));

        // Print table header
        System.out.println("\nXp\t|\tYp\t|\tXp+1\t|\tYp+1\t|\tRounded (Xp+1, Yp+1)");
        System.out.println("-------------------------------------------------------------");

        // Initialize starting point
        float xp = X0;
        float yp = Y0;

        // Run the DDA loop
        for (int i = 0; i < numberOfSteps; i++) {
            float xNext = xp + xIncrement;
            float yNext = yp + yIncrement;

            System.out.printf("%s\t|\t%s\t|\t%s\t|\t%s\t|\t%d,%d\n",
                    customRound(xp),
                    customRound(yp),
                    customRound(xNext),
                    customRound(yNext),
                    Math.round(xNext),
                    Math.round(yNext)
            );

            xp = xNext;
            yp = yNext;
        }

        // Note: Don't close Scanner here if shared with other methods
    }

    // Round float to 2 decimal places and return as String
    static String customRound(float x) {
        return String.format("%.2f", x);
    }
}
