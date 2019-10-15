package StarTrek;

import java.util.Random;

import StarTrek.exceptions.MissedException;
import StarTrek.weapon.Phaser;
import StarTrek.weapon.Photon;
import StarTrek.weapon.Weapon;
import Untouchables.WebGadget;

public class Game {

	private Weapon phaser = new Phaser();

	private Weapon photon = new Photon();

	public int EnergyRemaining() {
		return phaser.powerRemaining();
	}

	public void setTorpedoes(int value) {
		photon.setPowerRemaining(value);
	}

	public int getTorpedoes() {
		return photon.powerRemaining();

	}

	public void fireWeapon(WebGadget wg) {
		fireWeapon(new Galaxy(wg));
	}

	public void fireWeapon(Galaxy wg) {
		if (wg.parameter("command").equals("phaser")) {
			int amount = Integer.parseInt(wg.parameter("amount"));
			Klingon enemy = (Klingon) wg.variable("target");

			if (phaser.canFire(amount)) {
				int distance = enemy.distance();
				int damage = 0;
				try {
					damage = phaser.fire(distance);
				} catch (MissedException e) {
					wg.writeLine("Klingon out of range of phasers at " + distance + " sectors...");
					return;
				}
				if (damage < 1)
					damage = 1;
				wg.writeLine("Phasers hit Klingon at " + distance + " sectors with " + damage + " units");
				if (damage < enemy.getEnergy()) {
					enemy.setEnergy(enemy.getEnergy() - damage);
					wg.writeLine("Klingon has " + enemy.getEnergy() + " remaining");
				} else {
					wg.writeLine("Klingon destroyed!");
					enemy.delete();
				}
			} else {
				wg.writeLine("Insufficient energy to fire phasers!");
			}

		} else if (wg.parameter("command").equals("photon")) {
			Klingon enemy = (Klingon) wg.variable("target");

			if (photon.canFire(1)) {
				int distance = enemy.distance();
				int damage = 0;
				try {
					damage = photon.fire(distance);
					wg.writeLine("Photons hit Klingon at " + distance + " sectors with " + damage + " units");
				} catch (MissedException e) {
					wg.writeLine("Torpedo missed Klingon at " + distance + " sectors...");
					return;
				}

				if (damage < enemy.getEnergy()) {
					enemy.setEnergy(enemy.getEnergy() - damage);
					wg.writeLine("Klingon has " + enemy.getEnergy() + " remaining");
				} else {
					wg.writeLine("Klingon destroyed!");
					enemy.delete();
				}
			} else {
				wg.writeLine("No more photon torpedoes!");
			}
		}
	}

	// note we made generator public in order to mock it
	// it's ugly, but it's telling us something about our *design!* ;-)
	public static Random generator = new Random();

	public static int rnd(int maximum) {
		return generator.nextInt(maximum);
	}


}
