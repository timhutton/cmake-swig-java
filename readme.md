# Minimal example of using SWIG with Java and CMake #

Build:

`mkdir build`
`cd build`
`cmake ..`
`cmake --build .`

Run:

`java -jar Main.jar`

Output:

`124`

Troubleshooting:

* On Windows, if your Java installation is 64-bit then make sure to use a 64-bit C++ compiler.
  e.g. if you are using Visual Studio 2015: `cmake -G "Visual Studio 14 2015 Win64" ..`
  To detect the Java version, use `java -version`
