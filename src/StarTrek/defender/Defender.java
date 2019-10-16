package StarTrek.defender;

import StarTrek.SubSystem;
import StarTrek.exceptions.NotEnoughEnergyException;
import StarTrek.exceptions.TooMuchException;

public interface Defender extends SubSystem {

	boolean hasEnergy();

	int getEnergy();

	void plusEnergy(int energy) throws TooMuchException;

	void minusEnergy(int energy) throws NotEnoughEnergyException;
}
