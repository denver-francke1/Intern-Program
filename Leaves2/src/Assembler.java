import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Assembler extends JPanel {
    private Random rand = new Random();
    private final int[] sizes = {20, 40, 60}; // Small, medium, large sizes
    private final List<OvalLeaf> foregroundLeaves = new ArrayList<>(); // Leaves in front of the tree
    private final List<OvalLeaf> backgroundLeaves = new ArrayList<>(); // Leaves behind the tree
    private Timer timer;

    public Assembler() {
        // Start animation timer (runs every 100ms)
        timer = new Timer(100, e -> {
            for (OvalLeaf leaf : foregroundLeaves) {
                leaf.updateSway();
            }
            for (OvalLeaf leaf : backgroundLeaves) {
                leaf.updateSway();
            }
            repaint();
        });
        timer.start();
    }

    // Method to create random leaves
    private void createRandomLeaf(int x, int y, boolean isBackground) {
        int size = sizes[rand.nextInt(sizes.length)];
        OvalLeaf leaf = new OvalLeaf(x, y, size); // Create an OvalLeaf object

        if (isBackground) {
            backgroundLeaves.add(leaf); // Add to background list
        } else {
            foregroundLeaves.add(leaf); // Add to foreground list
        }
    }

    // Method to draw branches
    private void drawBranches(Graphics g, int trunkX, int trunkY, int trunkSize) {
        Branch branch = new Branch();
        int numBranches = 4;
        int branchSpacing = trunkSize / numBranches;

        for (int i = 0; i < numBranches; i++) {
            int branchStartY = (trunkY + i * branchSpacing - trunkSize / 2) + 100;
            boolean isLeftSide = i % 2 == 0;
            int branchStartX = trunkX + (isLeftSide ? -trunkSize / 8 : trunkSize / 8);
            int branchLength = (trunkSize / 3) + 5;
            double angle = Math.toRadians(30 + rand.nextInt(30));

            int branchEndX = branchStartX + (isLeftSide ? -1 : 1) * (int) (branchLength * Math.cos(angle));
            int branchEndY = branchStartY - (int) (branchLength * Math.sin(angle));

            branch.draw(g, trunkX, branchStartY, branchEndX, branchEndY);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int trunkX = getWidth() / 2;
        int trunkY = getHeight() / 2;
        int trunkSize = 300;

        // Define leaf-generating area
        int restrictedWidth = getWidth() / 2;
        int restrictedHeight = getHeight() - 300;
        int xOffset = getWidth() / 4;
        int yOffset = getHeight() / 4;

        int sideMargin = getWidth() / 20;
        int leafMinX = xOffset + sideMargin;
        int leafMaxX = xOffset + restrictedWidth - sideMargin;

        // Generate background leaves only once
        if (backgroundLeaves.isEmpty()) {
            for (int i = 0; i < 100; i++) {
                int x = leafMinX + rand.nextInt(leafMaxX - leafMinX);
                int y = yOffset + rand.nextInt(restrictedHeight);
                createRandomLeaf(x, y, true);
            }
        }

        // Draw background leaves (before the tree)
        for (OvalLeaf leaf : backgroundLeaves) {
            leaf.draw(g);
        }

        // Draw trunk
        Trunk trunk = new Trunk();
        trunk.draw(g, trunkX, trunkY, trunkSize);

        // Draw branches
        drawBranches(g, trunkX, trunkY, trunkSize);

        // Generate foreground leaves
        if (foregroundLeaves.isEmpty()) {
            for (int i = 0; i < 50; i++) {
                int x = leafMinX + rand.nextInt(leafMaxX - leafMinX);
                int y = yOffset + rand.nextInt(restrictedHeight);
                createRandomLeaf(x, y, false);
            }
        }

        // Draw foreground leaves (after the tree)
        for (OvalLeaf leaf : foregroundLeaves) {
            leaf.draw(g);
        }
    }







    // Method to set up the GUI
    public void assemble() {
        JFrame frame = new JFrame("Leaf and Branches Animation ");
        frame.add(this);
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
