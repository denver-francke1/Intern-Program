import java.awt.*;
import java.util.Random;

public class OvalLeaf extends Shape {
    private int x, y, size;
    private int swayOffset = 0; // Movement offset
    private int swayDirection = 1; // 1 = right, -1 = left
    private Color color; // Leaf color
    private static final Random rand = new Random();

    // Possible colors
    private static final Color[] LEAF_COLORS = {
            new Color(34, 139, 34),  // Forest Green
            new Color(50, 205, 50),  // Lime Green
            new Color(107, 142, 35)  // Olive Drab
    };

    public OvalLeaf(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.color = LEAF_COLORS[rand.nextInt(LEAF_COLORS.length)]; // Assign a random color
    }

    public void updateSway() {
        swayOffset += swayDirection * 2; // Move in current direction
        if (Math.abs(swayOffset) > 10) { // Change direction when reaching limit
            swayDirection *= -1;
        }
    }

    public void draw(Graphics g) {
        g.setColor(color); // Set random color
        g.fillOval(x + swayOffset, y, size, size / 2); // Apply sway
    }
}

