# Inheritance and Constructors

## Learning Goals

- Discuss constructors and inheritance
- Call a superclass constructor from a subclass

## Code Along

Fork and clone with lesson.  You will adapt classes
to add constructors.

## Initialization State Using Inherited Methods 

Recall the `Person` class hierarchy from the lesson on method overriding.
`Person` extends `Object` by default as no other superclass is listed.

![person uml hierarchy](https://curriculum-content.s3.amazonaws.com/6677/pillars/person_hierarchy_constructors.png)

The `Person` class defines fields `name` and `age`, along with methods that access and mutate
the fields.  
```java
public class Person {

    private String name;
    private int age;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public void celebrateBirthday() { age++; }
    public String getWeekendPlans() { return "sleep late and relax all day"; }
    @Override
    public String toString() { return "name=" + name + ", age=" + age; }
}
```

The subclass `Teacher` overrides `getWeekendPlans()`:

```java
public class Teacher extends Person {
    @Override
    public String getWeekendPlans() { return "grade homework assignments"; }
}
```

The subclass `Student` adds a `favoriteSubject` field and get/set methods, and overrides `getWeekendPlans` and `toString`:

```java
public class Student extends Person {
    private String favoriteSubject;

    public String getFavoriteSubject() { return favoriteSubject; }
    public void setFavoriteSubject(String favoriteSubject) { this.favoriteSubject = favoriteSubject; }

    @Override
    public String getWeekendPlans() { return "wake up early and study all day"; }

    @Override
    public String toString() { return super.toString() + ", favoriteSubject=" + favoriteSubject ; }
}
```

The `Main` driver class calls the no-args constructors `Person()`, `Teacher()`,
and `Student()`, and then call the various setter methods to initialize the object state:

```java
public class Main {
    public static void main(String[] args) {
        //Person instance
        System.out.println("Creating Person");
        Person person = new Person();
        person.setName("Kai");
        person.setAge(32);
        System.out.println("Person state:" + person);  

        //Teacher instance, inherits fields and methods from Person
        System.out.println("Creating Teacher");
        Teacher teacher = new Teacher();
        teacher.setName("Tal");
        teacher.setAge(40);
        System.out.println("Teacher state:" + teacher);   

        //Student instance, inherits fields and methods from Person
        System.out.println("Creating Student");
        Student student = new Student();
        student.setName("Hao");
        student.setAge(24);
        student.setFavoriteSubject("Java programming");
        System.out.println("Student state:" + student);   
    }
}
```

The program prints as output:

```text
Creating Person
Person state:name=Kai, age=32
Creating Teacher
Teacher state:name=Tal, age=40
Creating Student
Student state:name=Hao, age=24, favoriteSubject=Java programming
```

## Default No-Args Constructors

Recall that Java generates a no-args constructor if a class does not explicitly
define a constructor.  Let's update each class to add an
explicit no-args constructor with a print
statement to see how objects are created based on inheritance.

Add a constructor to `Person`:

```java
public class Person {

    private String name;
    private int age;

    public Person() {
        System.out.println("Person() constructor");
    }
    
    // other methods
    ...
}
```

Add a constructor to `Teacher`:

```java
public class Teacher extends Person {

    public Teacher() {
        System.out.println("Teacher() constructor");
    }

    // other methods

}
```

Add a constructor to `Student`:

```java
public class Student extends Person {
    private String favoriteSubject;

    public Student() {
        System.out.println("Student() constructor");
    }

    // other methods

}
```

Executing `Main.main()` results in the output:

![constructor output](https://curriculum-content.s3.amazonaws.com/6677/pillars/constructor_output.png)

The call `new Person()` causes the print statement in the `Person` constructor to execute
as expected.  But notice the calls `new Teacher()` and `new Student()` also cause
the `Person()` constructor to execute!  

## `super()`

In Java, **every** constructor method must invoke a superclass constructor as the first statement,
either implicitly or explicitly.  We can call a no-args superclass constructor using `super()`,
as shown below.  Java implicitly adds this statement as the first line of code if we leave it out.

```java
public class Person {

    private String name;
    private int age;

    public Person() {
        super();   //calls Object() constructor
        System.out.println("Person() constructor");
    }
    
    // other methods
    ...
}
```

```java
public class Teacher extends Person {

    public Teacher() {
        super();   //calls Person() constructor
        System.out.println("Teacher() constructor");
    }

    // other methods

}
```

```java
public class Student extends Person {
    private String favoriteSubject;

    public Student() {
        super();   //calls Person() constructor
        System.out.println("Student() constructor");
    }

    // other methods
}
```

Let's add a breakpoint at the call `new Teacher()` to watch how the superclass constructor
`Person()` is called.

![constructor breakpoint](https://curriculum-content.s3.amazonaws.com/6677/pillars/breakpoint_constructor.png)


Press "Step Into" to execute the statement `Teacher teacher = new Teacher()`.

The call stack shows a new frame named `<init>`, which represents the `Teacher()`
constructor method.  We can see control is transferred to the first statement
inside the `Teacher()` constructor, which is the call `super();`.

![teacher constructor](https://curriculum-content.s3.amazonaws.com/6677/pillars/teacher_constructor.png)

Press "Step Into" to execute the statement `super();`.

The call stack shows another new frame named `<init>`, which represents the `Person()`
constructor method.  We can see control is transferred to the first statement
inside the `Person()` constructor, which again is the call `super();`.

![person constructor](https://curriculum-content.s3.amazonaws.com/6677/pillars/person_constructor.png)

Executing the call `super();` within the `Person` constructor calls the `Object`
constructor, which we can't step into.  We can "Step Over" the `super();` statement.
You can continue stepping over the print statement and return 
to the `Teacher` constructor. Continue stepping back to the `main` method
or press the "Resume Program" button to execute the rest of the code.

## Calling superclass constructors with arguments

The no-args constructors initialize the fields to their default values.
Let's update `Person` to add a constructor with parameters to initialize
the fields to specific values.  We'll add a print statement in the constructor
as well just so we can see when it is called, versus the no-args constructor.
Defining multiple constructors is an example of **method overloading**,
as the class has two methods with the same name but different parameter lists.

```java
public class Person {

    private String name;
    private int age;

    public Person() {
        super();   //calls Object() constructor
        System.out.println("Person() constructor");
    }

    public Person(String name, int age) {
        super();   //calls Object() constructor
        System.out.println("Person(String name, int age) constructor");
        this.name = name;
        this.age = age;
    }

    // other methods
}
```

We'll update the `main` to call this constructor to initialize the `Person`
object's state:

```java
public class Main {

    public static void main(String[] args) {
        //Person instance
        System.out.println("Creating Person");
        Person person = new Person("Kai", 32);
        System.out.println("Person state:" + person); 

        //Teacher instance, inherits fields and methods from Person
        //...
    }
}
```

Running the code shows the `Person(String name, int age)` constructor
executing the call `new Person("Kai", 32);`.

However, notice the no-args `Person()` constructor is still called by calls
`new Teacher()` and `new Student()`.  That is because the first line
of code in both constructors is `super()`, which calls `Person()`.

![person args constructor](https://curriculum-content.s3.amazonaws.com/6677/pillars/person_args_constructor.png)


It would be nice to update the `main` method to call constructors with arguments
to create the `Teacher` and `Student` objects as shown below.
Both constructors need to take values for
the inherited `name` and `age` fields. The `Student` constructor
will also need a value for the `favoriteSubject` field:

```java
public class Main {

    public static void main(String[] args) {
        //Person instance
        System.out.println("Creating Person");
        Person person = new Person("Kai", 32);
        System.out.println("Person state:" + person);  

        //Teacher instance, inherits fields and methods from Person
        System.out.println("Creating Teacher");
        Teacher teacher = new Teacher("Tal", 40);
        System.out.println("Teacher state:" + teacher);  

        //Student instance, inherits fields and methods from Person
        System.out.println("Creating Student");
        Student student = new Student("Hao", 24, "Java programming");
        System.out.println("Student state:" + student);  
    }
}
```

Update the `Teacher` class to add a constructor with parameters
for `name` and `age`.  Notice the first statement is a call to the
superclass constructor `super(name, age)`, which will assign the
parameter values to the instance variables defined in `Person`.

```java
public Teacher(String name, int age) {
    super(name, age);  //calls Person(String name, int age) constructor
    System.out.println("Teacher(String name, int age) constructor");
}
```

The updated `Teacher` class looks like this:

```java
public class Teacher extends Person {

    public Teacher() {
        super();   //calls Person() constructor
        System.out.println("Teacher() constructor");
    }
    
    public Teacher(String name, int age) {
        super(name, age);  //calls Person(String name, int age) constructor
        System.out.println("Teacher(String name, int age) constructor");
    }

    @Override
    public String getWeekendPlans() {
        return "grade homework assignments";
    }
}
```


We'll also add a constructor with arguments to `Student`.
The constructor calls the superclass constructor, then initializes
the additional field `favoriteSubject`.

```java
public Student(String name, int age, String favoriteSubject) {
    super(name, age);   //calls Person(String name, int age) constructor
    System.out.println("Student(String name, int age, String favoriteSubject) constructor");
    this.favoriteSubject = favoriteSubject;
}
```

The `Student` class looks like this:

```java
public class Student extends Person {
    private String favoriteSubject;

    public Student() {
        super();   //calls Person() constructor
        System.out.println("Student() constructor");
    }

    public Student(String name, int age, String favoriteSubject) {
        super(name, age);   //calls Person(String name, int age) constructor
        System.out.println("Student(String name, int age, String favoriteSubject) constructor");
        this.favoriteSubject = favoriteSubject;
    }

    public String getFavoriteSubject() {
        return favoriteSubject;
    }

    public void setFavoriteSubject(String favoriteSubject) {
        this.favoriteSubject = favoriteSubject;
    }

    @Override
    public String getWeekendPlans() {
        return "wake up early and study all day";
    }

    @Override
    public String toString() {
        return super.toString() +
                ", favoriteSubject=" + favoriteSubject ;
    }
}
```

Executing the `main` method results in the output:

![all args constructors](https://curriculum-content.s3.amazonaws.com/6677/pillars/all_args_constructors.png)


## Final Code Check


We'll remove the print statements in the constructors,
since they were just for demonstration purposes.

We could also remove the no-args constructors, as they are
not needed by this application.  But we'll leave them
as an example of constructor overloading.

The final version of the class hierarchy is:

![person hierarchy with constructors](https://curriculum-content.s3.amazonaws.com/6677/pillars/person_hierarchy_constructors2.png)

```java
public class Person {

    private String name;
    private int age;

    public Person() {
        super();   //calls Object() constructor
    }

    public Person(String name, int age) {
        super();   //calls Object() constructor
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void celebrateBirthday() {
        age++;
    }

    public String getWeekendPlans() {
        return "sleep late and relax all day";
    }

    @Override
    public String toString() {
        return "name=" + name + ", age=" + age;
    }
}
```

```java
public class Teacher extends Person {

    public Teacher() {
        super();   //calls Person() constructor
    }

    public Teacher(String name, int age) {
        super(name, age);  //calls Person(String name, int age) constructor
    }

    @Override
    public String getWeekendPlans() {
        return "grade homework assignments";
    }

}
```

```java
public class Student extends Person {
    private String favoriteSubject;

    public Student() {
        super();   //calls Person() constructor
    }

    public Student(String name, int age, String favoriteSubject) {
        super(name, age);   //calls Person(String name, int age) constructor
        this.favoriteSubject = favoriteSubject;
    }

    public String getFavoriteSubject() {
        return favoriteSubject;
    }

    public void setFavoriteSubject(String favoriteSubject) {
        this.favoriteSubject = favoriteSubject;
    }

    @Override
    public String getWeekendPlans() {
        return "wake up early and study all day";
    }

    @Override
    public String toString() {
        return super.toString() +
                ", favoriteSubject=" + favoriteSubject ;
    }
}
```

```java
public class Main {

    public static void main(String[] args) {
        //Person instance
        System.out.println("Creating Person");
        Person person = new Person("Kai", 32);
        System.out.println("Person state:" + person);

        //Teacher instance, inherits fields and methods from Person
        System.out.println("Creating Teacher");
        Teacher teacher = new Teacher("Tal", 40);
        System.out.println("Teacher state:" + teacher);

        //Student instance, inherits fields and methods from Person
        System.out.println("Creating Student");
        Student student = new Student("Hao", 24, "Java programming");
        System.out.println("Student state:" + student);
    }
}
```

## Common Issue Involving Superclass Constructors

Consider the `GroceryItem` class with a constructor
to initialize the fields:

```java
public class GroceryItem {
    private String sku;
    private String name;
    private double price;

    public GroceryItem(String sku, String name, double price) {
        this.sku = sku;
        this.name = name;
        this.price = price;
    }
    
}
```

Let's add a subclass named `PerishableItem` with a
field named `expiration`:

```java
public class PerishableItem extends GroceryItem {
    private String expiration;
}
```

The `PerishableItem` class won't compile!

Recall Java generates a no-args constructor if  no constructor
is defined.  The constructor looks like this:

```java
public class PerishableItem extends GroceryItem {
    private String expiration;
    
    public PerishableItem() {
        super();
    }
}
```

The error arises from the superclass constructor call `super();`
Because `GroceryItem` defines a constructor with arguments,
it **does not** generate a no-args constructor for `GroceryItem`.
Thus, the call `super()` causes a compile-time error.

We would either need to add a no-args constructor to `GroceryItem`
or better yet update `PerishableItem` to have a constructor with arguments
that calls the superclass constructor with arguments:

```java
public class PerishableItem extends GroceryItem {
    private String expiration;
    
    public PerishableItem(String sku, String name, double price, String expiration ) {
        super(sku, name, price);
        this.expiration = expiration;
    }
    
}
```


## Conclusion

The first statement in every constructor must be a call to the superclass
constructor.  Java inserts an implicit call `super()` if the programmer
does not provide an explicit call to the superclass constructor.
We can call a superclass constructor that takes parameters using `super(parameter list);`.

## Resources

[Java Tutorial - Using the keyword super](https://docs.oracle.com/javase/tutorial/java/IandI/super.html)

