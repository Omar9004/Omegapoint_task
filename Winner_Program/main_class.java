import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class main_class {

    public static void main(String [] args) throws FileNotFoundException {
        player p = new player("Steve Carell","3100693","12:12:10","12:16:10","sackRace");
        File file = new File("race-results.txt");
        Scanner scanner = new Scanner(file);
        int count = 0 ;

//        Map<String,player> treeMap =new HashMap<>() ;

        HashMap <String, player> hashmap = new HashMap<>();
        String detail = "";
        while(scanner.hasNext()){

            detail = scanner.nextLine();
            String [] detailSpilt = detail.split(",");
            if (detailSpilt.length== 5){
                if (!hashmap.containsKey(detailSpilt[1])) {
                    hashmap.put(detailSpilt[1], new player(detailSpilt[0], detailSpilt[1], detailSpilt[2], detailSpilt[3], detailSpilt[4]));
                    hashmap.get(detailSpilt[1]).setTourCount();
                }else{
                    hashmap.get(detailSpilt[1]).addDuration(detailSpilt[2], detailSpilt[3]);
                    hashmap.get(detailSpilt[1]).setTourCount();
                }
            }


        }


        TreeMap<String, player>treeMap = new TreeMap<>(new playerCompare(hashmap));
        treeMap.putAll(hashmap);
        for(String key: treeMap.keySet()){
            System.out.println(treeMap.get(key));
        }


    }
}
