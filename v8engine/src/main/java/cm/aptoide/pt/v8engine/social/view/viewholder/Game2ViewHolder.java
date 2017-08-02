package cm.aptoide.pt.v8engine.social.view.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cm.aptoide.pt.v8engine.R;
import cm.aptoide.pt.v8engine.networking.image.ImageLoader;
import cm.aptoide.pt.v8engine.social.data.CardTouchEvent;
import cm.aptoide.pt.v8engine.social.data.Game2;
import cm.aptoide.pt.v8engine.social.view.viewholder.PostViewHolder;
import rx.subjects.PublishSubject;

public class Game2ViewHolder extends PostViewHolder<Game2> {

    private final TextView score;
    private final TextView leaderboard;
    private final ImageView questionIcon;
    private final TextView question;
    private final ImageView answerLeft;
    private final ImageView answerRight;
    private final PublishSubject<CardTouchEvent> cardTouchEventPublishSubject;


    public Game2ViewHolder(View itemView, PublishSubject<CardTouchEvent> cardTouchEventPublishSubject) {
        super(itemView);
        this.cardTouchEventPublishSubject = cardTouchEventPublishSubject;

        score = (TextView) itemView.findViewById(R.id.displayable_social_timeline_game_card_score);
        leaderboard = (TextView) itemView.findViewById(R.id.displayable_social_timeline_game_card_leaderboard);
        questionIcon = (ImageView) itemView.findViewById(R.id.game_card_question2_questionIcon);
        question = (TextView) itemView.findViewById(R.id.game_card_question2_question);
        answerLeft = (ImageView) itemView.findViewById(R.id.game_card_question2_leftA_icon);
        answerRight = (ImageView) itemView.findViewById(R.id.game_card_question2_rightA_icon);


    }

    @Override
    public void setPost(Game2 card, int position) {
        this.score.setText(String.valueOf(card.getScore()));
        this.leaderboard.setText(String.valueOf(card.getgRanking()));
        if (card.getQuestionIcon() == null){
            questionIcon.setVisibility(View.GONE);
            this.question.setText(card.getQuestion()+" "+card.getApp().getName()+"?");
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0, 100, 0, 50);
            lp.addRule(RelativeLayout.CENTER_HORIZONTAL);
            question.setLayoutParams(lp);

        }
        else{
            questionIcon.setVisibility(View.VISIBLE);
            ImageLoader.with(itemView.getContext()).load(card.getQuestionIcon(), questionIcon);
            this.question.setText(card.getQuestion());
        }

        //Randomize right answer to left or right side (if 0<rand<0.5, right answer is on the left side)
        if(Math.random()<0.5){
            ImageLoader.with(itemView.getContext()).load(card.getApp().getIcon(), answerLeft);
            ImageLoader.with(itemView.getContext()).load(card.getWrongIcon(), answerRight);
        }
        else{
            ImageLoader.with(itemView.getContext()).load(card.getWrongIcon(), answerLeft);
            ImageLoader.with(itemView.getContext()).load(card.getApp().getIcon(), answerRight);
        }

    }
}
