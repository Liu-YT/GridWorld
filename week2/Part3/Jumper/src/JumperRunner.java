import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

import java.awt.Color;


public class JumperRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        Jumper alice = new Jumper();
        alice.setColor(Color.ORANGE);
        Rock rock = new Rock();
        Flower flower = new Flower();
        world.add(new Location(3, 3), flower);
        world.add(new Location(2, 2), rock);
        world.add(new Location(5, 5), alice);
        world.show();
    }
}