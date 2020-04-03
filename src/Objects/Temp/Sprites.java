package Objects.Temp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class Sprites {

    public static BufferedImage SpriteCellTileBuffered = null;
    public static BufferedImage SpriteMineTileBuffered = null;
    public static BufferedImage SpriteDefaultTile = null;
    public static BufferedImage SpriteCellEmptyTile = null;

    public static BufferedImage SpriteNumberTile0 = null;
    public static BufferedImage SpriteNumberTile1 = null;
    public static BufferedImage SpriteNumberTile2 = null;
    public static BufferedImage SpriteNumberTile3 = null;
    public static BufferedImage SpriteNumberTile4 = null;
    public static BufferedImage SpriteNumberTile5 = null;
    public static BufferedImage SpriteNumberTile6 = null;
    public static BufferedImage SpriteNumberTile7 = null;
    public static BufferedImage SpriteNumberTile8 = null;
    public static BufferedImage SpriteNumberTile9 = null;

    private static HashMap<Integer, BufferedImage> SpriteNumberTiles = new HashMap<>();


    public static void loadSprites() throws IOException {

        SpriteCellTileBuffered = ImageIO.read(Sprites.class.getResource("/assets/tiles/SpriteBoxTileCell.png"));
        SpriteMineTileBuffered = ImageIO.read(Sprites.class.getResource("/assets/tiles/SpriteMineTile.png"));
        SpriteDefaultTile = ImageIO.read(Sprites.class.getResource("/assets/tiles/SpriteCellDefaultTile.png"));
        SpriteCellEmptyTile = ImageIO.read(Sprites.class.getResource("/assets/tiles/SpriteCellEmptyTile.png"));

        //SpriteNumberTile0 = ImageIO.read(Sprites.class.getResource("/assets/tiles/SpriteNumberTile0.png"));
        SpriteNumberTile1 = ImageIO.read(Sprites.class.getResource("/assets/tiles/SpriteNumberTile1.png"));
        SpriteNumberTile2 = ImageIO.read(Sprites.class.getResource("/assets/tiles/SpriteNumberTile2.png"));
        SpriteNumberTile3 = ImageIO.read(Sprites.class.getResource("/assets/tiles/SpriteNumberTile3.png"));
        SpriteNumberTile4 = ImageIO.read(Sprites.class.getResource("/assets/tiles/SpriteNumberTile4.png"));
        SpriteNumberTile5 = ImageIO.read(Sprites.class.getResource("/assets/tiles/SpriteNumberTile5.png"));
        SpriteNumberTile6 = ImageIO.read(Sprites.class.getResource("/assets/tiles/SpriteNumberTile6.png"));
        //SpriteNumberTile7 = ImageIO.read(Sprites.class.getResource("/assets/tiles/SpriteNumberTile7.png"));
        //SpriteNumberTile8 = ImageIO.read(Sprites.class.getResource("/assets/tiles/SpriteNumberTile8.png"));
        //SpriteNumberTile9 = ImageIO.read(Sprites.class.getResource("/assets/tiles/SpriteNumberTile9.png"));

        //SpriteNumberTiles.put(0, SpriteNumberTile0);
        SpriteNumberTiles.put(1, SpriteNumberTile1);
        SpriteNumberTiles.put(2, SpriteNumberTile2);
        SpriteNumberTiles.put(3, SpriteNumberTile3);
        SpriteNumberTiles.put(4, SpriteNumberTile4);
        SpriteNumberTiles.put(5, SpriteNumberTile5);
        SpriteNumberTiles.put(6, SpriteNumberTile6);
        //SpriteNumberTiles.put(7, SpriteNumberTile7);
        //SpriteNumberTiles.put(8, SpriteNumberTile8);
        //SpriteNumberTiles.put(9, SpriteNumberTile9);


    }

    public static BufferedImage getSpriteNumberTile(int number) {
        return SpriteNumberTiles.get(number);
    }

}
