package cm.aptoide.pt.dataprovider.model.v7.timeline;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;

/**
 * Created by franciscocalado on 7/27/17.
 */

@EqualsAndHashCode
public class GameTimelineItem implements TimelineItem<TimelineCard> {

    private final Game game;

    @JsonCreator
    public GameTimelineItem(@JsonProperty("data") Game game) {
        this.game = game;
    }

    @Override
    public Ab getAb() {
        return this.game.getAb();
    }

    @Override
    public Game getData() {
        return game;
    }
}
