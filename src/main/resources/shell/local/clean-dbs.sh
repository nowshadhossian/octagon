#!/usr/bin/env bash

MYSQL_PATH="/usr/local/mysql-5.7.20-macos10.12-x86_64/bin/mysql"
DB_NAME="medical"
IMAGE_DIRECTORY="/Users/nowshad/Documents/dump/medicalbd/question-image"

$MYSQL_PATH -u root -pa -e "drop database $DB_NAME";
$MYSQL_PATH -u root -pa -e "create database $DB_NAME";


mongo $DB_NAME --eval "db.dropDatabase()"
mongo $DB_NAME --eval "s = { Name : 'TecAdmin.net' }; db.testData.insert( s );"

rm -r $IMAGE_DIRECTORY
mkdir -p $IMAGE_DIRECTORY

