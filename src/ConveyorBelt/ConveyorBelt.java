package ConveyorBelt;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class ConveyorBelt extends StateBasedGame {
	private static final int MAINMENUSTATE = 0;
	private static final int GAMEPLAYSTATE = 1;
	public ConveyorBelt() {
		super("Conveyor Belt Game");
		this.addState(new MainMenuState(MAINMENUSTATE));
		this.addState(new GamePlayState(GAMEPLAYSTATE));
		this.enterState(MAINMENUSTATE);
	}
	public static void main(String argv[]) throws SlickException {
		AppGameContainer app = new AppGameContainer(new ConveyorBelt());
		app.setDisplayMode(1280, 720, false);
		app.start();
	}
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(MAINMENUSTATE).init(gc, this);
		this.getState(GAMEPLAYSTATE).init(gc, this);
	}
	

}
