package Tests;

import StarTrek.Game;
import StarTrek.defender.Shield;
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
	public void raiseUp() {
		game.raiseUpShield();
		Assert.assertTrue(game.isRaiseUpSheild());
		Assert.assertEquals(game.getShieldEnergy(), 2000);
	}

	//transfer
	@Test(expected = TooMuchException.class)
	public void whenTransferEnergyToDefender() {
		game.raiseUpShield();
		game.transferEnergyToDefender(3000);
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
