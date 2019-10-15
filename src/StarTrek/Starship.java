package StarTrek;

import StarTrek.defender.Defender;
import StarTrek.weapon.Phaser;
import StarTrek.weapon.Photon;
import StarTrek.weapon.Weapon;

import java.util.*;

public class Starship {

	private int energy = 40000;

	private Map<String, Weapon> weapons = new HashMap<>();

	private List<Defender> defenders;

	public Starship() {
		weapons.put("phaser", new Phaser());
		weapons.put("photon", new Photon());
	}

	public void addDefender(Defender defender) {
		if(defenders == null) {
			defenders = new ArrayList<>();
		}
		defenders.add(defender);
	}

	public void fire(Galaxy galaxy) {
		String command = galaxy.parameter("command");

		Weapon weapon = weapons.get(command);
		weapon.fire(galaxy);
	}

	public void onHit(int damage) {
		//TODO 적의 공격에 맞았을 때.
	}

	private boolean hasDefender() {
		if(this.defenders == null || this.defenders.isEmpty()) {
			return false;
		}
		for (Defender defender : this.defenders) {
			if(defender.hasEnergy()) {
				return true;
			}
		}
		return false;
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

}
