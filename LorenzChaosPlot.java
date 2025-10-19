import java.util.Scanner;
import static java.lang.Math.*;
import java.awt.Color;


//recovered?

public class LorenzChaosPlot {

    // Lorenz parameters
    private static final double SIGMA = 10.0;
    private static final double RHO   = 28.0;
    private static final double BETA  = 8.0 / 3.0;
    private static final float  SIGMA_F = 10f, RHO_F = 28f, BETA_F = 8f / 3f;

    // Canvas
    private static final int CANVAS_W = 1500;
    private static final int CANVAS_H = 1000;

    // >>> NEW: viewport and margins <<<
    private static final double VIEW_SECONDS = 30.0;  // show only the first 30 s of each series
    private static final double LEFT_MARGIN  = 2.0;   // seconds of empty space to the left
    private static final double RIGHT_MARGIN = 1.0;   // seconds to the right

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Lorenz chaos plot");
        System.out.print("Humidity percent (0..100): ");
        int humidity = safeInt(sc);
        System.out.print("Temperature Celsius (0..40): ");
        int temperatureC = safeInt(sc);
        System.out.print("Wind speed m/s (0..50): ");
        int wind = safeInt(sc);

        // Map to initial state
        double x0 = (temperatureC - 15) / 10.0;
        double y0 = clamp(humidity, 0, 100) / 100.0;
        double z0 = wind / 5.0 + 1.0;

        // Integration controls
        double dt = 0.01;        // 100 steps per second
        int totalSteps = 10000;  // 100 seconds
        int checkpoint  = 2000;  // t0 = 20 s

        double T  = totalSteps * dt;
        double t0 = checkpoint * dt;

        // Series widths: float runs T seconds, rounded runs (T - t0).
        // We want all to share the same visible width. Then we zoom to the first VIEW_SECONDS.
        double XMAX = T - t0;
        double XWIN = Math.min(XMAX, VIEW_SECONDS);

        // Baseline and variants
        StateD base = new StateD(x0, y0, z0);
        StateF fRun = new StateF((float)x0, (float)y0, (float)z0);
        StateD round3 = null, round4 = null, round6 = null;

        // StdDraw setup
        StdDraw.setCanvasSize(CANVAS_W, CANVAS_H);
        StdDraw.enableDoubleBuffering();

        double YMIN = -12.0, YMAX = 2.0;

        // >>> NEW: left/right padding so the start is not glued to the border
        StdDraw.setXscale(-LEFT_MARGIN, XWIN + RIGHT_MARGIN);
        StdDraw.setYscale(YMIN, YMAX);

        drawAxes(XWIN, YMIN, YMAX);
        legend(XWIN, YMIN, YMAX);

        // Previous points per series
        Double pxF = null, pyF = null;      // float
        Double px3 = null, py3 = null;      // 3 dp
        Double px4 = null, py4 = null;      // 4 dp
        Double px6 = null, py6 = null;      // 6 dp

        for (int step = 0; step <= totalSteps; step++) {
            double t = step * dt;

            if (step == checkpoint) {
                StateD s = new StateD(base.x, base.y, base.z);
                round3 = new StateD(roundToN(s.x, 3), roundToN(s.y, 3), roundToN(s.z, 3));
                round4 = new StateD(roundToN(s.x, 4), roundToN(s.y, 4), roundToN(s.z, 4));
                round6 = new StateD(roundToN(s.x, 6), roundToN(s.y, 6), roundToN(s.z, 6));
            }

            // Distances to baseline at same absolute time
            double dF  = distance(base, fRun);
            Double d3  = (round3 == null) ? null : distance(base, round3);
            Double d4  = (round4 == null) ? null : distance(base, round4);
            Double d6  = (round6 == null) ? null : distance(base, round6);

            // log10 distances for early exponential visibility
            double yF  = clamp(log10Safe(dF), YMIN, YMAX);
            Double y3  = (d3 == null) ? null : clamp(log10Safe(d3), YMIN, YMAX);
            Double y4  = (d4 == null) ? null : clamp(log10Safe(d4), YMIN, YMAX);
            Double y6  = (d6 == null) ? null : clamp(log10Safe(d6), YMIN, YMAX);

            // Time since start per series
            double xF = t;            // float starts at 0
            double xR = t - t0;       // rounded series start at 0 when t = t0

            // >>> NEW: only plot the first XWIN seconds of each series
            if (xF >= 0 && xF <= XWIN) {
                if (pxF != null) {
                    StdDraw.setPenColor(Color.RED);
                    StdDraw.setPenRadius(0.002);
                    StdDraw.line(pxF, pyF, xF, yF);
                }
                pxF = xF; pyF = yF;
            }
            if (y3 != null && xR >= 0 && xR <= XWIN) {
                if (px3 != null) {
                    StdDraw.setPenColor(Color.BLUE);
                    StdDraw.setPenRadius(0.002);
                    StdDraw.line(px3, py3, xR, y3);
                }
                px3 = xR; py3 = y3;
            }
            if (y4 != null && xR >= 0 && xR <= XWIN) {
                if (px4 != null) {
                    StdDraw.setPenColor(Color.GREEN);
                    StdDraw.setPenRadius(0.002);
                    StdDraw.line(px4, py4, xR, y4);
                }
                px4 = xR; py4 = y4;
            }
            if (y6 != null && xR >= 0 && xR <= XWIN) {
                if (px6 != null) {
                    StdDraw.setPenColor(Color.MAGENTA);
                    StdDraw.setPenRadius(0.002);
                    StdDraw.line(px6, py6, xR, y6);
                }
                px6 = xR; py6 = y6;
            }

            if (step % 5 == 0) StdDraw.show();

            // Advance integrators
            rk4StepDouble(base, dt);
            if (round3 != null) rk4StepDouble(round3, dt);
            if (round4 != null) rk4StepDouble(round4, dt);
            if (round6 != null) rk4StepDouble(round6, dt);
            rk4StepFloat(fRun, (float)dt);
        }

