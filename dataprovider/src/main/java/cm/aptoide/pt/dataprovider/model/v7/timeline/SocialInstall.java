package cm.aptoide.pt.dataprovider.model.v7.timeline;

import cm.aptoide.pt.dataprovider.model.v7.Comment;
import cm.aptoide.pt.dataprovider.model.v7.listapp.App;
import cm.aptoide.pt.dataprovider.model.v7.store.Store;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;

/**
 * Created by jdandrade on 15/12/2016.
 */
public class SocialInstall extends SocialCard implements TimelineCard {

  private final String cardId;
  private final App app;
  private final Ab ab;
  private final SocialCardStats stats;
  private final Store store;
  private final Comment.User user;
  private final Comment.User userSharer;
  private final Date date;

  @JsonCreator
  public SocialInstall(@JsonProperty("uid") String cardId, @JsonProperty("apps") List<App> apps,
      @JsonProperty("ab") Ab ab, @JsonProperty("user_sharer") Comment.User userSharer,
      @JsonProperty("user") Comment.User user, @JsonProperty("likes") List<UserTimeline> likes,
      @JsonProperty("comments") List<CardComment> comments,
      @JsonProperty("stats") SocialCardStats stats, @JsonProperty("my") My my,
      @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC") @JsonProperty("date") Date date,
      @JsonProperty("store") Store store, @JsonProperty("urls") Urls urls) {
    super(likes, comments, my, urls);
    this.ab = ab;
    this.date = date;
    this.cardId = cardId;
    this.user = user;
    this.userSharer = userSharer;
    this.stats = stats;
    this.store = store;
    if (!apps.isEmpty()) {
      this.app = apps.get(0);
    } else {
      this.app = null;
    }
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $cardId = this.cardId;
    result = result * PRIME + ($cardId == null ? 43 : $cardId.hashCode());
    final Object $ab = this.ab;
    result = result * PRIME + ($ab == null ? 43 : $ab.hashCode());
    final Object $stats = this.stats;
    result = result * PRIME + ($stats == null ? 43 : $stats.hashCode());
    final Object $store = this.store;
    result = result * PRIME + ($store == null ? 43 : $store.hashCode());
    final Object $user = this.user;
    result = result * PRIME + ($user == null ? 43 : $user.hashCode());
    final Object $userSharer = this.userSharer;
    result = result * PRIME + ($userSharer == null ? 43 : $userSharer.hashCode());
    final Object $date = this.date;
    result = result * PRIME + ($date == null ? 43 : $date.hashCode());
    return result;
  }

  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof SocialInstall)) return false;
    final SocialInstall other = (SocialInstall) o;
    if (!other.canEqual((Object) this)) return false;
    final Object this$cardId = this.cardId;
    final Object other$cardId = other.cardId;
    if (this$cardId == null ? other$cardId != null : !this$cardId.equals(other$cardId)) {
      return false;
    }
    final Object this$ab = this.ab;
    final Object other$ab = other.ab;
    if (this$ab == null ? other$ab != null : !this$ab.equals(other$ab)) return false;
    final Object this$stats = this.stats;
    final Object other$stats = other.stats;
    if (this$stats == null ? other$stats != null : !this$stats.equals(other$stats)) return false;
    final Object this$store = this.store;
    final Object other$store = other.store;
    if (this$store == null ? other$store != null : !this$store.equals(other$store)) return false;
    final Object this$user = this.user;
    final Object other$user = other.user;
    if (this$user == null ? other$user != null : !this$user.equals(other$user)) return false;
    final Object this$userSharer = this.userSharer;
    final Object other$userSharer = other.userSharer;
    if (this$userSharer == null ? other$userSharer != null
        : !this$userSharer.equals(other$userSharer)) {
      return false;
    }
    final Object this$date = this.date;
    final Object other$date = other.date;
    if (this$date == null ? other$date != null : !this$date.equals(other$date)) return false;
    return true;
  }

  protected boolean canEqual(Object other) {
    return other instanceof SocialInstall;
  }

  public String getCardId() {
    return this.cardId;
  }

  public App getApp() {
    return this.app;
  }

  public Ab getAb() {
    return this.ab;
  }

  public SocialCardStats getStats() {
    return this.stats;
  }

  public Store getStore() {
    return this.store;
  }

  public Comment.User getUser() {
    return this.user;
  }

  public Comment.User getUserSharer() {
    return this.userSharer;
  }

  public Date getDate() {
    return this.date;
  }
}
