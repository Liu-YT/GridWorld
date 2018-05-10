# junit单元测试

######注：Jumper默认向上跳

1. 测试当Jumper移动方向的第二个格子存在Actor的情况

   * Actor为石头的时候--`不能移动`

     ~~~java
     @Test
     public void testCanJumpFir() {
         ActorWorld world = new ActorWorld();
         //Create a new jumper.
         Jumper jumper = new Jumper();
         Rock rock = new Rock();
         world.add(new Location(2, 3), jumper);
         world.add(new Location(0, 3), rock);
         assertEquals(false,  jumper.canJump());
     }
     ~~~

     ​

   * Actor为花的时候--`正常移动`

     ~~~java
     @Test
     public void testCanJumpSec() {
         ActorWorld world = new ActorWorld();
         //Create a new jumper.
         Jumper jumper = new Jumper();
         Flower flower = new Flower();
         world.add(new Location(2, 3), jumper);
         world.add(new Location(0, 3), flower);
         assertEquals(true,  jumper.canJump());
     }
     ~~~

2. 测试当Jumper移动方向的第一个格子存在Actor的情况

   * Actor为石头--`正常移动`

     ~~~java
     @Test
     public void testCanJumpFou() {
         ActorWorld world = new ActorWorld();
         //Create a new jumper.
         Jumper jumper = new Jumper();
         Rock rock = new Rock();
         world.add(new Location(2, 3), jumper);
         world.add(new Location(1, 3), rock);
         assertEquals(true, jumper.canJump());
     }
     ~~~

   * Actor为花--`正常移动`

     ~~~java
     @Test

     public void testCanJumpFiv() {
         ActorWorld world = new ActorWorld();
         //Create a new jumper.
         Jumper jumper = new Jumper();
         Flower flower = new Flower();
         world.add(new Location(2, 3), jumper);
         world.add(new Location(1, 3), flower);
         assertEquals(true, jumper.canJump());
     }
     ~~~

3. 测试当Jumper移动超出Grid的情况

   ~~~java
   @Test
   //if the location two cells in front of the jumper is out of the grid
   public void testCanJumpThr() {
       ActorWorld world = new ActorWorld();
       //Create a new jumper.
       Jumper jumper = new Jumper();
       world.add(new Location(0, 9), jumper);
       assertEquals(false,  jumper.canJump());
   }
   ~~~

   ​