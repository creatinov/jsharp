package StarTrek.weapon;

import StarTrek.Galaxy;
import StarTrek.exceptions.MissedException;

public interface Weapon {

	boolean canFire(int amount);

	void fire(Galaxy galaxy) throws MissedException;

	int powerRemaining();

	void setPowerRemaining(int amount);

}