        StdDraw.show();
        System.out.println("Zoomed to the first " + XWIN + " s with a left margin so the beginnings are visible.");
    }

    // ----- Drawing helpers -----

    private static void drawAxes(double XWIN, double YMIN, double YMAX) {
        StdDraw.clear(Color.WHITE);

        // Border
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.003);
        StdDraw.line(-LEFT_MARGIN, YMIN, XWIN + RIGHT_MARGIN, YMIN);
        StdDraw.line(-LEFT_MARGIN, YMAX, XWIN + RIGHT_MARGIN, YMAX);
        StdDraw.line(-LEFT_MARGIN, YMIN, -LEFT_MARGIN, YMAX);
        StdDraw.line(XWIN + RIGHT_MARGIN, YMIN, XWIN + RIGHT_MARGIN, YMAX);

        // Grid
        StdDraw.setPenRadius(0.0015);
        StdDraw.setPenColor(new Color(220, 220, 220));
        double majorX = XWIN / 10.0;
        for (double x = 0; x <= XWIN + 1e-9; x += majorX) StdDraw.line(x, YMIN, x, YMAX);
        for (int y = (int)ceil(YMIN); y <= (int)floor(YMAX); y += 2) StdDraw.line(-LEFT_MARGIN, y, XWIN + RIGHT_MARGIN, y);

        // Labels
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.textLeft(-LEFT_MARGIN + 0.2, YMAX - 0.4, "log10 distance to baseline");
        StdDraw.textRight(XWIN + RIGHT_MARGIN - 0.2, YMIN + 0.5, "time since start (s)");

        // 0 s tick label placed slightly above bottom
        StdDraw.text(0.0, YMIN + 0.7, "0 s");
    }

    private static void legend(double XWIN, double YMIN, double YMAX) {
        double lx = 0.60 * XWIN;
        double ly = YMAX - 0.8;
        double w = 0.35 * XWIN;
        double h = 1.6;

        StdDraw.setPenColor(Color.WHITE);
        StdDraw.filledRectangle(lx + w/2, ly - h/2, w/2, h/2);
        StdDraw.setPenColor(new Color(180, 180, 180));
        StdDraw.setPenRadius(0.002);
        StdDraw.rectangle(lx + w/2, ly - h/2, w/2, h/2);

        StdDraw.setPenRadius(0.005);

        StdDraw.setPenColor(Color.RED);
        StdDraw.line(lx, ly, lx + 2.0, ly);
        StdDraw.textLeft(lx + 2.3, ly, "float from start");

        StdDraw.setPenColor(Color.BLUE);
        StdDraw.line(lx, ly - 0.5, lx + 2.0, ly - 0.5);
        StdDraw.textLeft(lx + 2.3, ly - 0.5, "restart at t0, rounded to 3 dp");

        StdDraw.setPenColor(Color.GREEN);
        StdDraw.line(lx, ly - 1.0, lx + 2.0, ly - 1.0);
        StdDraw.textLeft(lx + 2.3, ly - 1.0, "restart at t0, rounded to 4 dp");

        StdDraw.setPenColor(Color.MAGENTA);
        StdDraw.line(lx, ly - 1.5, lx + 2.0, ly - 1.5);
        StdDraw.textLeft(lx + 2.3, ly - 1.5, "restart at t0, rounded to 6 dp");
    }

    // ----- Math helpers -----
    private static double clamp(int v, int lo, int hi) { return Math.max(lo, Math.min(hi, v)); }
    private static double clamp(double v, double lo, double hi) { return Math.max(lo, Math.min(hi, v)); }
    private static int safeInt(Scanner sc) {
        while (true) {
            String s = sc.nextLine().trim();
            try { return Integer.parseInt(s); }
            catch (NumberFormatException e) { System.out.print("Please enter an integer: "); }
        }
    }
    private static double log10Safe(double d) { double eps = 1e-16; return Math.log10(Math.max(d, eps)); }
    private static double roundToN(double x, int n) { double m = Math.pow(10, n); return Math.round(x * m) / m; }
    private static double distance(StateD a, StateD b) {
        double dx = a.x - b.x, dy = a.y - b.y, dz = a.z - b.z;
        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }
    private static double distance(StateD a, StateF b) {
        double dx = a.x - b.x, dy = a.y - b.y, dz = a.z - b.z;
        return Math.sqrt(dx*dx + dy*dy + dz*dz);
    }

    // ----- Integrators -----
    private static void rk4StepDouble(StateD s, double dt) {
        double k1x = SIGMA * (s.y - s.x);
        double k1y = s.x * (RHO - s.z) - s.y;
        double k1z = s.x * s.y - BETA * s.z;

        double x2 = s.x + 0.5 * dt * k1x;
        double y2 = s.y + 0.5 * dt * k1y;
        double z2 = s.z + 0.5 * dt * k1z;

        double k2x = SIGMA * (y2 - x2);
        double k2y = x2 * (RHO - z2) - y2;
        double k2z = x2 * y2 - BETA * z2;

        double x3 = s.x + 0.5 * dt * k2x;
        double y3 = s.y + 0.5 * dt * k2y;
        double z3 = s.z + 0.5 * dt * k2z;

        double k3x = SIGMA * (y3 - x3);
        double k3y = x3 * (RHO - z3) - y3;
        double k3z = x3 * y3 - BETA * z3;

        double x4 = s.x + dt * k3x;
        double y4 = s.y + dt * k3y;
        double z4 = s.z + dt * k3z;

        double k4x = SIGMA * (y4 - x4);
        double k4y = x4 * (RHO - z4) - y4;
        double k4z = x4 * y4 - BETA * z4;

        s.x += dt * (k1x + 2*k2x + 2*k3x + k4x) / 6.0;
        s.y += dt * (k1y + 2*k2y + 2*k3y + k4y) / 6.0;
        s.z += dt * (k1z + 2*k2z + 2*k3z + k4z) / 6.0;
    }

    private static void rk4StepFloat(StateF s, float dt) {
        float k1x = SIGMA_F * (s.y - s.x);
        float k1y = s.x * (RHO_F - s.z) - s.y;
        float k1z = s.x * s.y - BETA_F * s.z;

        float x2 = s.x + 0.5f * dt * k1x;
        float y2 = s.y + 0.5f * dt * k1y;
        float z2 = s.z + 0.5f * dt * k1z;

        float k2x = SIGMA_F * (y2 - x2);
        float k2y = x2 * (RHO_F - z2) - y2;
        float k2z = x2 * y2 - BETA_F * z2;

        float x3 = s.x + 0.5f * dt * k2x;
        float y3 = s.y + 0.5f * dt * k2y;
        float z3 = s.z + 0.5f * dt * k2z;

        float k3x = SIGMA_F * (y3 - x3);
        float k3y = x3 * (RHO_F - z3) - y3;
        float k3z = x3 * y3 - BETA_F * z3;

        float x4 = s.x + dt * k3x;
        float y4 = s.y + dt * k3y;
        float z4 = s.z + dt * k3z;

        float k4x = SIGMA_F * (y4 - x4);
        float k4y = x4 * (RHO_F - z4) - y4;
        float k4z = x4 * y4 - BETA_F * z4;

        s.x += dt * (k1x + 2*k2x + 2*k3x + k4x) / 6f;
        s.y += dt * (k1y + 2*k2y + 2*k3y + k4y) / 6f;
        s.z += dt * (k1z + 2*k2z + 2*k3z + k4z) / 6f;
    }

    // ----- State containers -----
    private static class StateD { double x, y, z; StateD(double x, double y, double z){this.x=x; this.y=y; this.z=z;} }
    private static class StateF { float  x, y, z; StateF(float  x, float  y, float  z){this.x=x; this.y=y; this.z=z;} }
}
