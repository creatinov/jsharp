package StarTrek;

import StarTrek.defender.Defender;
import StarTrek.exceptions.NoDefenderException;
import StarTrek.weapon.Phaser;
import StarTrek.weapon.Photon;
import StarTrek.weapon.Weapon;

import java.util.HashMap;
import java.util.Map;

public class Starship {

	private int energy = 40000;

	private Map<String, SubSystem> subSystems = new HashMap<>();

	public Starship() {
		subSystems.put("phaser", new Phaser());
		subSystems.put("photon", new Photon());
	}

	public void setUpDefender(Defender defender) {
		this.subSystems.put("shield", defender);
	}

	public void fire(Galaxy galaxy) {
		String command = galaxy.parameter("command");

		Weapon weapon = (Weapon) subSystems.get(command);
		weapon.fire(galaxy);
	}

	public String onHit(int damage) {
		return onHit(damage, generator.nextInt(3));
//		return onHit(damage, 1);
	}

	public String onHit(int damage, int subsystem) {
		if (subsystem == 0) {
			return "an weapon is hit and damaged";
		} else if (subsystem == 1) {
			return "an engine is hit and damaged";
		} else if (subsystem == 2) {
			return "a shield is hit and damaged";
		} else {
			return "";
		}
	}

	public static Random generator = new Random();

	public int rnd(int maximum) {
		return generator.nextInt(maximum);
	}

	public boolean hasDefender() {
		if(!this.subSystems.containsKey("shield")) {
			return false;
		}
		return ((Defender) this.subSystems.get("shield")).hasEnergy();
	}

	public void plusEnergy(int value) {
		this.energy = this.energy + value;
	}

	public void minusEnergy(int value) {
		this.energy = this.energy - value;
	}

	public int getEnergy() {
		return this.energy;
	}

	public int getPhaserEnergy() {
		return ((Weapon)this.subSystems.get("phaser")).powerRemaining();
	}

	public void setPhotonTorpedoes(int value) {
		((Weapon) this.subSystems.get("photon")).setPowerRemaining(value);
	}

	public int getPhotonTorpedoes() {
		return ((Weapon)this.subSystems.get("photon")).powerRemaining();
	}

	public void transferEnergy(int energy) {
		if(!this.hasDefender()) {
			throw new NoDefenderException();
		}

		if(energy < 1){
			return;
		}

		this.minusEnergy(energy);
		((Defender) this.subSystems.get("shield")).plusEnergy(energy);
	}

	public Defender getDefender() {
		return (Defender) this.subSystems.get("shield");
	}

	public void exportEnergy(int energy) {
		if(!this.hasDefender()) {
			throw new NoDefenderException();
		}

		this.plusEnergy(energy);
		((Defender) this.subSystems.get("shield")).minusEnergy(energy);
	}
}
