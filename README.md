


# Prerequisits

  - Android SDK 29
  - Build tools 29.0.3
Install the dependencies and build apk in (Debug environment)
### Dependencies

* [RetroFit] - for Networking
* [MVVM]
* Kotlin
* Coroutines
* Android Navigation New Architecture component
* ExoPlayer in order to support HLS (HTTP Live Streaming)
* [Android LifeCycle Extension] - Android ViewModel lifecycle aware &LiveData
* [DataBinding]
* Mockito
### Technicality

This application uses MVVM design pattern and has the following Layers

-View
-ViewModel(Using liveData to notify views with data)
-Model 
-Clean Architecture (Domain layer+Data layer+Presentation layer)
-Modularization Concept
-Mocking Api through mySandBox

You can find local unit tests for ViewModel and UseCases under Testing module
