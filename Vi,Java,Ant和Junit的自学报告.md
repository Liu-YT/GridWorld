# Vi,Java,Ant和Junit的自学报告

### ①Vi

​	The vi editor is the standard editor for all Unix and Linux systems and its power is not inferior to any of the latest text editors. It is greatly different with other common editors. In this editor, we don't need a mouse just with some commands that we can do the same job like other editors and much faster. In a standard editor, typing on the keyboard is enought to write something and see it on the screen. But Vi is not,the common  operating may be invalid. Vi has three states: command mode, insert mode and last line mode.

​	The  command mode controls the movement of the cursor on the screen, the deletion of characters, words, or lines, the movement to copy a section.We can enter the Insert mode by pressing "i" on the keyboard, or to the last line mode by pressing ":" on the keyboard. Last line mode saves or exits the file from the vi. In the last line mode, we can also set the editing environment, such as looking for a string, listing line numbers, etc.  And there are many convenient and useful operations.

1. Insert mode variations

   - `a ` →  insert after the cursor
   - `o ` →  insert a new line after the current one
   - `O`  →  insert a new line before the current one
   - `cw` →  replace from the cursor to the end of the word

2. Basic moves

   - `0 ` →  go to the first column
   - `^ ` →  go to the first non-blank character of the line
   - `$ ` →  go to the end of line
   - `g_` →  go to the last non-blank character of line
   - `/pattern` → search for pattern and we can press "n" until we find the keyword you want. And the command `?pattern` is the same as this command
   - `h` → move the cursor one character left
   - `l` → move the cursor one character right
   - `k` → move cursor up one line
   - `j` → move cursor down one line

3. Copy/Paste/Delete

   - `p` →  paste before, remember p is paste after current position
   - `yy` →  copy the current line, easier but equivalent to ddP
   - `x` → each time you press, delete the "after" character at the cursor position.
   - `#x` →  for example, "6x" means to delete the "after" 6 characters at the cursor position
   - `X` → each time you press, delete the "before" character at the cursor position
   - `#X` → for example, "20X" means to delete the "before" 20 characters at the cursor position
   - `dd` →  delete the line where the cursor is
   - `#dd` →   Delete line # from the line where the cursor is

4. Undo/Redo

   - `u` → undo
   - `<Ctrl+r>` → redo

5. Load/Save/Quit/Change File (Buffer)

   - `:e <path/to/file>` → open

   - `:w` → save

   - `:saveas <path/to/file>` → save to `<path/to/file>`

   - `:x`, `ZZ` or `:wq` → save and quit (`:x` only save if necessary)

   - `:q!` → quit without saving, also: `:qa!` to quit even if there are modified hidden buffers.

   - `:bn` (resp. `:bp`) → show next (resp. previous) file (buffer)

     The insert mode only in the this mode, that we can do text input, and press the "ESC" key to return to the command line mode. 

     Vi is hard to learn, but incredible to use. The first step we learn vi is understand the command. We can learn the commands while doing the related operations in vi editor which will have a deeper impression about it.

------

### ②Java

- Installing and configuring Java JDK according the blog guide.

- Learn the basic grammar of Java. I think that the basic grammar of Java is greatly similar to the basic grammar of C++.  Java also has encapsulation, inheritance, polymorphism, abstract these features. But there are also many different. The main function is a static function and the Classname of the  class which contains the main function and the save filename should be the same. Like if the declaration class is named Hello and it's saved as hello.java. The name of the class (Classname) is specified in the source file. After the source file is compiled, a classname.class file can be generated in the directory where the source code is located.

- Then learn to try to write a java program. Like a simple program print `Hello World`.

- Then learn how to compile and run a Java program. There are two commands we need to know. 

  - `javac filename.java`
  - `java filename` 

  Javac is a jdk compiler. We can also use the hello.java which is a simple program as an example. Just when we entered `javac Hello.java`, it meant to compile the source file Hello.java into a bytecode, which is the Hello.class file. The Java command is an interpreter of java. `java Hello` means to put the compiled bytecode on the interpreter. From which we can also see the implementation of the java language, which is explained first after compilation

- The above study of Java is very basic.  Althought the basic gammar of Java is similar to the basic grammar of C++, there also many greatly different things in Java. So we need a deeper study of Java.

------

### ③Ant

1. Ant is a cross-platform component tool under the Apache Foundation that enables automated build and deployment of projects.

