package cm.aptoide.pt.v8engine.social.data;

/**
 * Created by franciscocalado on 8/2/17.
 */

public class GameCardTouchEvent extends CardTouchEvent {

    private final int cardPosition;
    private final String answerText;

    public GameCardTouchEvent(Post card, Type actionType, int position, String text) {
        super(card, actionType);
        this.cardPosition = position;
        this.answerText = text;
    }

    public int getCardPosition(){
        return cardPosition;
    }

    public String getAnswerText() {
        return answerText;
    }
}
