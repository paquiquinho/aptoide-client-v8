package cm.aptoide.pt.v8engine.social.data;

import java.util.List;

import cm.aptoide.pt.dataprovider.model.v7.listapp.App;

/**
 * Created by franciscocalado on 8/2/17.
 */

public class GameAnswer implements Post {

    private final String cardID;
    private final App rightAnswer;
    private final List<String> leaderboard;
    private final int score;
    private final int gRanking;
    private final int lRanking;
    private final int fRanking;
    private final String status;
    private final String message;
    private final String abUrl;
    private boolean isLiked;
    private final CardType cardType;

    public GameAnswer(String cardID, App rightAnswer, List<String> leaderboard, int score, int gRanking, int lRanking,
                      int fRanking, String status, String message, String abUrl, boolean isLiked, CardType cardType){

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

    public int getlRanking() {
        return lRanking;
    }

    public int getfRanking() {
        return fRanking;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
