Tomorrow Home Assignment
========================   

## Architecture

#### Clean Architecture + MVVM + Compose + Hilt + Retrofit

## Summary

Dear Tomorrow's Bank Team,

I am pleased to share with you an application that incorporates several cutting-edge technologies,
including MVVM, Jetpack Compose and Clean Architecture principles.

Although I have not created separate modules for features and components such as DI and Navigation
due to time constraints, it would be the recommended approach for better modularity and scalability.

The application is divided into 3 essential parts, namely the data, domain, presentation layers.
Currently, the schema of the app follows the pattern
of `Compose View > ViewModel -> Use Case -> Repository -> DataSource -> API`

If given more time, I would like to make the following improvements:

- Develop comprehensive testing methods for the application, including ViewModel, UseCases tests for
  increased robustness and reliability.
- Introduce multi-module architecture to facilitate the development process and maintain separation
  of concerns.
- Add database to store the weather information from API and add a direct subscription to DB for
  ViewModel
- Probably, I would implement something like Work Manager to constantly update the weather when the
  app is in background/or for widgets.  