package cm.aptoide.pt.v8engine.social.leaderboard.data;

import cm.aptoide.pt.dataprovider.ws.v7.post.GetLeaderboardEntriesResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by franciscocalado on 8/18/17.
 */

public class LeaderboardEntryMapper {

  public List<LeaderboardEntry> map(List<GetLeaderboardEntriesResponse.User> list){
    List<LeaderboardEntry> result = new ArrayList<LeaderboardEntry>();
    LeaderboardEntry entry;

    for(GetLeaderboardEntriesResponse.User user : list){
      entry = new LeaderboardEntry(user.getName(),user.getPosition(),user.getScore());
      result.add(entry);
    }

    return result;
  }
}
