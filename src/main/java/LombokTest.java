import lombok.*;

public class LombokTest {
    @SneakyThrows
    public static void main(String[] args){
        val p1 = new People(10, "Bob");
        System.out.println(p1);
        var p2 = new People(10, "Bod");
        System.out.println(p1 == p2);
        System.out.println(p1.equals(p2));
        p2 = p1;
        System.out.println(p1 == p2);
        System.out.println(p1.equals(p2));
        age(p1);
        age(null);

    }

    static void age(@NonNull People p){
        System.out.println(p.getAge());
    }

    @Data
    @AllArgsConstructor
    @ToString
    static class People {
        private int age;
        private String name;
    }
}
