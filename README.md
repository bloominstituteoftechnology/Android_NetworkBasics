# Network Basics

## Introduction

This project will have you interface with the simple API of a web comic [xkcd.com](). You will build a complete app which allows a user to browse the current and previous comics on this site.

## Instructions

### Part 1 - Setup

1. add permission to manifest `<uses-permission android:name="android.permission.INTERNET" />`
2. add `android:usesCleartextTraffic="true"` to application in manifest
    allows HTTP traffic in addition to https in android 9+

### Part 2 - Network Adapter
#### Request for JSON Response
1. Create a java class called `NetworkAdapter`
2. Create a `static` method called `httpRequest`, this will accept a `String` url and a `String` request type.
3. Create 3 data members in this method, a `String` for your result, an `InputStream`, and an `HttpUrlConnection`
4. Network connections can result in a number of exceptions, create a try block for the rest of the method.
5. *Inside the try block* Create and store a new `URL` object, passing in the url `String` parameter to the constructor.
6. Call `url.openConnection()` and store is in your connection field.
7. Create an `int` constant as a `class` data member for `TIMEOUT` and set the value to 3000.
8. Call `setReadTimeout` and `setConnectTimeout` on your connection object, passing in your `TIMEOUT` field for both.
9. Call `connect` on the connection object.
10. Retreive and store the `int` response code by calling `getResponseCode` on your connection object.
11. Check that value to make sure that it matches `HttpsURLConnection.HTTP_OK`.
  * This response code tells us that the request was processed correctly
12. If it doesn't match, throw a new `IOException` and pass it the error code.
13. You'll now need to create a catch block to handle this exception and provide a value for result
14. *Back in the try block after checking the result code* store the `InputStream` from the connection using `getInputStream`
15. Check if the stream is null. If not, create a new `InputStreamReader` object using your `InputStream`
16. Use that `InputStreamReader` object to construct a new `BufferedReader` object and store it as `reader`
17. Create a new `StringBuilder` object
18. Call `readLine` on your `reader` object and store the result
19. Write a while loop that will loop until the read line isn't null. Inside the loop, add the line to your `StringBuilder` and then read the next line.
20. Convert your `StringBuilder` to a `String` and store it in your result `String`
21. After your catch blocks, create a finally block.
22. In this block, check to see if the stream is null. If not, call `stream.close` 
  * You'll need to wrap this in a try/catch block
23. Afterward, if connection isn't null, call `disconnect` on it.
24. Return your result `String`
25. In your `MainActivity` write a line of code to call your `httpRequest` method using the url `https://xkcd.com/info.0.json` and log the result. Make sure you get a reasonable result.
  * Remember, this code needs to be in a separate thread. Wrap that line in a new thread and start it.
  * The quickest way is to use code like this
      `(new Thread(new Runnable() {
       ​     @Override
       ​     public void run() {
       ​         // log code here
       ​     }
        })).start();`
#### Request for Image Response
This app will display a web comic image. The previous method will retreive a JSON object which will give us the URL for the desired image along with other data about the image. Now that we have the data and the image URL, we need to get the image file itself. Surprisingly, this is easier than getting the JSON text
1. Write a static method called `httpImageRequest` which accepts a `String` and returns a `Bitmap`
2. Follow the steps for writing your standard httpRequest until checking the result code (through step 14)
3. Instead of building a string response with a buffer, pass it to the static method `BitmapFactory.decodeStream` this will return to you a `Bitmap` object as a result
4. Finish the method with steps 21 - 24 of the previous section and return the `Bitmap` image

### Part 3 - Data Model
1. Open postman and run a GET command with the url `https://xkcd.com/info.0.json`
2. Examine the resulting JSON and build a POJO class called `XkcdComic` to store an image object with the attributes seen in this JSON
3. Write a constructor for your class which accepts a JSONObject and pulls data for your object from it using the key values you've seen in postman
  * Each of these actions should be wrapped in a try/catch statement so a single missing field doesn't negate all the data for that object
> If a value in JSON is wrapped in quotes, it is a string
> Retreiving a value from a JSONObject is similar to retreiving it from an intent, call `getString` on the JSONObject for whatever type you want to retreive and pass in the key value (the green text in postman) as a string

4. Write getters for all these data members. Don't write setters.
5. Add another data member of type `Bitmap` write a getter and a setter for this member

