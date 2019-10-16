package StarTrek.defender;

import StarTrek.exceptions.NotEnoughEnergyException;
import StarTrek.exceptions.TooMuchException;

public class Shield implements Defender {

	private int energy;
	private boolean isUp = false;
	private int hp = 500;

	public Shield(int energy) {
		this.energy = energy;
	}

	@Override
	public int getEnergy() {
		return this.energy;
	}

	public void setEnergy(int energy){
		this.energy = energy;
	}

	@Override
	public boolean hasEnergy() {
		return this.energy > 0;
	}

	public void onHit(int damage) {
		if (damage >= this.energy) {
			this.energy = 0;
			this.isUp = false;
			this.onDamage(damage - this.energy);
		} else {
			this.energy -= damage;
		}
	}

	public boolean getIsUp() {
		return this.isUp;
	}

	public void setIsUp(boolean isUp) {
		this.isUp = isUp;
	}

	boolean isDamaged = false;

	public void onDamage(int damage){
		if(hp <= damage){
			hp = 0;
		}
		else{
			hp -= damage;
		}
		this.isDamaged = true;
	}

	public boolean getIsDamaged(){
		return this.isDamaged;
	}

	@Override
	public void plusEnergy(int energy) {
		if(this.energy + energy > 10000) {
			throw new TooMuchException();
		}

		this.energy = this.energy + energy;
	}

	@Override
	public void minusEnergy(int energy) throws NotEnoughEnergyException {
		if(this.energy - energy < 1) {
			throw new NotEnoughEnergyException();
		}

		this.energy = this.energy - energy;
	}
}
