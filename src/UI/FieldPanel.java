package UI;

import Objects.Cell;
import Objects.MineField;
import Objects.Temp.Sprites;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class FieldPanel {

    private final JPanel gui = new JPanel(new BorderLayout(3, 3));
    private int rows, columns;
    private MineField mineField;


    FieldPanel(int rows, int columns, MineField mineField) {
        this.columns = columns;
        this.rows = rows;
        this.mineField = mineField;
        initializeGui();
    }





    public final void initializeGui() {

        // set up the main GUI
        gui.setBorder(new EmptyBorder(16, 16, 16, 16));


        // the smaller of the two sizes
        JPanel panelMineField = new JPanel() {

            private void doDrawing(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                int startX = 0;
                int startY = 0;
                int height;
                int width = 0;
                for (int x = 0; x < rows; x++) {
                    for (int y = 0; y < columns; y++) {
                        height = (super.getHeight()) / columns;
                        width = (super.getWidth()) / rows;

                        BufferedImage currentImageCellTop, currentImageCellBottom, currentImageCellMid = null;

                        currentImageCellBottom = Sprites.SpriteCellEmptyTile;

                        currentImageCellTop = Sprites.SpriteCellTileBuffered;

                        if (mineField.getField() != null) {
                            Cell currentCell = mineField.getField().getCellFromField(x, y);
                            if (currentCell.isChecked()) {
                                currentImageCellMid = currentCell.getSprite();
                            }
                        }

                        g2d.drawImage(FieldPanel.this.resize(currentImageCellBottom, width, height), startX, startY, null);
                        if (currentImageCellMid != null) {
                            g2d.drawImage(FieldPanel.this.resize(currentImageCellMid, width, height), startX, startY, null);
                        } else {
                            g2d.drawImage(FieldPanel.this.resize(currentImageCellTop, width, height), startX, startY, null);
                        }

                        startY += height;
                    }
                    startY = 0;
                    startX += width;
                }
            }


            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                doDrawing(g);
            }

            /**
             * Override the preferred size to return the largest it can, in
             * a square shape.  Must (must, must) be added to a GridBagLayout
             * as the only component (it uses the parent as a guide to size)
             * with no GridBagConstaint (so it is centered).
             */
            @Override
            public final Dimension getPreferredSize() {
                Dimension d = super.getPreferredSize();
                Dimension prefSize;
                Component c = getParent();
                if (c == null) {
                    prefSize = new Dimension(
                            (int) d.getWidth(), (int) d.getHeight());
                } else if (c.getWidth() > d.getWidth() && c.getHeight() > d.getHeight()) {
                    prefSize = c.getSize();
                } else {
                    prefSize = d;
                }
                int w = (int) prefSize.getWidth();
                int h = (int) prefSize.getHeight();
                // the smaller of the two sizes
                int s = (Math.min(w, h));
                return new Dimension(s, s);
            }


        };


        //panelMineField.setBorder(new CompoundBorder(new EmptyBorder(1, 1, 1, 1), new LineBorder(Color.BLACK)));
        // Set the BG to be ochre
        Color ochre = new Color(142, 143, 144, 255);
        panelMineField.setBackground(ochre);
        JPanel panelFieldConstrain = new JPanel(new GridBagLayout());
        panelFieldConstrain.setBackground(ochre);
        panelFieldConstrain.add(panelMineField);
        gui.add(panelFieldConstrain);

        panelMineField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                int y = e.getY() / (panelMineField.getHeight() / columns);
                int x = e.getX() / (panelMineField.getWidth() / rows);


                if (e.getButton() == 1) {
                    mineField.leftClick(x, y);
                } else if (e.getButton() == 3) {
                    mineField.rightClick(x, y);
                }


                panelMineField.repaint();


                System.out.println("Clicked Cell: (x: " + x + " y: " + y + ")");


            }
        });

    }

    private BufferedImage resize(BufferedImage img, int newWidth, int newHeight) {
        Image tmp = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    public final JComponent getGui() {
        return gui;
    }
}
