package objects.assets;

import objects.assets.tiles.Default;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TileLoader {


    /**
     * Loads the Sprite from the given path
     *
     * @param path to the sprite
     * @return BufferedImage
     * @throws IOException if the default file does not exists
     */
    public BufferedImage loadSprite(String path) throws IOException {
        BufferedImage sprite;
        try {
            sprite = ImageIO.read(TileLoader.class.getResource(path));
        } catch (IOException e) {
            sprite = ImageIO.read(TileLoader.class.getResource(Default.DefaultSpritePath));
        }
        return sprite;
    }

}
