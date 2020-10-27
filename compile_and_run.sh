#!/bin/sh
mkdir -p bin;
javac -d bin -sourcepath src/ src/Main.java;
cd bin && java Main;