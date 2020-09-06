package window;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class StartWindow extends JPanel implements ActionListener{
    private JButton startB;
    private JButton optionsB;
    private JButton exitB;
    
    private ImageIcon backgroundImage;

    public StartWindow() {
    	//Variable setting
    	
        //construct components
        startB = new JButton ("Start Game");
        optionsB = new JButton ("Options");
        exitB = new JButton ("Exit");

        //adjust size and set layout
        setPreferredSize (new Dimension (780, 680));
        setLayout (null);

        //add components
        add (startB);
        add (optionsB);
        add (exitB);
        
        //Add ActionListener
        startB.addActionListener(this);
        optionsB.addActionListener(this);
        exitB.addActionListener(this);
        
        //Change Style of the buttons
        startB.setOpaque(false);
		startB.setContentAreaFilled(false);
		startB.setBorderPainted(false);
		
		optionsB.setOpaque(false);
		optionsB.setContentAreaFilled(false);
		optionsB.setBorderPainted(false);
		
		exitB.setOpaque(false);
		exitB.setContentAreaFilled(false);
		exitB.setBorderPainted(false);
        
		startB.setFont(new Font("Courier New", Font.BOLD, 40));
		optionsB.setFont(new Font("Courier New", Font.BOLD, 40));
		exitB.setFont(new Font("Courier New", Font.BOLD, 40));
		
		startB.setForeground(Color.WHITE);
		optionsB.setForeground(Color.WHITE);
		exitB.setForeground(Color.WHITE);
		
        //set component bounds (only needed by Absolute Positioning)
        startB.setBounds (150, 200, 500, 100);
        optionsB.setBounds (150, 300, 500, 100);
        exitB.setBounds (150, 400, 500, 100);
    }

	public void paintComponent(Graphics g)
	{
    	super.paintComponent(g);
    	backgroundImage = new ImageIcon("resources/startWindowRes/startFramePNG.png");
		backgroundImage.paintIcon(this, g, 0, 0);
	}
    
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startB){
			startGame();
		}else if(e.getSource() == optionsB){
			startOptions();
		}else if(e.getSource() == exitB){
			exitGame();
		}
	}

	public void exitGame() {
		Main.exitStartFrame();
	}
	
	public void startOptions() {
		
	}

	public void startGame() {
		exitGame();
		Main.startGameWindow();
	}
}