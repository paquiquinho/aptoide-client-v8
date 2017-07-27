package cm.aptoide.pt.dataprovider.model.v7.timeline;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * Created by franciscocalado on 7/27/17.
 */

@EqualsAndHashCode
public class WrongAnswer {

    @Getter private final String name;
    @Getter private final String icon;
    @Getter private final String url;

    @JsonCreator
    public WrongAnswer(@JsonProperty("name") String name, @JsonProperty("icon") String icon,
                       @JsonProperty("url") String url) {
        this.name = name;
        this.icon = icon;
        this.url = url;
    }
}
