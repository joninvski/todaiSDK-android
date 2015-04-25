package co.todai.client.android;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.Model;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;
import android.location.Location;

/*
 * An LocationEvent is an event that indicates a location the phone was at a certain time
 *
 * All the Table/Collumn and com.activeandroid.* stuff is a cool ORM for android to easilly store in DB
 */
@Table(name = "LocationEvent")
public class LocationEvent extends Model {

  @Column
  final Location location;
  @Column
  final long timestamp;

  public LocationEvent(final Location location, final long timestamp) {
    this.location = location;
    this.timestamp = timestamp;
  }

  public static List<LocationEvent> getAll() {
    return new Select()
           .from(LocationEvent.class)
           .execute();
  }
}

