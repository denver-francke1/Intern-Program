import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Assembler extends JPanel {
    private Random rand = new Random();

    private int[] sizes = {20, 40, 60}; // Small, medium, large sizes

    // Method to draw a random leaf
    private void drawRandomLeaf(Graphics g, int x, int y) {
        int size = sizes[rand.nextInt(sizes.length)];
        Shape leaf = null;
        int leafType = rand.nextInt(3); // genarate a random case number between 0-3
        switch (leafType) {
            case 0:
                leaf = new OvalLeaf();
                break;
            case 1:
                leaf = new HalfCircleLeaf();
                break;
            case 2:
                leaf = new TrangleLeaf();
                break;
            default:
                throw new IllegalStateException("Unexpected leaf type: " + leafType);
        }

        leaf.draw(g, x, y, size);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw random leaves at different positions
        for (int i = 0; i < 100; i++) {
            int x = rand.nextInt(getWidth());
            int y = rand.nextInt(getHeight());
            int xOffset = getWidth() / 4; // Offset to skip the leftmost 1/4

            drawRandomLeaf(g, x, y);
        }


    }
    // Method to set up the GUI
    public void assemble () {
        JFrame frame = new JFrame("Leaf Drawing");
        frame.add(this);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}