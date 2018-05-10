import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

/**
 * This class runs a world that contains box bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class ChameleonKidRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();
        Flower flower = new Flower();
        Rock rock = new Rock();
        ChameleonKid alice = new ChameleonKid();
        world.add(new Location(5, 5), alice);
        world.add(new Location(4, 4), rock);
        world.add(new Location(6, 6), flower);
        world.show();
    }
}