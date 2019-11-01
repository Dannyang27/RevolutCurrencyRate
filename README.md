# RevolutCurrencyRate
Revolut Currency Exchange App. Developed entirely with Kotlin.

### Task:
</br>
The app must download and update rates every 1 second using API
https://revolut.duckdns.org/latest?base=EUR

List all currencies you get from the endpoint (one per row). Each row has an input where you
can enter any amount of money. When you tap on currency row it should slide to top and its
input becomes first responder. When you’re changing the amount the app must simultaneously
update the corresponding value for other currencies.

<img src="https://github.com/Dannyang27/RevolutCurrencyRate/blob/master/readmefiles/revolut_gif.gif" width="250" height="450">
  
</br>
The App follows MVVM principles where we observe in this case the RoomDatabase object, and when we have the data we post it so the view can acknowledge the data is ready and process it. This could have been done in a way we don't use Room Database but I added it in order to prove you my knowledge.

</br>
<img src="https://github.com/Dannyang27/RevolutCurrencyRate/blob/master/readmefiles/mvvm.png" width="60%">

# Main Libraries
* Picasso
* Butter Knife
* Coroutines + Room
* Coroutines + Retrofit
