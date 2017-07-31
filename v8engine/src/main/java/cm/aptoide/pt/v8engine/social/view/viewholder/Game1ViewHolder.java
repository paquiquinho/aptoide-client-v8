package cm.aptoide.pt.v8engine.social.view.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cm.aptoide.pt.v8engine.R;
import cm.aptoide.pt.v8engine.networking.image.ImageLoader;
import cm.aptoide.pt.v8engine.social.data.CardTouchEvent;
import cm.aptoide.pt.v8engine.social.data.Game;
import cm.aptoide.pt.v8engine.social.data.Game1;
import cm.aptoide.pt.v8engine.timeline.view.LikeButtonView;
import cm.aptoide.pt.v8engine.util.DateCalculator;
import cm.aptoide.pt.v8engine.view.recycler.displayable.SpannableFactory;
import rx.subjects.PublishSubject;

/**
 * Created by franciscocalado on 7/28/17.
 */

public class Game1ViewHolder extends  PostViewHolder<Game1> {

    private final TextView score;
    private final TextView leaderboard;
    private final ImageView questionIcon;
    private final TextView question;
    private final TextView answerLeft;
    private final TextView answerRight;
    private final PublishSubject<CardTouchEvent> cardTouchEventPublishSubject;


    public Game1ViewHolder(View itemView, PublishSubject<CardTouchEvent> cardTouchEventPublishSubject) {
        super(itemView);
        this.cardTouchEventPublishSubject = cardTouchEventPublishSubject;

        score = (TextView) itemView.findViewById(R.id.displayable_social_timeline_game_card_score);
        leaderboard = (TextView) itemView.findViewById(R.id.displayable_social_timeline_game_card_leaderboard);
        questionIcon = (ImageView) itemView.findViewById(R.id.game_card_question1_icon);
        question = (TextView) itemView.findViewById(R.id.game_card_question1_question);
        answerLeft = (TextView) itemView.findViewById(R.id.game_card_question1_answerLeft);
        answerRight = (TextView) itemView.findViewById(R.id.game_card_question1_answerRight);


    }

    @Override
    public void setPost(Game1 card, int position) {
        this.score.setText(card.getScore());
        this.leaderboard.setText(card.getgRanking());
        ImageLoader.with(itemView.getContext()).load(card.getApp().getIcon(), questionIcon);
        this.question.setText(card.getQuestion());

        //Randomize right answer to left or right side (if 0<rand<0.5, right answer is on the left side)
        if(Math.random()<0.5){
            this.answerLeft.setText(card.getApp().getName());
            this.answerRight.setText(card.getWrongName());
        }
        else{
            this.answerLeft.setText(card.getWrongName());
            this.answerRight.setText(card.getApp().getName());
        }

    }
}
