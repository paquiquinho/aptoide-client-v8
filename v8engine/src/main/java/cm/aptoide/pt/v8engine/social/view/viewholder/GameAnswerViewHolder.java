package cm.aptoide.pt.v8engine.social.view.viewholder;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import cm.aptoide.pt.dataprovider.model.v7.Layout;
import cm.aptoide.pt.preferences.Application;
import cm.aptoide.pt.utils.AptoideUtils;
import cm.aptoide.pt.v8engine.R;
import cm.aptoide.pt.v8engine.networking.image.ImageLoader;
import cm.aptoide.pt.v8engine.social.data.CardTouchEvent;
import cm.aptoide.pt.v8engine.social.data.GameAnswer;
import cm.aptoide.pt.v8engine.social.data.LeaderboardTouchEvent;
import cm.aptoide.pt.v8engine.view.recycler.displayable.SpannableFactory;
import rx.subjects.PublishSubject;

/**
 * Created by franciscocalado on 8/2/17.
 */

public class GameAnswerViewHolder extends  PostViewHolder<GameAnswer> {

    private final TextView score;
    private final TextView increment;
    private final TextView leaderboard;
    private final ImageView scoreIcon;
    private final ImageView leaderboardIcon;
    private final ImageView headerIcon;
    private final TextView headerTitle;
    private final TextView headerSubTitle;
    private final SpannableFactory spannableFactory;
    private final ImageView appIcon;
    private final Button getApp;
    private final TextView answerStatus;
    private final TextView answerMessage;
    private final Button logIn;
    private final View leaderboards;
    private final TextView rankingFirst;
    private final TextView nameFirst;
    private final TextView scoreFirst;
    private final TextView rankingSec;
    private final TextView nameSec;
    private final TextView scoreSec;
    private final TextView rankingThird;
    private final TextView nameThird;
    private final TextView scoreThird;
    private final ProgressBar scoreProgress;
    private final ProgressBar leaderboardProgress;
    private final Button expandLeaderboard;

    private int scoreValue;

    private final PublishSubject<CardTouchEvent> cardTouchEventPublishSubject;

    public GameAnswerViewHolder(View itemView, PublishSubject<CardTouchEvent> cardTouchEventPublishSubject,
        SpannableFactory spannableFactory) {
        super(itemView);

        this.cardTouchEventPublishSubject = cardTouchEventPublishSubject;
        this.spannableFactory = spannableFactory;


        this.score = (TextView) itemView.findViewById(R.id.displayable_social_timeline_answer_card_score);
        increment = (TextView) itemView.findViewById(R.id.score_increment);
        leaderboard = (TextView) itemView.findViewById(R.id.displayable_social_timeline_answer_card_leaderboard);
        appIcon = (ImageView) itemView.findViewById(R.id.get_app_icon);
        getApp = (Button) itemView.findViewById(R.id.get_app_button);
        answerStatus = (TextView) itemView.findViewById(R.id.answer_status);
        answerMessage = (TextView) itemView.findViewById(R.id.answer_message);
        //playerList = (ListView) itemView.findViewById(R.id.leaderboard_display);
        logIn = (Button) itemView.findViewById(R.id.login_button);

        this.headerIcon = (ImageView) itemView.findViewById(R.id.displayable_social_timeline_answer_card_icon);
        this.headerTitle = (TextView) itemView.findViewById(R.id.displayable_social_timeline_answer_card_title);
        this.headerSubTitle = (TextView) itemView.findViewById(R.id.displayable_social_timeline_answer_card_subtitle);
        this.leaderboardIcon = (ImageView) itemView.findViewById(R.id.displayable_social_timeline_answer_card_leaderboard_icon);
        this.scoreIcon = (ImageView) itemView.findViewById(R.id.displayable_social_timeline_answer_card_score_icon);

        this.leaderboards = itemView.findViewById(R.id.card_leaderboard);

        this.rankingFirst = (TextView) itemView.findViewById(R.id.p1_ranking);
        this.nameFirst = (TextView) itemView.findViewById(R.id.p1_name);
        this.scoreFirst = (TextView) itemView.findViewById(R.id.p1_score);

        this.rankingSec = (TextView) itemView.findViewById(R.id.p2_ranking);
        this.nameSec = (TextView) itemView.findViewById(R.id.p2_name);
        this.scoreSec = (TextView) itemView.findViewById(R.id.p2_score);

        this.rankingThird = (TextView) itemView.findViewById(R.id.p3_ranking);
        this.nameThird = (TextView) itemView.findViewById(R.id.p3_name);
        this.scoreThird = (TextView) itemView.findViewById(R.id.p3_score);

        this.scoreProgress = (ProgressBar) itemView.findViewById(R.id.score_progress);
        this.leaderboardProgress = (ProgressBar) itemView.findViewById(R.id.leaderboard_progress);
        this.expandLeaderboard = (Button) itemView.findViewById(R.id.expand_leaderboard);


    }

