package StarTrek.weapon;

import StarTrek.Galaxy;
import StarTrek.Klingon;
import StarTrek.exceptions.MissedException;

import static StarTrek.Game.rnd;

public class Photon implements Weapon {

	private int torpedoes;

	public Photon() {
		this.torpedoes = 8;
	}

	public Photon(int torpedoes) {
		this.torpedoes = torpedoes;
	}

	@Override
	public boolean canFire(int amount) {
		return torpedoes >= amount;
	}

	@Override
	public void fire(Galaxy galaxy) {
		Klingon enemy = (Klingon) galaxy.variable("target");
		int amount = 1;

		if (this.canFire(amount)) {
			int distance = enemy.distance();
			int damage = 0;
			try {
				boolean isMissed = ((rnd(4) + ((distance / 500) + 1) > 7));
				this.torpedoes -= amount;
				if(isMissed) {
					throw new MissedException();
				}
				damage = calcDamage(distance);
				galaxy.writeLine("Photons hit Klingon at " + distance + " sectors with " + damage + " units");
			} catch (MissedException e) {
				galaxy.writeLine("Torpedo missed Klingon at " + distance + " sectors...");
				return;
			}

			if (damage < enemy.getEnergy()) {
				enemy.setEnergy(enemy.getEnergy() - damage);
				galaxy.writeLine("Klingon has " + enemy.getEnergy() + " remaining");
			} else {
				galaxy.writeLine("Klingon destroyed!");
				enemy.delete();
			}
		} else {
			galaxy.writeLine("No more photon torpedoes!");
		}
	}

	@Override
	public int powerRemaining() {
		return this.torpedoes;
	}

	@Override
	public void setPowerRemaining(int amount) {
		this.torpedoes = amount;
	}

	private int calcDamage(int distance) {
		return 800 + rnd(50);
	}
}
