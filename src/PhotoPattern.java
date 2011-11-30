import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 */

/**
 * @author Adrian
 *
 */
public class PhotoPattern {
	/*private*/ BufferedImage image;
	private int percentMatch;
	
	
	
	public int getPercentMatch(){ return percentMatch;}
	
	/**
	 * 
	 */
	public PhotoPattern(BufferedImage image) {
		this.image=image;
	}
	

	/**
	 * @param args
	 */
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
//
//	public /*Pair*/ Object size(){
//		//rets some object in whihc width and height are stored
//		return null;
//	}
	

	public boolean isEqual(PhotoPattern newPhoto, int delta/*equal w margin of error +/- delta */) {
//		Toolkit.getDefaultToolkit().createImage(imagedata)
//		if (!newPhoto.size().equals(this.size())) return false;
		
		for (int i=0; i<Math.min( image.getWidth(), newPhoto.image.getWidth()); i++){
			for (int j=0; j<Math.min( image.getHeight(), newPhoto.image.getHeight()); j++){
				if ( Math.abs(image.getRGB(i, j) - newPhoto.image.getRGB(i, j)) > delta){
					return false;
				}
			}
		}
		
		return true;
	}
	
	
	/** 
	 * Retrieves black and white XOR mask (difference mask bw this and newPhoto)
	 * 
	 * @param newPhoto
	 * @return
	 *///TODO add delta threshold for equality difference
	public PhotoPattern getDiffMask(PhotoPattern newPhoto){
		BufferedImage resultImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		//also cache last 10 XORs
		//get max size: this or newPhoto

		int width=Math.min( image.getWidth(), newPhoto.image.getWidth());
		int height=Math.min( image.getHeight(), newPhoto.image.getHeight()); 
		int unmatchedPixels=0;
		
		for (int i=0; i<width; i++){
			for (int j=0; j<height; j++){
				int rgb=newPhoto.image.getRGB(i, j) ^ image.getRGB(i, j);
				rgb = rgb | 0xff000000;//must have FF at the head to be visible
//				rgb = rgb & 0xffffffff;//yes, but no point since it's same as above hehe
				//could actually save the diff here and create an inverse of those pixels
				//so if they want to see the 2 images overlapped (@once) I can display 
				//those where the mask is by copying my saved inversed ontop of merged img
				//ALSO could apply the mask and where mask exists invert
				
				//mark all different pixels black and the rest white
				if ((rgb & 0x00ffffff) > 0){ //TODO add: (> threshold)
					rgb=0xffffffff;
					unmatchedPixels++;
				} else {
					rgb=0xff000000;
				}
				resultImage.setRGB(i, j, rgb);
//				resultImage.setRGB(i, j, c.HSBtoRGB(1, 1, 1));
			}
		}
		
		PhotoPattern resultPattern = new PhotoPattern (resultImage);
		
		//this is not exact; it looks only at portion matched not entire img
		resultPattern.percentMatch= (unmatchedPixels*100)/(width*height);
		
		return resultPattern;
	}
	
	
	//TODO add delta threshold for equality difference
	public PhotoPattern getDiff(PhotoPattern newPhoto){
		BufferedImage resultImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		//also cache last 10 XORs
		//get max size: this or newPhoto

		int width=Math.min( image.getWidth(), newPhoto.image.getWidth());
		int height=Math.min( image.getHeight(), newPhoto.image.getHeight()); 
		
		for (int i=0; i<width; i++){
			for (int j=0; j<height; j++){
				int rgb=newPhoto.image.getRGB(i, j) ^ image.getRGB(i, j);
				rgb = rgb | 0xff000000;//must have FF at the head to be visible
//				rgb = rgb & 0xffffffff;//yes, but no point since it's same as above hehe
				//could actually save the diff here and create an inverse of those pixels
				//so if they want to see the 2 images overlapped (@once) I can display 
				//those where the mask is by copying my saved inversed ontop of merged img
				//ALSO could apply the mask and where mask exists invert
				
//				rgb= image.getRGB(i, j) & 0xFFffffff;//mask: opacity,R,G,B
				resultImage.setRGB(i, j, rgb);
//				resultImage.setRGB(i, j, c.HSBtoRGB(1, 1, 1));
			}
		}
		
		return new PhotoPattern (resultImage);
	}


	private BufferedImage getImage(byte[] imageData) throws IOException{
		ByteArrayInputStream in = new ByteArrayInputStream(imageData);
		BufferedImage bi= ImageIO.read(in);
		return bi;
	}
	
	
	private byte[] getBytes(PhotoPattern newPhoto) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ImageIO.write(newPhoto.image, "png", baos);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] newPhotoBytes = baos.toByteArray();
		
		return newPhotoBytes;
		
//		WritableRaster raster   = image.getRaster();
//		DataBufferByte buffer = (DataBufferByte)raster.getDataBuffer();
//		return buffer.getData();

	}
	
	
	
}
