package cm.aptoide.pt.v8engine.social.view.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cm.aptoide.pt.v8engine.R;
import cm.aptoide.pt.v8engine.networking.image.ImageLoader;
import cm.aptoide.pt.v8engine.social.data.CardTouchEvent;
import cm.aptoide.pt.v8engine.social.data.Game1;
import cm.aptoide.pt.v8engine.social.data.GameCardTouchEvent;
import rx.subjects.PublishSubject;

/**
 * Created by franciscocalado on 7/28/17.
 */

public class Game1ViewHolder extends  PostViewHolder<Game1> {

    private final TextView score;
    private final TextView leaderboard;
    private final ImageView questionIcon;
    private final TextView question;
    private final TextView leftAnswer;
    private final TextView rightAnswer;
    private final PublishSubject<CardTouchEvent> cardTouchEventPublishSubject;

    private final ImageView headerIcon;
    private final TextView headerTitle;
    private final TextView headerSubTitle;



    public Game1ViewHolder(View itemView, PublishSubject<CardTouchEvent> cardTouchEventPublishSubject) {
        super(itemView);
        this.cardTouchEventPublishSubject = cardTouchEventPublishSubject;

        score = (TextView) itemView.findViewById(R.id.displayable_social_timeline_game_card_score);
        leaderboard = (TextView) itemView.findViewById(R.id.displayable_social_timeline_game_card_leaderboard);
        questionIcon = (ImageView) itemView.findViewById(R.id.game_card_question1_icon);
        question = (TextView) itemView.findViewById(R.id.game_card_question1_question);
        leftAnswer = (TextView) itemView.findViewById(R.id.game_card_question1_answerLeft);
        rightAnswer = (TextView) itemView.findViewById(R.id.game_card_question1_answerRight);

        this.headerIcon =
                (ImageView) itemView.findViewById(R.id.displayable_social_timeline_game_card_icon);
        this.headerTitle =
                (TextView) itemView.findViewById(R.id.displayable_social_timeline_game_card_title);
        this.headerSubTitle =
                (TextView) itemView.findViewById(R.id.displayable_social_timeline_game_card_subtitle);



    }

    @Override
    public void setPost(Game1 card, int position) {
        this.score.setText(String.valueOf(card.getScore()));
        this.leaderboard.setText(String.valueOf(card.getgRanking()));
        ImageLoader.with(itemView.getContext()).load(card.getApp().getIcon(), questionIcon);
        this.question.setText(card.getQuestion());

        ImageLoader.with(itemView.getContext()).load("http://pool.img.aptoide.com/dfl/783ac07187647799c87c4e1d5cde6b8b_icon.png", this.headerIcon);
        this.headerTitle.setText("Aptoide Timeline Quiz");
        this.headerSubTitle.setText("Card 1/10");

        //Randomize right answer to left or right side (if 0<rand<0.5, right answer is on the left side)
        if(Math.random()<0.5){
            this.leftAnswer.setText(card.getApp().getName());
            this.rightAnswer.setText(card.getWrongName());
        }
        else{
            this.leftAnswer.setText(card.getWrongName());
            this.rightAnswer.setText(card.getApp().getName());
        }

        leftAnswer.setOnClickListener(click -> cardTouchEventPublishSubject.onNext(
                new GameCardTouchEvent(card, CardTouchEvent.Type.BODY, position, String.valueOf(leftAnswer.getText()))));
        rightAnswer.setOnClickListener(click -> cardTouchEventPublishSubject.onNext(
                new GameCardTouchEvent(card, CardTouchEvent.Type.BODY, position, String.valueOf(rightAnswer.getText()))));
    }
}
