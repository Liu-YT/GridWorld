import junit.framework.Test;

import junit.framework.TestCase;

import junit.framework.TestSuite;
public class HelloWorldTest extends TestCase{
  
    public HelloWorldTest(String name){
        super(name);
    }

    public static void main(String args[]){
        junit.textui.TestRunner.run(HelloWorldTest.class);
    }
     
    public void testSayHello(){
        HelloWorld world = new HelloWorld();
        assert (world != null);
        assertEquals("HelloWorld",  world.sayHello() );
    }


    public void testgetInt()
    {
        HelloWorld world = new HelloWorld();
        assertEquals(6,  world.getInt() );
    }
    
}