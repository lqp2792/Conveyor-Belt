package ConveyorBelt;

import java.awt.Font;
import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class MainMenuState extends  BasicGameState {
	private int stateID = -1;
	private Image background = null;
	private Image logo = null;
	private Image startGameButton = null, highScoresButton = null, howToPlayButton = null, exitGameButton = null;
	private ArrayList<Image> gameDialog = null;
	private int startGameButtonX = 75, startGameButtonY = 150
			, howToPlayButtonX = 75, howToPlayButtonY = 250
			, highScoresButtonX = 75, highScoresButtonY = 350
			, exitGameButtonX = 75, exitGameButtonY = 450;
	
	private float startGameScale = 1, howToPlayScale = 1, highScoresScale = 1, exitGameScale = 1;
	private float scaleStep = 0.0001f;
	private int mouseX;
	private int mouseY;
	TrueTypeFont trueTypeFont = null;
	private int dialogIndex;
	
	public MainMenuState(int stateID) {
		this.stateID = stateID;
	}
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("background.png");
		logo = new Image("logo.png");
		gameDialog = new ArrayList<Image>();
		gameDialog.add(new Image("industrial/dialog-standard.png"));
		gameDialog.add(new Image("industrial/HowToPlayDialog.png"));
		startGameButton = new Image("industrial/startgamebutton.png");
		howToPlayButton = new Image("industrial/HowToPlayButton.png");
		highScoresButton = new Image("industrial/HighScoresButton.png");
		exitGameButton = new Image("industrial/ExitGameButton.png");
		Font font = new Font("Zekton Italic", Font.BOLD, 20);
		trueTypeFont = new TrueTypeFont(font, true);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw(0, 0);
		gameDialog.get(dialogIndex).draw(550, 100);
		logo.draw(650, 200);
		startGameButton.draw(startGameButtonX, startGameButtonY, startGameScale);
		howToPlayButton.draw(howToPlayButtonX, howToPlayButtonY, howToPlayScale);
		highScoresButton.draw(highScoresButtonX, highScoresButtonY, highScoresScale);
		exitGameButton.draw(exitGameButtonX, exitGameButtonY, exitGameScale);
		trueTypeFont.drawString(800, 50, "MouseX: "+mouseX+"\nMouseY: "+mouseY, Color.gray);
		trueTypeFont.drawString(1200, 675, "v1.00", Color.green);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		mouseX = input.getMouseX();
		mouseY = input.getMouseY();
		// ------------------------------------------------//
		boolean insideStartGame = false, insideHowToPlay = false, insideHighScores = false, insideExitGame = false;
		if((mouseX >= startGameButtonX && mouseX <= startGameButtonX + startGameButton.getWidth()) && 
				(mouseY >= startGameButtonY && mouseY <= startGameButtonY + startGameButton.getHeight())) {
			insideStartGame = true;
		}
		if(insideStartGame) {
			if(startGameScale < 1.05f) startGameScale += scaleStep*delta;
			if(startGameButtonX < 85) startGameButtonX ++;
			if(startGameButtonY >= 150) startGameButtonY --;
			if(input.isMousePressed(input.MOUSE_LEFT_BUTTON)) sbg.enterState(1);
		}
		else {
			if(startGameScale > 1.0f) startGameScale -= scaleStep*delta;
			if(startGameButtonX > 75) startGameButtonX--;
			if(startGameButtonY <= 150) startGameButtonY++;
		}
		// ------------------------------------------------//
		if((mouseX >= howToPlayButtonX && mouseX <= howToPlayButtonX + howToPlayButton.getWidth()) && 
				(mouseY >= howToPlayButtonY && mouseY <= howToPlayButtonY + howToPlayButton.getHeight())) {
			insideHowToPlay = true;
		}
		if(insideHowToPlay) {
			if(howToPlayScale < 1.05f) howToPlayScale += scaleStep*delta;
			if(howToPlayButtonX < 85) howToPlayButtonX ++;
			if(howToPlayButtonY >= 250) howToPlayButtonY --;
			if(input.isMousePressed(input.MOUSE_LEFT_BUTTON)) {
				dialogIndex = 1;
			}
		}
		else {
			if(howToPlayScale > 1.0f) howToPlayScale -= scaleStep*delta;
			if(howToPlayButtonX > 75) howToPlayButtonX--;
			if(howToPlayButtonY <= 250) howToPlayButtonY++;
		}
		// ------------------------------------------------//
		if((mouseX >= highScoresButtonX && mouseX <= highScoresButtonX + highScoresButton.getWidth()) && 
				(mouseY >= highScoresButtonY && mouseY <= highScoresButtonY + highScoresButton.getHeight())) {
			insideHighScores = true;
		}
		if(insideHighScores) {
			if(highScoresScale < 1.05f) highScoresScale += scaleStep*delta;
			if(highScoresButtonX < 85) highScoresButtonX ++;
			if(highScoresButtonY >= 350) highScoresButtonY --;
			
		}
		else {
			if(highScoresScale > 1.0f) highScoresScale -= scaleStep*delta;
			if(highScoresButtonX > 75) highScoresButtonX--;
			if(highScoresButtonY <= 350) highScoresButtonY++;
		}
		// ------------------------------------------------//
		if((mouseX >= exitGameButtonX && mouseX <= exitGameButtonX + exitGameButton.getWidth()) && 
				(mouseY >= exitGameButtonY && mouseY <= exitGameButtonY + exitGameButton.getHeight())) {
			insideExitGame = true;
		}
		if(insideExitGame) {
			if(exitGameScale < 1.05f) exitGameScale += scaleStep*delta;
			if(exitGameButtonX < 85) exitGameButtonX ++;
			if(exitGameButtonY >= 450) exitGameButtonY --;
			if(input.isMousePressed(input.MOUSE_LEFT_BUTTON)) gc.exit();
		}
		else {
			if(exitGameScale > 1.0f) exitGameScale -= scaleStep*delta;
			if(exitGameButtonX > 75) exitGameButtonX--;
			if(exitGameButtonY <= 450) exitGameButtonY++;
		}
		// ------------------------------------------------//
		
	}

	@Override
	public int getID() {
		return stateID;
	}
	
}
