import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class helloworldTest extends TestCase {
	public helloworldTest(String name) {
		super(name);
	}

	public static void main(String args[]) {
        junit.textui.TestRunner.run(helloworldTest.class);
    }

    public void testSayHello() {
        helloworld world = new helloworld();
        assert( world!=null );
        assertEquals("Hello World!",  world.sayHello());
    }
}