2. Ant's key elements project, target, property, and task

   - Project. The project element is the root element of the Ant artifact file, and the Ant artifact file should contain at least one project element, or an error will occur. Under each project element, there can be multiple target elements. Next, show the reader the attributes of the project element

     - name attribute
       Used to specify the name of the project element.

     - default attribute

       Used to specify the name of the target that will be executed when the project is executed by default.

     - basedir attribute
       Used to specify the location of the base path. When this attribute is not specified, the attached directory of the Ant component file is used as the base directory.

   - Target. It is the basic execution unit of Ant and it can contain one or more specific tasks. Multiple targets can have interdependencies. It has the following properties:

     - name attribute

       Specifies the name of the target element, which is unique within a project element. We can specify a target by specifying the name of the target element.

     - depends attribute

       Used to describe the dependencies between the targets. If there is a dependency relationship with multiple targets, they need to be separated by ",". Ant executes each target one by one in the order in which the target attribute appears in the depends attribute. The target that is being relied on will be executed first.

     - if attribute

       It is used to verify whether the specified attribute exists. If it does not exist, the target will not be executed.

     - unless attribute

       The function of this property is exactly the opposite of the function of the if property. It is also used to verify the existence of the specified property. If it does not exist, the target will be executed.

     - description attribute

       This attribute is a brief description and description of the target function.

   - Property. This element can be viewed as a parameter or parameter definition. The properties of the project can be set via the property element, and can also be set outside of Ant. To import a file externally, such as the build.properties file, you can import it with the following content: `<property file= "build.properties"/> `The property element can be used as a property value for a task. In the task is achieved by placing the property name between "${" and "}" and placing it in the task property value

3. An example of ant

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?> 
   <project name="HelloWorld" default="run" basedir=".">
   <property name="src" value="src"/>
   <property name="dest" value="classes"/>
   <property name="hello_jar" value="helloworld.jar"/>
   <target name="init">
      <mkdir dir="${dest}"/>
   </target>
   <target name="compile" depends="init">
      <javac srcdir="${src}" destdir="${dest}"/>
   </target>
   <target name="build" depends="compile">
      <jar jarfile="${hello_jar}" basedir="${dest}"/>
   </target>
   <target name="run" depends="build">
      <java classname="HelloWorld" classpath="${hello_jar}"/>
   </target>
   <target name="clean">
      <delete dir="${dest}" />
      <delete file="${hello_jar}" />
   </target>
   <target name="rerun" depends="clean,run">
      <ant target="clean" />
      <ant target="run" />
   </target>
   </project>
   ```

4. Run a jar

   - `java -jar classname.jar `

     But this type need to have another operation in xml.

     ```xml
     <manifest>
           <attribute name = "Main-Class" value = "classname" />
     </manifest>

     ```

   - `java -cp classname.jar classname`

   ​

------

### ④Junit

1. Configure junit environment path

    `no root`

```
   vim ~/.bashrc
   Add at the end of the file:
   export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_91
   export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:*/junit-4.9.jar:$CLASSPATH 
   export PATH=$JAVA_HOME/bin:$JAVA_HOME/jre/bin:$PATH:$HOME/bin
   '*' -- mean the path of your junit-4.9.jar
   Exit after saving. Among them, the junit path is set by itself, and it can be changed according to that format.
   source ~/.bashrc
```

2. Learn how to use junit.

   - Including necessary Package

     ```java
     import junit.framework.Test;
     import junit.framework.TestCase;
     import junit.framework.TestSuite;
     ```

   - Test class declaration

     The test class is a separate class without any parent class. The name of the test class can also be named arbitrarily, without any limitations. So we can't judge whether it is a test class by class declaration. The difference between it and the ordinary class lies in the declaration of its internal methods. 

   - Create an object to test

     Which class you need to test, then create an object of that class. For example to test the HelloWorld class, we must create a HelloWorld object

     ```java
     HelloWorld world = new HelloWorld();
     ```

   - Write a simple test method

     According the blog and other tutorial, try to write a simple test class of HellWord.

   - Integrate tests into my build using Ant

     ```xml
     <target name="runtests" depends="compile" if="junit.present">
     		<junit>
     			<classpath>
     				<fileset dir="/home/vinzor/Downloads">
     					<include name="junit-4.9.jar" />
     				</fileset>
     				<pathelement path="${dest}" />
     			</classpath>
     			<batchtest>
     				<fileset dir="${dest}">
     					<include name="helloworldTest.class" />
     				</fileset>
     			</batchtest>
     		</junit>
         </target>
     ```

     ​