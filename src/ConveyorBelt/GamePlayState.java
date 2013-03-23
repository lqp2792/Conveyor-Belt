package ConveyorBelt;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

public class GamePlayState extends BasicGameState {
	private int stateID = -1;
	private Image background = null;
	private Image gameDialog = null;
	private TiledMap tiledMap;
	
	int mouseX, mouseY;
	public GamePlayState(int stateID) {
		this.stateID = stateID;
	}
	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("background.png");
		gameDialog = new Image("industrial/dialog-standard.png");
		tiledMap = new TiledMap("map/level1.tmx");
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw(0, 0);
		gameDialog.draw(300, 80, 740, 575);
		tiledMap.render(350, 125);
		
		g.drawString("MouseX: "+mouseX+"\nMouse: "+mouseY, 900, 50);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		Input input = gc.getInput();
		mouseX = input.getMouseX();
		mouseY = input.getMouseY();
	}

	@Override
	public int getID() {
		return stateID;
	}
	
}
