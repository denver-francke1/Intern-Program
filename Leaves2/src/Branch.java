import java.awt.*;

public class Branch extends Shape{

    public void draw(Graphics g, int startX, int startY, int endX, int endY) {
        g.setColor(new Color(139, 69, 19));

        // Draw the branch as a line
        g.drawLine(startX, startY, endX, endY);

        // Optionally, add thickness to the branch by drawing a polygon
        int thickness = 20; // Thickness of the branch
        int[] xPoints = {startX - thickness, startX + thickness, endX + thickness, endX - thickness};
        int[] yPoints = {startY, startY, endY, endY};
        g.fillPolygon(xPoints, yPoints, 4);
    }

}
