package gametut;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import gametut.Game.STATE;

public class Menu extends MouseAdapter	{

	private Game game;
	private Handler handler;
	private Random r = new Random();
	
	public Menu(Game game, Handler handler){
		this.game = game;
		this.handler = handler;
	}
	
	public void mousePressed(MouseEvent e){
		int mx = e.getX();
		int my = e.getY();
		
		
		if(game.gameState == STATE.Menu)	{
			//play
			if(mouseOver(mx, my, 220, 150, 200, 64))	{
				game.gameState = STATE.Game;
				handler.object.clear();
				handler.addObject(new Player(r.nextInt(Game.WIDTH - 64) + 32, r.nextInt(Game.WIDTH - 64) + 32, ID.Player, handler));
				
				//System.out.println(handler.object.size());

				handler.addEnemies(ID.BasicEnemy, 1);
			}
			
			//quit
			if(mouseOver(mx, my, 220, 350, 200, 64))	{
				System.exit(1);
			}
			
			//help
			if(mouseOver(mx, my, 220, 250, 200, 64))	{
				game.gameState = STATE.Help;
			}
		} else if(game.gameState == STATE.Help)	{
			if(mouseOver(mx, my, 220, 350, 200, 64))	{
				game.gameState = STATE.Menu;
			}
		}
		
	}
	
	public void mouseReleased(MouseEvent e){
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height)	{
		if(mx > x && mx < x + width)	{
			if(my > y && my < y + height){
				return true;
			}else return false;
		} else return false;

	}
	
	public void tick()	{
		
	}
	
	public void render(Graphics g)	{
		if(game.gameState == STATE.Menu)	{
			
			
			Font font = new Font("arial", 1, 50);
			Font font2 = new Font("arial", 1, 30);
			g.setFont(font);
			
			g.setColor(Color.white);
			g.drawString("Menu", 250, 80);
			
			g.setFont(font2);
			g.drawRect(220, 150, 200, 64);
			g.drawString("Play", 290, 193);
			
			g.drawRect(220, 250, 200, 64);
			g.drawString("Help", 290, 293);
			
			g.drawRect(220, 350, 200, 64);
			g.drawString("Quit", 290, 393);
			
			
			
		} else if(game.gameState == STATE.Help)	{
			Font font = new Font("arial", 1, 15);
			g.setFont(font);
			
			g.setColor(Color.white);
			g.drawString("Use W, A, S, D keys to move. Try not to hit shit. It's not that hard.", 40, 30);
		
			Font font2 = new Font("arial", 1, 30);
			g.setFont(font2);
			
			g.drawRect(220, 350, 200, 64);
			g.drawString("Back", 290, 393);
		
		}
		
	}
	
}
