# Eurail

This Android appl is developed for the recruitment process at Eurail.


## CI/CD
I integrated "GitHub Actions" and wrote the script that runs the tests as well as the build and generation of an apk and make it available for download. This script is triggered automatically after each push.![alt text](https://scontent.ftun1-1.fna.fbcdn.net/v/t1.15752-9/82792547_2418986781698774_487581540893589504_n.png?_nc_cat=102&_nc_oc=AQkBb_L-r8RutjBBUExqci43OTQ9j1udGMxg2crfNZES4QuHOmpl1NYhQ52wAT_qV84&_nc_ht=scontent.ftun1-1.fna&oh=72a51f1e29ccf692e94d131d81761ffa&oe=5EA3B4F5)

![alt text](https://scontent.ftun1-1.fna.fbcdn.net/v/t1.15752-9/82766903_785470041933487_4333706814383194112_n.png?_nc_cat=103&_nc_oc=AQnoRjqwYwZZDdIpixozrpPSQdDf83q6L2wKZ-mEC6ZVQqPiF8LFcmf6uwc08-DouiE&_nc_ht=scontent.ftun1-1.fna&oh=235b00c155877e6a1c982fbeec6f7bf6&oe=5EA0F75D)

![alt text](https://scontent.ftun1-1.fna.fbcdn.net/v/t1.15752-9/82165400_211645443190102_7796144019163054080_n.png?_nc_cat=108&_nc_oc=AQkzueMdMsqBZr5lo3feeGgljOzUa9_Ybzqo0UoSXNLhgHYGyrfkH3llDcJ0NtWur7Y&_nc_ht=scontent.ftun1-1.fna&oh=afb4a61950592281123141d7a0c50279&oe=5E8F48D3)

## Run the app
To run the app on your device or emulator, just click on the run button in Android Studio once the project is cloned on your machine.\
![alt text](https://scontent.ftun1-1.fna.fbcdn.net/v/t1.15752-9/83339050_190016038717673_3041085232116662272_n.png?_nc_cat=111&_nc_oc=AQnQO7RJ2BLpn3A0C2GedpEJZChJN9QP6PYNFCjHSkexCl6xjZo8y41n3etgCuCsmf8&_nc_ht=scontent.ftun1-1.fna&oh=1284514edeab3057f92f1bbf6d27d4c6&oe=5EDACE61)

You can also run the app by downloading the apk file previously generated on your smartphone and install it.

## Features

This application starts with a splash screen displaying an animation of Eurail's logo, and then it offers the possibility to navigate between four different screens: 
- Map
- Countdown
- Storage
- WebView

The navigation is provided by the bottom nav bar. This choice has been made because I think that it is more user friendly for the app's features.

The map screen displays a map and centers the camera at the device's location, shows a pin in random proximity and the distance between these two places. The random generated locations are stored in the local SQLI database using "room" library.
The second screen displays a loading animation and a countdown from 10 to 1 with half a second delay between each number. It behaves normally when the device is rotated.

The storage screen contains an edit text, a text view and a button. The click on the button will save the edit text's value in the Shared Preferences and show it in the text view.

The last screen contains a webview which loads "https://www.eurail.com/en/get-inspired" . Once the loading is finished, the number of "div" tags in the loaded html page is displayed on the top of the screen. The calculation of the occurences of "div" tags is done by the injection of a javascript code in our webview. I have tested another method and it returns the same result. 
  

If internet connection is not ok, an animation is displayed which indicates the absence of the internet connection.

## Screenshots

Splash screen\
![alt text](https://scontent.ftun1-1.fna.fbcdn.net/v/t1.15752-9/87504782_188047572504448_3522858409562996736_n.png?_nc_cat=106&_nc_oc=AQknmmDg1QfP3ENdSVCgyC55wcU1SRUqbmwVYzIC1fonzRME05wLTHi4NJInGNvc1Oc&_nc_ht=scontent.ftun1-1.fna&oh=7695f323b1ff324605843a652be242b6&oe=5EC1B8EC)

Map view\
![alt text](https://scontent.ftun1-1.fna.fbcdn.net/v/t1.15752-9/87495385_790613961451045_3017386556060073984_n.png?_nc_cat=111&_nc_oc=AQmcqtuylqen19PXPruxDgtgUMrKxygm6JBhxI8Iez3lOfRAkcYMuRBYWAgs7JoLP4A&_nc_ht=scontent.ftun1-1.fna&oh=8cce393f389a4e64451dcae741d2815e&oe=5EF05141)

Countdown screen\
![alt text](https://scontent.ftun1-1.fna.fbcdn.net/v/t1.15752-9/87450021_1257100421167165_1738672236811780096_n.png?_nc_cat=111&_nc_oc=AQlPNcn3tgJ8YjwJzIvgRFb43Wy02iyFViUiINSHwEaKGYUNvSTwlVH6Wu5gWClzJIM&_nc_ht=scontent.ftun1-1.fna&oh=5bac2f847fd39830ac90f77755f20ff4&oe=5EFF4EE9)

Storage screen\
![alt text](https://scontent.ftun1-1.fna.fbcdn.net/v/t1.15752-9/87394565_194467115122211_1346525809833148416_n.png?_nc_cat=111&_nc_oc=AQlDAV4xcONOahp-0WgFgyjKcbMxHcf58IaCkKFxlvJHqucwdgBfkMRBRq85pItN94Y&_nc_ht=scontent.ftun1-1.fna&oh=2ec431847ae77d99372dd1b2b82c79e4&oe=5F00974A)

WebView\
![alt text](https://scontent.ftun1-1.fna.fbcdn.net/v/t1.15752-9/87472202_143584226798227_838343312275406848_n.png?_nc_cat=102&_nc_oc=AQlRZLgvDEL-FAD1AKfni8-UMrTd-VNRhBuzUrEKLgxMyA-zMgK3PrWUiYeLRJELBPU&_nc_ht=scontent.ftun1-1.fna&oh=fbc6e5556328a848ade601ce32466e29&oe=5EF3144C)

Internet problem\
![alt text](https://scontent.ftun1-1.fna.fbcdn.net/v/t1.15752-9/87585179_618747015628488_9062808541374447616_n.png?_nc_cat=103&_nc_oc=AQlHt8kdDAdA3gXjqpUbKFjd6bS-EFid7DfgvZHzB2hyOw_SUYEf6ksDIwunq_EyR9o&_nc_ht=scontent.ftun1-1.fna&oh=5c4d89452bd33865aef16f175007dbc6&oe=5EF610CE)




## Libraries
Libraries used are: 
- Google Maps for the map view.
- Lottie for displaying GIFs.
