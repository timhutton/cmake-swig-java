# Minimal example of using SWIG with Java and CMake #

Build:

`mkdir build`  
`cd build`  
`cmake ..`  
`cmake --build .`

Run:

`java -Djava.library.path=. -jar Main.jar`

Output:

`124`

Troubleshooting:
* On Windows, if your Java installation is 64-bit then make sure to use a 64-bit C++ compiler.  
  e.g. if you are using Visual Studio 2015: `cmake -G "Visual Studio 14 2015 Win64" ..`  
* To detect the Java version, use `java -version` and `javac -version`
* You should set your JAVA_HOME variable to avoid problems such as CMake not able to find JNI.  
  e.g. on Ubuntu 14.04 with OpenJDK: `export JAVA_HOME=/usr/lib/jvm/java-7-openjdk-amd64/`
* Check that `java -version`, `javac -version` and `JAVA_HOME` all refer to the same version.  
  (on Windows: `echo %JAVA_HOME%`)

Tested on:
* Windows (10) with Visual Studio 2015, 2013 and Oracle JDK.
* Linux (Ubuntu 15.10, 14.04; Debian 7, 8) with gcc and OpenJDK or Oracle JDK.
* MacOSX (10.11) with clang and Oracle JDK.
