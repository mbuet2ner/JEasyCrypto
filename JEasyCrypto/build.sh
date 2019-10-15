mkdir bin
javac src/easycrypto/*.java -d bin
cd bin
jar cvf ../../EasyCryptoLib.jar easycrypto/*.class
