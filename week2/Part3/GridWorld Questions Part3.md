1. How would you access the row value for loc1?

   `loc1.getRow();`

   //@file: GridWorld/framework/info/gridworld/grid/Location.java

   //@line: 110~113

   ~~~java
   public int getRow()
   {
       return row;
   }
   ~~~

2. What is the value of b after the following statement is executed?

   (`boolean b = loc1.equals(loc2);`)

   b = false

   //@file: GridWorld/framework/info/gridworld/grid/Location.java

   //@line: 205~212

   ~~~java
   public boolean equals(Object other)
   {
       if (!(other instanceof Location))
           return false;

       Location otherLoc = (Location) other;
       return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
   }
   ~~~

3. What is the value of loc3 after the following statement is executed?

   (`Location loc3 = loc2.getAdjacentLocation(Location.SOUTH);`)

   (4, 4)

   //@file: GridWorld/framework/info/gridworld/grid/Location.java

   //@line: 81

   ~~~java
   public static final int SOUTH = 180;
   ~~~

   //@file: GridWorld/framework/info/gridworld/grid/Location.java

   //@line: 130~169

   ~~~java
   public Location getAdjacentLocation(int direction)
   {
       // reduce mod 360 and round to closest multiple of 45
       int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
       if (adjustedDirection < 0)
           adjustedDirection += FULL_CIRCLE;

       adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
       int dc = 0;
       int dr = 0;
       if (adjustedDirection == EAST)
           dc = 1;
       else if (adjustedDirection == SOUTHEAST)
       {
           dc = 1;
           dr = 1;
       }
       else if (adjustedDirection == SOUTH)
           dr = 1;
       else if (adjustedDirection == SOUTHWEST)
       {
           dc = -1;
           dr = 1;
       }
       else if (adjustedDirection == WEST)
           dc = -1;
       else if (adjustedDirection == NORTHWEST)
       {
           dc = -1;
           dr = -1;
       }
       else if (adjustedDirection == NORTH)
           dr = -1;
       else if (adjustedDirection == NORTHEAST)
       {
           dc = 1;
           dr = -1;
       }
       return new Location(getRow() + dr, getCol() + dc);
   }
   ~~~

4. What is the value of dir after the following statement is executed?

   (`int dir = loc1.getDirectionToward(new Location(6, 5));`)

   The value of the dir is 135. 

   //@file: GridWorld/framework/info/gridworld/grid/Location.java

   //@line: 178~195

   ~~~java
   public int getDirectionToward(Location target)
   {
       int dx = target.getCol() - getCol();
       int dy = target.getRow() - getRow();
       // y axis points opposite to mathematical orientation
       int angle = (int) Math.toDegrees(Math.atan2(-dy, dx));

       // mathematical angle is counterclockwise from x-axis,
       // compass angle is clockwise from y-axis
       int compassAngle = RIGHT - angle;
       // prepare for truncating division by 45 degrees
       compassAngle += HALF_RIGHT / 2;
       // wrap negative angles
       if (compassAngle < 0)
           compassAngle += FULL_CIRCLE;
       // round to nearest multiple of 45
       return (compassAngle / HALF_RIGHT) * HALF_RIGHT;
   }
   ~~~

5. How does the getAdjacentLocation method know which adjacent location to return?

   According the parameter in the getAdjacentLocation method. The method returns the adjacent location in the compass direction that is closest to the direction given in the parameter list.

   //@file: GridWorld/framework/info/gridworld/grid/Location.java

   //@line: 130~169

   ~~~java
   public Location getAdjacentLocation(int direction)
   {
       // reduce mod 360 and round to closest multiple of 45
       int adjustedDirection = (direction + HALF_RIGHT / 2) % FULL_CIRCLE;
       if (adjustedDirection < 0)
           adjustedDirection += FULL_CIRCLE;

       adjustedDirection = (adjustedDirection / HALF_RIGHT) * HALF_RIGHT;
       int dc = 0;
       int dr = 0;
       if (adjustedDirection == EAST)
           dc = 1;
       else if (adjustedDirection == SOUTHEAST)
       {
           dc = 1;
           dr = 1;
       }
       else if (adjustedDirection == SOUTH)
           dr = 1;
       else if (adjustedDirection == SOUTHWEST)
       {
           dc = -1;
           dr = 1;
       }
       else if (adjustedDirection == WEST)
           dc = -1;
       else if (adjustedDirection == NORTHWEST)
       {
           dc = -1;
           dr = -1;
       }
       else if (adjustedDirection == NORTH)
           dr = -1;
       else if (adjustedDirection == NORTHEAST)
       {
           dc = 1;
           dr = -1;
       }
       return new Location(getRow() + dr, getCol() + dc);
   }
   ~~~

