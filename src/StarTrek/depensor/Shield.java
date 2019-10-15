package StarTrek.depensor;

public class Shield implements Defender {

	private int energy;

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
