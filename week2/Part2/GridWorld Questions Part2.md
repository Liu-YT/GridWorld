1. What is the role of the instance variable sideLength?

   The instance variable sideLength defines the numberi of step which the bug can move on each side.

   //@file: GridWorldCode/projects/boxBug/BoxBug.java

   //@line: 45~49

   ~~~java
   if (steps < sideLength && canMove())
   {
       move();
       steps++;
   }
   ~~~

2. What is the role of the instance variable steps?

   The instance variable steps records the number of step the bug has moved on the current side.

   //@file: GridWorldCode/projects/boxBug/BoxBug.java

   //@line: 45~55

   ~~~java
   if (steps < sideLength && canMove())
   {
       move();
       steps++;
   }
   else
   {
       turn();
       turn();
       steps = 0;
   }
   ~~~

3. Why is the turn method called twice when steps becomes equal to sideLength?

   Because one turn only turn 45 degree, and the bug need to turn 90 degree to next side. So the bug need to turn two times.

   //@file: GridWorldCode/projects/boxBug/BoxBug.java

   //@line: 45~55

   ~~~java
   if (steps < sideLength && canMove())
   {
       move();
       steps++;
   }
   else
   {
       turn();
       turn();
       steps = 0;
   }
   ~~~

4. Why can the move method be called in the BoxBug class when there is no move method in the BoxBug code?

   Becasue the BoxBug class is extend for the bug class, and the bug class has a public method move.

   //@file: GridWorldCode/projects/boxBug/BoxBug.java

   //line:25

   ~~~java
   public class BoxBug extends Bug
   ~~~

   //@file: GridWorldCode/framework/info/gridworld/actor/Bug.java

   //line:71

   ~~~java
   public void move()
   ~~~

5. After a BoxBug is constructed, will the size of its square pattern always be the same? Why or why not?

   Yes. Once a BoxBug is constructed, the size of its square pattern always be the same.

   //@file: GridWorldCode/projects/boxBug/BoxBug.java

   //line:35~38

   ~~~java
   public BoxBug(int length)
   {
       steps = 0;
       sideLength = length;
   }
   ~~~

6. Can the path a BoxBug travels ever change? Why or why not?

   Yes.If a anthor actor(like rock) is in front of the bug  which makes the bug can't move then the BoxBug travels will change.

   //@file: GridWorldCode/projects/boxBug/BoxBug.java

   //@line: 45~55

   ~~~java
   if (steps < sideLength && canMove())
   {
       move();
       steps++;
   }
   else
   {
       turn();
       turn();
       steps = 0;
   }
   ~~~

7. When will the value of steps be zero?

   * Initialization.

     //@file: GridWorldCode/projects/boxBug/BoxBug.java

     //@line: 34~38

     ~~~java
     public BoxBug(int length)
     {
         steps = 0;
         sideLength = length;
     }
     ~~~

   * The steps is equal to the sideLength

     //@file: GridWorldCode/projects/boxBug/BoxBug.java

     //@line: 45~55

     ~~~java
     if (steps < sideLength && canMove())
     {
         move();
         steps++;
     }
     else
     {
         turn();
         turn();
         steps = 0;
     }
     ~~~

   * The bug can't move and need to have a new BugBox path.

     //@file: GridWorldCode/projects/boxBug/BoxBug.java

     //@line: 45~55

     ~~~java
     if (steps < sideLength && canMove())
     {
         move();
         steps++;
     }
     else
     {
         turn();
         turn();
         steps = 0;
     }
     ~~~

     ​