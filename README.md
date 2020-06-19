


# Prerequisits

  - Android SDK 28
  - Build tools 28
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
*
### Technicality

This application uses MVVM design pattern and has the following Layers

- View
- ViewModel(Using liveData to notify views with data)
- Model 
-Clean Architecture (Domain layer+Data layer+Presentation layer)
-Modularization Concept
You can find local unit tests for ViewModel and UseCases under Testing module
