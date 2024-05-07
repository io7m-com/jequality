jequality
===

[![Maven Central](https://img.shields.io/maven-central/v/com.io7m.jequality/com.io7m.jequality.svg?style=flat-square)](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.io7m.jequality%22)
[![Maven Central (snapshot)](https://img.shields.io/nexus/s/com.io7m.jequality/com.io7m.jequality?server=https%3A%2F%2Fs01.oss.sonatype.org&style=flat-square)](https://s01.oss.sonatype.org/content/repositories/snapshots/com/io7m/jequality/)
[![Codecov](https://img.shields.io/codecov/c/github/io7m-com/jequality.svg?style=flat-square)](https://codecov.io/gh/io7m-com/jequality)
![Java Version](https://img.shields.io/badge/21-java?label=java&color=007fff)

![com.io7m.jequality](./src/site/resources/jequality.jpg?raw=true)

| JVM | Platform | Status |
|-----|----------|--------|
| OpenJDK (Temurin) Current | Linux | [![Build (OpenJDK (Temurin) Current, Linux)](https://img.shields.io/github/actions/workflow/status/io7m-com/jequality/main.linux.temurin.current.yml)](https://www.github.com/io7m-com/jequality/actions?query=workflow%3Amain.linux.temurin.current)|
| OpenJDK (Temurin) LTS | Linux | [![Build (OpenJDK (Temurin) LTS, Linux)](https://img.shields.io/github/actions/workflow/status/io7m-com/jequality/main.linux.temurin.lts.yml)](https://www.github.com/io7m-com/jequality/actions?query=workflow%3Amain.linux.temurin.lts)|
| OpenJDK (Temurin) Current | Windows | [![Build (OpenJDK (Temurin) Current, Windows)](https://img.shields.io/github/actions/workflow/status/io7m-com/jequality/main.windows.temurin.current.yml)](https://www.github.com/io7m-com/jequality/actions?query=workflow%3Amain.windows.temurin.current)|
| OpenJDK (Temurin) LTS | Windows | [![Build (OpenJDK (Temurin) LTS, Windows)](https://img.shields.io/github/actions/workflow/status/io7m-com/jequality/main.windows.temurin.lts.yml)](https://www.github.com/io7m-com/jequality/actions?query=workflow%3Amain.windows.temurin.lts)|

## jequality

The `jequality` package provides functions to compare floating-point numbers,
annotations to mark classes as using specific types of equality, and a validator
for equality methods.

## Status

As the Java platform evolves, libraries that may have been necessary in the
past can become unnecessary due to new platform features. If you were already
using `jequality`, then you know what it does and why you were using it. If
you aren't already using `jequality`, then you should not start.

This package is in maintenance mode and will not see any new functionality.

Use [equalsverifier](https://jqno.nl/equalsverifier/) as a replacement for
the `jequality` validator.

Use JDK 17 record types to eliminate a lot of the issues with `equals()` and
`hashCode()`.

Use JUnit 5 assertions to perform approximate equality checks of floating-point
values in unit tests. Outside of unit tests, find some other way to compare
numbers.

## Features

* High coverage test suite.
* Written in pure Java 21.
* [OSGi-ready](https://www.osgi.org/)
* [JPMS-ready](https://en.wikipedia.org/wiki/Java_Platform_Module_System)
* ISC license.

