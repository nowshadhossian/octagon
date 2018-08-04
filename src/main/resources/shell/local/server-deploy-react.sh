#!/usr/bin/env bash

PROJECT_LOCATION="/Users/nowshad/programming/octagon-exam"
LOCAL_PROJECT_POST_BUILD_LOCATION="/Users/nowshad/programming/octagon-exam/build"
SSH_TO="admin@medprepbd.com"

echo "############################"
echo "Clean ups"
echo "############################"

echo "...[start] cleaning jar and serve at 8793,3000.."
ssh ${SSH_TO} /bin/bash << EOF
    kill $(lsof -i :3001 -sTCP:LISTEN | awk 'FNR==2 {print $2}')
    rm -r med-build
EOF

echo "...[complete]cleaning jar at 8793.."

echo "############################"
echo "Run the apps"
echo "############################"

echo "..[start] build jar file..."
cd ${PROJECT_LOCATION}
rm -r build
npm run build


echo "..[complete] build jar file..."


echo "...transfer jar file..."
scp -r ${LOCAL_PROJECT_POST_BUILD_LOCATION} ${SSH_TO}:/home/admin/med-build

echo "....[start] java jar...."

echo "....[Complete] java jar...."


echo "....[start] serve octagon-react...."
ssh ${SSH_TO} "serve -s -p 3001 med-build &"
echo "....[Complete] serve octagon-react...."
