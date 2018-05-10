import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

public class KingCrab extends CrabCritter
{	
	public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
        	if(!(a instanceof KingCrab))
        		moveAway(a);
        }
    }
	
	private void moveAway(Actor actor) {
		ArrayList<Location> moveLocation = getGrid().getEmptyAdjacentLocations(actor.getLocation());
		if(moveLocation.size() == 0 || actor instanceof Flower || actor instanceof Rock) {
			actor.removeSelfFromGrid();
		}
		else {
			int n = moveLocation.size();
	        int r = (int) (Math.random() * n);
	        actor.moveTo(moveLocation.get(r));
		}
	}
}
