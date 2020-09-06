package window;

import javax.swing.JFrame;

public class Main{
	
	public static JFrame startMenuFrame = new JFrame("Simon Says");
	public static JFrame startGameFrame = new JFrame("Simon Says");
	public static JFrame startOptionFrame = new JFrame ("Simon Says");
	
	public static void main (String[] args) {
		startGameWindow();
    }
	
	public static void startFrameWindow(){
		startMenuFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        startMenuFrame.getContentPane().add (new StartWindow());
        startMenuFrame.pack();
        startMenuFrame.setLocationRelativeTo(null);
        startMenuFrame.setResizable(false);
        startMenuFrame.setVisible (true);
        startMenuFrame.setFocusable(true);
        startMenuFrame.setFocusableWindowState(true);
	}
	
	public static void exitStartFrame(){
		startMenuFrame.dispose();
	}
	
	public static void startGameWindow(){
		startGameFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        startGameFrame.getContentPane().add (new GameWindow());
        startGameFrame.pack();
        startGameFrame.setLocationRelativeTo(null);
        startGameFrame.setResizable(false);
        startGameFrame.setVisible (true);
        startGameFrame.setFocusable(true);
        startGameFrame.setFocusableWindowState(true);
	}
	
	public static void exitGameWindow(){
		startGameFrame.dispose();
	}
	
	public static void startOptionWindow(){
		
        startOptionFrame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        startOptionFrame.getContentPane().add (new OptionWindow());
        startOptionFrame.pack();
        startOptionFrame.setLocationRelativeTo(null);
        startOptionFrame.setResizable(false);
        startOptionFrame.setVisible (true);
        startOptionFrame.setFocusable(true);
        startOptionFrame.setFocusableWindowState(true);
	}
	
	public static void exitStartOptionWindow(){
		startOptionFrame.dispose();
	}
}
