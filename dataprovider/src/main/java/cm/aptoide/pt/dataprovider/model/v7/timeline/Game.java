package cm.aptoide.pt.dataprovider.model.v7.timeline;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

import cm.aptoide.pt.dataprovider.model.v7.listapp.App;
import lombok.Getter;

/**
 * Created by franciscocalado on 7/27/17.
 */

public class Game implements TimelineCard {

    @Getter private final String cardId;
    @Getter private final App rightAnswer;
    @Getter private final int gameType;
    @Getter private final String answerURL;
    @Getter private final List<WrongAnswer> wrongAnswer;
    @Getter private final String question;
    @Getter private final GameRankings rankings;
    @Getter private final Ab ab;
    @Getter @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC") private Date timestamp;



    @JsonCreator
    public Game(@JsonProperty("uid") String cardId, @JsonProperty("question") String question, @JsonProperty("questionType") int gameType,
                @JsonProperty("timestamp") Date timestamp, @JsonProperty("app") App rightAnswer, @JsonProperty("answerURL") String answerURL,
                @JsonProperty("wrongAnswer") List<WrongAnswer> wrongAnswer, @JsonProperty("rankings") GameRankings rankings, @JsonProperty("ab") Ab ab) {
        this.ab = ab;
        this.cardId = cardId;
        this.timestamp = timestamp;
        this.rightAnswer = rightAnswer;
        this.answerURL = answerURL;
        this.wrongAnswer = wrongAnswer;
        this.question = question;
        this.rankings = rankings;
        this.gameType = gameType;
    }
}
