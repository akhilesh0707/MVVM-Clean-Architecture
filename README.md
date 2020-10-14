[![Akhilesh StackOverflow](https://img.shields.io/badge/Akhilesh-StackOverflow-orange.svg?style=for-the-badge)](https://stackoverflow.com/users/1548824/akhilesh0707)
[![Akhilesh LinkedIn](https://img.shields.io/badge/Akhilesh-LinkedIn-blue.svg?style=for-the-badge)](https://www.linkedin.com/in/akhilesh0707/)

# MVVM Clearn Architecture 
An Android Clean Architecture app written in Kotlin, using Rxjava, Android Architecture Components and used Dagger2 for Dependency Injection.

## Architecture
Uses concepts of the notorious Uncle Bob's architecture called [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html).</br>

* Better separation of concerns. Each module has a clear API., Feature related classes life in different modules and can't be referenced without explicit module dependency.
* Features can be developed in parallel eg. by different teams
* Each feature can be developed in isolation, independently from other features
* faster compile time

## Modules:
* **truecaller-ui** - It uses all the components and classes releated to Android Framework. It gets the data from presentation layer and shows on UI. (**access all the modules**)
* **data** - The data layer implements the repository interface that the domain layer defines. This layer provide a single source of truth for data. (Kotlin module that **can only access domain module**)
* **remote** - Handles data interacting with the network. (**can only access data module**)
* **domain** - The domain layer contains the UseCases that encapsulate a single and very specific task that can be performed. This task is part of the business logic of the application. (Kotlin module that **cannot access any other module**)
* **presentation** - MVVM with ViewModels exposing LiveData that the UI consume. The ViewModel does not know anything about it's consumers. (Android module that **can only access domain module**)

## Tech Stack:
* [RX Java][1] A library for composing asynchronous and event-based programs using observable sequences for the Java VM.
* [RX Kotlin][2] RxJava bindings for Kotlin.
* [RX Android][3] RxJava bindings for Android.
* [ViewModel][12] - Stores UI-related data that isn't destroyed on UI changes. 
* [LiveData][4] for reactive style programming (from VM to UI).
* [Dagger2][5] for dependency injection.
* [Retrofit][6] for REST api communication.
* [OkHttp][14] HTTP client that's efficient by default: HTTP/2 support allows all requests to the same host to share a socket
* [Gson][13] used to convert Java Objects into their JSON representation and vice versa.
* [Timber][7] for logging.
* [Espresso][8] for UI tests.
* [Mockito-Kotlin][9] for mocking in tests.
* [MockWebServer][10] for Instrumentation tests.
* [AndroidX Test Library][11] for providing JUnit4 and functions as `launchActivity` in UI tests

[1]:  https://github.com/ReactiveX/RxJava
[2]:  https://github.com/ReactiveX/RxKotlin
[3]:  https://github.com/ReactiveX/RxAndroid
[4]:  https://developer.android.com/topic/libraries/architecture/livedata
[5]:  https://github.com/google/dagger
[6]:  https://github.com/square/retrofit
[7]:  https://github.com/JakeWharton/timber
[8]:  https://developer.android.com/training/testing/espresso/
[9]:  https://github.com/nhaarman/mockito-kotlin
[10]: https://github.com/square/okhttp/tree/master/mockwebserver
[11]: https://github.com/android/android-test
[12]: https://developer.android.com/topic/libraries/architecture/viewmodel
[13]: https://github.com/google/gson
[14]: http://square.github.io/okhttp/

## Testing
Currently covered domain, data, presentation and remote module with moderate test coverage 

## TODO
1. Instrumentation test cases.
2. More unit test cases 
3. Improve UI
4. Internet connectivity
