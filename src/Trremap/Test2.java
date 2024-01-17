package Trremap;
import java.util.*;
public class Test2 {
    public static void main(String[] args) {
        TreeMap<Integer,String> t= new TreeMap<Integer,String>();
        t.put(10,"good");
        t.put(18,"nice");
        t.put(5,"cool");
        System.out.println(t.higherKey(10));
        System.out.println(t.lowerKey(10));
        System.out.println(t.floorKey(10));
        System.out.println(t.ceilingKey(10));
//        t.remove(5);
//        System.out.println(t.size());
        System.out.println(t.higherEntry(10).getValue());
        System.out.println(t.lowerEntry(10).getValue());
        System.out.println(t.floorEntry(10).getValue());
        System.out.println(t.ceilingEntry(10).getValue());
    }
}
