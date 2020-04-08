package Objects.assets;

import Objects.assets.tiles.Default;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TileLoader {

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
