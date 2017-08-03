package cm.aptoide.pt.v8engine.social.view.viewholder;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import cm.aptoide.pt.v8engine.R;
import cm.aptoide.pt.v8engine.networking.image.ImageLoader;
import cm.aptoide.pt.v8engine.social.data.CardTouchEvent;
import cm.aptoide.pt.v8engine.social.data.GameAnswer;
import cm.aptoide.pt.v8engine.social.data.GameAnswerTouchEvent;
import cm.aptoide.pt.v8engine.social.data.GameCardTouchEvent;
import cm.aptoide.pt.v8engine.social.data.Recommendation;
import cm.aptoide.pt.v8engine.view.recycler.displayable.SpannableFactory;
import rx.subjects.PublishSubject;

/**
 * Created by franciscocalado on 8/2/17.
 */

public class GameAnswerViewHolder extends  PostViewHolder<GameAnswer> {

    private final TextView score;
    private final TextView leaderboard;
    private final ImageView headerIcon;
    private final TextView headerTitle;
    private final TextView headerSubTitle;
    private final SpannableFactory spannableFactory;
    private final ImageView appIcon;
    private final Button getApp;
    private final TextView answerStatus;
    private final TextView answerMessage;
    private final ListView playerList;

    private final PublishSubject<CardTouchEvent> cardTouchEventPublishSubject;



    public GameAnswerViewHolder(View itemView, PublishSubject<CardTouchEvent> cardTouchEventPublishSubject, SpannableFactory spannableFactory) {
        super(itemView);

        this.cardTouchEventPublishSubject = cardTouchEventPublishSubject;
        this.spannableFactory = spannableFactory;

        score = (TextView) itemView.findViewById(R.id.displayable_social_timeline_answer_card_score);
        leaderboard = (TextView) itemView.findViewById(R.id.displayable_social_timeline_answer_card_leaderboard);
        appIcon = (ImageView) itemView.findViewById(R.id.get_app_icon);
        getApp = (Button) itemView.findViewById(R.id.get_app_button);
        answerStatus = (TextView) itemView.findViewById(R.id.answer_status);
        answerMessage = (TextView) itemView.findViewById(R.id.answer_message);
        playerList = (ListView) itemView.findViewById(R.id.leaderboard_display);

        this.headerIcon =
                (ImageView) itemView.findViewById(R.id.displayable_social_timeline_answer_card_icon);
        this.headerTitle =
                (TextView) itemView.findViewById(R.id.displayable_social_timeline_answer_card_title);
        this.headerSubTitle =
                (TextView) itemView.findViewById(R.id.displayable_social_timeline_answer_card_subtitle);
    }

    @Override
    public void setPost(GameAnswer card, int position) {

        this.score.setText(String.valueOf(card.getScore()));
        this.leaderboard.setText(String.valueOf(card.getgRanking()));
        ImageLoader.with(itemView.getContext()).load(card.getRightAnswer().getIcon(), appIcon);
        this.answerStatus.setText(card.getStatus());
        this.answerMessage.setText(card.getMessage());

        this.getApp.setText("get app");

        ImageLoader.with(itemView.getContext()).load("http://pool.img.aptoide.com/dfl/783ac07187647799c87c4e1d5cde6b8b_icon.png", this.headerIcon);
        this.headerTitle.setText(getStyledTitle(itemView.getContext()));
        this.headerSubTitle.setText("Card 1/10");

        getApp.setOnClickListener(click -> cardTouchEventPublishSubject.onNext(
                new CardTouchEvent(card, CardTouchEvent.Type.BODY)));

    }

    private Spannable getStyledTitle(Context context) {
        return spannableFactory.createColorSpan("Aptoide Timeline Quiz",
            ContextCompat.getColor(context, R.color.card_store_title));
    }
}
