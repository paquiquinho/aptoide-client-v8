package cm.aptoide.pt.v8engine.social.view.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import cm.aptoide.pt.v8engine.R;
import cm.aptoide.pt.v8engine.networking.image.ImageLoader;
import cm.aptoide.pt.v8engine.social.data.CardTouchEvent;
import cm.aptoide.pt.v8engine.social.data.Game3;
import cm.aptoide.pt.v8engine.social.view.viewholder.PostViewHolder;
import rx.subjects.PublishSubject;

public class Game3ViewHolder extends PostViewHolder<Game3> {

    private final TextView score;
    private final TextView leaderboard;
    private final ImageView questionIcon;
    private final TextView question;
    private final TextView answerLeft;
    private final TextView answerRight;
    private final ImageView answerLeftIcon;
    private final ImageView answerRightIcon;
    private final PublishSubject<CardTouchEvent> cardTouchEventPublishSubject;


    public Game3ViewHolder(View itemView, PublishSubject<CardTouchEvent> cardTouchEventPublishSubject) {
        super(itemView);
        this.cardTouchEventPublishSubject = cardTouchEventPublishSubject;

        score = (TextView) itemView.findViewById(R.id.displayable_social_timeline_game_card_score);
        leaderboard = (TextView) itemView.findViewById(R.id.displayable_social_timeline_game_card_leaderboard);
        questionIcon = (ImageView) itemView.findViewById(R.id.game_card_question3_icon);
        question = (TextView) itemView.findViewById(R.id.game_card_question3_question);
        answerLeft = (TextView) itemView.findViewById(R.id.game_card_question3_answer_left);
        answerRight = (TextView) itemView.findViewById(R.id.game_card_question3_answer_right);
        answerLeftIcon = (ImageView) itemView.findViewById(R.id.game_card_question3_icon_left);
        answerRightIcon = (ImageView) itemView.findViewById(R.id.game_card_question3_icon_right);


    }

    @Override
    public void setPost(Game3 card, int position) {
        this.score.setText(card.getScore());
        this.leaderboard.setText(card.getgRanking());
        if (card.getQuestionIcon() == null){
            questionIcon.setVisibility(View.GONE);
        }
        else{
            questionIcon.setVisibility(View.VISIBLE);
            ImageLoader.with(itemView.getContext()).load(card.getApp().getIcon(), questionIcon);
        }
        this.question.setText(card.getQuestion());

        //Randomize right answer to left or right side (if 0<rand<0.5, right answer is on the left side)
        if(Math.random()<0.5){
            ImageLoader.with(itemView.getContext()).load(card.getApp().getIcon(), answerLeftIcon);
            this.answerLeft.setText(card.getApp().getName());
            ImageLoader.with(itemView.getContext()).load(card.getWrongIcon(), answerRightIcon);
            this.answerRight.setText(card.getWrongName());
        }
        else{
            ImageLoader.with(itemView.getContext()).load(card.getWrongIcon(), answerLeftIcon);
            this.answerLeft.setText(card.getWrongName());
            ImageLoader.with(itemView.getContext()).load(card.getApp().getIcon(), answerRightIcon);
            this.answerRight.setText(card.getApp().getName());
        }

    }
}
