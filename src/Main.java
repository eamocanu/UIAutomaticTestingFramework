import java.awt.AWTException;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author Adrian
 *
 */
public class Main {

	/**
	 * 
	 */
	public Main() {
		// TODO Auto-generated constructor stub
	}

	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		try {
			BufferedImage captureScreen = ImageUtils.captureScreen(0, 0, 400, 400);
//			ImageUtils.saveJPEGImage(captureScreen, "C:/tst/3.jpg");
			
			BufferedImage img1= ImageUtils.openImage("C:/tst/1.png");
			BufferedImage img2= ImageUtils.openImage("C:/tst/2.png");
			
			PhotoPattern p1= new PhotoPattern(img1);
			PhotoPattern p2= new PhotoPattern(img2);
			
			PhotoPattern p3= p1.getDiff(p2);
			ImageUtils.saveJPEGImage(p3.image, "C:/tst/1XOR2.png");
			
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
