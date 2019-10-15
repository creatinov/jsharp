package StarTrek;

import StarTrek.defender.Defender;
import StarTrek.defender.Shield;
import StarTrek.exceptions.NoDefenderException;
import StarTrek.exceptions.TooMuchException;
import StarTrek.weapon.Phaser;
import StarTrek.weapon.Photon;
import StarTrek.weapon.Weapon;

import java.util.*;

public class Starship {

	private int energy = 40000;

	private Map<String, Weapon> weapons = new HashMap<>();

	private Defender defender;

	public Starship() {
		weapons.put("phaser", new Phaser());
		weapons.put("photon", new Photon());
	}

	public void setUpDefender(Defender defender) {
		this.defender = defender;
	}

	public void fire(Galaxy galaxy) {
		String command = galaxy.parameter("command");

		Weapon weapon = weapons.get(command);
		weapon.fire(galaxy);
	}

	public void onHit(int damage) {
		//TODO 적의 공격에 맞았을 때.
	}

	public boolean hasDefender() {
		if(this.defender == null) {
			return false;
		}
		return this.defender.hasEnergy();
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
		return this.weapons.get("phaser").powerRemaining();
	}

	public void setPhotonTorpedoes(int value) {
		this.weapons.get("photon").setPowerRemaining(value);
	}

	public int getPhotonTorpedoes() {
		return this.weapons.get("photon").powerRemaining();
	}

	public void transferEnergy(int energy) {
		if(!this.hasDefender()) {
			throw new NoDefenderException();
		}

		if(energy < 1){
			return;
		}

		this.minusEnergy(energy);
		this.defender.plusEnergy(energy);
	}

	public Defender getDefender() {
		return this.defender;
	}

	public void exportEnergy(int energy) {
		if(!this.hasDefender()) {
			throw new NoDefenderException();
		}

		this.plusEnergy(energy);
		this.defender.minusEnergy(energy);
	}
}
