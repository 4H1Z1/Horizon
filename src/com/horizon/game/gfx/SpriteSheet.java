package com.horizon.game.gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	public String path;
	public int width;
	public int height;

	public int[] pixles;

	public SpriteSheet(String path) {
		BufferedImage image = null;
		try {
			image = ImageIO.read(SpriteSheet.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (image == null) {
			return;
		}
		this.path = path;
		this.height = image.getHeight();
		this.width = image.getWidth();

		pixles = image.getRGB(0, 0, width, height, null, 0, width);
		/*for (int i = 0; i < pixles.length; i++) {
			pixles[i] = (pixles[i] & 0xff) / 64;
		}*/
	}
}
