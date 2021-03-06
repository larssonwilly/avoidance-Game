/**
 * 
 */
package gametut;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 * @author Willy
 *
 */
public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
	
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	Random r;
	private Menu menu;
	
	public enum STATE	{
		Menu,
		Game,
		Help, 
		End
	};
	
	public STATE gameState = STATE.Menu;
	
	public Game()	{
		new Window(WIDTH, HEIGHT, "Game", this);
		
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		hud = new HUD();
		spawner = new Spawn(handler, this);
		menu = new Menu(this, handler, hud);

		this.addMouseListener(menu);
		
		r = new Random();
		
		if(gameState == STATE.Menu)	{
			handler.addEnemies(ID.ColorEnemy, 10);
			//System.out.println(handler.object.size() + " 1");
		}

	}
	
	public synchronized void start()	{
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop()	{
		try	{
			thread.join();
			running = false;
		}catch(Exception e)	{
			e.printStackTrace();
		}
	}
	
	public void run()	{
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running)	{
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1)	{
				tick();
				delta--;
			}
			if(running)	
				render();
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
			
		}
		stop();
	}
	
	private void tick()	{
		handler.tick();
		if(gameState == STATE.Game)	{
			hud.tick();
			
			if(HUD.HEALTH <= 0)	{
				HUD.HEALTH = 100;
				
				gameState = STATE.End;
				handler.object.clear();
				handler.addEnemies(ID.ColorEnemy, 5);

			}
			
			spawner.tick();
		}	else if(gameState == STATE.Menu || gameState == STATE.End)	{
			menu.tick();
		}	
		
	}
	
	private void render()	{
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null){ //at start
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(gameState != STATE.Help)
			handler.render(g);
		
		if(gameState == STATE.Game)	{
			hud.render(g);
		}	else if(gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.End)	{
				menu.render(g);

		}	
		
		g.dispose();
		bs.show();
	}
	
	public static float clamp(float var, float min, float max)	{
		if(var >= max)
			return var = max;
		else if(var <= min)
			return var = min;
		else
			return var;
	}
	
	public static void main(String[] args) {
		new Game();
		
	}

	
}
