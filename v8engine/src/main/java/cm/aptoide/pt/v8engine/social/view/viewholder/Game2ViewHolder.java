package cm.aptoide.pt.v8engine.social.view.viewholder;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cm.aptoide.pt.v8engine.R;
import cm.aptoide.pt.v8engine.networking.image.ImageLoader;
import cm.aptoide.pt.v8engine.social.data.CardTouchEvent;
import cm.aptoide.pt.v8engine.social.data.Game2;
import cm.aptoide.pt.v8engine.social.data.GameCardTouchEvent;
import cm.aptoide.pt.v8engine.social.view.viewholder.PostViewHolder;
import rx.subjects.PublishSubject;

public class Game2ViewHolder extends PostViewHolder<Game2> {

    private final TextView score;
    private final TextView leaderboard;
    private View wrapper;
    private ImageView questionIcon;
    private TextView question;
    private final ImageView answerLeft;
    private final ImageView answerRight;
    private final PublishSubject<CardTouchEvent> cardTouchEventPublishSubject;

    private final ImageView headerIcon;
    private final TextView headerTitle;
    private final TextView headerSubTitle;



    public Game2ViewHolder(View itemView, PublishSubject<CardTouchEvent> cardTouchEventPublishSubject) {
        super(itemView);
        this.cardTouchEventPublishSubject = cardTouchEventPublishSubject;

        score = (TextView) itemView.findViewById(R.id.displayable_social_timeline_game_card_score);
        leaderboard = (TextView) itemView.findViewById(R.id.displayable_social_timeline_game_card_leaderboard);
        answerLeft = (ImageView) itemView.findViewById(R.id.game_card_question2_leftA_icon);
        answerRight = (ImageView) itemView.findViewById(R.id.game_card_question2_rightA_icon);

        this.headerIcon =
                (ImageView) itemView.findViewById(R.id.displayable_social_timeline_game_card_icon);
        this.headerTitle =
                (TextView) itemView.findViewById(R.id.displayable_social_timeline_game_card_title);
        this.headerSubTitle =
                (TextView) itemView.findViewById(R.id.displayable_social_timeline_game_card_subtitle);



    }

    @Override
    public void setPost(Game2 card, int position) {
        this.score.setText(String.valueOf(card.getScore()));
        this.leaderboard.setText(String.valueOf(card.getgRanking()));

        ImageLoader.with(itemView.getContext()).load("http://pool.img.aptoide.com/dfl/783ac07187647799c87c4e1d5cde6b8b_icon.png", this.headerIcon);
        this.headerTitle.setText("Aptoide Timeline Quiz");
        this.headerSubTitle.setText("Card 1/10");

        if (card.getQuestionIcon() == null){
            itemView.findViewById(R.id.icon_question).setVisibility(View.GONE);
            wrapper = itemView.findViewById(R.id.question);
            wrapper.setVisibility(View.VISIBLE);
            question = (TextView) wrapper.findViewById(R.id.game_card_question);
            this.question.setText(card.getQuestion()+" "+card.getApp().getName()+"?");
        }
        else{
            itemView.findViewById(R.id.question).setVisibility(View.GONE);
            wrapper = itemView.findViewById(R.id.icon_question);
            wrapper.setVisibility(View.VISIBLE);
            questionIcon = (ImageView) wrapper.findViewById(R.id.game_card_questionIcon);
            question = (TextView) wrapper.findViewById(R.id.game_card_question);
            ImageLoader.with(itemView.getContext()).load(card.getQuestionIcon(), questionIcon);
            this.question.setText(card.getQuestion());
        }

        //Randomize right answer to left or right side (if 0<rand<0.5, right answer is on the left side)
        if(Math.random()<0.5){
            ImageLoader.with(itemView.getContext()).load(card.getApp().getIcon(), answerLeft);
            ImageLoader.with(itemView.getContext()).load(card.getWrongIcon(), answerRight);
            answerLeft.setOnClickListener(click -> cardTouchEventPublishSubject.onNext(
                new GameCardTouchEvent(card, CardTouchEvent.Type.BODY, position, String.valueOf(card.getApp().getIcon()))));
            answerRight.setOnClickListener(click -> cardTouchEventPublishSubject.onNext(
                new GameCardTouchEvent(card, CardTouchEvent.Type.BODY, position, String.valueOf(card.getWrongIcon()))));
        }
        else{
            ImageLoader.with(itemView.getContext()).load(card.getWrongIcon(), answerLeft);
            ImageLoader.with(itemView.getContext()).load(card.getApp().getIcon(), answerRight);
            answerLeft.setOnClickListener(click -> cardTouchEventPublishSubject.onNext(
                new GameCardTouchEvent(card, CardTouchEvent.Type.BODY, position, String.valueOf(card.getWrongIcon()))));
            answerRight.setOnClickListener(click -> cardTouchEventPublishSubject.onNext(
                new GameCardTouchEvent(card, CardTouchEvent.Type.BODY, position, String.valueOf(card.getApp().getIcon()))));
        }



    }
}
