package cm.aptoide.pt.dataprovider.ws.v7.post;

import cm.aptoide.pt.dataprovider.model.v7.BaseV7Response;
import java.util.List;

/**
 * Created by franciscocalado on 8/17/17.
 */

public class GetLeaderboardEntriesResponse extends BaseV7Response {

  private Data data;

  public Data getData() {return data;}
  public void setData(Data data) {this.data = data;}

  public static class Data{
    private List<User> leaderboard;

    public Data(){

    }

    public List<User> getLeaderboard() {return leaderboard;}
    public void setLeaderboard(List<User> leaderboard) {this.leaderboard = leaderboard;}
  }

  public static class User{
    private String name;
    private int position;
    private int score;

    public User(){

    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getPosition() {return position;}
    public void setPosition(int position) {this.position = position;}

    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}
  }
}
