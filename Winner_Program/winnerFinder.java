import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class winnerFinder {
    private final ArrayList<String> errorLog;
    String path;
    public winnerFinder(String path){
        this.path = path;
        errorLog = new ArrayList<>();
    }
    /**
    * This method ensures that the given player info matches the input format condition.
    * @param playerInfo
    *         An array type that contains a player info
    * @param hashMap
    *       hashMap that contains the record of the pre-saved players.
    *
    * */
    public void specsCheck(String[] playerInfo, HashMap<String,player> hashMap){
//        if (playerInfo.length != 5) {
//            throw new IllegalArgumentException("Entry is not correctly formatted: " + Arrays.toString(playerInfo));
//        }
        String name = playerInfo[0].trim();
        String ID = playerInfo[1].trim();
        String startTime = playerInfo[2].trim();
        String endTime = playerInfo[3].trim();
        String tournament = playerInfo[4].trim();

        List<String> events = Arrays.asList("1000m","eggRace","sackRace") ;


        //Check the name content
        if (!name.matches("[a-zA-Z ]+")) {
            this.errorLog.add("Invalid name: " + name);
//            throw new IllegalArgumentException("Invalid name: " + name);
        }
        //Check the ID content
        if (!ID.matches("\\d+")) {
            this.errorLog.add("ID must contain only digits: " + ID);
//            throw new IllegalArgumentException("ID must contain only digits: " + ID);
        }
        //Check stating time format
        try {
            LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format, should be HH:mm:ss: " + startTime);
        }
        //Check ending time format
        try {
            LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format, should be HH:mm:ss: " + endTime);
        }
        if (!events.contains(tournament)){
            this.errorLog.add("Invalid competition type: " + tournament);
//            throw new IllegalArgumentException("Invalid competition type: " + tournament);
        }
        if (hashMap.containsKey(ID) && !hashMap.get(ID).getPlayerName().equals(name)) {
            this.errorLog.add("Duplicate ID with different names: " + ID+ ", the ID is already assigned to this player: "+hashMap.get(ID).getPlayerName());
//            throw new IllegalArgumentException("Duplicate ID with different names: " + ID+ ", the ID is already assigned to this player: "+hashMap.get(ID).getPlayerName());
        }

    }

    /**
     * This method scans the given file to Class winnerFinder.java,
     * and saves the scanned player info into a HashMap.
     * @return a HashMap consists of keys= PlayerIDs and Values = Player-Objects.
     * */
    public HashMap<String, player> fileScanner() throws FileNotFoundException {
        File file = new File(this.path);
        if (!file.exists() && ! file.isDirectory()){
            throw new RuntimeException("No such a file in the given path directory: "+ this.path+" !!");
        }

        Scanner scanner = new Scanner(file);
        HashMap <String, player> hashmap = new HashMap<>();
        String playerInfo;

        while(scanner.hasNext()){
            playerInfo = scanner.nextLine();
            String [] playerInfoS = playerInfo.split(",");
            if ((playerInfoS.length== 5)){
                specsCheck(playerInfoS, hashmap);
                if (!hashmap.containsKey(playerInfoS[1])) {
                    hashmap.put(playerInfoS[1], new player(playerInfoS[0], playerInfoS[1], playerInfoS[2], playerInfoS[3], playerInfoS[4]));
                }else{
                    hashmap.get(playerInfoS[1]).addDuration(playerInfoS[2], playerInfoS[3]);
                }
            }

        }

        return hashmap;
    }

    /**
     * This method converts the HashMap containing the player info into TreeMap.
     * The TreeMap sorts player info based on the highest number of participations and the shortest duration-time
     * using PlayerCompare Comparator class.
     * @param hashMap
     *        Contains the players' info. The tags of this Map must be <String, player(object)>
     * @return TreeMap<String, player(object)>
     *
     * */
    public TreeMap<String,player> hashToTree (HashMap<String, player> hashMap) throws Exception {
        if (hashMap.isEmpty()){
            throw new Exception("The hashMap is Empty!!");
        }
        TreeMap<String, player>treeMap = new TreeMap<>(new playerCompare(hashMap));
        treeMap.putAll(hashMap);
        return treeMap;
    }

    /**
     * The treePrinter prints out the content of the given TreeMap.
     * It returns the winner who has participated in all tournaments and has the shortest average duration.
     * Additionally, if all players got the same numbers(participation and duration-time), the method will print out all of them.
     * @param treeMap
     *        contains the sorted player info.
     *
     * */
    public void treePrinter(TreeMap<String, player> treeMap) throws Exception {
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
        System.out.println();
    }

    public ArrayList<String> getLog(){
        return this.errorLog;
    }

    /**
     * Prints out all the error logs of wrong formatted player info that previously appeared during the file's scanning process.
     * */
    public void errorLogPrinter (){
        System.out.println("Error log during the reading of this file: "+this.path);
        for (String error: this.errorLog){
            System.out.println(error);
        }
    }

}
