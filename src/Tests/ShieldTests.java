package Tests;

import StarTrek.Game;
import StarTrek.exceptions.TooMuchException;
import StarTrek.defender.Shield;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

public class ShieldTests {

	private Game game;

	private MockGalaxy context;

	@Before
	public void setUp() {
		game = new Game();
		context = new MockGalaxy();
	}

	@Test
	public void raiseUp() {
		game.raiseUpShield();
		Assert.assertTrue(game.isRaiseUpSheild());
		Assert.assertEquals(game.getShieldEnergy(), 2000);
	}

	//transfer
	@Test(expected = TooMuchException.class)
	public void whenTransferEnergyToShield() {
		game.raiseUpShield();
		game.transferEnergyToShield(3000);
		Assert.assertEquals(5000, game.getShieldEnergy());
		game.transferEnergyToShield(5001);
	}

	//export

	//shield down


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
