import java.util.Comparator;
import java.util.Map;

public class playerCompare implements Comparator<String> {
    private final Map<String, player> refMap;

    public playerCompare(Map<String, player> refMap){
        this.refMap = refMap;
    }
    @Override
    public int compare(String o1, String o2) {

        player p1 = refMap.get(o1);
        player p2 = refMap.get(o2);
        if(p1.getNumPart() != p2.getNumPart()){
            return Integer.compare(p2.getNumPart(),p1.getNumPart()); // Firstly, compare two players on a descending order, based on participation !!
        }
        try{
            int avgCompare = Integer.compare(p1.getAvgD(), p2.getAvgD()); // Secondly, compare two players on an ascending order, based on durations!!
            if (avgCompare != 0) {
                return avgCompare;
            }
        }catch (Exception e){
            throw new RuntimeException("The duration time is Empty at the player object!!");
        }


        // As a last resort, compare the player names to ensure all different entries are considered
        return o1.compareTo(o2);

    }


}