    @Override public void setPost(GameAnswer card, int position) {

        String increment;

        if (card.getPoints() > 0) {
            increment ="(" + "+" + String.valueOf(card.getPoints()) + ")";
        } else if(card.getPoints()==0){
            increment="(0)";
        }else
            increment = "(" + String.valueOf(card.getPoints()) + ")";

        this.increment.setText(increment);
        if(card.getPoints() < 0)
            this.increment.setTextColor(Color.RED);
        else if(card.getPoints()>0)
            this.increment.setTextColor(Color.GREEN);
        else
            this.increment.setTextColor(Color.BLUE);

        this.leaderboard.setText(String.valueOf(card.getgRanking()));
        ImageLoader.with(itemView.getContext())
            .load(card.getRightAnswer()
                .getIcon(), appIcon);
        this.answerStatus.setText(card.getStatus());
        if(card.getStatus() == "Wrong")
            this.answerStatus.setTextColor(Color.RED);
        else if(card.getStatus()=="Out of tries!")
            this.answerStatus.setTextColor(Color.BLUE);
        else
            this.answerStatus.setTextColor(Color.GREEN);
        this.answerMessage.setText(card.getMessage());

        this.getApp.setText("get app");

        ImageLoader.with(itemView.getContext())
            .load("http://pool.img.aptoide.com/dfl/783ac07187647799c87c4e1d5cde6b8b_icon.png", this.headerIcon);
        this.headerTitle.setText(getStyledTitle(itemView.getContext(), getTitle(itemView.getContext()
            .getResources()), Application.getConfiguration()
                .getMarketName()));
        if(card.getPlayed() == -1)
            this.headerSubTitle.setText("Out of Cards");
        else
            this.headerSubTitle.setText("Card "+String.valueOf(card.getPlayed())+"/10");

        getApp.setOnClickListener(click -> cardTouchEventPublishSubject.onNext(
            new CardTouchEvent(card, CardTouchEvent.Type.BODY)));

        expandLeaderboard.setOnClickListener(click -> cardTouchEventPublishSubject.onNext(
            new LeaderboardTouchEvent(card, CardTouchEvent.Type.BODY)));


        //if(!card.getLoginButton()){
        //    playerList.setVisibility(View.GONE);
        //    logIn.setVisibility(View.VISIBLE);
        //    logIn.setOnClickListener(click -> cardTouchEventPublishSubject.onNext(
        //        new CardTouchEvent(card, CardTouchEvent.Type.LOGIN)));
        //    score.setVisibility(View.GONE);
        //    leaderboard.setVisibility(View.GONE);
        //    leaderboardIcon.setVisibility(View.GONE);
        //    scoreIcon.setVisibility(View.GONE);
        //    this.increment.setVisibility(View.GONE);
        //}
        if(card.getUser1() != null){
            score.setVisibility(View.VISIBLE);
            scoreProgress.setVisibility(View.INVISIBLE);
            this.score.setText(String.valueOf(card.getScore()));
            leaderboards.setVisibility(View.VISIBLE);
            expandLeaderboard.setVisibility(View.VISIBLE);
            leaderboardProgress.setVisibility(View.INVISIBLE);

            rankingFirst.setText(String.valueOf(card.getUser1().getPosition()));
            nameFirst.setText(card.getUser1().getName());
            scoreFirst.setText(String.valueOf(card.getUser1().getScore()));

            rankingSec.setText(String.valueOf(card.getUser2().getPosition()));
            nameSec.setText(card.getUser2().getName());
            scoreSec.setText(String.valueOf(card.getUser2().getScore()));

            rankingThird.setText(String.valueOf(card.getUser3().getPosition()));
            nameThird.setText(card.getUser3().getName());
            scoreThird.setText(String.valueOf(card.getUser3().getScore()));
        }
        else{
            score.setVisibility(View.INVISIBLE);
            scoreProgress.setVisibility(View.VISIBLE);
            leaderboards.setVisibility(View.INVISIBLE);
            expandLeaderboard.setVisibility(View.INVISIBLE);
            leaderboardProgress.setVisibility(View.VISIBLE);
        }
    }

    private Spannable getStyledTitle(Context context, String title, String coloredTextPart) {
        return spannableFactory.createColorSpan(title,
            ContextCompat.getColor(context, R.color.card_store_title), coloredTextPart);
    }

    public String getTitle(Resources resources) {
        return AptoideUtils.StringU.getFormattedString(
            R.string.timeline_title_card_title_game_quiz_present_singular, resources,
            Application.getConfiguration()
                .getMarketName());
    }

}
