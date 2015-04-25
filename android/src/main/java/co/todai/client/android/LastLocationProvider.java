package co.todai.client.android;

import android.content.Context;
import android.location.Location;

import java.util.List;
import android.location.LocationManager;

/*
 * Small provider just to abstract on how to get last know location
 **/
public final class LastLocationProvider {

  private final LocationManager mLocationManager;

  public LastLocationProvider(final Context context) {
    mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
  }

  /*
   * Returns the most accurate already know location on the phone
   */
  public Location getLastKnownLocation() {
    List<String> matchingProviders = mLocationManager.getAllProviders();

    Location mostAccurate = null;

    for (String provider : matchingProviders) {
      final Location newLocation = mLocationManager.getLastKnownLocation(provider);

      if (newLocation != null) {
        if (mostAccurate == null) {
          mostAccurate = newLocation;
        } else {
          mostAccurate = mostAccurate.getAccuracy() > newLocation.getAccuracy()
                         ?  mostAccurate : newLocation;
        }
      }
    }
    if (mostAccurate == null) {
      // TODO deal with error
    }

    return mostAccurate;
  }
}
