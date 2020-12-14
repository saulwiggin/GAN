# GAN BreakingBad TechTest
## About
<p>It simply loads Characters data from API and stores it in persistence storage (i.e SQLite Database). Characters will be always loaded from local database. 
  Remote data(from API) and local data is always synchronized. Also it includes Search character by name and Filter Characters by Appearance features. </p>
  <ul>
  <li>This makes it offline capable 😃.</li>
  </ul>

## Built With 🛠
<p> Kotlin - First class and official programming language for Android development.</p>
<p>Coroutines - For concurrency and multithreading</p>
<p>Android Architecture Components - Collection of libraries that help you design robust, testable, and maintainable apps.</p>
<ul>
  <li>LiveData - Data objects that notify views when the underlying database changes.</li>
  <li>ViewModel - Stores UI-related data that it's survive configuration changes.</li>
  <li>Workmanager - It is for background work that's deferrable and requires guranteed execution.</li>
  <li>ViewBinding - Generates a binding class for each XML layout file present in that module and allows you to more easily write code that interacts with views.</li>
  <li>Room - SQLite object relational mapping library.</li>
  </ul>
<p>Koin - It's a lightweight dependency injection framework for kotlin.<p>
<p>Retrofit - A type-safe HTTP client for Android and Java.</p>
<p>GSON - Gson is a Java library that can be used to convert Java Objects into their JSON representation.<p>
<p>Glide - An image loading library for Android </p>
<p>SafeArgs - To pass data between fragments
<p>Material Components for Android - Modular and customizable Material Design UI components for Android.</p>

## Architecture
<p>This app uses MVVM (Model View View-Model) architecture.</p>

![68747470733a2f2f646576656c6f7065722e616e64726f69642e636f6d2f746f7069632f6c69627261726965732f6172636869746563747572652f696d616765732f66696e616c2d6172636869746563747572652e706e67](https://user-images.githubusercontent.com/58938625/91903352-ff443400-ec9a-11ea-8fd0-853d6336bcf4.png)


