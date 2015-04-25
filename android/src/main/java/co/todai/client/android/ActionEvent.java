package co.todai.client.android;

import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.Model;
import com.activeandroid.query.Delete;
import com.activeandroid.query.Select;

import java.util.List;

/*
 * An action event is an event that indicates an happen to measure by today
 *
 * Could be click a button or do something on the app
 *
 * All the Table/Collumn and com.activeandroid.* stuff is a cool ORM for android to easilly store in DB
 */
@Table(name = "ActionEvent")
public class ActionEvent extends Model {

  @Column
  final String actionName;
  @Column
  final long timestamp;

  public ActionEvent(final String actionName, final long timestamp) {
    this.actionName = actionName;
    this.timestamp = timestamp;
  }

  public static List<ActionEvent> getAll() {
    return new Select()
           .from(ActionEvent.class)
           .execute();
  }
}


