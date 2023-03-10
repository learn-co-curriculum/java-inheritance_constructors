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