package StarTrek.weapon;

import StarTrek.exceptions.MissedException;

import static StarTrek.Game.rnd;

public class Photon implements Weapon {

	private int torpedoes = 8;

	@Override
	public boolean canFire(int amount) {
		return torpedoes >= amount;
	}

	@Override
	public int fire(int distance) {
		boolean isMissed = ((rnd(4) + ((distance / 500) + 1) > 7));
		this.torpedoes--;
		if(isMissed) {
			throw new MissedException();
		}
		return calcDamage(distance);
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
