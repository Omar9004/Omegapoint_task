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
        this.setPlayerName(playerName);
        this.setPlayerID(playerID);
        this.setStartTime(startTime);
        this.setEndTime(endTime);
        this.setRaceType(raceType);
        this.durationList = new ArrayList<>();
        this.addDuration(this.startTime, this.endTime);
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRaceType() {
        return raceType;
    }

    public void setRaceType(String raceType) {
        this.raceType = raceType;
    }

    public void addDuration (String startTime, String endTime){
        Duration duration = Duration.between(LocalTime.parse(startTime), LocalTime.parse(endTime));
        duration = duration.abs();
        this.durationList.add((int)duration.toMinutes());
    }

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
    } // Return the participation number of the player.

    public String toString(){
        try {
            return "[Player: "+ this.getPlayerName()+", PlayerID: "+this.getPlayerID()+", Participation Num: "+this.getNumPart()+", Average duration: "+this.getAvgD()+"]";
        } catch (Exception e) {
            throw new RuntimeException("The duration time is Empty at the player object!!");
        }
    }
}
