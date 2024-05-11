import java.io.File;
import java.io.FileNotFoundException;
import java.time.temporal.Temporal;
import java.util.*;

public class main_class {

    public static void treePrinter(TreeMap<String, player> treeMap) throws Exception {
        if (treeMap.isEmpty()){
            throw new Exception("The tree is Empty!!!");
        }
        int bestTime = treeMap.firstEntry().getValue().getAvgD();
        for (Map.Entry<String,player> element: treeMap.entrySet()){
            if(element.getValue().getAvgD() ==bestTime){
                System.out.println(element.getValue());

            }else if(element.getValue().getAvgD()>bestTime){ //Once the given duration time becomes bigger than the least one, then break out of the loop!
                break;
            }
        }
    }

    public static HashMap<String, player> fileScanner(String path){
        try{
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            HashMap <String, player> hashmap = new HashMap<>();
            String detail;

            while(scanner.hasNext()){
                detail = scanner.nextLine();
                String [] detailSpilt = detail.split(",");
                if ((detailSpilt.length== 5)){
                    if (!hashmap.containsKey(detailSpilt[1])) {
                        hashmap.put(detailSpilt[1], new player(detailSpilt[0], detailSpilt[1], detailSpilt[2], detailSpilt[3], detailSpilt[4]));
                    }else{
                        hashmap.get(detailSpilt[1]).addDuration(detailSpilt[2], detailSpilt[3]);
                    }
                }

            }
            return hashmap;
        }catch (Exception a){
            throw new RuntimeException("No such file in the given path directory: "+ path+" !!");
        }

    }

    public static TreeMap<String,player> hashToTree (HashMap<String, player> hashMap) throws Exception {
        if (hashMap.isEmpty()){
            throw new Exception("The hashMap is Empty!!");
        }
        TreeMap<String, player>treeMap = new TreeMap<>(new playerCompare(hashMap));
        treeMap.putAll(hashMap);
        return treeMap;
    }
    public static void main(String [] args) throws Exception {
        HashMap<String, player> hashMap = fileScanner("race-results.txt"); // Scan the given file and return a HashMap!!
        TreeMap<String,player> treeMap = hashToTree(hashMap); // Converting the HashMap into TreeMap.
        treePrinter(treeMap);// Print out the winner that has achieved the requirements!!

    }
}
