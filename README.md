# GetMovies
_This is an Android app that enables you to keep up with the latest movies and tv series, in addition to documentaries._

## Getting Started
For development and testing follow these steps:

1. `Git clone`
2. open folder with `Android Studio`

## Prerequisites
You will need to have installed:

* `java jdk` [follow instructions](http://www.wikihow.com/Install-Oracle-Java-on-Ubuntu-Linux)
* `android studio` [download here](https://developer.android.com/studio/index.html)

## Running Tests
To run test during development, three test dependencies should be added to the build.gradle(Module:app):
* `junit` for unit testing in java
* `robolectric` for unit testing specific to android apps
* `espresso` for instrumentation testing

### Instrumentation testing
This is important in order to test how well the app will work. This testing emulates an android for or uses an android phone with developer options activated.
### Unit testing
This is important in order to test the logic used in the app. This tests the backend bit of the app.

## Built With
* `java` - The backend language
* `android studio` - The IDE for android development
*`XML` - FrontEnd.

## Author
Charles Kakai