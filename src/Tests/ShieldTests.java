package Tests;

import StarTrek.Game;
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
		int currentShieldEnergy = game.getShieldEnergy();
		Assert.assertEquals(currentShieldEnergy, 100);
	}

	@Test
	public void checkTransferEnergyToSheild() {
		game.raiseUpShield(100);
		game.transferEnergy(1000);
		int currentShieldEnergy = game.getShieldEnergy();
		Assert.assertEquals(currentShieldEnergy, 1100);
	}

	@Test
	public void checkTranferEnergyFailure() {
		game.raiseUpShield(10000);
		game.transferEnergy(1);
		Assert.assertEquals (game.getShieldEnergy(), 10000);


	}
	@Test
	public void checkTransferEnergyMinVaule(){
		game.raiseUpShield(100);
		game.transferEnergy(-1);
		Assert.assertEquals(game.getShieldEnergy(),100);
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
}
