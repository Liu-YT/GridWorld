1. Where is the isValid method specified? Which classes provide an implementation of this method?

   The method isValid us specified in the Grid.

   The UnboundedGrid class and the BoundedGrid class provide an implementation of this method.

   ~~~java
   //@file: info/gridworld/grid/Grid.java
   //@line: 50
   boolean isValid(Location loc);

   //@file: info/gridworld/grid/UnboundedGrid.java
   //@line: 53~56
   public boolean isValid(Location loc)
   {
       return true;
   }

   //@file: info/gridworld/grid/BoundedGrid.java
   //@line: 60~64

   public boolean isValid(Location loc)
   {
       return 0 <= loc.getRow() && loc.getRow() < getNumRows()
           && 0 <= loc.getCol() && loc.getCol() < getNumCols();
   }
   ~~~

2. Which AbstractGrid methods call the isValid method? Why don't the other methods need to call it?

   The method getValidAdjacentLocations. Because the other method use this method either directly or indirectly. So they don't need to call the method isValid again.

   ~~~java
   //@file: info/gridworld/grid/AbstractGrid.java
   //@line: 44~45
   if (isValid(neighborLoc))
       locs.add(neighborLoc);
   ~~~

3. Which methods of the Grid interface are called in the getNeighbors method? Which classes provide implementations of these methods?

   The method getOccupiedAdjacentLocations and the method get.  The AbstractGrid class provides implementations of the method getOccupiedAdjacentLocations. The BoundedGrid class and the UnboundedGrid class provide implementations the method get.

   ~~~java
   //@file: info/gridworld/grid/AbstractGrid.java
   //@line: 31~32
   for (Location neighborLoc : getOccupiedAdjacentLocations(loc))
       neighbors.add(get(neighborLoc));

   //@file: info/gridworld/grid/AbstractGrid.java
   //@line: 62~71
   public ArrayList<Location> getOccupiedAdjacentLocations(Location loc)
   {
       ArrayList<Location> locs = new ArrayList<Location>();
       for (Location neighborLoc : getValidAdjacentLocations(loc))
       {
           if (get(neighborLoc) != null)
               locs.add(neighborLoc);
       }
       return locs;
   }

   //@file: info/gridworld/grid/BoundedGrid.java
   //@line: 85
   public E get(Location loc)
       
   //@file: info/gridworld/grid/UnboundedGrid.java
   //@line: 66
   public E get(Location loc)
   ~~~

4. Why must the get method, which returns an object of type E, be used in the getEmptyAdjacentLocations method when this method returns locations, not objects of type E?

   Becasue the method get return a reference to the object stored in the grid at the given location or null if there is no object so the method get have to returns an object of type E. And the method getEmptyAdjacentLocations use the get method and then judge the return result wheather is null.  By this way, we can get the right empty  place.

   ~~~java
   //@file: info/gridworld/grid/AbstractGrid.java
   //@line: 51~60
   public ArrayList<Location> getEmptyAdjacentLocations(Location loc)
   {
       ArrayList<Location> locs = new ArrayList<Location>();
       for (Location neighborLoc : getValidAdjacentLocations(loc))
       {
           if (get(neighborLoc) == null)
               locs.add(neighborLoc);
       }
       return locs;
   }
   ~~~

5. What would be the effect of replacing the constant Location.HALF_RIGHT with Location.RIGHT in the two places where it occurs in the getValidAdjacentLocations method?

   The max number of valid adjacent locations decreases from 8 to 4. Only have north, west, sourth, east.

   ~~~java
   //@file: info/gridworld/grid/AbstractGrid.java
   //@line: 40~47
   int d = Location.NORTH;
   for (int i = 0; i < Location.FULL_CIRCLE / Location.HALF_RIGHT; i++)
   {
       Location neighborLoc = loc.getAdjacentLocation(d);
       if (isValid(neighborLoc))
           locs.add(neighborLoc);
       d = d + Location.HALF_RIGHT;
   }
   ~~~

6. What ensures that a grid has at least one valid location?

   The constructor for the BoundedGrid.

   ~~~java
   //@file: info/gridworld/grid/BoundedGrid.java
   //@line: 39~46
   public BoundedGrid(int rows, int cols)
   {
       if (rows <= 0)
           throw new IllegalArgumentException("rows <= 0");
       if (cols <= 0)
           throw new IllegalArgumentException("cols <= 0");
       occupantArray = new Object[rows][cols];
   }
   ~~~

   ​

