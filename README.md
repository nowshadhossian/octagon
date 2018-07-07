##Installation:

**1. Mongodb**
brew install mongodb

To start MongoDB:
mongod --dbpath /Users/nowshad/programming/data/db

Download Robo 3T as mongodb client

**2. Java-React Communication**
-through URL u(encryptedUserId) and s(encrypted json settings) params are send to react [DailyExamController]
-React take these settings & make Controller call /questions/{limit}/subject/{subjectId}/etoken with e value and s value send as RequestHeader
-The controller take these header value > decrypts the u value and send as JWTToken claim as long and encrypted settings s as claims > send them in header
named jwtToken to react.
- React take these jwtToken and send as Authorization header in every request[QuestionApiInterceptor verifies JWTToken in every request]

**3. Reverse Proxy**
-server uses reverse proxy nginx. That runs in port 80. Spring boot on 8793 and react app 3000.
Reverse Proxy makes the app run in port 80 for both the apps.
-for MacOS install nginx using brew.
-brew services start nginx >> start/stop nginx
- location / {
             proxy_pass http://127.0.0.1:8793;
          }
 
          location /app {
             proxy_pass http://localhost:3000/;
          }
The above in /usr/local/etc/nginx/nginx.conf

