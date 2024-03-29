package Tests;

import StarTrek.Game;
import StarTrek.defender.Shield;
import StarTrek.exceptions.NoDefenderException;
import StarTrek.exceptions.NotEnoughEnergyException;
import StarTrek.exceptions.TooMuchException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

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

	@Test
	public void whenTransferEnergyToDefender() {
		game.raiseUpDefender();
		game.transferEnergyToDefender(3000);
		Assert.assertEquals(37000, game.getShipEnergy());
		Assert.assertEquals(5000, game.getShieldEnergy());
	}

	@Test(expected = TooMuchException.class)
	public void whenTransferEnergyToDefenderTooMuch() {
		game.raiseUpDefender();
		game.transferEnergyToDefender(3000);
		game.transferEnergyToDefender(5001);
	}

	@Test
	public void whenTransferEnergyToStarship() {
		game.raiseUpDefender();
		game.transferEnergyToDefender(3000);
		game.transferEnergyToStarship(2000);

		Assert.assertEquals(39000, game.getShipEnergy());
		Assert.assertEquals(3000, game.getShieldEnergy());

	}

	@Test(expected = NotEnoughEnergyException.class)
	public void whenTransferEnergyToStarshipOverEnergy() {
		game.raiseUpDefender();
		game.transferEnergyToStarship(2001);
	}

	// TODO: for story A-2 "Buckle"
	@Test
	public void energyWillBeReducedAfterHit() {
		Shield shield = new Shield(2000);
		shield.setIsUp(true);
		shield.onHit(200);
		Assert.assertEquals(1800, shield.getEnergy());
		Assert.assertEquals(true, shield.getIsUp());
		Assert.assertEquals(false, shield.getIsDamaged());
	}

	@Test
	public void whenEnergyDecreasedBelowZero_thenGoesDown() {
		Shield shield = new Shield(2000);
		shield.setIsUp(true);
		shield.onHit(2001);
		Assert.assertEquals(0, shield.getEnergy());
		Assert.assertEquals(false, shield.getIsUp());
	}

	@Test
	public void checkDamaged(){
		Shield shield = new Shield(0);
		shield.onDamage(500);
		Assert.assertEquals(true, shield.getIsDamaged());
	}

}