7. How is the number of columns in the grid determined by the getNumCols method? What assumption about the grid makes this possible?

   occupantArray[0].length

   The BoundedGrid at least has one row and one column.

   ~~~java
   //@file: info/gridworld/grid/BoundedGrid.java
   //@line: 53~58
   public int getNumCols()
   {
       // Note: according to the constructor precondition, numRows() > 0, so
       // theGrid[0] is non-null.
       return occupantArray[0].length;
   }
   ~~~

   ​

8. What are the requirements for a Location to be valid in a BoundedGrid? In the next four questions, let r = number of rows, c = number of columns, and n = number of occupied locations.

   the requirements:

   * the row of the location < the number  of rows 
   * the row of the location >= 0 
   * the col of the location < the number of colums
   * the col of the location > = 0

   ~~~java
   //@file: info/gridworld/grid/BoundedGrid.java
   //@line: 60~64
   public boolean isValid(Location loc)
   {
       return 0 <= loc.getRow() && loc.getRow() < getNumRows()
           && 0 <= loc.getCol() && loc.getCol() < getNumCols();
   }
   ~~~

9. What type is returned by the getOccupiedLocations method? What is the time complexity (Big-Oh) for this method?

   return type:  `ArrayList<Location>`

   time complexity : `O(r * c)`

   ~~~java
   //@file: info/gridworld/grid/BoundedGrid.java
   //@line: 66~83
   public ArrayList<Location> getOccupiedLocations()
   {
       ArrayList<Location> theLocations = new ArrayList<Location>();

       // Look at all grid locations.
       for (int r = 0; r < getNumRows(); r++)
       {
           for (int c = 0; c < getNumCols(); c++)
           {
               // If there's an object at this location, put it in the array.
               Location loc = new Location(r, c);
               if (get(loc) != null)
                   theLocations.add(loc);
           }
       }

       return theLocations;
   }
   ~~~

10. What type is returned by the get method? What parameter is needed? What is the time complexity (Big-Oh) for this method?

   return type: `E`

   parameter: `a Location object`

   time complexity: `O(1)`

   ~~~java
   //@file: info/gridworld/grid/BoundedGrid.java
   //@line: 85~91
   public E get(Location loc)
   {
       if (!isValid(loc))
           throw new IllegalArgumentException("Location " + loc
                                              + " is not valid");
       return (E) occupantArray[loc.getRow()][loc.getCol()]; // unavoidable warning
   }
   ~~~

11. What conditions may cause an exception to be thrown by the put method? What is the time complexity (Big-Oh) for this method?

    exception cause conditions :

    * the location is not valid(not in the grid)
    * the object is null

    time complexity: `O(1)`

    ~~~java
    //@file: info/gridworld/grid/BoundedGrid.java
    //@line: 93~105
    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                                               + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }
    ~~~

12. What type is returned by the remove method? What happens when an attempt is made to remove an item from an empty location? What is the time complexity (Big-Oh) for this method?

    return type: `E`

    if the location is in the grid(is valid), then there is no error.Otherwise, there will have a exception.

    time complexity: `O(1)`

    ~~~java
    //@file: info/gridworld/grid/BoundedGrid.java
    //@line: 107~117
    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                                               + " is not valid");

        // Remove the object from the grid.
        E r = get(loc);
        occupantArray[loc.getRow()][loc.getCol()] = null;
        return r;
    }
    ~~~

13. Based on the answers to questions 4, 5, 6, and 7, would you consider this an efficient implementation? Justify your answer.

    Yes, I think this an efficient implementation. Because the time complexity of the methods above is very low.  The time complexity of the method getOccupiedLocations  is O(r*c), and the time complexity of methods put, get, remove is O(1).

14. Which method must the Location class implement so that an instance of HashMap can be used for the map? What would be required of the Location class if a TreeMap were used instead? Does Location satisfy these requirements?

    HashMap: the Location class implement the methods hashCode and equals.

    TreeMap: The Location implements the Comparable interface. And the compareTo method need to be implemented. The TreeMap requires keys of the map to be Comparable

    Yes, the Location class satidfy these requirements.

    ~~~java
    //@file: info/gridworld/grid/Location.java
    //@line: 28
    public class Location implements Comparable

    //@line: 218~221
    public int hashCode()
    {
        return getRow() * 3737 + getCol();
    }

    //@line: 205~212
    public boolean equals(Object other)
    {
        if (!(other instanceof Location))
            return false;

        Location otherLoc = (Location) other;
        return getRow() == otherLoc.getRow() && getCol() == otherLoc.getCol();
    }
    ~~~

