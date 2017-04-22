#!/bin/bash

app_home=/home/admin/Project/vacodemaker
lib_path=$app_home/lib
src_path=$app_home/src/main/java

jars=.:$lib_path/patchca-0.5.0.jar
srcs=$src_path/nb/vacodemaker/bootstrap.java

function build()
{
        javac -classpath $jars $srcs
}       

function run()
{
        cd $src_path
        java -classpath $jars nb.vacodemaker.bootstrap
}       

build;
run;