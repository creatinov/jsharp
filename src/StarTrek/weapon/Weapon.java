package StarTrek.weapon;

import StarTrek.exceptions.MissedException;

public interface Weapon {

	boolean canFire(int amount);

	int fire(int distance) throws MissedException;

	int powerRemaining();

	void setPowerRemaining(int amount);

}
