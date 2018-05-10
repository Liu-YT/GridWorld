1. What methods are implemented in Critter?

   `act`and`getActors`and`processActors`and`getMoveLocations`and`selectMoveLocation`and `makeMove`

   ```java
   // @file: info/gridworld/actor/Critter.java
   // @line: 38, 56, 71, 88, 104, 125
   public void act()

   public ArrayList<Actor> getActors()

   public void processActors(ArrayList<Actor> actors)

   public ArrayList<Location> getMoveLocations()

   public Location selectMoveLocation(ArrayList<Location> locs)

   public void makeMove(Location loc)
   ```

2. What are the five basic actions common to all critters when they act?

   ```java
   ArrayList<Actor> getActors();
   void processActors(ArrayList<Actor> actors);
   ArrayList<Location> getMoveLocations();
   Location selectMoveLocation(ArrayList<Location> locs);
   void makeMove(Location loc);
   ```

   ~~~java
   // @file: info/gridworld/actor/Critter.java
   // @line: 56, 71, 88, 104, 125
   public ArrayList<Actor> getActors()

   public void processActors(ArrayList<Actor> actors)

   public ArrayList<Location> getMoveLocations()

   public Location selectMoveLocation(ArrayList<Location> locs)

   public void makeMove(Location loc)
   ~~~

3. Should subclasses of Critter override the getActors method? Explain.

   Yes.Subclasses of Critter should override the getActors method if the locations of the actors the subclass want to get are different from the Critter class.

4. Describe the way that a critter could process actors.

   Could move the other critter away like the kingcrab or eat the other actor(remove them).

   ~~~java
   // @file: info/gridworld/actor/Critter.java
   // @line: 71~78
   public void processActors(ArrayList<Actor> actors)
   {
       for (Actor a : actors)
       {
           if (!(a instanceof Rock) && !(a instanceof Critter))
               a.removeSelfFromGrid();
       }
   }
   ~~~

5. What three methods must be invoked to make a critter move? Explain each of these methods.

   ```java
   ArrayList<Location> getMoveLocations();
   Location selectMoveLocation(ArrayList<Location> locs);
   void makeMove(Location loc);
   ```

   `getMoveLocation` : get the next locations which the citter can move

   `selectMoveLocation` : select a location randomly in the locations arraylist as the next location which the citter move

   `makeMove` : if the next location is null, then remove itself , otherwise, move to the next location.

   ~~~java
   // @file: info/gridworld/actor/Critter.java
   // @line: 38~47
   public void act()
   {
       if (getGrid() == null)
           return;
       ArrayList<Actor> actors = getActors();
       processActors(actors);
       ArrayList<Location> moveLocs = getMoveLocations();
       Location loc = selectMoveLocation(moveLocs);
       makeMove(loc);
   }
   ~~~

6. Why is there no Critter constructor?

   Because the Critter class extends  the Actor class, and the Actor class has a constructor which make the actor color is blue and direction is north,  and also we don't need the Critter have a special Initialization state, so that we can just use the constructor of the Actor to initial the critter.

   ~~~java
   // @file: info/gridworld/actor/Actor.java
   // @line: 39~45
   public Actor()
   {
       color = Color.BLUE;
       direction = Location.NORTH;
       grid = null;
       location = null;
   }
   ~~~

7. Why does act cause a ChameleonCritter to act differently from a Critter even though ChameleonCritter does not override act?

   In the method act,  it invokes a mthod processActors. In the ChameleonCritter class, the method processActors is overloaded. 

   ~~~java
   // @file: GridWorldCode/projects/critters/ChameleonCritter.java
   //@line: 36~45
   public void processActors(ArrayList<Actor> actors)
   {
       int n = actors.size();
       if (n == 0)
           return;
       int r = (int) (Math.random() * n);

       Actor other = actors.get(r);
       setColor(other.getColor());
   }
   ~~~

8. Why does the makeMove method of ChameleonCritter call super.makeMove?

   In the ChameleonCritter, the method makeMove is a little different from the method makeMove in Citter. The method makeMove in ChameleonCitter first just change the citter face direction and then the remaining steps are the same so it can call super.makeMove to use the method makeMove in Citter.

   ~~~java
   // @file: GridWorldCode/projects/critters/ChameleonCritter.java
   //@line: 50~54
   public void makeMove(Location loc)
   {
       setDirection(getLocation().getDirectionToward(loc));
       super.makeMove(loc);
   }
   ~~~

9. How would you make the ChameleonCritter drop flowers in its old location when it moves?

   the code:

   ~~~java
   public void makeMove(Location loc)
   {
     setDirection(getLocation().getDirectionToward(loc));
       super.makeMove(loc);
       Location oldLoc = getLocation();
       if(!oldLoc.equals(loc)) {
           Grid<Actor> gr = getGrid();
           Flower flower = new Flower(getColor());
           flower.putSelfInGrid(gr, loc);
       }
   }
   ~~~

   ~~~java
   // @file: GridWorldCode/projects/critters/ChameleonCritter.java
   //@line: 50~54
   public void makeMove(Location loc)
   {
     setDirection(getLocation().getDirectionToward(loc));
       super.makeMove(loc);
   }
   ~~~

