package cm.aptoide.pt.v8engine.social.data;

import cm.aptoide.pt.dataprovider.model.v7.timeline.SocialCard;
import java.util.List;

import cm.aptoide.pt.dataprovider.model.v7.listapp.App;

/**
 * Created by franciscocalado on 8/2/17.
 */

public class GameAnswer implements Post {

    private final String cardID;
    private final App rightAnswer;
    private final List<String> leaderboard;
    private int score;
    private int gRanking;
    private int lRanking;
    private int fRanking;
    private String status;
    private final String message;
    private final String abUrl;
    private boolean isLiked;
    private final CardType cardType;
    private int points;
    private User user1;
    private User user2;
    private User user3;
    private int played;

    public GameAnswer(String cardID, App rightAnswer, List<String> leaderboard, int score, int gRanking, int lRanking,
                      int fRanking, String status, String message, String abUrl, boolean isLiked, CardType cardType, int points, User user1, User user2, User user3, int played){

        this.cardID = cardID;
        this.rightAnswer = rightAnswer;
        this.leaderboard = leaderboard;
        this.score = score;
        this.gRanking = gRanking;
        this.lRanking = lRanking;
        this.fRanking = fRanking;
        this.status = status;
        this.message = message;
        this.abUrl = abUrl;
        this.isLiked = isLiked;
        this.cardType = cardType;
        this.points = points;
        this.user1 = user1;
        this.user2 = user2;
        this.user3 = user3;
        this.played = played;
    }


  public static class User{
      final String name;
      final int position;
      final int score;

      public User(String name, int position, int score){
        this.name=name;
        this.position=position;
        this.score=score;
      }

      public String getName(){return name;}
      public int getPosition(){return position;}
      public int getScore(){return score;}
  }

    @Override
    public String getCardId() {
        return cardID;
    }

    @Override
    public CardType getType() {
        return cardType;
    }

    @Override
    public String getAbUrl() {
        return abUrl;
    }

    @Override
    public boolean isLiked() {
        return isLiked;
    }

    @Override
    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    @Override
    public boolean isLikeFromClick() {
        return false;
    }

  @Override public List<SocialCard.CardComment> getComments() {
    return null;
  }

  @Override public long getCommentsNumber() {
    return 0;
  }

  public int getPlayed() {return played;}
  public void setPlayed(int played) {this.played = played;}


  @Override public void addComment(SocialCard.CardComment postComment) {

  }

  public App getRightAnswer() {
        return rightAnswer;
    }

    public List<String> getLeaderboard() {
        return leaderboard;
    }

    public int getScore() {
        return score;
    }

    public int getgRanking() {
        return gRanking;
    }
    public void setgRanking(int ranking){gRanking=ranking;}

    public int getlRanking() {
        return lRanking;
    }

    public int getfRanking() {
        return fRanking;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status){this.status=status;}

    public String getMessage() {
        return message;
    }

    public int getPoints() {
        return points;
    }
    public void setPoints(int points){this.points=points;}

    public void setScore(int score){this.score = score;}

    public User getUser1() {return user1;}
    public void setUser1(User user1) {this.user1 = user1;}

    public User getUser2() {return user2;}
    public void setUser2(User user2) {this.user2 = user2;}

    public User getUser3() {return user3;}
    public void setUser3(User user3) {this.user3 = user3;}

}
