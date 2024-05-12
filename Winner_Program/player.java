import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class player {
    private String playerName;
    private String playerID;
    private String startTime;
    private String endTime;
    private String raceType;
    private final ArrayList<Integer> durationList;

    public player (String playerName, String playerID, String startTime, String endTime, String raceType){
        this.playerName = playerName;
        this.playerID = playerID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.raceType = raceType;

        this.durationList = new ArrayList<>();
        this.addDuration(this.startTime, this.endTime);
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerID() {
        return this.playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getStartTime() {
        return this.startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRaceType() {
        return this.raceType;
    }

    public void setRaceType(String raceType) {
        this.raceType = raceType;
    }

    /**
     * The method calculates the period between the starting and ending times.
     * Then, the calculated duration (in minutes) is added to the player's duration list.
     * @param startTime when the player starts the tournament.
     * @param endTime when the player finish the tournament.
     * */
    public void addDuration (String startTime, String endTime){
        Duration duration = Duration.between(LocalTime.parse(startTime), LocalTime.parse(endTime));
        duration = duration.abs();
        this.durationList.add((int)duration.toMinutes());
    }
    /**
     * This method calculates the player's average duration across all the tournaments that the player participated in.
     * @return The average duration time, or the first duration from the list if the player only participated in a tournament.
     * */
    public int getAvgD () throws Exception {
        if (this.durationList.isEmpty()){
            throw new Exception("The duration list is Empty!!");
        }

        int listLength= this.durationList.size();
        if( listLength>1){
            int sum = 0 ;
            for (Integer integer : this.durationList) {
                sum += integer;
            }
             return sum/listLength;
        } else {
            return this.durationList.get(0);
        }
    }
    public int getNumPart(){
        return this.durationList.size();
    } // Return the number of participations.

    public String toString(){
        try {
            return "[Player: "+ this.getPlayerName()+", PlayerID: "+this.getPlayerID()+", Participation Num: "+this.getNumPart()+", Average duration(m): "+this.getAvgD()+"]";
        } catch (Exception e) {
            throw new RuntimeException("The duration time is Empty at the player object!!");
        }
    }
}
