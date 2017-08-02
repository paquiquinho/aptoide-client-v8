package cm.aptoide.pt.v8engine.social.data;

import cm.aptoide.pt.dataprovider.model.v7.listapp.App;
import cm.aptoide.pt.v8engine.social.data.CardType;
import cm.aptoide.pt.v8engine.social.data.Game;

/**
 * Created by franciscocalado on 7/31/17.
 */

public class Game2 extends Game {

    private final App app;
    private final String wrongIcon;
    private final String wrongURL;
    private final String questionIcon;
    private boolean isLiked;
    private boolean likedFromClick;

    public Game2(String cardId, App rightAnswer, String answerURL, String question, int score, int gRanking,
                 int lRanking, int fRanking, String abUrl, boolean isLiked, CardType cardType, String wrongIcon, String wrongURL, String questionIcon) {
        super(cardId, rightAnswer, answerURL, question, score, gRanking, lRanking, fRanking, abUrl, isLiked, cardType);
        this.app = rightAnswer;
        this.wrongIcon = wrongIcon;
        this.wrongURL = wrongURL;
        this.questionIcon = questionIcon;
    }

    public String getWrongIcon() {
        return wrongIcon;
    }

    public String getWrongURL() {
        return wrongURL;
    }

    public String getQuestionIcon() {
        return questionIcon;
    }

    public App getApp() {
        return app;
    }


    public boolean isLiked() {
        return isLiked;
    }

    @Override public void setLiked(boolean liked) {
        isLiked = liked;
        likedFromClick = true;
    }

    @Override public boolean isLikeFromClick() {
        return likedFromClick;
    }
}
