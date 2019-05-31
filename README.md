# Android-countries

Simple Android app made for practice. I used this [REST API](https://restcountries.eu) as my data provider and Retrofit as my networking library.

Note: I'm working on refactoring api calls to coroutines, but still learning how to properly test them. You can see my work so far on this [branch](https://github.com/Rolfrider/Android-countries/tree/networking_coroutines).

App consists of 2 screens(Activity):
- Country browser screen
- Country detail screen

### Country browser screen 

This activity is based on recycler view. Unfortunately the api provides flags in SVG format, which is a bit problematic in Android. That's why I used GlideToVectorYou library to download flag images. 

Of course there is a feature to search country by name, to animate this I implemented DiffUtil. 
Here are the screen shots of it:

<img src="https://github.com/Rolfrider/Android-countries/blob/master/screen_shots/list_sc.png" alt="list_drawing" width="250"/>         <img src="https://github.com/Rolfrider/Android-countries/blob/master/screen_shots/search_sc.png" alt="search_drawing" width="250"/>

Clicking on country takes you to detail screen with shared element(the country flag) transition. 

### Country detail screen

This activity is based on ConstraintLayout. It is build on in three parts: country header with flag and name, Google maps centered on the country and some additional country info. I believe that hiding additional info part for example with swipe gesture or on map touch would massively improve the UX of this screen. Making the map bigger if needed and bringing some live to this activity. I'm looking forward to doing so, I'm just waiting for some free time.

Here is a screen shot of it: 

<img src="https://github.com/Rolfrider/Android-countries/blob/master/screen_shots/country_sc.png" alt="search_drawing" width="250"/>