6. How can you obtain a count of the objects in a grid? How can you obtain a count of the empty locations in a bounded grid?

   * By the method `getOccupiedLocations()` and then get the size of the return ArrayList by the method `size()`.

     //@file: GridWorld/framework/info/gridworld/grid/Grid.java

     //@line: 85

      ~~~java
      ArrayList<Location> getOccupiedLocations();
      ~~~

   * By a simple calculation

     `the num = grid.getNumRows()*grid.getNumCols - grid.getOccupiedLocations().size()` 

     //@file: GridWorld/framework/info/gridworld/grid/BoundedGrid.java

     //@line: 48, 53, 66

     ~~~java
     public int getNumRows()
     public int getNumCols()
     public ArrayList<Location> getOccupiedLocations()
     ~~~

7. How can you check if location (10,10) is in a grid?

   * BoundedGrid

     By the method `isValid` 

     //@file: GridWorld/framework/info/gridworld/grid/BoundedGrid.java

     //@line: 60~64

     ~~~java
     public boolean isValid(Location loc)
     {
         return 0 <= loc.getRow() && loc.getRow() < getNumRows()
             && 0 <= loc.getCol() && loc.getCol() < getNumCols();
     }
     ~~~

   * UnBounderGrid

     Always valid

     //@file: GridWorld/framework/info/gridworld/grid/UnBoundedGrid.java

     //@line: 53~56

     ~~~java
     public boolean isValid(Location loc)
     {
         return true;
     }
     ~~~

8. Grid contains method declarations, but no code is supplied in the methods. Why? Where can you find the implementations of these methods?

   Because the Grid is just a interfacea and so just contains method declarations. And it will be implement

   and then the method will be finsih in the implement class. We can find the methods have code supplement in the class `AbstractGrid` and the class `BounderGrid` and the class `UnboundedGrid`,  which the last two class inherit  the AbstractedGrid.

   //@file: GridWorld/framework/info/gridworld/grid/AbstractGrid.java

   //@line: 26

   ~~~java
   public abstract class AbstractGrid<E> implements Grid<E>
   ~~~

   //@file: GridWorld/framework/info/gridworld/grid/BounderGrid.java

   //@line: 29

   ~~~java
   public class BoundedGrid<E> extends AbstractGrid<E>
   ~~~

   //@file: GridWorld/framework/info/gridworld/grid/UnBounderGrid.java

   //@line: 31

   ~~~java
   public class UnboundedGrid<E> extends AbstractGrid<E>
   ~~~

9. All methods that return multiple objects return them in an ArrayList. Do you think it would be a better design to return the objects in an array? Explain your answer.

   No, I think that the ArrayList is much better than the array.

   Because the capacity of ArrayList can grow dynamically but the capacity of array can't be change after it is declared. At the same time, the usage of the ArrayList is very convenient.

   (the code behind just the method which use the ArrayList)

   //@file:GridWorld/framework/info/gridworld/grid/UnBounderGrid.java

   //@line: 85,96, 107, 118, 129

   ~~~java
   ArrayList<Location> getOccupiedLocations();
   ArrayList<Location> getValidAdjacentLocations(Location loc);
   ArrayList<Location> getEmptyAdjacentLocations(Location loc);
   ArrayList<Location> getOccupiedAdjacentLocations(Location loc);
   ArrayList<E> getNeighbors(Location loc);
   ~~~

