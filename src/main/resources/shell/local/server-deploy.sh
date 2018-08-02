#!/usr/bin/env bash

PROJECT_LOCATION="/Users/nowshad/programming/octagon/"
LOCAL_PROJECT_POST_BUILD_LOCATION="/Users/nowshad/programming/octagon/build/libs"



JAR_FILE_NAME="medprepbd.jar"

echo "############################"
echo "Clean ups"
echo "############################"

echo "...[start] cleaning jar and serve at 8793,3000.."
ssh root@mastermcq.com /bin/bash << EOF
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

scp ${LOCAL_PROJECT_POST_BUILD_LOCATION}/${JAR_FILE_NAME} root@mastermcq.com:/root

echo "....[start] java jar...."
ssh root@mastermcq.com "java -Xmx192m -jar $JAR_FILE_NAME &"
echo "....[Complete] java jar...."


echo "....[start] serve octagon-react...."
#serve -s -p 3000 octagon-react &
echo "....[Complete] serve octagon-react...."
