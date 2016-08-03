package utils;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

/**
 * A set of utility functions for the main frame.
 * @author Tim
 *
 */
public class WindowUtils {
	/**
	 * Private constructor.
	 */
	private WindowUtils() {
		
	}
	
	/**
	 * Positions a frame at the center of the screen.
	 * @param frame The frame in question.
	 */
	public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
	
	/**
	 * Resizes the given frame to full screen.
	 * @param frame The frame in question.
	 */
	public static void setFullScreen(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) dimension.getWidth();
        int y = (int) dimension.getHeight();
        frame.setLocation(0, 0);
        frame.setSize(x, y);
    }
}
