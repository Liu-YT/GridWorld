import info.gridworld.actor.Actor;
import info.gridworld.actor.Flower;
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;


public class Jumper extends Actor
{
	public Jumper() {
		// TODO Auto-generated constructor stub
	}
	
	/*
	 * 	if can jump jump
	 * 	else turn
	 * */
	public void act() {
		if(canJump()) {
			jump();
		}
		else {
			turn();
		}
	}
	
	/*
	 * 	change the jump direction of the jumper
	 * */
	public void turn(){
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
	
	/*
	 *	jump two cell 
	 * */
	public void jump(){
        Grid<Actor> gr = getGrid();
        if (gr == null)
            return;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location nNext = next.getAdjacentLocation(getDirection());
        if (gr.isValid(nNext)) {
        	moveTo(nNext);
        }
        else {
            removeSelfFromGrid();
        }
        Flower flower = new Flower(getColor());
        flower.putSelfInGrid(gr, loc);
    }
	
	/*
	 * judge whether the jumper can jump
	 * 
	 * */
	public boolean canJump() {
		Grid<Actor> gr = getGrid();
        if (gr == null)
            return false;
        Location loc = getLocation();
        Location next = loc.getAdjacentLocation(getDirection());
        Location nNext = next.getAdjacentLocation(getDirection());
        if (!gr.isValid(nNext))
            return false;
        Actor targetDes = gr.get(nNext);
        return (targetDes == null) || (targetDes instanceof Flower);
        // ok to move into empty location or onto flower
        // not ok to move onto any other actor
	}
}
