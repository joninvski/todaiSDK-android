package co.todai.client.android;

import android.content.Context;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.Model;

import java.util.List;
import android.content.pm.PackageManager;
import android.Manifest;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/*
 * Class responsible for the management of the temporary DB that stores events until they are sent to the server.
 *
 * Right now highly coupled to the ORM choosen activeandroid
 * */
public class TodaiEventDB {
  private final Context context;

  public TodaiEventDB(final Context context) {
    this.context = context;
    ActiveAndroid.initialize(context);
  }

  public void store(final Model event) {
    event.save();
    sync(); // Let's try to sync with the server
  }

  public void remove(final Model event) {
    event.delete();
  }

  public void sync() {

    if(isNetworkConnected() == false) {
      return; // We cannot sync if we arent connect to the internet
    }

    List<ActionEvent> allActionEvents = ActionEvent.getAll();
    List<LocationEvent> allLocationEvents = LocationEvent.getAll();

    // TODO
    // for all events go trough each and send it to the server
    // if was received ok just use the remove() method previous decided on the object
    //
    // otherwise, leave it in the database
  }

  /*
   * Checks if the phone is connected to a network
   */
  private boolean isNetworkConnected() {
    boolean canCheckNetworkState = context.checkCallingOrSelfPermission(Manifest.permission.ACCESS_NETWORK_STATE) ==
                                   PackageManager.PERMISSION_GRANTED;

    if (!canCheckNetworkState) return true; // If difficulties in knowing if we have network just say we are connected

    // Check if there is an active network connection
    ConnectivityManager connectivityManager
      = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
    return activeNetworkInfo != null && activeNetworkInfo.isConnected();
  }
}

