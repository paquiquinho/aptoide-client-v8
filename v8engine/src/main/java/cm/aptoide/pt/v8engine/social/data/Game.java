package cm.aptoide.pt.v8engine.social.data;

import cm.aptoide.pt.dataprovider.model.v7.listapp.App;
import cm.aptoide.pt.dataprovider.model.v7.timeline.Ab;

/**
 * Created by franciscocalado on 7/28/17.
 */

public class Game implements Post {

    private final String cardId;
    private final App rightAnswer;
    private final String answerURL;
    private final String wrongName;
    private final String wrongIcon;
    private final String wrongURL;
    private final String question;
    private final int score;
    private final int gRanking;
    private final int lRanking;
    private final int fRanking;
    private final String abUrl;
    private final boolean isLiked;
    private final CardType cardType;

    public Game(String cardId, App rightAnswer, String answerURL, String wrongName, String wrongIcon, String wrongURL,
                String question, int score, int gRanking, int lRanking, int fRanking, String abUrl, boolean isLiked, CardType cardType){
        this.cardId = cardId;
        this.rightAnswer = rightAnswer;
        this.answerURL = answerURL;
        this.wrongName = wrongName;
        this.wrongIcon = wrongIcon;
        this.wrongURL = wrongURL;
        this.question = question;
        this.score = score;
        this.gRanking = gRanking;
        this.lRanking = lRanking;
        this.fRanking = fRanking;
        this.abUrl = abUrl;
        this.isLiked = isLiked;
        this.cardType = cardType;
    }

    @Override
    public String getCardId() {
        return cardId;
    }

    @Override
    public CardType getType() {
        return cardType;
    }

    @Override
    public String getAbUrl() {
        return abUrl;
    }

    public App getRightAnswer() {
        return rightAnswer;
    }

    public String getAnswerURL() {
        return answerURL;
    }

    public String getWrongName() {
        return wrongName;
    }

    public String getWrongIcon() {
        return wrongIcon;
    }

    public String getWrongURL() {
        return wrongURL;
    }

    public String getQuestion() {
        return question;
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
}
