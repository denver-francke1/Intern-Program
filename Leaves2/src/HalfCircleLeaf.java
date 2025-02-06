import java.awt.*;

class HalfCircleLeaf extends Shape {

    public void draw(Graphics g, int x, int y, int size) {
        g.setColor(new Color(0, 255, 0)); // Bright green color
        g.fillArc(x, y, size, size, 0, 180);
    }
}

//testing comment
// test