package Tests;

import StarTrek.Starship;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;
import java.util.Random;

@RunWith(MockitoJUnitRunner.class)
public class StarShipTests {
    Starship starship = null;

    @Before
    public void Setup() {
        starship = new Starship();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenUseMock_hitByEnemy_thenWeapon() {
        Starship starshipMock = Mockito.mock(Starship.class);
        Mockito.when(starshipMock.rnd(3)).thenReturn(0);
        Assert.assertEquals("an weapon is hit and damaged",
                starship.onHit(100, starshipMock.rnd(3)));
    }

    @Test
    public void whenUseMock_hitByEnemy_thenEngine() {
        Random mockRandom = Mockito.mock(Random.class);
        Starship.generator = mockRandom;
        Mockito.when(mockRandom.nextInt(3)).thenReturn(1);
        Assert.assertEquals("an engine is hit and damaged", starship.onHit(100));
        Mockito.verify(mockRandom).nextInt(3);
    }

    @Test
    public void whenUseMock_hitByEnemy_thenShield() {
        Starship starshipMock = Mockito.mock(Starship.class);
        Mockito.when(starshipMock.rnd(3)).thenReturn(2);
        Assert.assertEquals("a shield is hit and damaged",
                starship.onHit(100, starshipMock.rnd(3)));

    }
}
