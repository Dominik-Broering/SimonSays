package window;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import tools.ButtonsGame1;
import tools.ReadWriteTextFile;

@SuppressWarnings("serial")
public class GameWindow extends JPanel implements ActionListener{
    private ButtonsGame1 greenB;
    private ButtonsGame1 redB;
    private ButtonsGame1 yellowB;
    private ButtonsGame1 blueB;
    private ButtonsGame1 restartB;
    private ButtonsGame1 backB;
    private JLabel levelL;
    private JLabel scoreL;
    private JLabel highScoreL;
    private ButtonsGame1 optionsB;
    private ImageIcon backgroundImage;
    private int level = 1;
    private int tiles = 4;
    private int startTiles = 4;
    private int score = 0;
    private int time = 250;
    private int tilesAddedPerRound = 1;
    private int highscore = 0;
    private ArrayList<Integer> order = new ArrayList<Integer>();
    private Timer blinkTimer = new Timer(time, new Blinker());
    
    private int counter = 0; //Tracks which order it is
    private int scorePerRight = 10;
    
    private JLabel gameOverL;
    private JLabel gameOverL2;
    
    private final String[] defaultOptions= {"//Option File for Simon says Game by Dodo2314",
    										"//Time between tiles(Default: 500, Easy: 1000, Hard: 250)","500",
    										"//Start Tiles how many tiles in level 1(Default: 4)","4",
    										"//Highscore(10 per tile 100 per level)","0",
    										"//Tiles added per Round(Default 1)","1"};
    
    ReadWriteTextFile rw = new ReadWriteTextFile();
    
    public GameWindow() {
        loadOptions();
    	setupGame();
    }
    
