package gametut;

import java.util.Random;

import gametut.Game.STATE;

public class Spawn {

	private Handler handler;

	private Random r = new Random();
	private int scoreKeep = 0;
	Game game;
	
	public Spawn(Handler handler,  Game game)	{
		this.handler = handler;
		this.game = game;
	}
	
	public void tick()	{
		scoreKeep++;
		
		if(scoreKeep >= 250 && game.gameState == STATE.Game)	{
			scoreKeep = 0;
			HUD.level += 1;
			
			if(HUD.level == 2)	{
				handler.addEnemies( ID.BasicEnemy, 1);
				handler.addEnemies(ID.SmartEnemy, 1);
			}	else if(HUD.level == 3)	{
				handler.addEnemies(ID.BasicEnemy, 2);
			}	else if(HUD.level == 4)	{
				handler.addEnemies(ID.FastEnemy, 2);
			}	else if(HUD.level == 5)	{
				handler.addEnemies(ID.BasicEnemy, 1);
			}	else if(HUD.level == 6)	{
				handler.addEnemies(ID.FastEnemy, 1);
			}	else if(HUD.level == 7)	{
				handler.addEnemies(ID.FastEnemy, 1);
			}	else if(HUD.level == 10)	{
				handler.clearEnemies();
				HUD.HEALTH = 100;
				handler.addEnemies(ID.EnemyBoss, 1);
			}	else if(HUD.level == 15)	{
				handler.clearEnemies();
				handler.addEnemies( ID.BasicEnemy, 6);
			}
			
		}
	}
	


}
