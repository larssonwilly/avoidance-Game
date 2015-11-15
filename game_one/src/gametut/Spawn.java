package gametut;

import java.util.Random;

public class Spawn {

	private Handler handler;
	private HUD hud;
	private Random r = new Random();
	private int scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud)	{
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick()	{
		scoreKeep++;
		
		if(scoreKeep >= 250)	{
			scoreKeep = 0;
			hud.setLevel(hud.getLevel() + 1);
			
			if(hud.getLevel() == 2)	{
				addEnemies( ID.BasicEnemy, 1);
				addEnemies(ID.SmartEnemy, 1);
			}	else if(hud.getLevel() == 3)	{
				addEnemies(ID.BasicEnemy, 2);
			}	else if(hud.getLevel() == 4)	{
				addEnemies(ID.FastEnemy, 2);
			}	else if(hud.getLevel() == 5)	{
				addEnemies(ID.BasicEnemy, 1);
			}	else if(hud.getLevel() == 6)	{
				addEnemies(ID.FastEnemy, 1);
			}	else if(hud.getLevel() == 7)	{
				addEnemies(ID.FastEnemy, 1);
			}	else if(hud.getLevel() == 10)	{
				handler.clearEnemies();
				HUD.HEALTH = 100;
				addEnemies(ID.EnemyBoss, 1);
			}	else if(hud.getLevel() == 15)	{
				handler.clearEnemies();
				addEnemies( ID.BasicEnemy, 6);
			}
		}
	}
	
	public void addEnemies(ID id, int number)	{
		if(id == ID.BasicEnemy){
			for(int i = 0; i < number; i++){
				BasicEnemy enemy = new BasicEnemy(r.nextInt(Game.WIDTH - 64) + 32, r.nextInt(Game.HEIGHT - 64) + 32, ID.BasicEnemy, handler);
				handler.addObject(enemy);
			}
		} else if(id == ID.FastEnemy)	{
			for(int i = 0; i < number; i++){
				FastEnemy enemy = new FastEnemy(r.nextInt(Game.WIDTH - 64) + 32, r.nextInt(Game.HEIGHT - 64) + 32, ID.FastEnemy, handler);
				handler.addObject(enemy);
			}
		} else if(id == ID.SmartEnemy)	{
			for(int i = 0; i < number; i++){
				SmartEnemy enemy = new SmartEnemy(r.nextInt(Game.WIDTH - 64) + 32, r.nextInt(Game.HEIGHT - 64) + 32, ID.SmartEnemy, handler);
				handler.addObject(enemy);
			}
		} else if(id == ID.EnemyBoss)	{
			for(int i = 0; i < number; i++){
				EnemyBoss enemy = new EnemyBoss((Game.WIDTH / 2) - 48, -120, ID.EnemyBoss, handler);
				handler.addObject(enemy);
			}
		}
	}
	
}
