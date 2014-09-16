#!/bin/sh
java -classpath \
${0%/*}/../testdatagenerator_all.jar \
jp.co.dk.testdatagenerator.TestDataGenerator $*