10. Name three properties of every actor.

   Each actor have its color, direction and a location and the reference the grid which it in.

   //@file:GridWorld/framework/info/gridworld/actor/Actor.java

   //@line: 31~34

   ~~~java
       private Grid<Actor> grid;
       private Location location;
       private int direction;
       private Color color;
   ~~~

11. When an actor is constructed, what is its direction and color?

    North, Blue

    //@file:GridWorld/framework/info/gridworld/actor/Actor.java

    //@line: 31~34

    ~~~java
    public Actor()
    {
        color = Color.BLUE;
        direction = Location.NORTH;
        grid = null;
        location = null;
    }
    ~~~

12. Why do you think that the Actor class was created as a class instead of an interface?

    Because each actor has some attributes and some same operating  and so that we can  complete function with code and don't need to complete in the classes which are inherited the Actor class.

    (just show some same attributes)

    //@file:GridWorld/framework/info/gridworld/actor/Actor.java

    //@line: 31~34

    ~~~java
    public Actor()
    {
        color = Color.BLUE;
        direction = Location.NORTH;
        grid = null;
        location = null;
    }
    ~~~

13. Can an actor put itself into a grid twice without first removing itself? Can an actor remove itself from a grid twice? Can an actor be placed into a grid, remove itself, and then put itself back? Try it out. What happens?

    No. It can't run.

    ~~~
    Exception in thread "main" java.lang.IllegalStateException: This actor is already contained in a grid.
    	at info.gridworld.actor.Actor.putSelfInGrid(Actor.java:118)
    	at BugRunner.main(BugRunner.java:36)
    ~~~

    //@file:GridWorld/framework/info/gridworld/actor/Actor.java

    //@line: 115~127

    ~~~java
    public void putSelfInGrid(Grid<Actor> gr, Location loc)
    {
        if (grid != null)
            throw new IllegalStateException(
            "This actor is already contained in a grid.");

        Actor actor = gr.get(loc);
        if (actor != null)
            actor.removeSelfFromGrid();
        gr.put(loc, this);
        grid = gr;
        location = loc;
    }
    ~~~

    No. It' can. It have a error.

    ~~~
    Exception in thread "main" java.lang.IllegalStateException: This actor is not contained in a grid.
    	at info.gridworld.actor.Actor.removeSelfFromGrid(Actor.java:136)
    	at BugRunner.main(BugRunner.java:37)
    ~~~

    //@file:GridWorld/framework/info/gridworld/actor/Actor.java

    //@line: 133~146

    ~~~java
    public void removeSelfFromGrid()
        {
            if (grid == null)
                throw new IllegalStateException(
                        "This actor is not contained in a grid.");
            if (grid.get(location) != this)
                throw new IllegalStateException(
                        "The grid contains a different actor at location "
                                + location + ".");

            grid.remove(location);
            grid = null;
            location = null;
        }
    ~~~

    Yes. We can add it to the grid and then remove it. It is legel.

14. How can an actor turn 90 degrees to the right?

    `setDirection(getDirection() + Location.RIGHT)`

    //@file:GridWorld/framework/info/gridworld/actor/Actor.java

    //@line: 80~85, 102~105

    ~~~java
    public void setDirection(int newDirection)
    {
        direction = newDirection % Location.FULL_CIRCLE;
        if (direction < 0)
            direction += Location.FULL_CIRCLE;
    }
    public Location getLocation()
    {
        return location;
    }
    ~~~

15. Which statement(s) in the canMove method ensures that a bug does not try to move out of its grid?

    By the method isValid.

    //@file:GridWorld/framework/info/gridworld/actor/Bug.java

    //@line: 98~99

    ~~~java
    if (!gr.isValid(next))
        return false;
    ~~~

16. Which statement(s) in the canMove method determines that a bug will not walk into a rock?

    judge weather the next is a instance of rock

    //@file:GridWorld/framework/info/gridworld/actor/Bug.java

    //@line: 100-101

    ~~~java
    Actor neighbor = gr.get(next);
    return (neighbor == null) || (neighbor instanceof Flower);
    ~~~

