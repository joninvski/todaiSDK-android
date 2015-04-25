Todai Java Clients
===================

[![Build Status](https://travis-ci.org/joninvski/todaiSDK-android.svg?branch=master)](https://travis-ci.org/joninvski/todaiSDK-android)

### Build From Source

1. `export JAVA_HOME=<path to Java>` (Windows: `set JAVA_HOME=<path to Java>`)
1. `./gradlew build` (Windows: `gradlew.bat build`)
1. Jars will be built and deposited into the various `build/libs` directories (e.g. `java/build/libs`, `android/build/libs`). You can then use these jars just as if you had downloaded them.

## Usage

### Initialization

On your Android class that extends Android just start the todai sdk client with:

```java
  TodaiClient tc = TodaiClient("MyUserId", "Mysecret pass", this);
  tc.init(); // This gets the last known location, goes and fetch a more recent one, and sends a special action event to mark that the app has started.
```

### Send Events to todai IO

Here's a very basic example for an app a action event:

```java
    tc.addActionEvent("NameOfTheEvent", timestampOf the event)
```

That's it!


## TODO:

1. Create a timer to sync the DB to the server periodically. Check ``TodaiEventDB``` class  method ```sync()```

2. Implement the rest calls in an assynchronous manner (i recommend retrofit, it is easy)

3. Events have to be unique (a uuid p.ex) so that if they are uploaded twice, the server rejects the second time

4. Putting in travis-ci for auto building

5. A sample app
