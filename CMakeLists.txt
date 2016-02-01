cmake_minimum_required(VERSION 2.8)

find_package(Swig REQUIRED)
find_package(Java REQUIRED)
find_package(JNI REQUIRED)
include(UseJava)
include(UseSWIG)

set(CMAKE_SWIG_OUTDIR ${CMAKE_CURRENT_BINARY_DIR})

# First build the C++ code into a dynamic library: example.dll (on Windows) or libexample.so (on Linux)
swig_add_module(
    example 
    java 
    example.i 
    example.c
)
include_directories(${JNI_INCLUDE_DIRS})

# For convenience we copy the dynamic library to the current build folder
ADD_CUSTOM_COMMAND(
    TARGET example POST_BUILD
    COMMAND ${CMAKE_COMMAND} -E copy_if_different $<TARGET_FILE:example> ${CMAKE_CURRENT_BINARY_DIR}
)

# Then build the Java code into ExampleJNI.jar
add_jar(
    ExampleJNI 
    ${CMAKE_SWIG_OUTDIR}/example.java
    ${CMAKE_SWIG_OUTDIR}/exampleJNI.java
)
add_dependencies( ExampleJNI example )
SET(ExampleJNI_JAR "${CMAKE_CURRENT_BINARY_DIR}/ExampleJNI_JAR.jar")

# Finally build the demonstration code into Main.jar
add_jar(
    Main 
    SOURCES "main.java"
    INCLUDE_JARS ${ExampleJNI_JAR}
    ENTRY_POINT main
)
add_dependencies( Main ExampleJNI )
SET(CMAKE_JAVA_COMPILE_FLAGS "-source" "1.8" "-target" "1.8")
