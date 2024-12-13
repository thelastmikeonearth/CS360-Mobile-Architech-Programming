# CS360-Mobile-Architech-Programming
1. Briefly summarize the requirements and goals of the app you developed. What user needs was this app designed to address?
* The goal of the app I developed is to provide a platform where users can track their weight and monitor their progress against a goal weight.
* The requirements of the app were to display the user's weight over time, give the user the ability to add and delete weight measurements, allow the user to set a weight goal, and notify the user when they reach their weight goal. The app also needed to support multiple users.
* The app was designed to address the user need to track weight over time and meet a goal.
  
2. What screens and features were necessary to support user needs and produce a user-centered UI for the app? How did your UI designs keep users in mind? Why were your designs successful?
* Login screen: This screen allows users to either create a new username and password or login with an existing usesrname and password.
* Weight tracking screen: This screen shows the users weight history and allows the user to delete weight measurements.
* Add weight screen: This screen allows users to enter new weights for the date of their choosing.
* Set goal weight screen: This screen asks the user for permission to send notifications and allows the user to set a goal weight.
* My UI design kept users in mind by providing modern and easy to use screens. My UI decisions were successful but there is a consistent design theme that is used throughout the application.

3. How did you approach the process of coding your app? What techniques or strategies did you use? How could those techniques or strategies be applied in the future?
* This was my first time developing an Android app. I made two activities, one for the login screen and another for the weight tracking features. The weight tracking activity included three different fragments, one for looking at the weight data, another for entering new weight information, and a third for adding a weight goal. I used shared preferences to keep track of and share user information throughout the application. I think this strategy ended up being successful. A lot of the fragments needed to share information so it was good to have them in the same activity.

4. How did you test to ensure your code was functional? Why is this process important, and what did it reveal?
* I tested my code by running the application after each change I made to ensure that the requirements of that feature had been met. I often found that my code included small mistakes, such as SQL mistakes in my DatabaseHelper class. The testing process is important because there are almost always mistakes in code. Runtime errors are usually easy to find through manual testing. In the end, the project was successfully built into a .apk which was another way of checking for errors.

5. Consider the full app design and development process from initial planning to finalization. Where did you have to innovate to overcome a challenge?
* I decided to use an expanding FAB for navigation. This was not a built-in activity in Android Studio and required me to create a custom navigation menu. This was one of the more challenging designs decisions I made, but I think it provides a good experience for the user.

6. In what specific component of your mobile app were you particularly successful in demonstrating your knowledge, skills, and experience?
* The weight tracking page was the component of the app where I was most successful in demonstrating my knowledge, skills, and experience. That page required a database call to get all the weight information for a particular user. Once the information was retrieved, the table had to be updated to display all the historical weight data. Each row in the data included a delete button. When a weight datapoint is deleted, the data refreshes and the new data is displayed to the user. This page also includes a FAB navigation menu, allowing the user to view the other pages of the application.
