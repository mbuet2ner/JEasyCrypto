<p align="center">
  <img src="logo.png">
</p>

<img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/mbuet2ner/JEasyCrypto?style=for-the-badge"> <img alt="GitHub issues" src="https://img.shields.io/github/issues/mbuet2ner/JEasyCrypto ?style=for-the-badge"> <img alt="GitHub pull requests" src="https://img.shields.io/github/issues-pr/mbuet2ner/JEasyCrypto?style=for-the-badge"> <img alt="GitHub" src="https://img.shields.io/github/license/mbuet2ner/JEasyCrypto?style=for-the-badge">

# README #

JEasyCrypto is a library and a collection of related applications enabling encryption and decryption of text using different methdods.

The code here has been created for educational purposes, and the apps (probably) have no other value for anyone.

*(c) Antti Juustila, Degree Program of Information Processing Science, University of Oulu, Finland.*

### What is this repository for? ###

Repository includes four components: 

* **JEasyCryptoLib** which implements several crypto methods and is extensible with new methods.
* **JEasyCryptoConsole**, a console app which enables using the cryptographic methods of the Lib
* **JEasyCryptoServer**, which provides a way to use the Lib from internet, using UDP and JSON.
* **JEasyCryptoClient**, which uses the Server over network for en-/decryption of text.

Additionally, the code uses the **JSON-java** library which is licensed under [this LICENSE](https://github.com/stleary/JSON-java/blob/master/LICENSE). (see below).

You can also run the Client and Server on the same machine. When launching the client, give "localhost" as the server address in this case.

### How do I get set up? ###

0. For this project, we use `openjdk`.
    * Install instructions and download links for all systems can be found [here](https://openjdk.java.net/install/).
    * The current version used is `openjdk 11.0.4`.
    * We encourage you to use the [IntelliJ IDEA](https://www.jetbrains.com/idea/) IDE.
1. Clone the project.
    * Dependencies
        * Uses https://github.com/stleary/JSON-java on Server and Client to create/parse JSON.
        * Download the [json-20190722.jar](https://repo1.maven.org/maven2/org/json/json/20190722/json-20190722.jar) to project root directory before building and running the Client or Server.
2. Build the lib using `build.sh` (on GNU/Linux, macOS) or `build_windows.bat` (on Windows), which exports it as a `.jar` file into the project root directory.
3. Then build the server and client, using corresponding build files for each. 
4. Use the provided `start.sh` (on GNU/Linux, macOS) or `start_windows.bat` (on Windows) files to start the client and server. Use Client and Server for cryption.
5. For testing the Lib without network, you can use the Console without the overhead of networking between the Client and Server.


### Contribution guidelines ###

* To get started contributing to the project, see the [contributing guide](CONTRIBUTING.md).
* Please also take a look at our [code of conduct](code-of-conduct.md), that acts as a guideline on how to communicate with people on this project. If you experienced a violation, do not hesitate to contact one of the maintainers.
* We follow [these](https://google.github.io/styleguide/javaguide.html) Java coding conventions, so please make sure that you adhere to them.
* Please format your code. This project considers the output of the [IntelliJ IDEA](https://www.jetbrains.com/idea/) IDE `Reformat Code` option as the gold standard.

### Who do I talk to? ###

* Please use our Slack channel to message the maintainers.
