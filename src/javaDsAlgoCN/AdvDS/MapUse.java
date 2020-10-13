package javaDsAlgoCN.AdvDS;

public class MapUse {
    public static void main(String[] args) {
        Map<String,Integer> map = new Map<>();
        for (int i = 0; i < 20; i++) {
            map.insert("rat"+i, 1+i);
            System.out.println("i = " + i + " lf = " + map.loadFactor());
        }
        for (int i = 0; i < 20; i++) {
            System.out.println("rat"+i+ "<->"+map.removeKey("rat"+i));
            System.out.println("i = " + i + " lf = " + map.loadFactor());
        }
    }
}
