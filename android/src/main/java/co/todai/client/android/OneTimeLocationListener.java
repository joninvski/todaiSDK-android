package co.todai.client.java;

import android.content.Context;
import android.location.Location;

import java.util.List;

public final class LastLocationProvider {

  private final LocationManager mLocationManager;

  private OneTimeLocationListener(Context context) {
    mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
  }

  public Location getLastKnownLocation() throws NoProviderException {
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
      throw new NoProviderException("No provider was available");
    }

    return mostAccurate;
  }
}
