package utils;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;

public class WindowUtils {
	
	private WindowUtils() {
		
	}
	
	public static void centreWindow(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
        int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(x, y);
    }
	
	public static void setFullScreen(Window frame) {
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (int) dimension.getWidth();
        int y = (int) dimension.getHeight();
        frame.setLocation(0, 0);
        frame.setSize(x, y);
    }
}
