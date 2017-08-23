package cm.aptoide.pt.dataprovider.ws.v7.post;

import cm.aptoide.pt.dataprovider.model.v7.BaseV7Response;
import java.util.List;

/**
 * Created by franciscocalado on 8/14/17.
 */

public class UpdateLeaderboardResponse extends BaseV7Response {

  private Data data;

  public Data getData(){return  data;}
  public void setData(Data data){this.data=data;}

  public static class Data{

    private String uid;
    private int score;
    private int position;
    private List<User> leaderboard;

    public Data(){

    }

    public String getUid(){return uid;}
    public void setUid(String uid){this.uid = uid;}

    public int getScore(){return score;}
    public void setScore(int score){this.score=score;}

    public List<User> getLeaderboard(){return leaderboard;}
    public void setLeaderboard(List<User> leaderboard){this.leaderboard=leaderboard;}

    public int getPosition() {return position;}
    public void setPosition(int position) {this.position = position;}
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
