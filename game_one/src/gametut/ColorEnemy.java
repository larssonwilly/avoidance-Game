package gametut;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class ColorEnemy extends GameObject	{
	
	private Handler handler;
	private Random r = new Random();

	private int red = r.nextInt(255);
	private int blue = r.nextInt(255);
	private int green = r.nextInt(255);
	private Color col;
	
	public ColorEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		velX = 5;
		velY = 5;
		this.handler = handler;
		
		col = new Color(red, green, blue);
		
	}
	
	public Rectangle getBounds()	{
		return new Rectangle((int)x, (int)y, 16, 16);
	}

	@Override
	public void tick() {
		x += velX;
		y += velY;
		
		if(y <= 0 || y >= Game.HEIGHT - 32)
			velY *= -1;
		if(x <= 0 || x >= Game.WIDTH - 32)
			velX *= -1;
		
		handler.addObject(new Trail((int)x, (int)y, ID.Trail, col, 16, 16, 0.02f, handler));

	}

	@Override
	public void render(Graphics g) {
		g.setColor(col);
		//g.setColor(new Color(r.nextFloat(), r.nextFloat(), r.nextFloat()));
		g.fillRect((int)x, (int)y, 16, 16);
		
	}

}