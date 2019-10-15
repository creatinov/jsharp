package StarTrek.weapon;

import StarTrek.exceptions.MissedException;

import static StarTrek.Game.rnd;

public class Phaser implements Weapon {

	private int energy = 10000;

	private int amount = 0;

	@Override
	public boolean canFire(int amount) {
		this.amount = amount;
		return energy >= amount;
	}

	@Override
	public int fire(int distance) {
		boolean isMissed = distance > 4000;
		this.energy -= amount;
		if(isMissed) {
			throw new MissedException();
		}
		return calcDamage(distance);
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
		return amount - (((amount / 20) * distance / 200) + rnd(200));
	}
}
