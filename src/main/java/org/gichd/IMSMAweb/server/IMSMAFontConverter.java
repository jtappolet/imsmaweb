package org.gichd.IMSMAweb.server;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.font.GlyphVector;
import java.awt.font.TextLayout;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class IMSMAFontConverter {

	public static String outputFolder = "/Users/jt/Desktop/IMSMA";
	public static int outputWidth = 64;
	public static int outputHeight = 64;
	public static String defaultFont = "IMSMA2009";
	public static String defaultFontPath = "/Users/jt/Documents/workspace/imsmaweb/war/WEB-INF/resources/fonts/IMSMA2009.TTF";
	
	
	
	

	public static void main(String[] args) {

		new IMSMAFontConverter().convertAllIMSMAFonts();

	}

	public void convertAllIMSMAFonts() {

		String[] fontPaths = {"/Users/jt/Documents/workspace/imsmaweb/imsmaweb/war/WEB-INF/resources/fonts/IMSMA2009.TTF" };
		Font imsma = null;

		for (String imsmaFont : fontPaths) {

			try {
				BufferedInputStream is = new BufferedInputStream(
						new FileInputStream(imsmaFont));
				imsma = Font.createFont(Font.TRUETYPE_FONT, is);

			} catch (Exception e) {
				e.printStackTrace();
			}

			convertIMSMAFont(imsma, new File(outputFolder), Color.red, outputWidth, outputHeight);
		}
	}

	public void convertIMSMAFont(Font imsmaFont,
			File outputFolder, Color color, int width, int height) {

		String characterMap = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		for (Character character : characterMap.toCharArray()) {

			convertIMSMACharacter(imsmaFont, character, color, width, height);
			
		}

	}
	
	public String getImagePathForCharacter(Character character, Color color, int width, int height){
		
		Font imsma = null;
		try {
			BufferedInputStream is = new BufferedInputStream(
					new FileInputStream(defaultFontPath));
			imsma = Font.createFont(Font.TRUETYPE_FONT, is);

		} catch (Exception e) {
			e.printStackTrace();
		}
		File outputfile = new File(IMSMAFontConverter.outputFolder + "/"
				+ imsma.getName() + "_" + outputWidth + "x" + outputHeight + "_R"+color.getRed()+"G"+color.getGreen()+"B"+color.getBlue()+"_"
				+ (Character.isUpperCase(character) ? "uc_" : "lc_")
				+ character + ".png");
		if(!outputfile.exists()){
			convertIMSMACharacter(imsma,character,color,width,height);
		}
		return outputfile.getAbsolutePath();
	}
	
	
	public String convertIMSMACharacter(Font imsmaFont, Character character, Color color, int width, int height){
		BufferedImage bi = new BufferedImage(outputWidth, outputHeight,
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = (Graphics2D) bi.getGraphics();
		g2d.setColor(new Color(255, 0, 0, 255));
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY);
		;
		Font current = imsmaFont.deriveFont(Font.PLAIN,
				(float) (Math.max(height, width) * 2 - 4));
		g2d.setFont(current);
		TextLayout tl = new TextLayout(character.toString(), current, g2d
				.getFontMetrics().getFontRenderContext());
		GlyphVector v = current.createGlyphVector(g2d.getFontMetrics()
				.getFontRenderContext(), character.toString());

		g2d.translate(0, outputHeight);
		g2d.setColor(Color.orange);
		// g2d.fill(charShape);
		;
		g2d.setColor(color);
		// g2d.draw(charShape);
		tl.draw(g2d, 0, 0);
		File outputfile = new File(IMSMAFontConverter.outputFolder + "/"
				+ imsmaFont.getName() + "_" + outputWidth + "x" + outputHeight + "_R"+g2d.getColor().getRed()+"G"+g2d.getColor().getGreen()+"B"+g2d.getColor().getBlue()+"_"
				+ (Character.isUpperCase(character) ? "uc_" : "lc_")
				+ character + ".png");
		try {
			BufferedImage out = new BufferedImage(outputWidth,
					outputHeight, BufferedImage.TYPE_INT_ARGB);
			out.getGraphics().drawImage(
					makeColorTransparent(bi, Color.white), 0, 0, null);
			;
			ImageIO.write(out, "png", outputfile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputfile.getAbsolutePath();
	}

	// code taken from:
	// http://marxsoftware.blogspot.it/2011/04/making-white-image-backgrounds.html
	public static Image makeColorTransparent(final BufferedImage im,
			final Color color) {
		final ImageFilter filter = new RGBImageFilter() {
			// the color we are looking for (white)... Alpha bits are set to
			// opaque
			public int markerRGB = color.getRGB() | 0xFFFFFFFF;

			public final int filterRGB(final int x, final int y, final int rgb) {
				if ((rgb | 0xFF000000) == markerRGB) {
					// Mark the alpha bits as zero - transparent
					return 0x00FFFFFF & rgb;
				}

				else {
					// nothing to do
					;
					return rgb;
				}
			}
		};

		final ImageProducer ip = new FilteredImageSource(im.getSource(), filter);
		return Toolkit.getDefaultToolkit().createImage(ip);
	}

}
