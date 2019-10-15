package Tests;

import StarTrek.Starship;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class StarShipTests {
    Starship starship=null;
    @Before
    public void Setup(){
        starship = new Starship();
    }
}
