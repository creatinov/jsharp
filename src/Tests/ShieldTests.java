package Tests;

import StarTrek.Game;
import StarTrek.defender.Shield;
import StarTrek.exceptions.NoDefenderException;
import StarTrek.exceptions.TooMuchException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShieldTests {

	private Game game;

	@Before
	public void setUp() {
		game = new Game();
	}

	@Test
	public void whenDefenderRaiseUp() {
		game.raiseUpDefender();
		Assert.assertTrue(game.isRaiseUpDefender());
		Assert.assertEquals(2000, game.getShieldEnergy());
		Assert.assertEquals(40000, game.getShipEnergy());	//not use starship energy
	}

	@Test(expected = NoDefenderException.class)
	public void whenTransferEnergyToDefenderBeforeRaiseUpDefender() {
		game.transferEnergyToDefender(3000);
	}

	@Test(expected = TooMuchException.class)
	public void whenTransferEnergyToDefender() {
		game.raiseUpDefender();
		game.transferEnergyToDefender(3000);
		Assert.assertEquals(37000, game.getShipEnergy());
		Assert.assertEquals(5000, game.getShieldEnergy());


		game.transferEnergyToDefender(5001);
	}

	// TODO: for story A-2 "Buckle"

	@Test
	public void energyWillBeReducedAfterHit() {
		Shield shield = new Shield(2000);
		shield.setIsUp(true);
		shield.onHit(200);
		Assert.assertEquals(1800, shield.getEnergy());
		shield.onHit(1801);
		Assert.assertEquals(false, shield.getIsUp());
	}

	@Test
	public void checkDamaged(){
		Shield shield = new Shield(0);
		shield.onDamage(500);
		Assert.assertEquals(true, shield.getIsDamaged());
	}

}
