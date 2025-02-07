import java.awt.*;

public class Trunk extends Shape {
 public void draw(Graphics g, int x, int y, int size) {
     g.setColor(new Color(139, 69, 19)); // Brown color for the tree trunk

     // Define the dimensions of the trunk
     int bottomWidth = size / 2; // Bottom width of the trunk
     int topWidth = size / 4;    // Top width of the trunk (narrower)
     int trunkHeight = size;     // Height of the trunk

     // Define the vertices of the trapezoid
     int[] xPoints = {
             x - bottomWidth / 2, // Bottom-left corner
             x + bottomWidth / 2, // Bottom-right corner
             x + topWidth / 2,    // Top-right corner
             x - topWidth / 2     // Top-left corner
     };
     int[] yPoints = {
             y + trunkHeight, // Bottom edge
             y + trunkHeight, // Bottom edge
             y,                // Top edge
             y               //  Top edge
     };

     // Draw the filled trapezoid for the trunk
     g.fillPolygon(xPoints, yPoints, 4);

 }

}