### Part 4 - Data Access Object
Now we'll write a class to wrap our generic network adapter to provide access to our specific API. The goal for a data access object is to encapsulate all the tasks that a developer could reasonably want to perform when interacting with the corredsponding database. In this case, we want the user to be able to perform 4 tasks:
1. Get the most recent comic
2. Get the next comic
3. Get the previous comic
4. Get a random comic
We will write a method for each of these tasks, but first, we must write a method with all the shared code for those tasks
1. Create a new java class called `XkcdDao`
2. Create constant values for the api's base url (`https://xkcd.com/`), one for the url ending (`info.0.json`), one to get the most recent comic which is the previous two concatenated, and one for retreiving a specific comic which is generated by concatenating the base and the ending and putting an id in the middle, we can do this by placing `%d/` between the two other strings which will allow us to replace the value using `String.format`
  * doing this allows you to have a SSOT for each url you'll be using.
3. First, we'll write the shared method.
4. Write a `private static` method called `getComic` which accepts a `String` url and returns a single `XkcdComic` object
5. In this method, first call your `NetworkAdapter.httpRequest` method and pass it in the url parameter
6. Wrap the returned value in a `JSONObject` constructor and store the result.
7. Pass your JSONObject into the `XkcdComic` constructor
  * you'll have to wrap your JSON interactions in a try/catch statement again. Be sure to have a return statement for every code path (you can return null for failures as long as you check for it in your calling method)
8. Now that you have pulled the data for your comic, call your `httpImageRequest` method and pass it in for the comic's `img` attribute
  * you'll see when looking at postman, that the `img` attribute is the url for the image resource
9. Take the resulting `Bitmap` and pass it into your comic's `setImage` method
Now that we have the method which does our heavy lifting, we can write our wrapper methods which the user has access to
10. First, we'll write the `getRecentComic` method.
11. This is also `static` but will be `public`.
12. Call `getComic` and pass in your `RECENT_COMIC` constant value.
13. return the result
14. `getNextComic` and `getPreviousComic` will both accept an `XkcdComic` object.
15. They will pull the `num` value (remember, `num` must be an int as shown in the JSON in postman) and either add one or remove one from it.
16. Take the new value and stitch it into your `SPECIFIC_COMIC` constant value using `String.format`
17. Pass the result into `getComic` and return whatever it gives you
18. The last one is a bit more complicated, we need to generate a random comic id between the oldest and newest ids
19. Add a `public static` data member to the class called `maxComicNumber`
20. Go into your `getRecentComic` method. After getting the comic and before returning it. Store its `num` value in your new data member
  > if you look experiment with the xkcd urls, you will find that there is no comic for id 0 and all the ids increment with each new comic, we'll need to know this for the next part

21. We can generate a random number by calling the static method `Math.random()` this will return a value between 0.0 and 1.0. Think of this as a percent value. Multiply that number by your max and you now have a random number between 0 and your max! However, we don't want 0 so let's add 1 to the result
  > Math.random() will not return 1.0 as a result and as such, multiplying by your maximum will never result in your max value, so we are safe adding 1 to the result. Now the most recent comic could possibly come up in a random search

22. Take your random number, put it into the URL, pass it to your `getComic` and return the result

### Part 5 - Main Activity
Now we'll build an activity to use the tools we just wrote
1. Build a layout with 3 buttons and an imageview (I used a `BottomNavigationActivity` to get the look that I wanted and you are welcome to do so if you want to experiment with it)
2. Add a textview for a title and any other information you with to diaplay (Xkcd alt text is usually fun to read and expands upon the comic)
3. Write a method called `updateUI` which accepts a `XkcdComic` object and uses it's data to populate the user interface
4. In order to prevent bugs, you'll want to disable your previous and next buttons when your are viewing the first and last comics respectively
5. In your `onCreate` method, write a simple thread to call the `getRecentComic` method from your DAO class and update the UI with the result. Remember to only call your `updateUi` method from the ui thread
6. If that works you know your backend is GTG.
7. Add listeners to your previous, next, and random buttons to call their respective methods (in separate threads) and test them.

## Challenge
* Wrap your DAO in the MVVM architecture to allow the user to rotate their screen to better view the comic they are currently on
* Change your simple threads to `AsyncTasks`
* Add additional QOL (quality of life) features to your app like allowing the user to enter a comic id they want
