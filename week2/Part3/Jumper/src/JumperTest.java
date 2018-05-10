import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class JumperTest {

	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	//the two cell have a rock
	public void testCanJumpFir() {
		 ActorWorld world = new ActorWorld();
        //Create a new jumper.
        Jumper jumper = new Jumper();
        Rock rock = new Rock();
        world.add(new Location(2, 3), jumper);
        world.add(new Location(0, 3), rock);
        assertEquals(false,  jumper.canJump());
	}

	@Test
	//the two cell have a flower
	public void testCanJumpSec() {
		 ActorWorld world = new ActorWorld();
       //Create a new jumper.
       Jumper jumper = new Jumper();
       Flower flower = new Flower();
       world.add(new Location(2, 3), jumper);
       world.add(new Location(0, 3), flower);
       assertEquals(true,  jumper.canJump());
	}
	
	@Test
	//if the location two cells in front of the jumper is out of the grid
	public void testCanJumpThr() {
		 ActorWorld world = new ActorWorld();
       //Create a new jumper.
       Jumper jumper = new Jumper();
       world.add(new Location(0, 9), jumper);
       assertEquals(false,  jumper.canJump());
	}
	
	@Test
	//the first cell have a rock
	public void testCanJumpFou() {
		 ActorWorld world = new ActorWorld();
        //Create a new jumper.
        Jumper jumper = new Jumper();
        Rock rock = new Rock();
        world.add(new Location(2, 3), jumper);
        world.add(new Location(1, 3), rock);
        assertEquals(true, jumper.canJump());
	}
	
	@Test
	//the first cell have a flower
	public void testCanJumpFiv() {
		 ActorWorld world = new ActorWorld();
        //Create a new jumper.
        Jumper jumper = new Jumper();
        Flower flower = new Flower();
        world.add(new Location(2, 3), jumper);
        world.add(new Location(1, 3), flower);
        assertEquals(true, jumper.canJump());
	}
}
