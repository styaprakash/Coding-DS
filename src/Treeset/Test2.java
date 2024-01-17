package Treeset;
import java.util.*;
public class Test2 {
    public static void main(String[] args) {
        TreeSet<String> s=new TreeSet<String>();
        s.add("gfg");
        s.add("courses");
        s.add("ide");
        System.out.println(s);
        System.out.println(s.contains("ide"));
        Iterator<String> i= s.iterator();
        while (i.hasNext()){
            System.out.println(i.next());
        }
    }
}