    public void loadOptions(){
    	/* Load Time
    	 * Load start Tiles
    	 * Load HighScore
    	 * Load add Tiles Per Round
    	 * Option to reset Reset Highscore
    	 * */
    	
    	String workingDir = System.getProperty("user.dir");
		String path = workingDir + "/optionsFile.txt";
		
		File data = new File(path);
		
		if(data.exists()){}
		else{
			try {
				data.createNewFile();
				rw.writeLineArray(path, defaultOptions, false);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		final String[] optionsLoaded = rw.readLinesToArray(path, false);
		
		time = Integer.parseInt(optionsLoaded[2]);
		startTiles = Integer.parseInt(optionsLoaded[4]);
		tiles = startTiles;
		highscore = Integer.parseInt(optionsLoaded[6]);
		tilesAddedPerRound = Integer.parseInt(optionsLoaded[8]);
		
		System.out.println("Time: "+time);
    }
    
    public void setupGame(){
    	//construct components
        greenB = new ButtonsGame1 ("");
        redB = new ButtonsGame1 ("");
        yellowB = new ButtonsGame1 ("");
        blueB = new ButtonsGame1 ("");
        restartB = new ButtonsGame1 ("Start");
        backB = new ButtonsGame1 ("Exit");
        levelL = new JLabel (" Level: "+level);
        scoreL = new JLabel (" Score: "+score);
        optionsB = new ButtonsGame1 ("Options");
        gameOverL = new JLabel ("  Game Over");
        gameOverL2 = new JLabel("Press Restart");
        highScoreL = new JLabel(" Highscore: "+highscore);
        
        //adjust size and set layout
        setPreferredSize (new Dimension (764, 550));
        setLayout (null);
        
        //add components
        add (greenB);
        add (redB);
        add (yellowB);
        add (blueB);
        add (restartB);
        add (backB);
        add (levelL);
        add (scoreL);
        add (optionsB);
        add (gameOverL);
        add (gameOverL2);
        add (highScoreL);
        
        //set component bounds (only needed by Absolute Positioning)
        greenB.setBounds (25, 25, 250, 250);
        redB.setBounds (285, 25, 250, 250);
        yellowB.setBounds (25, 285, 250, 250);
        blueB.setBounds (285, 285, 250, 250);
        restartB.setBounds (590, 25, 150, 50);
        backB.setBounds (590, 80, 150, 50);
        optionsB.setBounds (590, 135, 150, 50);
        levelL.setBounds (590, 190, 150, 50);
        scoreL.setBounds (590, 245, 150, 50);
        gameOverL.setBounds(590, 380, 150, 50);
        gameOverL2.setBounds(590, 440, 180, 50);
        highScoreL.setBounds(590, 300, 150, 50);
        
        //Set Action Listener
        greenB.addActionListener(this);
        redB.addActionListener(this);
        yellowB.addActionListener(this);
        blueB.addActionListener(this);
        restartB.addActionListener(this);
        backB.addActionListener(this);
        optionsB.addActionListener(this);
        
        //Change Button Style
        Color buttonColor = Color.DARK_GRAY;
        int textSize = 20;
        
        greenB.setOpaque(true);
		greenB.setBorderPainted(false);
		greenB.setBackground(Color.GREEN);
		greenB.setFont(new Font("Courier New", Font.BOLD, textSize));
		greenB.setForeground(Color.WHITE);
		greenB.setMargin(getInsets());
		
		redB.setOpaque(true);
		redB.setBorderPainted(false);
		redB.setBackground(Color.RED);
		redB.setFont(new Font("Courier New", Font.BOLD, textSize));
		redB.setForeground(Color.WHITE);
		redB.setMargin(getInsets());
		
		yellowB.setOpaque(true);
		yellowB.setBorderPainted(false);
		yellowB.setBackground(Color.YELLOW);
		yellowB.setFont(new Font("Courier New", Font.BOLD, textSize));
		yellowB.setForeground(Color.WHITE);
		yellowB.setMargin(getInsets());
		
		blueB.setOpaque(true);
		blueB.setBorderPainted(false);
		blueB.setBackground(Color.BLUE);
		blueB.setFont(new Font("Courier New", Font.BOLD, textSize));
		blueB.setForeground(Color.WHITE);
		blueB.setMargin(getInsets());
        
        restartB.setOpaque(true);
		restartB.setBorderPainted(false);
		restartB.setBackground(buttonColor);
		restartB.setFont(new Font("Courier New", Font.BOLD, textSize));
		restartB.setForeground(Color.WHITE);
		restartB.setMargin(getInsets());
		
		backB.setOpaque(true);
		backB.setBorderPainted(false);
		backB.setBackground(buttonColor);
		backB.setFont(new Font("Courier New", Font.BOLD, textSize));
		backB.setForeground(Color.WHITE);
		backB.setMargin(getInsets());
		
		optionsB.setOpaque(true);
		optionsB.setBorderPainted(false);
		optionsB.setBackground(buttonColor);
		optionsB.setFont(new Font("Courier New", Font.BOLD, textSize));
		optionsB.setForeground(Color.WHITE);
		optionsB.setMargin(getInsets());
		
		levelL.setOpaque(true);
		levelL.setBackground(buttonColor);
		levelL.setFont(new Font("Courier New", Font.BOLD, textSize));
		levelL.setForeground(Color.WHITE);
		
		scoreL.setOpaque(true);
		scoreL.setBackground(buttonColor);
		scoreL.setFont(new Font("Courier New", Font.BOLD, textSize));
		scoreL.setForeground(Color.WHITE);
		
		highScoreL.setOpaque(true);
		highScoreL.setBackground(buttonColor);
		highScoreL.setFont(new Font("Courier New", Font.BOLD, 15));
		highScoreL.setForeground(Color.WHITE);
		
		gameOverL.setFont(new Font("Courier New", Font.BOLD, 20));
		gameOverL.setForeground(Color.BLACK);
		gameOverL.setVisible(false);
		
		gameOverL2.setFont(new Font("Courier New", Font.BOLD, 20));
		gameOverL2.setForeground(Color.BLACK);
		gameOverL2.setVisible(false);
		
		//Disable all color buttons
		enDisableButtons(false);
		
		counter = 0;
		updateScoreLevelHighscore();
    }

    public void paintComponent(Graphics g)
	{
    	super.paintComponent(g);
    	backgroundImage = new ImageIcon("resources/gameWindowRes/blackBackground.png");
		backgroundImage.paintIcon(this, g, 0, 0);
	}
    
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == greenB){
			isRightButton(0);
		}else if(e.getSource() == redB){
			isRightButton(1);
		}else if(e.getSource() == yellowB){
			isRightButton(2);
		}else if(e.getSource() == blueB){
			isRightButton(3);
		}else if(e.getSource() == restartB){
			startGame();
		}else if(e.getSource() == backB){
			Main.exitGameWindow();
		}else if(e.getSource() == optionsB){
			try {
				Class GameWindow = Class.forName("GameWindow");
				OptionWindow obj = (OptionWindow) GameWindow.newInstance();
			} catch (InstantiationException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			Main.exitGameWindow();
		}
	}
	
	public void startGame(){
		level = 1;
		tiles = startTiles;
		score = 0;
		counter = 0;
		updateScoreLevelHighscore();
		order.removeAll(order);
		restartB.setText("Restart");
		
		for(int i = 0; i<=tiles; i++){
			order.add(randomNumber(0,3));
			System.out.println(order.get(i));
		}
		blinkTimer.start();
		System.out.println("Timer");
	}
	
	public void isRightButton(int b){
			if(b == order.get(counter)){
				score += scorePerRight;
				counter++;
				updateScoreLevelHighscore();
				System.out.println("Right Button");
			}else{
				endGame();
			}
			if(counter >= tiles){nextPhase();}
	}
	
	public void nextPhase(){
		counter = 0;
		tiles += tilesAddedPerRound;
		for(int i = 0; i<=tilesAddedPerRound; i++){
			order.add(randomNumber(0,3));
		}
		level++;
		score += 100;
		updateScoreLevelHighscore();
		blinkTimer.start();
	}
	
	public void updateScoreLevelHighscore(){
		levelL.setText(" Level: "+level);
		scoreL.setText(" Score: "+score);
		highScoreL.setText(" Highscore: "+highscore);
	}
	
	public int randomNumber(int min, int max){
		return min + (int)(Math.random() * ((max - min) + 1));
	}
	
	public void enDisableButtons(boolean tf){
		greenB.setEnabled(tf);
		redB.setEnabled(tf);
		yellowB.setEnabled(tf);
		blueB.setEnabled(tf);
	}
	
	public void endGame(){
		System.out.println("Wrong Button");
		enDisableButtons(false);
		
		setHighscore();
		
		gameOverL.setVisible(true);
		gameOverL2.setVisible(true);		
	}
	
	public void setHighscore(){
		if(score > highscore){
			highscore = score;
			rw.writeLineOverAt("/optionsFile.txt", ""+highscore, 7, true);
		}
		highScoreL.setText(" Highscore: "+highscore);
	}
	
	 class Blinker implements ActionListener{
	        boolean on=false;
	        int i = 0;
	        public void actionPerformed(ActionEvent e) {
	            // blink the label background on and off
	        	enDisableButtons(false);
	        	
	        	switch(order.get(i)){
				case 0:  greenB.setBackground( on ? Color.GREEN : null); System.out.println("case0"); break;
				case 1:  redB.setBackground( on ? Color.RED : null); System.out.println("case1");break;
				case 2:  yellowB.setBackground( on ? Color.YELLOW : null); System.out.println("case2");break;
				case 3:  blueB.setBackground( on ? Color.BLUE : null); System.out.println("case3");break;
				}
	        	
				if(on){
					i++;
				}
				
				if(i >= tiles){
					i = 0;
					enDisableButtons(true);
					blinkTimer.stop();
					System.out.println("Timer stopped"+tiles);
				}
	            on = !on;
	        }
	    }
}
