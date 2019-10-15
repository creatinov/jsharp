package StarTrek.defender;

import StarTrek.exceptions.TooMuchException;

public interface Defender {

	boolean hasEnergy();

	int getEnergy();

	void plusEnergy(int energy) throws TooMuchException;
}
