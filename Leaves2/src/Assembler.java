import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Assembler extends JPanel {
    private Random rand = new Random();
    private Shape[] leafTypes = {new OvalLeaf(), new HalfCircleLeaf()};
    private int[] sizes = {20, 40, 60}; // Small, medium, large sizes

    // Method to draw a random leaf
    private void drawRandomLeaf(Graphics g, int x, int y) {
        int type = rand.nextInt(leafTypes.length);
        int size = sizes[rand.nextInt(sizes.length)];
        leafTypes[type].draw(g, x, y, size);
    }



    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw random leaves at different positions
        for (int i = 0; i < 100; i++) {
            int x = rand.nextInt(getWidth());
            int y = rand.nextInt(getHeight());
            drawRandomLeaf(g, x, y);
        }


    }
    // Method to set up the GUI
    public void draw () {
        JFrame frame = new JFrame("Leaf Drawing");
        frame.add(this);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}