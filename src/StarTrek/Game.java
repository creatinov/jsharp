package StarTrek;

import java.util.Random;

import StarTrek.defender.Shield;
import StarTrek.exceptions.MissedException;
import StarTrek.weapon.Phaser;
import StarTrek.weapon.Photon;
import StarTrek.weapon.Weapon;
import Untouchables.WebGadget;

public class Game {

	private Starship starship = new Starship();

	private Shield shield = new Shield(0);

	public int EnergyRemaining() {
		return starship.getPhaserEnergy();
	}

	public void setTorpedoes(int value) {
		starship.setPhotonTorpedoes(value);
	}

	public int getTorpedoes() {
		return starship.getPhotonTorpedoes();

	}

	public void raiseUpDefender() {
		starship.setUpDefender(new Shield(2000));
	}

	public int getShieldEnergy() {
		return starship.getDefender().getEnergy();
	}



	public int getShipEnergy(){
		return starship.getEnergy();
	}

	public void fireWeapon(WebGadget wg) {
		fireWeapon(new Galaxy(wg));
	}

	public void fireWeapon(Galaxy wg) {
		starship.fire(wg);
	}

	// note we made generator public in order to mock it
	// it's ugly, but it's telling us something about our *design!* ;-)
	public static Random generator = new Random();

	public static int rnd(int maximum) {
		return generator.nextInt(maximum);
	}


	public boolean isRaiseUpDefender() {
		return this.starship.hasDefender();
	}

	public void transferEnergyToDefender(int energy) {
		starship.transferEnergy(energy);
	}
}
