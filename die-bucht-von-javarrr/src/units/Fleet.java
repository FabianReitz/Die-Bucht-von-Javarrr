package units;

import java.util.ArrayList;
import java.util.Random;

import game.Game;
import game.Statistics;
import states.GameState;

public class Fleet {

	private ArrayList<Gegner> canShoot = new ArrayList<Gegner>();
	private ArrayList<Gegner> shooting = new ArrayList<Gegner>();
	private ArrayList<Gegner> cooldown = new ArrayList<Gegner>();
	private	ArrayList<Gegner> enemys = new ArrayList<Gegner>();
	private int chosen;
	private Game game;
	private long shootCooldown = 400;
	private boolean reloading = false;
	private long reloadStart;
	
	
	public Fleet(Game game) {
	this.game = game;
	}
	public void update() {
		shoot();
		hit();
		removeShot();
	}
	/* Nachdem die Zeit des Reloads abgelaufen ist wird ein neuer Gegner ausgewaehlt, der dann
 	anfaengt zu schiessen. Zudem wird der Gegner, welcher am laengsten nicht geschossen hat wieder zu
    den Gegnern hinzugefuegt, welche bereit sind zu schiessen.
    Sollte ein Gegner tot sein wird dieser aus der Liste entfernt
     */
	
	public void shoot() {
		if (!reloading) {
			
			if (game.getFleet().getCanShoot().size() > 0) {
				chooseEnemy();
			}
			fire();
			if (game.getFleet().getCooldown().size() > 0) {
				if(cooldown.get(0).getAlive() == true) {
				canShoot.add(cooldown.get(0));
				cooldown.remove(0);
				}
				else
				{
				cooldown.remove(0);
				}
			}
			reloading = true;
			reloadStart = System.currentTimeMillis();
		}
		if (reloading && ((System.currentTimeMillis() - reloadStart) >= shootCooldown)) {
			reloading = false;
		}
	}

	// Der ausgewaehlte Gegner bekommt einen neuen Schuss und wird zu den
	// schiessenden hinzugefuegt
	public void fire() {
		if (game.getFleet().getCanShoot().size() > 0) {
			canShoot.get(chosen).setSchuss(new EnemyShot(game, canShoot.get(chosen).getX(), canShoot.get(chosen).getY())); 
			shooting.add(canShoot.get(chosen));
			game.getFleet().getCanShoot().remove(game.getFleet().getChosen());
		}
	}

	/* Wenn der Schuss mit dem Spieler kollidiert, wird dieser entfernt.
	 * Zudem werden dem Spueler HP abgezogen
	 *
	 */
	public void hit() {
		for (int z = 0; z < shooting.size(); z++) {
			if (((shooting.get(z).getSchuss().getSX() + 20) > GameState.getPlayer().x)
					&& (shooting.get(z).getSchuss().getSX() < GameState.getPlayer().x + 72)
					&& shooting.get(z).getSchuss().getSY() > GameState.getPlayer().y) {
				cooldown.add(game.getFleet().getShooting().remove(z));
				Statistics.setHealth(Statistics.getHealth() - 10); 
				game.getWindow().lblleben.setText("Leben: " + Statistics.getHealth() +"|"+ Statistics.getMaxHealth());
				if(Statistics.getHealth() <= 0) {
					GameState.setGameLose(true);
				}
			}
		}
	}

	/* Wenn der Schuss aus dem Bildschirm fliegt, wird dieser Entfernt und der Gegner
	 * wird in die Warteliste fuer den naechsten Schuss gesetzt
	 */
	public void removeShot() {
		for (int j = 0; j < shooting.size(); j++) {
			if (shooting.size() > 0 && (shooting.get(j).getSchuss().getSY() > 512)) {
				cooldown.add(shooting.remove(j));
			}
		}
	}

	/* Es wird eine Nummer aus der Liste der gegner, welche bereit sind zu
		schiessen, bestimmt
	 */
	public void chooseEnemy() {
		Random random = new Random();
		chosen = random.nextInt(canShoot.size());
	}

	public ArrayList<Gegner> getCanShoot() {
		return canShoot;
	}

	public ArrayList<Gegner> getShooting() {
		return shooting;
	}

	public ArrayList<Gegner> getCooldown() {
		return cooldown;
	}

	public ArrayList<Gegner> getEnemys() {
		return enemys;
	}
	
	public int getChosen() {
		return chosen;
	}

	public void setChosen(int chosen) {
		this.chosen = chosen;
	}
	
	
}
