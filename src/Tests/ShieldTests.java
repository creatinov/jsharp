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
		game.raiseUpShield(1000);
		Assert.assertTrue(game.isRaiseUpSheild());
	}

	@Test
	public void checkShieldEnergy() {
		game.raiseUpShield(100);
		int currentShieldEnergy = game.getShieldEnerrgy();
		Assert.assertEquals(currentShieldEnergy, 100);
	}

	@Test
	public void checkTransferEnergyToSheild() {
		game.raiseUpShield(100);
		game.transferEnergy(1000);
		int currentShieldEnergy = game.getShieldEnerrgy();
		Assert.assertEquals(currentShieldEnergy, 1100);
	}

	@Test
	public void checkTranferEnergyFailure() {
		game.raiseUpShield(10000);
		game.transferEnergy(1);
		Assert.assertEquals (game.getShieldEnerrgy(), 10000);


	}
	@Test
	public void checkTransferEnergyMinVaule(){
		game.raiseUpShield(100);
		game.transferEnergy(-1);
		Assert.assertEquals(game.getShieldEnerrgy(),100);
	}

	@Test
	public void checkShipEnergy(){
		Assert.assertEquals(game.getShipEnergy(),40000);
	}

	@Test
	public void checkShipEnergy2(){
		game.transferEnergy(1000);
		Assert.assertEquals(game.getShipEnergy(),39000);
	}

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





//	@Test
//	public void canBuckledHitByEnemyWeapon(){
//
//
//	}

}
