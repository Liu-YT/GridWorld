package info.gridworld.maze;

import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.JOptionPane;



/**
 * A <code>MazeBug</code> can find its way in a maze. <br/>
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {
	private Location next;	//��¼��һ��Ҫ���ߵ���λ��
	private Location last;	//��¼��һ����λ�ã��������ߵ���·��ͷʱ����
	private boolean isEnd = false;	
	private Stack<Location> crossLocation = new Stack<Location>();	//��¼���Ľڵ��ջ
	private Integer stepCount = 0;	//��¼���Թ��ߵ��������õĲ���
	private boolean hasShown = false;	//final message has been shown
	private int[] weight = {1,1,1,1};	//�ĸ������Ȩֵ���ֱ�Ϊ���������ϡ���
	private Location orignLocation = null;
	
	/**
	 * Constructs a box bug that traces a square of a given side length
	 * 
	 * @param length
	 *            the side length
	 */
	public MazeBug() {
		setColor(Color.GREEN);
		last = new Location(0, 0);
	}

	/**
	 * Moves to the next location of the square.
	 */
	public void act() {
		boolean willMove = canMove();
		if (isEnd) {
			//reach the goal position and then end
			//to show step count when reach the goal		
			if (!hasShown) {
				String msg = stepCount.toString() + " steps";
				JOptionPane.showMessageDialog(null, msg);
				hasShown = true;
			}
		} else if (willMove) {
			last = getLocation();
			crossLocation.push(last);
        	weight[getLocation().getDirectionToward(next) / 90] += 1;
			//can move
			move();
			//increase step count when move 
			stepCount++;
		} else {
			//can't move then back
			back();
		}
	}

	/**
	 * Find all positions that can be move to.
	 * 
	 * @param loc
	 *            the location to detect.
	 * @return List of positions.
	 */
	public ArrayList<Location> getValid(Location loc) {
		Grid<Actor> gr = getGrid();
		if (gr == null){
			return null;
		}
		ArrayList<Location> valid = new ArrayList<Location>();
		
		Location location = getLocation();
		int[] dir = {Location.NORTH, Location.EAST, Location.SOUTH, Location.WEST};
		
		for(int direction : dir) {
			Location tmp = location.getAdjacentLocation(direction);
			if(gr.isValid(tmp)) {
				Actor actor = gr.get(tmp);
				if(actor == null) {
					valid.add(tmp);
				}
				else if(actor instanceof Rock && actor.getColor().equals(Color.RED)){
					isEnd = true;
					valid.clear();
					return valid;
				}
				
			}
		}
		return valid;
	}

	/**
	 * Tests whether this bug can move forward into a location that is empty or
	 * contains a flower.
	 * 
	 * @return true if this bug can move.
	 */
	public boolean canMove() {
		if(orignLocation == null) {
			orignLocation = getLocation();
		}
		if(getValid(orignLocation).size() == 0) {
			return false;
		}
		Grid<Actor> gr = getGrid();
        if (gr == null) {
            return false;
        }
        Location location = getLocation();
        ArrayList<Location> validLocation = getValid(location);
        if(validLocation.size() > 0) {
        	int maxWeight = -1;
        	Location tmp = null;
        	for(Location loc : validLocation) {
        		if(weight[location.getDirectionToward(loc) / 90] > maxWeight) {
        			maxWeight = weight[location.getDirectionToward(loc) / 90];
        			tmp = loc;
        		}
        	}
        	next = tmp;
        	return true;
        }
        return false;
        // ok to move if has valid location
        // not ok to move if doesn't have valid location
	}
	
	/**
	 * Moves the bug forward, putting a flower into the location it previously
	 * occupied.
	 */
	public void move() {
		Grid<Actor> gr = getGrid();
		if (gr == null) {
			return;
		}
		Location loc = getLocation();
		if (gr.isValid(next)) {
			setDirection(getLocation().getDirectionToward(next));
			moveTo(next);
		} 
		else {
			removeSelfFromGrid();
		}
		Flower flower = new Flower(getColor());
		flower.putSelfInGrid(gr, loc);
	}
	
	//move back and step num add 1
	private void back() {
		if(crossLocation.size() > 0) {
			next = crossLocation.pop();
		}		
		weight[(getLocation().getDirectionToward(next) / 90 + 2) % 4] -= 1;
		move();
		stepCount++;
	}
}
