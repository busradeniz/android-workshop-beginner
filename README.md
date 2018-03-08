# Task 1 - Creating Project and Dependencies 

As first task of workshop, we'll create project in Android Studio and get familiar to Android project structure, gradle and add some third party libraries as dependency.


##  1- Creating Android Project 
* Open Android Studio -> Start a new Android Project 

![MacDown Screenshot](https://github.com/busradeniz/android-workshop-beginner/blob/task1/img/1.png)

* Edit Application Name, Package Name and Project Location - > Next

![MacDown Screenshot](https://github.com/busradeniz/android-workshop-beginner/blob/task1/img/2.png)

* Select "Phone and Tablet" -> API 23 or higher versions -> Next

![MacDown Screenshot](https://github.com/busradeniz/android-workshop-beginner/blob/task1/img/3.png)

* Select "Empty Activity" -> Next

![MacDown Screenshot](https://github.com/busradeniz/android-workshop-beginner/blob/task1/img/4.png)


* Configure Activity -> Finish

![MacDown Screenshot](https://github.com/busradeniz/android-workshop-beginner/blob/task1/img/5.png)

* The initial project 

![MacDown Screenshot](https://github.com/busradeniz/android-workshop-beginner/blob/task1/img/6.png)


##  2 - Adding Dependencies 
 * Open app-level ```build.gradle``` file (under app folder) and add following dependencies : 
 
 ```
  implementation 'com.squareup.retrofit2:retrofit:2.3.0'
  implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
  implementation 'com.android.support:recyclerview-v7:27.1.0'
  implementation 'com.github.bumptech.glide:glide:4.6.1'
  annotationProcessor 'com.github.bumptech.glide:compiler:4.6.1'
  
 ```
 
 ![MacDown Screenshot](https://github.com/busradeniz/android-workshop-beginner/blob/task1/img/7.png)
 
##  3 - Creating Emulator & Running Application 
* Click to Run button on top bar

![MacDown Screenshot](https://github.com/busradeniz/android-workshop-beginner/blob/task1/img/8.png)

* Click "Create New Virtual Device" 

![MacDown Screenshot](https://github.com/busradeniz/android-workshop-beginner/blob/task1/img/9.png)

*  Configure your Emulator 

![MacDown Screenshot](https://github.com/busradeniz/android-workshop-beginner/blob/task1/img/10.png)
![MacDown Screenshot](https://github.com/busradeniz/android-workshop-beginner/blob/task1/img/11.png)
![MacDown Screenshot](https://github.com/busradeniz/android-workshop-beginner/blob/task1/img/12.png)

* After clicking Finish, you should see your emulator on "Select Deployment Target"  window. Select your emulator , the project will start to run 

![MacDown Screenshot](https://github.com/busradeniz/android-workshop-beginner/blob/task1/img/13.png)
![MacDown Screenshot](https://github.com/busradeniz/android-workshop-beginner/blob/task1/img/14.png)




