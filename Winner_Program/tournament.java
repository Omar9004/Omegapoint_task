import java.util.*;

public class tournament {
    public static void main(String [] args) throws Exception {
        winnerFinder wf = new winnerFinder("race-results2.txt");

        HashMap<String, player> hashMap = wf.fileScanner(); // Scan the given file and return a HashMap!!
        TreeMap<String,player> treeMap = wf.hashToTree(hashMap); // Converting the HashMap into TreeMap.
        wf.treePrinter(treeMap);// Print out the winner\s that has achieved the requirements!!
        wf.errorLogPrinter();

    }
}
