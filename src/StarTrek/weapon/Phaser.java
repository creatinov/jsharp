package StarTrek.weapon;

import StarTrek.Galaxy;
import StarTrek.Klingon;
import StarTrek.exceptions.MissedException;

import static StarTrek.Game.rnd;

public class Phaser implements Weapon {

	private int energy;

	private int amount = 0;

	public Phaser() {
		this.energy = 10000;
	}

	public Phaser(int energy) {
		this.energy = energy;
	}

	@Override
	public boolean canFire(int amount) {
		this.amount = amount;
		return energy >= amount;
	}

	@Override
	public void fire(Galaxy galaxy) {
		Klingon enemy = (Klingon) galaxy.variable("target");
		int amount = Integer.parseInt(galaxy.parameter("amount"));

		if (this.canFire(amount)) {
			int distance = enemy.distance();
			int damage = 0;
			try {
				boolean isMissed = distance > 4000;
				this.energy -= amount;
				if(isMissed) {
					throw new MissedException();
				}
				damage = calcDamage(distance);
			} catch (MissedException e) {
				galaxy.writeLine("Klingon out of range of phasers at " + distance + " sectors...");
				return;
			}
			galaxy.writeLine("Phasers hit Klingon at " + distance + " sectors with " + damage + " units");
			if (damage < enemy.getEnergy()) {
				enemy.setEnergy(enemy.getEnergy() - damage);
				galaxy.writeLine("Klingon has " + enemy.getEnergy() + " remaining");
			} else {
				galaxy.writeLine("Klingon destroyed!");
				enemy.delete();
			}
		} else {
			galaxy.writeLine("Insufficient energy to fire phasers!");
		}

	}

	@Override
	public int powerRemaining() {
		return this.energy;
	}

	@Override
	public void setPowerRemaining(int amount) {
		this.energy = amount;
	}

	private int calcDamage(int distance) {
		int damage = amount - (((amount / 20) * distance / 200) + rnd(200));
		if (damage < 1) {
			damage = 1;
		}
		return damage;
	}
}
