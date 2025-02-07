import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Assembler extends JPanel {
    private Random rand = new Random();

    final private int[] sizes = {20, 40, 60}; // Small, medium, large sizes

    // Method to draw a random leaf
    private void drawRandomLeaf(Graphics g, int x, int y) {
        int size = sizes[rand.nextInt(sizes.length)];
        Shape leaf = null;
        int leafType = rand.nextInt(2); // genarate a random case number between 0-3
        switch (leafType) {
            case 0:
                leaf = new OvalLeaf();
                break;
            case 1:
                leaf = new HalfCircleLeaf();
                break;
//            case 2:
//                leaf = new TrangleLeaf();
//                break;
            default:
                throw new IllegalStateException("Unexpected leaf type: " + leafType);
        }

        leaf.draw(g, x, y, size);
    }

    // Method to draw branches
    private void drawBranches(Graphics g, int trunkX, int trunkY, int trunkSize) {
        Branch branch = new Branch();

        // Define multiple attachment points along the height of the trunk
        int numBranches = 4; // Number of branches
        int branchSpacing = trunkSize / numBranches; // Vertical spacing between branches

        for (int i = 0; i < numBranches; i++) {
            // Calculate the vertical position of the branch attachment point
            int branchStartY = (trunkY + i * branchSpacing - trunkSize / 2)+100;

            // Alternate branches on the left and right sides of the trunk
            boolean isLeftSide = i % 2 == 0;
            int branchStartX = trunkX + (isLeftSide ? -trunkSize / 8 : trunkSize / 8);

            // Define the length and angle of the branch
            int branchLength = (trunkSize / 3) +5; // Length of the branch
            double angle = Math.toRadians(30 + rand.nextInt(30)); // Random angle between 30° and 60°

            // Calculate the endpoint of the branch
            int branchEndX = branchStartX + (isLeftSide ? -1 : 1) * (int) (branchLength * Math.cos(angle));
            int branchEndY = branchStartY - (int) (branchLength * Math.sin(angle));

            // Draw the branch
            branch.draw(g, trunkX, branchStartY, branchEndX, branchEndY);
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the tree trunk in the center of the panel
        int trunkX = getWidth() / 2;
        int trunkY = getHeight() / 2;
        int trunkSize = 300; // Size of the trunk
        Trunk trunk = new Trunk();
        trunk.draw(g, trunkX, trunkY, trunkSize);

// Draw branches extending from the trunk
        drawBranches(g, trunkX, trunkY, trunkSize);


        int restrictedWidth = getWidth() / 2; // Use only the middle half of the width
        int restrictedHeight = getHeight() -300; // Use only the top half of the height
        int xOffset = getWidth() / 4; // Offset to skip the leftmost
        int yOffset = getHeight()/4; // Start from the top

        // Reduce the leaf-generating area further by adding margins on both sides
        int sideMargin = getWidth() / 20; // Margin on each side (1/8 of the panel width)
        int leafMinX = (xOffset + sideMargin); // Minimum x-coordinate for leaves
        int leafMaxX = xOffset + restrictedWidth - sideMargin; // Maximum x-coordinate for leaves

        // Draw random leaves at different positions within the adjusted restricted region
        for (int i = 0; i < 100; i++) {
            int x = leafMinX + rand.nextInt(leafMaxX - leafMinX); // Random x-coordinate within the adjusted range
            int y = yOffset + rand.nextInt(restrictedHeight); // Random y-coordinate in the restricted height
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