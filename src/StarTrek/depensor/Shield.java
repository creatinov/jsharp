package StarTrek.depensor;

public class Shield implements Depensor {

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
}
