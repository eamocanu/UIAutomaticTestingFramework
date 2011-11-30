import java.util.List;

/**
 * 
 */

/**
 * @author Adrian
 *
 */
public class DiffEngine {
	private PhotoPattern basePattern;
	

	
	public DiffEngine(PhotoPattern basePattern) {
		this.basePattern=basePattern;
	}
	

	
	/**
	 * Sets pattern against which to check all requests
	 *  
	 * @param basePattern
	 */
	public void setBasePattern(PhotoPattern basePattern){
		this.basePattern=basePattern;
	}
	
	
	/**
	 * @param newPhoto
	 * @param marginOfError		maximum difference between 2 pixel colour to be considered the same colour
	 * @return
	 */
	public boolean isDifferent(PhotoPattern newPhoto, int marginOfError){
		return !basePattern.isEqual(newPhoto, marginOfError);
	}
	
	
	/** Checkes if any of the photo patterns in the input differ from base pattern. 
	 * 
	 * @param newPhotos		list of patterns to check
	 * @param marginOfError		maximum difference between 2 pixel colour to be considered the same colour
	 * @return		an array of booleans indicating which patterns in list differ from base pattern set
	 */
	public boolean [] isDifferent(List<PhotoPattern> newPhotos, int marginOfError){
		boolean [] difference = new boolean[newPhotos.size()];
		int i=0;
		
		for (PhotoPattern crtPattern: newPhotos){
			difference[i++]= !basePattern.isEqual(crtPattern, marginOfError); 
		}
		
		return difference;
	}
	
	
	public boolean [] getDiffMask(List<PhotoPattern> newPhotos, int marginOfError){
		boolean [] difference = new boolean[newPhotos.size()];
		int i=0;
		
		for (PhotoPattern crtPattern: newPhotos){
			difference[i++]= !basePattern.isEqual(crtPattern, marginOfError); 
		}
		
		return difference;
	}
	
	
	public PhotoPattern getDifference(PhotoPattern newPhoto){
		return basePattern.getDiff(newPhoto);
	}
	
	
	public static boolean isDifferent(PhotoPattern a, PhotoPattern b, int marginOfError /*threshold*/){
		return a.isEqual(b, marginOfError);
	}
	
	
	public static PhotoPattern getDifference(PhotoPattern a, PhotoPattern b){
		return a.getDiff(b);
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
