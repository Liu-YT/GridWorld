import info.gridworld.actor.Bug;

public class DancingBug extends Bug
{
    private int stage;
    private int danceArray[];

    /**
     * Constructs a dancing bug that traces a circle of a given side length
     * @param length the side length
     */
    public DancingBug(int[] array)
    {
        stage = 0;
        danceArray = array;
    }

    public void dance(int index) {
		for(int i = 0; i < danceArray[index]; i++) {
			turn();
		}
		stage = (stage + 1) % danceArray.length;
	}
    
    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        if (canMove())
        {
        	dance(stage);
            move();
        }
    }
}
