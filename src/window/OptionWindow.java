package window;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import tools.ReadWriteTextFile;

@SuppressWarnings("serial")
public class OptionWindow extends JPanel implements ActionListener, Runnable{
    
    private JLabel difficultyL;
    private JButton difEasyB;
    private JButton difMediumB;
    private JButton difHardB;
    
    private JButton cancelB;
    private JButton saveB;
    private JLabel errorL;
    
    private JLabel startTilesL;
    private JTextField startTilesTF;
    
    private JLabel timeBetwL;
    private JTextField timeBetweenTF;
    
    private JLabel tilesAddedL;
    private JTextField tilesAddedTF;
    
    private JButton resetHighScoreB;
    
    private ImageIcon backgroundImage;
    
    private int startTiles;
    private int time;
    private int tilesAdded;
    
    private boolean resetHighscore = false;
    private boolean showErrorMessage = false;
    private boolean running = false;
    private String errorMessage;
    
    ReadWriteTextFile rw = new ReadWriteTextFile();

    public OptionWindow() {
    	System.out.println("Loaded Constructor");
    	loadOptions();
    	setupGui();
    }
    
    public void loadOptions(){
    	final String[] optionsLoaded = rw.readLinesToArray("/optionsFile.txt", true);
		
		time = Integer.parseInt(optionsLoaded[2]);
		startTiles = Integer.parseInt(optionsLoaded[4]);
		tilesAdded = Integer.parseInt(optionsLoaded[8]);
    }
    
    public void setupGui(){
    	 //construct components
        difEasyB = new JButton ("Easy");
        difficultyL = new JLabel ("Difficulty:");
        difMediumB = new JButton ("Medium");
        difHardB = new JButton ("Hard");
        cancelB = new JButton ("Cancel");
        saveB = new JButton ("Save");
        errorL = new JLabel ("");
        startTilesL = new JLabel ("Starting Tiles: ");
        startTilesTF = new JTextField (""+startTiles);
        timeBetwL = new JLabel ("Time in between from tiles: ");
        timeBetweenTF = new JTextField (""+time);
        tilesAddedL = new JLabel ("Tiles Added per Round: ");
        tilesAddedTF = new JTextField (""+tilesAdded);
        resetHighScoreB = new JButton ("Reset Highscore");

        //adjust size and set layout
        setPreferredSize (new Dimension (544, 410));
        setLayout (null);

        //add components
        add (difEasyB);
        add (difficultyL);
        add (difMediumB);
        add (difHardB);
        add (cancelB);
        add (saveB);
        add (errorL);
        add (startTilesL);
        add (startTilesTF);
        add (timeBetwL);
        add (timeBetweenTF);
        add (tilesAddedL);
        add (tilesAddedTF);
        add (resetHighScoreB);
        
        //Action Listeners
        difEasyB.addActionListener(this);
        difMediumB.addActionListener(this);
        difHardB.addActionListener(this);
        resetHighScoreB.addActionListener(this);
        cancelB.addActionListener(this);
        saveB.addActionListener(this);
        
        //set component bounds (only needed by Absolute Positioning)
        difEasyB.setBounds (100, 25, 120, 50);
        difficultyL.setBounds (10, 40, 100, 25);
        difMediumB.setBounds (235, 25, 120, 50);
        difHardB.setBounds (370, 25, 120, 50);
        cancelB.setBounds (100, 345, 120, 50);
        saveB.setBounds (370, 345, 120, 50);
        errorL.setBounds(235, 345, 120, 50);
        startTilesL.setBounds (10, 115, 200, 25);
        startTilesTF.setBounds (235, 115, 120, 25);
        timeBetwL.setBounds (10, 165, 200, 25);
        timeBetweenTF.setBounds (235, 165, 120, 25);
        tilesAddedL.setBounds (10, 215, 200, 25);
        tilesAddedTF.setBounds (235, 215, 120, 25);
        resetHighScoreB.setBounds (100, 270, 390, 50);
        
        difEasyB.setOpaque(true);
		difEasyB.setBorderPainted(false);
		difEasyB.setBackground(Color.DARK_GRAY);
		difEasyB.setFont(new Font("Courier New", Font.BOLD, 20));
		difEasyB.setForeground(Color.WHITE);
		difEasyB.setMargin(getInsets());
		
		difMediumB.setOpaque(true);
		difMediumB.setBorderPainted(false);
		difMediumB.setBackground(Color.DARK_GRAY);
		difMediumB.setFont(new Font("Courier New", Font.BOLD, 20));
		difMediumB.setForeground(Color.WHITE);
		difMediumB.setMargin(getInsets());
		
		difHardB.setOpaque(true);
		difHardB.setBorderPainted(false);
		difHardB.setBackground(Color.DARK_GRAY);
		difHardB.setFont(new Font("Courier New", Font.BOLD, 20));
		difHardB.setForeground(Color.WHITE);
		difHardB.setMargin(getInsets());
		
		cancelB.setOpaque(true);
		cancelB.setBorderPainted(false);
		cancelB.setBackground(Color.DARK_GRAY);
		cancelB.setFont(new Font("Courier New", Font.BOLD, 20));
		cancelB.setForeground(Color.WHITE);
		cancelB.setMargin(getInsets());
		
		saveB.setOpaque(true);
		saveB.setBorderPainted(false);
		saveB.setBackground(Color.DARK_GRAY);
		saveB.setFont(new Font("Courier New", Font.BOLD, 20));
		saveB.setForeground(Color.WHITE);
		saveB.setMargin(getInsets());
		
		resetHighScoreB.setOpaque(true);
		resetHighScoreB.setBorderPainted(false);
		resetHighScoreB.setBackground(Color.DARK_GRAY);
		resetHighScoreB.setFont(new Font("Courier New", Font.BOLD, 20));
		resetHighScoreB.setForeground(Color.WHITE);
		resetHighScoreB.setMargin(getInsets());
		
		int textSize = 12;
		
		difficultyL.setFont(new Font("Courier New", Font.BOLD, textSize));
		difficultyL.setForeground(Color.WHITE);
		
		startTilesL.setFont(new Font("Courier New", Font.BOLD, textSize));
		startTilesL.setForeground(Color.WHITE);
		
		timeBetwL.setFont(new Font("Courier New", Font.BOLD, textSize));
		timeBetwL.setForeground(Color.WHITE);
		
		tilesAddedL.setFont(new Font("Courier New", Font.BOLD, textSize));
		tilesAddedL.setForeground(Color.WHITE);
		
		errorL.setFont(new Font("Courier New", Font.BOLD, textSize));
		errorL.setForeground(Color.RED);
		
		startTilesTF.setBackground(Color.DARK_GRAY);
		startTilesTF.setForeground(Color.WHITE);
		startTilesTF.setBorder(null);
		
		tilesAddedTF.setBackground(Color.DARK_GRAY);
		tilesAddedTF.setForeground(Color.WHITE);
		tilesAddedTF.setBorder(null);
		
		timeBetweenTF.setBackground(Color.DARK_GRAY);
		timeBetweenTF.setForeground(Color.WHITE);
		timeBetweenTF.setBorder(null);
		
		errorL.setText(" ");
		running = true;
		Thread t = new Thread(this);
		t.start();
    }
    
