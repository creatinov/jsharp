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
		game.raiseUpShield();
		Assert.assertTrue(game.isRaiseUpSheild());
		Assert.assertEquals(game.getShieldEnergy(), 2000);
	}

	//transfer

	//export

	//shield down

}
