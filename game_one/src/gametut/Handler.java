package gametut;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Handler {
	
	ArrayList<GameObject> object = new ArrayList<GameObject>();
	private Random r = new Random();
	
	public void tick()	{
		for(int i = 0; i < object.size(); i++)	{
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public synchronized void render(Graphics g)	{
		for(int i=0; i < object.size(); i++)	{
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void clearEnemies()	{
		for(int i=0; i < object.size(); i++)	{
			GameObject tempObject = object.get(i);
			
			if(tempObject.getId() != ID.Player)	{
				removeObject(tempObject);
				i = 0;
			}
		}
	}
	
	public void addEnemies(ID id, int number)	{
		
		if(id == ID.BasicEnemy){
			for(int i = 0; i < number; i++){
				BasicEnemy enemy = new BasicEnemy(r.nextInt(Game.WIDTH - 64) + 32, r.nextInt(Game.HEIGHT - 64) + 32, ID.BasicEnemy, this);
				addObject(enemy);
			}
		} else if(id == ID.FastEnemy)	{
			for(int i = 0; i < number; i++){
				FastEnemy enemy = new FastEnemy(r.nextInt(Game.WIDTH - 64) + 32, r.nextInt(Game.HEIGHT - 64) + 32, ID.FastEnemy, this);
				addObject(enemy);
			}
		} else if(id == ID.SmartEnemy)	{
			for(int i = 0; i < number; i++){
				SmartEnemy enemy = new SmartEnemy(r.nextInt(Game.WIDTH - 64) + 32, r.nextInt(Game.HEIGHT - 64) + 32, ID.SmartEnemy, this);
				addObject(enemy);
			}
		} else if(id == ID.EnemyBoss)	{
			for(int i = 0; i < number; i++){
				EnemyBoss enemy = new EnemyBoss((Game.WIDTH / 2) - 48, -120, ID.EnemyBoss, this);
				addObject(enemy);
			}
		} else if(id == ID.ColorEnemy)	{
			for(int i = 0; i < number; i++){
				ColorEnemy enemy = new ColorEnemy(r.nextInt(Game.WIDTH - 64) + 32, r.nextInt(Game.HEIGHT - 64) + 32, ID.ColorEnemy, this);
				addObject(enemy);
			}
		}
	}
	
	public void addObject(GameObject object)	{
		this.object.add(object);
	}
	
	public void removeObject(GameObject object)	{
		this.object.remove(object);
	}

}