17. Which methods of the Grid interface are invoked by the canMove method and why?

    `boolean isValid(Location loc)` and `E get(Location loc)`

    By the method isValid we can know that whether a location is in the grid and by the method get we can know what is it in the location and then judge whether the bug can move.

     //@file:GridWorld/framework/info/gridworld/grid/Grid.java

    //@line: 50, 79

    ~~~java
    boolean isValid(Location loc);
    E get(Location loc)
    ~~~

    //@file:GridWorld/framework/info/gridworld/actor/Bug.java

    //@line: 98~101

    ~~~java
    f (!gr.isValid(next))
        return false;
    Actor neighbor = gr.get(next);
    return (neighbor == null) || (neighbor instanceof Flower);
    ~~~

18. Which method of the Location class is invoked by the canMove method and why?

    `Location getAdjacentLocation(int direction)` 

    By this method we can know the next position along the current direction and then judge it whether is valid.

    //@file:GridWorld/framework/info/gridworld/grid/Location.java

    //@line: 130

    ~~~java
    public Location getAdjacentLocation(int direction)
    ~~~

19. Which methods inherited from the Actor class are invoked in the canMove method?

    `Location getLocation()` and `Grid<Actor> getGrid()` and `int getDirection()`

    //@file:GridWorld/framework/info/gridworld/actor/Actor.java

    //@line: 69, 92,102

    ~~~java
    public int getDirection()
    public Grid<Actor> getGrid()
    public Location getLocation()
    ~~~

20. What happens in the move method when the location immediately in front of the bug is out of the grid?

    The bug will remove itself from the grid.

    //@file:GridWorld/framework/info/gridworld/actor/Bug.java

    //@line: 78~81

    ~~~java
    if (gr.isValid(next))
        moveTo(next);
    else
        removeSelfFromGrid();
    ~~~

21. Is the variable loc needed in the move method, or could it be avoided by calling getLocation() multiple times?

    Yes, the variable needed in the move method and could not avoided by calling getLocation() multiple times. Because once the bug move, then the location is also change and then the flower will in the location the bug in.

    //@file:GridWorld/framework/info/gridworld/actor/Bug.java

    //@line: 82~83

    ~~~java
    Flower flower = new Flower(getColor());
    flower.putSelfInGrid(gr, loc);
    ~~~

22. Why do you think the flowers that are dropped by a bug have the same color as the bug?

    We can easy to observe the trajectory of bug.

    //@file:GridWorld/framework/info/gridworld/actor/Bug.java

    //@line: 82~83

    ```java
    Flower flower = new Flower(getColor());
    flower.putSelfInGrid(gr, loc);
    ```

23. When a bug removes itself from the grid, will it place a flower into its previous location?

    * call the method `removeSelfFromGrid()` will not have a flower.

      //@file:GridWorld/framework/info/gridworld/actor/Actor.java

      //@line: 133~146

      ~~~java
      public void removeSelfFromGrid()
      {
          if (grid == null)
              throw new IllegalStateException(
              "This actor is not contained in a grid.");
          if (grid.get(location) != this)
              throw new IllegalStateException(
              "The grid contains a different actor at location "
              + location + ".");

          grid.remove(location);
          grid = null;
          location = null;
      }
      ~~~

    * call the method `move()` and then the next location is invalid will have a flower.

      //@file:GridWorld/framework/info/gridworld/actor/Bug.java

      //@line: 79~83

      ~~~java
      if (gr.isValid(next))
          moveTo(next);
      else
          removeSelfFromGrid();
      Flower flower = new Flower(getColor());
      flower.putSelfInGrid(gr, loc);
      ~~~

      ​

24. Which statement(s) in the move method places the flower into the grid at the bug's previous location?

    //@file:GridWorld/framework/info/gridworld/actor/Bug.java

    //@line: 82~83

    ```java
    Flower flower = new Flower(getColor());
    flower.putSelfInGrid(gr, loc);
    ```

25. If a bug needs to turn 180 degrees, how many times should it call the turn method?

    4, once call the turn method, just turn 45 degree.

    //@file:GridWorld/framework/info/gridworld/actor/Bug.java

    //@line: 62~65

    ~~~java
    public void turn()
    {
        setDirection(getDirection() + Location.HALF_RIGHT);
    }
    ~~~

    ​