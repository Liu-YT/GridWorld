 public class HelloWorld{
    public String sayHello()
	{
		return "Hello World";
	} 
    public int getInt(){
		return 5;
	}
    public static void main(String args[]){  
		HelloWorld world = new HelloWorld();
		System.out.println(world.sayHello());
	}  
} 