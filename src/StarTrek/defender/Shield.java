package StarTrek.defender;

public class Shield implements Defender {

	private int energy = 2000;

	public Shield() {
	}

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

}
