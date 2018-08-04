#!/usr/bin/env bash

PROJECT_LOCATION="/Users/nowshad/programming/octagon/"
LOCAL_PROJECT_POST_BUILD_LOCATION="/Users/nowshad/programming/octagon/build/libs"
SSH_TO="admin@medprepbd.com"


JAR_FILE_NAME="medprepbd.jar"

echo "############################"
echo "Clean ups"
echo "############################"

echo "...[start] cleaning jar and serve at 8793,3000.."
ssh ${SSH_TO} /bin/bash << EOF
    kill $(lsof -i :8794 -sTCP:LISTEN | awk 'FNR==2 {print $2}')
    #kill $(lsof -i :3001 -sTCP:LISTEN | awk 'FNR==2 {print $2}')
    rm ${JAR_FILE_NAME}
EOF

echo "...[complete]cleaning jar at 8793.."

echo "############################"
echo "Run the apps"
echo "############################"

echo "..[start] build jar file..."
cd ${PROJECT_LOCATION}
./gradlew clean build
cd ${LOCAL_PROJECT_POST_BUILD_LOCATION}
mv octagon-0.0.1-SNAPSHOT.jar ${JAR_FILE_NAME}
echo "..[complete] build jar file..."


echo "...transfer jar file..."

scp ${LOCAL_PROJECT_POST_BUILD_LOCATION}/${JAR_FILE_NAME} ${SSH_TO}:/home/admin

echo "....[start] java jar...."
ssh ${SSH_TO} "java -jar $JAR_FILE_NAME &"
echo "....[Complete] java jar...."


echo "....[start] serve octagon-react...."
#serve -s -p 3001 med-build &
echo "....[Complete] serve octagon-react...."
