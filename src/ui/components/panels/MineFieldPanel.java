package ui.components.panels;

import objects.*;
import objects.assets.TileHandler;
import objects.base.Point;
import objects.type.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class MineFieldPanel extends JPanel {

    private final MineField mineField;

    public MineFieldPanel(LayoutManager layout, Difficulty difficulty) {
        super(layout);
        mineField = new MineField(difficulty);
    }

    /**
     * Calculates the max Cell Size => min of
     * (height of the JPanel divided by Columns of the FieldPanel and width of the JPanel divided by the Rows of the FieldPanel)
     *
     * @return int max cell size
     */
    public int calculateMaxCellSize() {
        Dimension mineFieldDimension = mineField.getDimension();
        return (int) Math.min((super.getHeight()) / mineFieldDimension.getHeight(), (super.getWidth()) / mineFieldDimension.getWidth());
    }

    /**
     * Calculate the JPanel width and height by the max CellSize and the rows and columns
     *
     * @param maxCellSize the calculated Max Cell Size
     * @return Dimension of the JPanel
     */
    public Dimension calculatePanelMineFieldDimension(int maxCellSize) {
        Dimension mineFieldDimension = mineField.getDimension();
        return new Dimension(maxCellSize * (int) mineFieldDimension.getWidth(), maxCellSize * (int) mineFieldDimension.getHeight());
    }

    /**
     * Calculate the difference between the actual JPanel size and the Calculated Image Size of all Cell Tiles
     * => (JPanel Height minus ( the Tile height times the Columns of the FieldPanel )) divided by 2 because the image is placed in the center and the border should be always the same size
     *
     * @param imageDimension the Calculated JPanel Size.
     * @return Point with the x and y offset
     */
    public Point calculateOffsetPoint(Dimension imageDimension) {
        return new Point((int) (super.getWidth() - imageDimension.getWidth()) / 2, (int) (super.getHeight() - imageDimension.getHeight()) / 2);
    }

    /**
     * Draws the Minefield on the Graphics g
     */
    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        int s = calculateMaxCellSize();
        Dimension imageDimension = calculatePanelMineFieldDimension(s);
        Point startPoint = calculateOffsetPoint(imageDimension);
        int startPointY = startPoint.getY();

        Dimension mineFieldDimension = mineField.getDimension();

        for (int x = 0; x < mineFieldDimension.getWidth(); x++) {
            for (int y = 0; y < mineFieldDimension.getHeight(); y++) {


                BufferedImage currentImageCellTop, currentImageCellBottom, currentImageCellMid = null, currentImageFlag = null;
                currentImageCellBottom = TileHandler.SPRITE_BOTTOM;
                currentImageCellTop = TileHandler.SPRITE_TOP;

                if (!mineField.isEmpty()) {
                    Cell currentCell = mineField.getFromField(x, y);
                    if (currentCell.isChecked()) {
                        currentImageCellMid = currentCell.getSprite();
                    } else if (currentCell.isMarked()) {
                        currentImageFlag = TileHandler.SPRITE_FLAG;
                    }
                }


                if (currentImageCellMid != null) {
                    g2d.drawImage(resize(currentImageCellBottom, s, s), startPoint.getX(), startPoint.getY(), this);
                    g2d.drawImage(resize(currentImageCellMid, s, s), startPoint.getX(), startPoint.getY(), this);
                } else {
                    g2d.drawImage(resize(currentImageCellTop, s, s), startPoint.getX(), startPoint.getY(), this);
                }
                if (currentImageFlag != null) {
                    g2d.drawImage(resize(currentImageFlag, s, s), startPoint.getX(), startPoint.getY(), this);
                }

                startPoint.setY(startPoint.getY() + s);
            }
            startPoint.setY(startPointY);
            startPoint.setX(startPoint.getX() + s);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public final Dimension getPreferredSize() {
        int s;
        Dimension mineFieldDimension = mineField.getDimension();
        if (super.getWidth() == 0 || super.getHeight() == 0) {
            s = 32;
        } else
            s = (int) Math.max(Math.min((super.getWidth()) / mineFieldDimension.getWidth(), (super.getHeight()) / mineFieldDimension.getHeight()), 32);
        //int calcRows = (int) (s * mineFieldDimension.getWidth());
        //int calcColumns = (int) (s * mineFieldDimension.getHeight());
        return new Dimension((int) (s * mineFieldDimension.getWidth()), (int) (s * mineFieldDimension.getHeight()));
    }

    /**
     * Resize a BufferedImage
     *
     * @param img       BufferedImage to resize
     * @param newHeight of the Image
     * @param newWidth  of the Image
     * @return new BufferedImage
     */
    private BufferedImage resize(BufferedImage img, int newWidth, int newHeight) {
        Image tmp = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage dImg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dImg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dImg;
    }

    public MineFieldState getMineFieldState() {
        return mineField.getMineFieldState();
    }

    public String getDifficultyString() {
        return mineField.getDifficultyString();
    }

    public void debugShowAll() {
        mineField.showField();
        revalidate();
        repaint();
    }

    /**
     * Updates the minefield by the given GameAction
     * and revalidate ans repaints the JPanel
     *
     * @param gameAction tu update the minefield
     */
    public GameState update(GameAction gameAction) {
        GameState state = mineField.update(gameAction);
        revalidate();
        repaint();
        return state;
    }

}
