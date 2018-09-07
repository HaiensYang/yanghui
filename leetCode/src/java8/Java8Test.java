package java8;

import javafx.scene.shape.Path;

import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by hui10.yang on 18/9/4.
 */
public class Java8Test {
    public static void main(String[] args) {
        //java8集合新增方法测试
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        numbers.replaceAll(x->x+1);
        System.out.println(numbers);
        //通过lambda进行排序

        List<Student> students = Arrays.asList(new Student("yanghui", 15),new Student("Mary",23),new Student("Jack",22));

        students.sort((Student s1,Student s2)->s2.getName().compareTo(s1.getName()));
        // 正序
        Collections.sort(students,Comparator.comparing(Student::getAge));
        //逆序
        Collections.sort(students,Comparator.comparing(Student::getAge).reversed());

        String join = String.join(", ", "zhangsan", "lisi", "wangwu");
        System.out.println(join);

        try {
            Files.lines(Paths.get("/Users/trade.vip/Desktop/域名及git地址.txt"), StandardCharsets.UTF_8).flatMap(str->Arrays.stream(str.split("\n"))).forEach(n->System.out.println(n));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(students.toString());

    }

}
