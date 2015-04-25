package co.todai.client.java;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class TodaiClient {

  private final String baseUrl;
  private final TodaiCredentials credentials;
  private final LastLocationProvider lastLocationProvider;
  private final TodaiEventDB todaiEventDB;

  /*
   * Auxiliary Constructor
   */
  public TodaiClient(String userId, String privateKey, Context context) {
    TodaiClient(TodaiConstant.SERVER_ADDRESS + '/' + SERVER_BASE_URL, new TodaiCredentials(userId, privateKey));
  }

  /*
   * Auxiliary Constructor
   */
  public TodaiClient(TodaiCredentials credentials, Context context) {
    TodaiClient(TodaiConstant.SERVER_ADDRESS + '/' + SERVER_BASE_URL, credentials);
  }

  /*
   * Main Constructor
   */
  public TodaiClient(String baseUrl, Credentials credentials, Context context) {
    this.baseUrl = baseUrl;
    this.credentials = credentials;
    this.lastLocationProvider = new LastLocationProvider(context);
    this.todaiEventDB = new TodaiEventDB(context);

    // TODO - Create the rest client (see retrofit here)
  }

  /*
   * Must be called by the SDK user when app start
   *
   * Just put call it in a class that extends Application
   *
   * The function:
   *  1. gets the last know location an uploads it to the server,
   *  2. sends a action event to the server with the special INITIAL_OPEN_APP event
   *  3. Triggers an async task to fetch a more recent location
   * */
  public void init() {
    final Location lastKnowLocation = lastLocationProvider.getLastKnownLocation();
    addLocationEvent(location, location.getTime());
    addActionEventEvent(TodaiConstant.INITIAL_OPEN_APP_EVENT);

    if(locationGoodEnough(lastKnowLocation) == false) {
        // TODO Launch an async task to get a new location
        // that will addLocationEvent with new location when it finds the new one
    }
  }

  /*
   * SDK method to add a location event to todai
   *
   * A location event is just a measurement of where the phone was at a certain time
   * */
  public void addLocationEvent(final Location location, final long timestamp) {
    final LocationEvent locationEvent = new LocationEvent(location, timestamp);
    todaiEventDB.store(locationEvent);
  }

  /*
   * SDK method to add an action event to todai
   *
   * (by action event i mean something that happens like click button or do something)
   *
   * An action event is just a recording of something that happen
   * */
  public void addActionEvent(final String name, final long timestamp) {
    final ActionEvent actionEvent = new ActionEvent(name, timestamp);
    todaiEventDB.store(actionEvent);
  }

  /*
   * Show the base url we are using to talk with the user
   * */
  public String getBaseUrl() {
    return baseUrl;
  }

  /*
   * Checks if the passed location is accurate and recent enough
   */
  private boolean lastLocationGoodEnough(final Location location) {
    return true; // TODO Just a STUB for now
  }
}
