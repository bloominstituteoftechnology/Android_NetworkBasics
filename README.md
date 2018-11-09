# Android_NetworkBasics

## Introduction

This assignment will have you build an app which retreives information from a network interface and display it

## Instructions

### Part 1 - Setup

1. add permission to manifest `<uses-permission android:name="android.permission.INTERNET" />`
2. add `android:usesCleartextTraffic="true"` to application in manifest
    allows HTTP traffic in addition to https in android 9+
    
### Part 2 - Network Adapter

1. Create a new POJO class called `NetworkAdapter`
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
12. If it doesn't match, trow a new `IOException` and pass it the error code.
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
25. In your `MainActivity` write a line of code to call your `httpRequest` method using the url `http://hubblesite.org/api/v3/images/all` and log the result. Make sure you get a reasonable result.
  * Remember, this code needs to be in a separate thread. Wrap that line in a new thread and start it.
  * The quickest way is to use code like this
      `(new Thread(new Runnable() {
       ​     @Override
       ​     public void run() {
       ​         // log code here
       ​     }
        })).start();`

### Part 3 - Data Model
1. Open postman and run a GET command with the url `http://hubblesite.org/api/v3/images/all`
2. Examine the resulting JSON and build a POJO class called `ImageData` to store an image object with the attributes seen in this JSON
3. Write a constructor for your class which accepts a JSON object and pulls data for your object from it using the key values you've seen in postman
  * Each of these actions should be wrapped in a try/catch statement so a single missing field doesn't negate all the data for that object
4. Write getters for all data members. Don't write setters.

### Part 4 - Data Access Object
Now we'll write a class to wrap our generic network adapter to provide access to our specific API.
1. Create a new POJO class called `HubbleDao`
2. Create constant values for the api's base url (`http://hubblesite.org/api/v3`) and one for the url for the base plus all images (`/images/all`)
  * doing this allows you to have a SSOT for each portion of the url without breaking it up too much.
3. Write a `static` method called `getImageList` which returns an array of `ImageData` objects
4. In this method, call your `NetworkAdapter.httpRequest` method and pass it in the full url constant (base plus images)
5. Wrap the returned value in a `JSONArray` constructor and store the result.
6. Create a new array of `ImageData` objects who's length matches the length of the `JSONArray`you just created
7. Loop through the array and pass each element to an `ImageData` constructor and store the result in your new array
8. return your new array
  * you'll have to wrap your JSON interactions in a try/catch statement again. Be sure to have a return statement for every code path (you can return null for failures as long as you check for it in your calling method)
9. Change your test cod in your main activity to use this method and log the results

### Part 5 - Async Task
Now we'll build an activity to use the tools we just wrote
1. Add an `ArrayList` data member to store a list of `ImageData` objects.
2. Write an AsyncTask class which will call the `HubbleDao.getImageList` method in the background
3. Add the resulting array to your list using `addAll(Arrays.asList(array))`
4. In `onPostExecute` append a `String` representation of your `ImageData` objects to the `TextView` in your layout.
5. Remove your previous test code and test your app

### Part 6 - Recycler View
1. Replace the `TextView` in your layout with a `RecyclerView`
  * remember to add `implementation 'com.android.support:recyclerview-v7:28.0.0-rc02'` to gradle file
2. Build a layout which adequately displays data for the user, use at least 3 data members in the list.
3. Build a `ListAdapter` with your `ArrayList` and attach it to your `RecyclerView` as we've done before
4. In your `AsyncTask`, after you add data to you're list you'll have to call `notifyDataSetChanged` on your list adapter to get it to reload the data
5. Test your app
