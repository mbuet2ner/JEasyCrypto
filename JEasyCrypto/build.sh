javac -d bin src/easycrypto/*.java
cd bin
jar cvf ../../EasyCryptoLib.jar easycrypto/*.class
