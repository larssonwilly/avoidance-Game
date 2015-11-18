package gametut;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject	{

	Handler handler;
	private HUD hud;
	private int level10Counter = 0;
	
	public Player(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		this.handler = handler;
		this.hud = hud;

	}
	
	public Rectangle getBounds()	{
		return new Rectangle((int)x, (int)y, 32, 32);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(HUD.level == 10 || HUD.level == 11 || HUD.level == 12 || HUD.level == 13 || HUD.level == 14 || HUD.level == 15){
			if (level10Counter == 0) x = y = 300;
			level10Counter++;
			y = Game.clamp(y, 150, Game.HEIGHT - 60);
			x = Game.clamp(x, 0, Game.WIDTH - 37);
		} else	{
			x = Game.clamp(x, 0, Game.WIDTH - 37);
			y = Game.clamp(y, 0, Game.HEIGHT - 60);
		}

		
		handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.05f, handler));
		
		collision();
		
	}
	
	private void collision()	{
		for(int i = 0; i < handler.object.size(); i++)	{
			GameObject tempObject = handler.object.get(i);
			
			if(tempObject.getId() == ID.BasicEnemy || tempObject.getId() == ID.FastEnemy || tempObject.getId() == ID.SmartEnemy)	{
				if(getBounds().intersects(tempObject.getBounds()))	{
					HUD.HEALTH -= 2;
				}
			}

		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.white);
		g.fillRect((int)x, (int)y, 32, 32);
		
	}
	
	
	
}
