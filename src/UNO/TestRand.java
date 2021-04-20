package UNO;

public class TestRand {
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int rnd = (int)(Math.random() * ((99 - 0) + 1)) + 0;
            System.out.println(rnd + " " + i);
        }
    }
}
