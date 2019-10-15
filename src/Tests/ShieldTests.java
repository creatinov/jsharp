package Tests;

import StarTrek.Game;
import StarTrek.defender.Shield;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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

	//export

	//shield down

	@Test
	public void checkTransferShipEnergyUnderZero(){
		game.transferEnergy(101);
		game.transferEnergy(1000);
		//TODO
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
