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