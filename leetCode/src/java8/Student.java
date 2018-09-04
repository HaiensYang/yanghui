package java8;

/**
 * Created by hui10.yang on 18/9/4.
 */
public class Student {

        String name;
        Integer age;

        public Student(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public  String getName() {
            return name;
        }



        public Integer getAge() {
            return age;
        }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
