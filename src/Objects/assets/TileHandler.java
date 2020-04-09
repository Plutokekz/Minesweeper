package Objects.assets;


import Objects.assets.tiles.Number;
import Objects.assets.tiles.*;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

public class TileHandler {

    private static final HashMap<Integer, BufferedImage> SpriteNumberTiles = new HashMap<>();
    public static BufferedImage SPRITE_TOP = null;
    public static BufferedImage SPRITE_BOTTOM = null;
    public static BufferedImage SPRITE_MINE = null;
    public static BufferedImage SPRITE_FLAG = null;
    public static BufferedImage SPRITE_DEFAULT = null;

    //numbers

    public static BufferedImage SPRITE_NUMBER_1 = null;
    public static BufferedImage SPRITE_NUMBER_2 = null;
    public static BufferedImage SPRITE_NUMBER_3 = null;
    public static BufferedImage SPRITE_NUMBER_4 = null;
    public static BufferedImage SPRITE_NUMBER_5 = null;
    public static BufferedImage SPRITE_NUMBER_6 = null;
    public static BufferedImage SPRITE_NUMBER_7 = null;
    public static BufferedImage SPRITE_NUMBER_8 = null;
    private static final TileLoader tileLoader = new TileLoader();

    public static void loadDefaultSprites() throws IOException {

        SPRITE_TOP = tileLoader.loadSprite(Top.SpriteCellTopDefaultPath);
        SPRITE_BOTTOM = tileLoader.loadSprite(Background.SpriteCellBackgroundDefaultPath);
        SPRITE_MINE = tileLoader.loadSprite(Mine.SpriteCellMineDefaultPath);
        SPRITE_FLAG = tileLoader.loadSprite(Flag.SpriteFlagDefaultPath);
        SPRITE_DEFAULT = tileLoader.loadSprite(Default.DefaultSpritePath);

        SPRITE_NUMBER_1 = tileLoader.loadSprite(Number.SpriteCellNumberDefault1Path);
        SPRITE_NUMBER_2 = tileLoader.loadSprite(Number.SpriteCellNumberDefault2Path);
        SPRITE_NUMBER_3 = tileLoader.loadSprite(Number.SpriteCellNumberDefault3Path);
        SPRITE_NUMBER_4 = tileLoader.loadSprite(Number.SpriteCellNumberDefault4Path);
        SPRITE_NUMBER_5 = tileLoader.loadSprite(Number.SpriteCellNumberDefault5Path);
        SPRITE_NUMBER_6 = tileLoader.loadSprite(Number.SpriteCellNumberDefault6Path);
        SPRITE_NUMBER_7 = tileLoader.loadSprite(Number.SpriteCellNumberDefault7Path);
        SPRITE_NUMBER_8 = tileLoader.loadSprite(Number.SpriteCellNumberDefault8Path);

        SpriteNumberTiles.put(1, SPRITE_NUMBER_1);
        SpriteNumberTiles.put(2, SPRITE_NUMBER_2);
        SpriteNumberTiles.put(3, SPRITE_NUMBER_3);
        SpriteNumberTiles.put(4, SPRITE_NUMBER_4);
        SpriteNumberTiles.put(5, SPRITE_NUMBER_5);
        SpriteNumberTiles.put(6, SPRITE_NUMBER_6);
        SpriteNumberTiles.put(7, SPRITE_NUMBER_7);
        SpriteNumberTiles.put(8, SPRITE_NUMBER_8);

    }

    public static BufferedImage getSpriteNumberTile(int number) {
        BufferedImage image = SpriteNumberTiles.get(number);
        if (image == null) {
            image = SPRITE_DEFAULT;
        }
        return image;
    }


}
