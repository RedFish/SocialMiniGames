package kinect;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class KinectMonitor extends JFrame {
	private static final long serialVersionUID = 1L;
	public AveragePointTracking embed;
	
	public KinectMonitor(){
		setTitle("Kinect tracker");
        setLayout(new BorderLayout());
        setSize(640,570);
        embed = new AveragePointTracking();
        add(embed, BorderLayout.CENTER);
        embed.init(this);
        setVisible(true);
	}
	
	public static void main(String[] args) {
		new KinectMonitor();
	}

}
