package StarTrek;

import StarTrek.defender.Defender;
import StarTrek.weapon.Phaser;
import StarTrek.weapon.Photon;
import StarTrek.weapon.Weapon;

import java.util.*;

public class Starship {

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

	public int fire(String command, int distance) {
		Weapon weapon = weapons.get(command);
		return weapon.fire(distance);
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
}
