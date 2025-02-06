import java.awt.*;

class OvalLeaf extends Shape {

    public void draw(Graphics g, int x, int y, int size) {
        g.setColor(new Color(0, 153, 0));
        g.fillOval(x, y, size, size / 2);
    }
}