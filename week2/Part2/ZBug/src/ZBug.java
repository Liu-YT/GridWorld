import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;


public class ZBug extends Bug
{
    private int steps;
    private int sideLength;
    private int stage;
    
    /**
     * Constructs a z bug that traces a circle of a given side length
     * @param length the side length
     */
    public ZBug(int length)
    {
    	stage = 1;
    	setDirection(Location.EAST);
        steps = 0;
        sideLength = length;
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
    	//not having finish a z
        if(stage <= 3) {
        	if(canMove() && steps < sideLength) {
        		steps++;
        		move();
        	}
        	else if(stage == 1 && steps == sideLength) {
        		stage++;
        		steps = 0;
        		setDirection(Location.SOUTHWEST);
        	}
        	else if(stage == 2 && steps == sideLength) {
        		stage++;
        		steps = 0;
        		setDirection(Location.EAST);
        	}
        }
    }
}
