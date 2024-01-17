package Trremap;
import java.util.Map;
import  java.util.TreeMap;
public class Test {
    public static void main(String[] args) {
        TreeMap<Integer,String> t=new TreeMap<Integer,String>();
        t.put(10,"CodingNinja");
        t.put(15,"ide");
        t.put(5,"courses");
        System.out.println(t);
        System.out.println(t.containsKey(10));
        for (Map.Entry<Integer,String>e:t.entrySet()){
            System.out.println(e.getKey()+" ,"+e.getValue());
        }
    }
}
