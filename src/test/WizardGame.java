package test;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.tiled.TiledMap;

public class WizardGame extends BasicGame {
	private TiledMap map;
	private boolean[][] blockMap;
	private Animation sprite, up, down, left, right;
	private float wizardX, wizardY;
	private int mapX, mapY;
	private Polygon wizardPolygon;
	private ArrayList<Object> polyMap;
	public static final int TILE_SIZE=32;
	public final int duration = 300; //miliseconds
	public WizardGame () {
		super("Wizard Game 1.00");
	}
	public static void main(String[] argv) throws SlickException {
		AppGameContainer app = new AppGameContainer(new WizardGame());
		app.setDisplayMode(800, 640, false);
		app.start();
	}
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException {
		map.render(mapX, mapY);
		sprite.draw(wizardX, wizardY);
		g.draw(wizardPolygon);
		for(int i=0; i<polyMap.size(); i++) {
			g.draw((Polygon) polyMap.get(i));
		}
	}

	@Override
	//-------------------------------------------------------//
	public void init(GameContainer gc) throws SlickException {
		map = new TiledMap("map/testmap.tmx");
		mapX = 50;
		mapY = 50;
		wizardX = 150f;
		wizardY = 150f;
		polyMap = new ArrayList<Object>();
		wizardPolygon = new Polygon(new float[] {
				wizardX, wizardY,
				wizardX + 32, wizardY,
				wizardX + 32, wizardY + 32,
				wizardX, wizardY + 32
		});
		Image[] movementUp = {new Image("wizard/wmg1_bk1.png"), new Image("wizard/wmg1_bk2.png")};
		Image[] movementDown = {new Image("wizard/wmg1_fr1.png"), new Image("wizard/wmg1_fr2.png")};
		Image[] movementLeft = {new Image("wizard/wmg1_lf1.png"), new Image("wizard/wmg1_lf2.png")};
		Image[] movementRight = {new Image("wizard/wmg1_rt1.png"), new Image("wizard/wmg1_rt2.png")};

		up = new Animation(movementUp, duration, false);
		down = new Animation(movementDown, duration, false);
		left = new Animation(movementLeft, duration, false);
		right = new Animation(movementRight, duration, false);
		sprite = right;
		
		blockMap = new boolean[map.getHeight()][map.getWidth()];
		for(int x=0; x<map.getWidth(); x++) {
			for(int y=0; y<map.getHeight(); y++) {
				int tileID = map.getTileId(x, y, 0);
				String value = map.getTileProperty(tileID, "blocked", "false");
				if(value.equals("true")) blockMap[x][y] = true;
				//System.out.println("x: "+x+" y: "+y+" value: "+value);
			}
		}
		createPolyMap(blockMap);
	}
	//-------------------------------------------------------//
	@Override
	public void update(GameContainer gc, int delta) throws SlickException {
		Input input = gc.getInput();
		if(input.isKeyDown(Input.KEY_W)) {
			sprite = up;
			sprite.update(delta);
			wizardY -= delta * 0.1f;
			wizardPolygon.setY(wizardY);
			if(isBlocked()) {
				wizardY += delta * 0.1f;
				wizardPolygon.setY(wizardY);
			}
		}
		if(input.isKeyDown(Input.KEY_S)) {
			sprite = down;
			sprite.update(delta);
			wizardY += delta * 0.1f;
			wizardPolygon.setY(wizardY);
			if(isBlocked()) {
				wizardY -= delta * 0.1f;
				wizardPolygon.setY(wizardY);
			}
		}
		if(input.isKeyDown(Input.KEY_A)) {
			sprite = left;
			sprite.update(delta);
			wizardX -= delta * 0.1f;
			wizardPolygon.setX(wizardX);
			if(isBlocked()) {
				wizardX += delta * 0.1f;
				wizardPolygon.setX(wizardX);
			}
		}
		if(input.isKeyDown(Input.KEY_D)) {
			sprite = right;
			sprite.update(delta);
			wizardX += delta * 0.1f;
			wizardPolygon.setX(wizardX);
			if(isBlocked()) {
				wizardX -= delta * 0.1f;
				wizardPolygon.setX(wizardX);
			}
		}
	}
	public boolean isBlocked() {
		for(int i=0; i<polyMap.size(); i++) {
			if(wizardPolygon.intersects((Polygon) polyMap.get(i))) {
				return true;
			}
		}
		return false;
	}
	public void createPolyMap(boolean[][] blockMap) {
		for(int x=0; x<map.getWidth(); x++) {
			for(int y=0; y<map.getHeight(); y++) {
				System.out.println("x: "+x+" y: "+y+" value: "+ blockMap[x][y]);
				if(blockMap[x][y] == true) {
					Polygon temp = new Polygon(new float[] {
							(float)mapX + x*TILE_SIZE, (float)mapY + y*TILE_SIZE,
							(float)mapX + x*TILE_SIZE + TILE_SIZE, (float)mapY + y*TILE_SIZE,
							(float)mapX + x*TILE_SIZE + TILE_SIZE, (float)mapY + y*TILE_SIZE + TILE_SIZE,
							(float)mapX + x*TILE_SIZE, (float)mapY + y*TILE_SIZE + TILE_SIZE
					});
					polyMap.add(temp);
				}
			}
		}
	}
}
