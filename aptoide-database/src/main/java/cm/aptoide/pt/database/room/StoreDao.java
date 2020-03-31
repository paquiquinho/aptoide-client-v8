package cm.aptoide.pt.database.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Observable;
import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao public interface StoreDao {

  @Query("SELECT * from store") Observable<List<RoomStore>> getAll();

  @Query("SELECT * from store where storeName = :storeName") Observable<RoomStore> getByStoreName(
      String storeName);

  @Query("SELECT * from store where storeName = :storeId") Observable<RoomStore> getByStoreId(
      long storeId);

  @Query("SELECT COUNT(*) from store where storeName = :storeId") Observable<Integer> isSubscribed(
      long storeId);

  @Query("DELETE FROM store where storeName = :storeName") void removeByStoreName(String storeName);

  @Insert(onConflict = REPLACE) void insert(RoomStore store);

  @Query("SELECT COUNT(*) FROM store") Observable<Long> countAll();

  @Insert(onConflict = REPLACE) void saveAll(List<RoomStore> storeList);
}
