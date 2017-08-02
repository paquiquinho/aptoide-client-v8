package cm.aptoide.pt.v8engine.social.data;

/**
 * Created by franciscocalado on 8/2/17.
 */

public class GameAnswerTouchEvent extends CardTouchEvent {

    private final int cardPosition;

    public GameAnswerTouchEvent(Post card, Type actionType, int position) {
        super(card, actionType);
        this.cardPosition = position;
    }

    public int getCardPosition() {
        return cardPosition;
    }
}
