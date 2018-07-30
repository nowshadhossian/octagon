#!/bin/bash


LOCAL_PROJECT_POST_BUILD_LOCATION="/Users/nowshad/Documents/dump/local-run"
MONGO_DATA_LOCATION="/Users/nowshad/programming/data/db"

echo "############################"
echo "Clean ups"
echo "############################"

echo "...[start] cleaning jar and serve at 8793,3000.."
kill $(lsof -i :8793 -sTCP:LISTEN | awk 'FNR==2 {print $2}')
kill $(lsof -i :3000 -sTCP:LISTEN | awk 'FNR==2 {print $2}')
echo "...[complete]cleaning jar at 8793.."

echo "############################"
echo "Run the apps"
echo "############################"

echo "....Running Mongo...."
mongod --dbpath $MONGO_DATA_LOCATION
echo "....Mongo Running complete...."

cd $LOCAL_PROJECT_POST_BUILD_LOCATION


echo "....[start] java jar...."
java -jar octagon.jar &
echo "....[Complete] java jar...."


echo "....[start] serve octagon-react...."
serve -s -p 3000 octagon-react &
echo "....[Complete] serve octagon-react...."
