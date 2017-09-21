package cm.aptoide.pt.dataprovider.ws.v7.post;

import cm.aptoide.pt.dataprovider.model.v7.BaseV7Response;

/**
 * Created by franciscocalado on 8/30/17.
 */

public class GetUserGameInfoResponse extends BaseV7Response {

  private Data data;

  public Data getData() {return data;}
  public void setData(Data data) {this.data = data;}

  public static class Data{
    private User current;

    public Data(){

    }

    public User getCurrent() {return current; }
    public void setCurrent(User current) {this.current = current;}
  }

  public static class User{
    private String name;
    private int position;
    private int score;
    private int played;
    private String avatar;

    public User(){

    }

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public int getPosition() {return position;}
    public void setPosition(int position) {this.position = position;}

    public int getScore() {return score;}
    public void setScore(int score) {this.score = score;}

    public int getPlayed() {return played;}
    public void setPlayed(int played) {this.played = played;}

    public String getAvatar() {return avatar;}
    public void setAvatar(String avatar) {this.avatar = avatar;}
  }
}
