 <p align="center">
 <img src="https://github.com/theDIRone/T3/blob/dev/assets/t3.png" height= 500 width = 1200 />
</p>

# T3 🎮

T3 is a multiplayer real-time Tic-Tac-Toe game that uses the Socket Programming internally. In simple, this app demonstrates a real-world application of socket programming in Kotlin without using any external server.

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-0095D5?&style=for-the-badge&logo=kotlin&logoColor=white)
<br>
![Minimum API Level](https://img.shields.io/badge/Min%20API%20Level-24-green)
![Maximum API Level](https://img.shields.io/badge/Max%20API%20Level-33-orange)
![GitHub repo size](https://img.shields.io/github/repo-size/theDIRone/T3)
[![License](https://img.shields.io/badge/license-MIT-%2397ca00.svg)](https://github.com/theDIRone/T3/blob/develop/LICENSE)
[![Open Source Love svg1](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/) 
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg?style=flat-square)](http://makeapullrequest.com) 
![contributions welcome](https://img.shields.io/static/v1.svg?label=Contributions&message=Welcome&color=0059b3&style=flat-square) 
![Maintenance](https://img.shields.io/maintenance/yes/2021)


## Here is the basic instruction about app functionality,
* To enjoy this game you need connect to a common wifi connection.

* **Host Game** 🧩
> Here as a player, you can host the game. After clicking on the "Host Game" button a QR will appear. Internally if you are hosting the game, it means your device now becomes a server and waiting for a client to join so that the socket connection can be established.

* **Join Game** 👥
>  Here as a player, you can join a hosted game. After clicking on the "Join Game" button a QR SCanner will appear. Now you need to scan any host's QR from your app. Internally if you are joining the game, it means your device now becomes a client which is joining to a SocketServer so that the socket connection can be established.

## Built With 🛠
- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more. With the help of Coroutines the Socket programming logic and the game's business logic is implemented.
- [Jetpack Compose](https://developer.android.com/jetpack/compose/tutorial) - Jetpack Compose is Android's recommended modern toolkit for building native UI. It simplifies and accelerates UI development on Android. Our whole application's UI is built with jetpack compose.
- [Module Install Apis](https://developers.google.com/android/guides/module-install-apis) - This is an API provided by Google to ensure different Google API availability with ModuleInstallClient in our android devices. Here in our app, we are ensuring the Google Play's scanner module availability.
- [Google code Scanner](https://developers.google.com/ml-kit/vision/barcode-scanning/code-scanner) - The Google code scanner API provides a complete solution for scanning code without requiring your app to request camera permission, while preserving user privacy. We have used it to create the QR code scanner at the time of game joining.
- [Zxing](https://mvnrepository.com/artifact/com.google.zxing/core/3.5.1) - Zxing is an open-source barcode scanning library for Java and Android. Here we have used it to generate the QR at the time game hosting.

## Screenshots 📷 
![light_screenshot](https://github.com/theDIRone/T3/blob/dev/assets/all_grid.png)

## Download 📥
- Click [Here](https://drive.google.com/u/2/uc?id=11T21yw4XEM86NQcavzTloqOzt7qo2lOF&export=download) to download the latest apk.

## Requirements 🎯 
- Android 7.0 and Above
- Min sdk version 24

## Permissions 💻
- Internet

## Contributing Guideline ✍
- Before start your contribution please go through our contribution guideline carefully. ([READ](https://github.com/theDIRone/T3/blob/dev/assets/Contributing.md))
- You need to push your PR on [develop](https://github.com/theDIRone/T3/tree/dev) branch only.
- Join our whatsapp group for further discussion.[CLICK HERE](https://chat.whatsapp.com/JXYziNoD3lD9RWQwD3VyEe) TO JOIN.

## 😎Maintainers
<table>
  <tbody><tr>
    <td align="center"><a href="https://github.com/theDIRone"><img alt="" src="https://avatars.githubusercontent.com/theDIRone" width="200px;"><br><sub><b> Sitam Sardar </b></sub></a><br></td> </a></td>

     
    
  </tr>
</tbody></table>

## License 

```
MIT License

Copyright (c) 2023 Sitam Sardar

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
### If you liked the project don't forget to star 🌟 and fork 🍽 the project.
![forthebadge](https://forthebadge.com/images/badges/built-with-love.svg)
![ForTheBadge ANDROID](https://forthebadge.com/images/badges/built-for-android.svg)
![ForTheBadge GIT](https://forthebadge.com/images/badges/uses-git.svg)

### Credits goes to these people:✨

<table>
	<tr>
		<td>
   <a href="https://github.com/sitamadex11/CovidHelp/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=theDIRone/T3" />
</a>
		</td>
	</tr>
</table>



<br>
<br>
<h2 align="center">❤ Built with Love ❤</h2>

<br>
