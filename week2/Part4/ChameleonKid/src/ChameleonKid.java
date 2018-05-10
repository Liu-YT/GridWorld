import java.util.ArrayList;

import info.gridworld.actor.Actor;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

public class ChameleonKid extends ChameleonCritter
{	
	public ChameleonKid() {
		// TODO Auto-generated constructor stub
	}
	
	//just get front and behind actor
	public ArrayList<Actor> getActors()
	{
		Grid<Actor> grid = getGrid();
		ArrayList<Actor> neighbors = new ArrayList<Actor>();
		Location front = getLocation().getAdjacentLocation(Location.AHEAD);
		Location behind = getLocation().getAdjacentLocation(Location.HALF_CIRCLE);
		if (grid.isValid(front) && grid.get(front) != null) {
			neighbors.add(grid.get(front));
		}
		if(grid.isValid(behind) && grid.get(behind) != null)
			neighbors.add(grid.get(behind));
		return neighbors;
	}
}