10. Why doesn't ChameleonCritter override the getActors method?

    Becasue ChameleonCritter doesn't have some new things for getActor, it is the same as the Critter.

    ~~~java
    // @file: info/gridworld/actor/Citter.java
    //@line: 71~78
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (!(a instanceof Rock) && !(a instanceof Critter))
                a.removeSelfFromGrid();
        }
    }
    ~~~

11. Which class contains the getLocation method?

    The Actor class contains the getLocation method and all subclass of Actor also have this method.

    ~~~java
    // @file: info/gridworld/actor/Actor.java
    //@line: 102~105
    public Location getLocation()
    {
        return location;
    }
    ~~~

12. How can a Critter access its own grid?

    By the method getLocation which inherit from the class Actor.

    ~~~java
    // @file: info/gridworld/actor/Actor.java
    //@line: 92~95
    public Grid<Actor> getGrid()
    {
        return grid;
    }
    ~~~

13. Why doesn't CrabCritter override the processActors method?

    Becasue the CrabCritter eat all actor except the rock and the instance of Critter. It eat all actors which get by the method getActor, and it has the same behavior like the Citter so that CrabCritter doesn't need to override the processActors method.

    ~~~java
    // @file: info/gridworld/actor/Critter.java
    //@line: 71~78
    public void processActors(ArrayList<Actor> actors)
    {
        for (Actor a : actors)
        {
            if (!(a instanceof Rock) && !(a instanceof Critter))
                a.removeSelfFromGrid();
        }
    }
    ~~~

14. Describe the process a CrabCritter uses to find and eat other actors. Does it always eat all neighboring actors? Explain.

    By the method getActors,  A crab gets the actors in the three locations immediately in front, to its front-right and to its front-left . And then call the method processActor  and then eat the actors which can be eaten. No. It just eats the actors which immediately in front, to its front-right and to its front-left

    ~~~java
    // @file: GridWorldCode/projects/critters/CrabCritter.java
    //@line: 44~57
    public ArrayList<Actor> getActors()
    {
        ArrayList<Actor> actors = new ArrayList<Actor>();
        int[] dirs =
        { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
        for (Location loc : getLocationsInDirections(dirs))
        {
            Actor a = getGrid().get(loc);
            if (a != null)
                actors.add(a);
        }

        return actors;
    }
    ~~~

15. Why is the getLocationsInDirections method used in CrabCritter?

    Because a crab gets the actors in the three locations immediately in front, to its front-right and to its front-left . And then by this method, we can get the locations in this three direction and at the same time jugde whether the locations is valid in grid.

    ~~~java
    // @file: GridWorldCode/projects/critters/CrabCritter.java
    //@line: 101~114
    public ArrayList<Location> getLocationsInDirections(int[] directions)
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        Grid gr = getGrid();
        Location loc = getLocation();

        for (int d : directions)
        {
            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (gr.isValid(neighborLoc))
                locs.add(neighborLoc);
        }
        return locs;
    }    
    ~~~

16. If a CrabCritter has location (3, 4) and faces south, what are the possible locations for actors that are returned by a call to the getActors method?

    (4, 4), (4, 3), (4, 5)

    A crab gets the actors in the three locations immediately in front, to its front-right and to its front-left .

    ~~~java
    // @file: GridWorldCode/projects/critters/CrabCritter.java
    //@line: 47~48
    int[] dirs = { Location.AHEAD, Location.HALF_LEFT, Location.HALF_RIGHT };
    ~~~

17. What are the similarities and differences between the movements of a CrabCritter and a Critter?

    * Similarities: Randomly choose a location to move.

    * Differences: a CrabCritter only can move to the left or right, but the Critter can move to every direction(8).

      ~~~java
      // @file: GridWorldCode/projects/critters/CrabCritter.java
      //@line: 65~71
      int[] dirs = 
      	{ Location.LEFT, Location.RIGHT };
      for (Location loc : getLocationsInDirections(dirs))
          if (getGrid().get(loc) == null)
              locs.add(loc);
      return locs;

      // @file: GridWorldCode/framework/info/gridworld/actor/Critter.java
      //@line: 90
      return getGrid().getEmptyAdjacentLocations(getLocation());
      ~~~

18. How does a CrabCritter determine when it turns instead of moving?

    if the makeMove parameter is equal to the CrabCritter now location, the CrabCritter will turn.

    ~~~java
    // @file: GridWorldCode/projects/critters/CrabCritter.java
    //@line: 79~88
    if (loc.equals(getLocation()))
    {
        double r = Math.random();
        int angle;
        if (r < 0.5)
            angle = Location.LEFT;
        else
            angle = Location.RIGHT;
        setDirection(getDirection() + angle);
    }
    ~~~

19. Why don't the CrabCritter objects eat each other?

    The CrabCritter inherits the Critter, and the method processActor does't override in the class CrabCritter. So the CrabCritter could not eat rock and critter. And the CrabCritter is a instance of Critter, so the CrabCritter objects doesn't eat each other.

    ~~~java
    // @file: GridWorldCode/framework/info/gridworld/actor/Critter.java
    //@line: 75~76
    if (!(a instanceof Rock) && !(a instanceof Critter))
        a.removeSelfFromGrid();
    ~~~

    ​