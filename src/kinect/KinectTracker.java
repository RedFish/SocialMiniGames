package kinect;
import java.awt.AWTException;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import processing.core.PVector;


class KinectTracker {
	public AveragePointTracking parent;
	private boolean auto_click;
	private DialogTropLoin dialogtroploin;
	private boolean wait;
	
	// Size of kinect image
	int kw = 640;
	int kh = 480;
	int threshold_init = 600;
	int threshold = threshold_init;

	// Raw location
	PVector loc;

	// Interpolated location
	public PVector lerpedLoc;

	// Depth data
	int[] depth;


	PImage display;

	Mouse objMouse;
	
	KinectTracker(AveragePointTracking p) {
		parent = p;
		parent.kinect.start();
		parent.kinect.enableDepth(true);
		setAuto_click(true);
		dialogtroploin = new DialogTropLoin();
		dialogtroploin.setLocationRelativeTo(parent);
		setWait(false);
		
		// We could skip processing the grayscale image for efficiency
		// but this example is just demonstrating everything
		parent.kinect.processDepthImage(true);

		display = parent.createImage(kw,kh,PConstants.RGB);

		loc = new PVector(0,0);
		lerpedLoc = new PVector(0,0);

		try {
			objMouse = new Mouse();
			objMouse.setAutoDelay(0);
			objMouse.setAutoWaitForIdle(false);
		} catch (AWTException e) {
			System.out.println("Objet Curseur non initialisé");
			e.printStackTrace();
			parent.stop();
			System.exit(0);
		}
	}

	public void track() {

		// Get the raw depth as array of integers
		depth = parent.kinect.getRawDepth();

		// Being overly cautious here
		if (depth == null) return;

		float sumX = 0;
		float sumY = 0;
		float count = 0;

		for(int x = 0; x < kw; x++) {
			for(int y = 0; y < kh; y++) {
				// Mirroring the image
				int offset = kw-x-1+y*kw;
				// Grabbing the raw depth
				int rawDepth = depth[offset];

				// Testing against threshold
				if (rawDepth < threshold) {
					sumX += x;
					sumY += y;
					count++;
				}
			}
		}
		// As long as we found something
		if (count != 0) {
			loc = new PVector(sumX/count,sumY/count);
		}

		// Interpolating the location, doing it arbitrarily for now
		lerpedLoc.x = PApplet.lerp(lerpedLoc.x, loc.x, 0.3f);
		lerpedLoc.y = PApplet.lerp(lerpedLoc.y, loc.y, 0.3f);
		
		
		try {
			if (count != 0) {
				dialogtroploin.setVisible(false);
				//on actualise la position de la souris (et clic si immobile @see Mouse et PositionHistory)
				objMouse.mouseMove((int) loc.x,(int)  loc.y, auto_click, wait);
			}
			else{//cas ou personne n'est reconnu
				if (parent.frame != null) {
					dialogtroploin.setVisible(true);
				}
			}
		} catch (Exception e) {
			System.out.println("Objet Curseur non initialisé");
			e.printStackTrace();
			parent.stop();
			System.exit(0);
		}
	}

	public PVector getLerpedPos() {
		return lerpedLoc;
	}

	public PVector getPos() {
		return loc;
	}

	public void display() {
		PImage img = parent.kinect.getDepthImage();

		// Being overly cautious here
		if (depth == null || img == null) return;

		// Going to rewrite the depth image to show which pixels are in threshold
		// A lot of this is redundant, but this is just for demonstration purposes
		display.loadPixels();
		for(int x = 0; x < kw; x++) {
			for(int y = 0; y < kh; y++) {
				// mirroring image
				int offset = kw-x-1+y*kw;
				// Raw depth
				int rawDepth = depth[offset];

				int pix = x+y*display.width;
				if (rawDepth < threshold) {
					// A red color instead
					display.pixels[pix] = parent.color(150,50,50);
				} 
				else {
					display.pixels[pix] = img.pixels[offset];
				}
			}
		}
		display.updatePixels();

		// Draw the image
		parent.image(display,0,0);
	}

	public int getThreshold() {
		return threshold;
	}
	
	public int getThresholdInit() {
		return threshold_init;
	}

	public void setThreshold(int t) {
		threshold =  t;
	}

	public boolean isAuto_click() {
		return auto_click;
	}

	public void setAuto_click(boolean auto_click) {
		this.auto_click = auto_click;
	}

	public boolean isWait() {
		return wait;
	}

	public void setWait(boolean wait) {
		this.wait = wait;
	}
}