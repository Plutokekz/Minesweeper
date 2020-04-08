package UI;

import Objects.Cell;
import Objects.MineField;
import Objects.assets.TileHandler;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class FieldPanel {

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private JPanel panelMineField;
    private int rows, columns;
    private final MineField mineField;
    private JLabel remainingMinesLabel;
    private JLabel clock;

    /**
     * @param columns height of the field
     * @param rows    width of the field
     **/
    FieldPanel(int rows, int columns, MineField mineField) {
        this.columns = columns;
        this.rows = rows;
        this.mineField = mineField;
        initializeGui();
    }


    public final void initializeGui() {

        // set up the main GUI
        gui.setBorder(new EmptyBorder(8, 8, 8, 8));


        // the smaller of the two sizes
        panelMineField = new JPanel(new CardLayout(10, 10)) {

            //Drawing Magic
            private void doDrawing(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;

                int s = Math.min((super.getHeight()) / FieldPanel.this.columns, (super.getWidth()) / FieldPanel.this.rows);

                int imageHeight = s * FieldPanel.this.columns;
                int imageWidth = s * FieldPanel.this.rows;


                int startX = (super.getWidth() - imageWidth) / 2;
                int startY = (super.getHeight() - imageHeight) / 2;


                for (int x = 0; x < FieldPanel.this.rows; x++) {
                    for (int y = 0; y < FieldPanel.this.columns; y++) {


                        BufferedImage currentImageCellTop, currentImageCellBottom, currentImageCellMid = null, currentImageFlag = null;
                        currentImageCellBottom = TileHandler.SPRITE_BOTTOM;
                        currentImageCellTop = TileHandler.SPRITE_TOP;

                        if (mineField.getField() != null) {
                            Cell currentCell = mineField.getFromField(x, y);
                            if (currentCell.isChecked()) {
                                currentImageCellMid = currentCell.getSprite();
                            } else if (currentCell.isMarked()) {
                                currentImageFlag = TileHandler.SPRITE_FLAG;
                            }
                        }


                        if (currentImageCellMid != null) {
                            g2d.drawImage(FieldPanel.this.resize(currentImageCellBottom, s, s), startX, startY, this);
                            g2d.drawImage(FieldPanel.this.resize(currentImageCellMid, s, s), startX, startY, this);
                        } else {
                            g2d.drawImage(FieldPanel.this.resize(currentImageCellTop, s, s), startX, startY, this);
                        }
                        if (currentImageFlag != null) {
                            g2d.drawImage(FieldPanel.this.resize(currentImageFlag, s, s), startX, startY, this);
                        }

                        startY += s;
                    }
                    startY = (super.getHeight() - imageHeight) / 2;
                    startX += s;
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
                if (super.getWidth() == 0 || super.getHeight() == 0) {
                    s = 32;
                } else
                    s = Math.max(Math.min((super.getWidth()) / FieldPanel.this.rows, (super.getHeight()) / FieldPanel.this.columns), 32);
                int rows = s * FieldPanel.this.rows;
                int columns = s * FieldPanel.this.columns;
                return new Dimension(rows, columns);
            }

        };

        Color ochre = new Color(142, 143, 144, 255);
        panelMineField.setBackground(ochre);
        gui.add(panelMineField);

        // set up panel in the top
        clock = new JLabel("00:00");
        remainingMinesLabel = new JLabel("Mines: " + mineField.getMinesRemaining());
        JButton resetButton = new JButton("Restart");
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        topPanel.add(clock);
        topPanel.add(resetButton);
        topPanel.add(remainingMinesLabel);
        gui.add(topPanel, BorderLayout.PAGE_START);


        // Action Listeners

        resetButton.addActionListener(e -> rest());

        panelMineField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                //Calculate the max Cell Tile Size => mint of (height of the JPanel times the Columns of the FieldPanel and width of the JPanel times Rows id the FieldPanel)
                int s = Math.min((panelMineField.getHeight()) / FieldPanel.this.columns, (panelMineField.getWidth()) / FieldPanel.this.rows);

                //Calculate the Image width and height
                int imageHeight = s * FieldPanel.this.columns;
                int imageWidth = s * FieldPanel.this.rows;

                //Calculate the difference between the actual JPanel size and the Calculated Image Size of all Cell Tiles
                // => (JPanel Height minus ( the Tile height times the Columns of the FieldPanel )) divided by 2 because the image is placed in the center and the border is always the same size
                int yDiff = (panelMineField.getHeight() - imageHeight) / 2;
                int xDiff = (panelMineField.getWidth() - imageWidth) / 2;


                //Calculate the x and y indexes for the Field
                // => Subtract the x and y difference from the Mouse Coordinates and divide it by the Calculate Image Size which gets divided
                int y = (e.getY() - yDiff) / (imageHeight / columns);
                int x = (e.getX() - xDiff) / (imageWidth / rows);

                // Left Click
                if (e.getButton() == 1) {
                    mineField.leftClick(x, y);
                    // Start Clock
                }
                // Debug Show entire Field by clicking the Mousewheel
                else if (e.getButton() == 2) {
                    mineField.showField();
                }
                // Right Click
                else if (e.getButton() == 3) {
                    mineField.rightClick(x, y);
                    // update minesRemaining
                    updateMinesLabel(mineField.getMinesRemaining());
                }

                // Update The drawn Image
                panelMineField.revalidate();
                panelMineField.repaint();

                // Debug
                System.out.println("Clicked Cell: (x: " + x + " y: " + y + ")");
            }
        });
    }

    public void rest() {
        //reset the field
        mineField.reset();
        //update the mine label
        updateMinesLabel(mineField.getMinesRemaining());
        //redraw everything
        panelMineField.revalidate();
        panelMineField.repaint();

    }

    private void updateMinesLabel(int mines) {
        remainingMinesLabel.setText("Mines: " + mines);
    }

    private void updateClockLabel(int minutes, int seconds) {
        // TODO make the timer label updatable
        clock.setText("" + "");
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

    public final JComponent getGui() {
        return gui;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }
}