15. Why are the checks for null included in the get, put, and remove methods? Why are no such checks included in the corresponding methods for the BoundedGrid?

    * In the UnboundedGrid, the data is stored in a HashMap, and the key is location and the value is the object in the corresponding location. And the key of a HashMap is cannot be null. So that we need to check for the parameter location is not null.
    * In the BoundedGrid, the data is stored in an array. And we check the location whether it is valid in thoese methods. So if it is null, an exception will also be thrown.

16. What is the average time complexity (Big-Oh) for the three methods: get, put, and remove? What would it be if a TreeMap were used instead of a HashMap?

    ​The average time complexity for the three methods: get, put, and remove is O(1).

    If a TreeMap were used instead of a HashMap, the average time complexity will change from O(1) to O(logn)

17. How would the behavior of this class differ, aside from time complexity, if a TreeMap were used instead of a HashMap?

    For the method getOccupieLocations, when using the HashMap, the return occupants mostly in diffrerent order because the keys in a HashMap are placed in a hash table and then we get it by the key(location). But if we use a TreeMap, we store the data in a binary tree and traverses this tree in an stable order, maybe inorder traversal.

18. Could a map implementation be used for a bounded grid? What advantage, if any, would the two-dimensional array implementation that is used by the BoundedGrid class have over a map implementation?

    Yes, a map implementation could be used for a bounded grid. If we use a HashMap, the average time complexity for getOccupiedLocations would be O(n) and the average time complexity for the methods get, put and remove is O(1). If we use a TreeMap, the average time complexity for getOccupiedLocations would be O(n) and the average time complexity for the methods get, put and remove is O(logn) which is litte bigger than the HashMap. But using a two-dimensional array to store also have a advantage if there are so many items in the grid. The array just need to store the object in the grid but the map need to store the location and the corresponding object.

**[Coding Exercises]**

2.Consider using a HashMap or TreeMap to implement the SparseBoundedGrid. How could you use the UnboundedGrid class to accomplish this task? Which methods of UnboundedGrid could be used without change?

According the implementation of UnboundedGrid, finishing the SpareBoundedGrid.

The methods could be used without change: 

* public ArrayList<Location> getOccupiedLocations()
* public E get(Location loc)
* public E put(Location loc, E obj)
* public E remove(Location loc)



Fill in the following chart to compare the expected Big-Oh efficiencies for each implementation of the SparseBoundedGrid.

Let r = number of rows, c = number of columns, and n = number of occupied locations

| 方法                           | `SparseGridNode`版 | `LinkedList<OccupantInCol>`版 | `HashMap`版 | `TreeMap`版 |
| :----------------------------- | :----------------: | :---------------------------: | :---------: | :---------: |
| `getNeighbors`                 |        O(c)        |             O(c)              |    O(1)     |   O(logn)   |
| `getEmptyAdjacentLocations`    |        O(c)        |             O(c)              |    O(1)     |   O(logn)   |
| `getOccupiedAdjacentLocations` |        O(c)        |             O(c)              |    O(1)     |   O(logn)   |
| `getOccupiedLocations`         |       O(c+n)       |            O(r+n)             |    O(n)     |    O(n)     |
| `get`                          |        O(c)        |             O(c)              |    O(1)     |   O(logn)   |
| `put`                          |        O(c)        |             O(c)              |    O(1)     |   O(logn)   |
| `remove`                       |        O(c)        |             O(c)              |    O(1)     |   O(logn)   |

- 3.Consider an implementation of an unbounded grid in which all valid locations have non-negative row and column values. The constructor allocates a 16 x 16 array. When a call is made to the put method with a row or column index that is outside the current array bounds, double both array bounds until they are large enough, construct a new square array with those bounds, and place the existing occupants into the new array.

Implement the methods specified by the Grid interface using this data structure. What is the Big-Oh efficiency of the get method? What is the efficiency of the put method when the row and column index values are within the current array bounds? What is the efficiency when the array needs to be resized?

- The method get  Big-Oh:`O(1)`
-  the efficiency of the put method when the row and column index values are within the current array bounds is `O(1)`
- The method resize Big-Oh:
  * O(n2), n is the number of rows or col of the current array(before resize).