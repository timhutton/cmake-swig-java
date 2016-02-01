cmake_minimum_required(VERSION 2.8)

find_package(Swig REQUIRED)
find_package(Java REQUIRED)
find_package(JNI REQUIRED)
include(UseJava)
include(UseSWIG)
include_directories(${JNI_INCLUDE_DIRS})

set( CMAKE_SWIG_OUTDIR ${CMAKE_CURRENT_BINARY_DIR} )

# Build the C++ code into a dynamic library: example.dll (on Windows) or libexample.so (on Linux)
swig_add_module(
    example 
    java 
    example.i 
    example.c
)

# For convenience we copy the dynamic library to the current build folder
add_custom_command(
    TARGET example 
    POST_BUILD
    COMMAND ${CMAKE_COMMAND} -E copy_if_different $<TARGET_FILE:example> ${CMAKE_CURRENT_BINARY_DIR}
)

# Build the Java code into ExampleJNI.jar
add_jar(
    ExampleJNI
    SOURCES
    ${CMAKE_SWIG_OUTDIR}/example.java
    ${CMAKE_SWIG_OUTDIR}/exampleJNI.java
)
add_dependencies( ExampleJNI example )

# Finally build the demonstration code into Main.jar
add_jar(
    Main 
    SOURCES main.java
    ENTRY_POINT main
)
add_dependencies( Main ExampleJNI )
set( CMAKE_JAVA_COMPILE_FLAGS "-source" "1.8" "-target" "1.8" )