    public void paintComponent(Graphics g)
	{
    	super.paintComponent(g);
    	backgroundImage = new ImageIcon("resources/gameWindowRes/blackBackground.png");
		backgroundImage.paintIcon(this, g, 0, 0);
	}
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == difEasyB){
			setTextFields(3, 1000, 1);
		}else if(e.getSource() == difMediumB){
			setTextFields(4, 500, 1);
		}else if(e.getSource() == difHardB){
			setTextFields(5, 350, 2);
		}else if(e.getSource() == resetHighScoreB){
			resetHighscore = true;
			resetHighScoreB.setText("To Reset Press Save");
		}else if(e.getSource() == cancelB){
			running = false;
			try {
				Class GameWindow = Class.forName("GameWindow");
				GameWindow obj = (GameWindow) GameWindow.newInstance();
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			Main.exitStartOptionWindow();
		}else if(e.getSource() == saveB){
			saveOptions();
		}
	}
	
	public void saveOptions(){
		errorL.setText("");
		if(!isInteger(timeBetweenTF.getText(), 10)){
			errorMessage = "      Time between has to be an integer!";
			showErrorMessage = true;
			System.out.println("Save options try1");
		}else if(!isInteger(startTilesTF.getText(), 10)){
			errorMessage = "      Start Tiles has to be an integer!";
			showErrorMessage = true;
			System.out.println("Save options try2");
		}else if(!isInteger(tilesAddedTF.getText(), 10)){
			errorMessage = "      Tiles Added per Round has to be an integer!";
			showErrorMessage = true;
			System.out.println("Save options try3");
		}else if(Integer.parseInt(timeBetweenTF.getText()) <= 100 || Integer.parseInt(timeBetweenTF.getText()) >= 10000){
			errorMessage = "      Time has to be between 100 and 10000!";
			showErrorMessage = true;
			System.out.println("Save options try4");
		}else if(Integer.parseInt(startTilesTF.getText()) < 1){
			errorMessage = "      Start Tiles has to be greater than 1!";
			showErrorMessage = true;
			System.out.println("Save options try5 "+Integer.parseInt(startTilesTF.getText()));
		}else if(Integer.parseInt(tilesAddedTF.getText()) < 1){
			errorMessage = "      Tiles added per Round has to be greater than 1!";
			showErrorMessage = true;
			System.out.println("Save options try6");
		}else{
			System.out.println("Save options");
			rw.writeLineOverAt("/optionsFile.txt", ""+timeBetweenTF.getText(), 3, true);
			rw.writeLineOverAt("/optionsFile.txt", ""+startTilesTF.getText(), 5, true);
			rw.writeLineOverAt("/optionsFile.txt", ""+tilesAddedTF.getText(), 9, true);
			
			if(resetHighscore){
				rw.writeLineOverAt("/optionsFile.txt", "0", 7, true);
			}
			running = false;
			Main.startGameWindow();
			Main.exitStartOptionWindow();
		}
	}
	
	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}
	
	public void setTextFields(int startTiles, int timeBetw, int tilesAdded){
		startTilesTF.setText(""+startTiles);
		timeBetweenTF.setText(""+timeBetw);
		tilesAddedTF.setText(""+tilesAdded);
	}

	@Override
	public void run() {
		while(running){
			System.out.println("");
			if(showErrorMessage){
				char c = errorMessage.charAt(0);
	        	String rest = errorMessage.substring(1);
	        	errorMessage = rest + c;
	        	errorL.setText(errorMessage);
	        	try{
	            	Thread.sleep(200);
	        	}catch(InterruptedException e){}
			}
	    }
	}
}
