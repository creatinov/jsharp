package StarTrek.defender;

public class Shield implements Defender {

	private int energy;
	private boolean isUp = false;
	private  int hp = 500;
	public Shield(int energy) {
		this.energy = energy;
	}

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
}
