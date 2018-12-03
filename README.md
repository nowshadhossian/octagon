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


**4. Domain Setup**
Go to godaddy and enter nameservers of digitalocean ns2.digitalocean.com

Then, go to digital ocean and add nameserver hostname medprepbd.com value/directsTo ns2.digitalocean.com.
Enter A record. hostname: medprepbd.com IP: digital ocean server IP


**5. Port**
Mastermcq running at port 8793 and react at 3000
medprepbd running at port 8794 and react at 3001

**6. Uptime Monitoring**
email is sent to my email address when server is down for 20 minutes. This is done by 
https://uptimerobot.com/

**6. Auto restart after reboot**
Done by systemctl also called systemd. Paste the two service named mastermcq.service & mastermcq-react.service 
inside '/etc/systemd/system' of server.

Then enable the service to run after boot by:
systemctl enable mastermcq.service
systemctl enable mastermcq-react.service

if the jar doesn't run then 
chmod +x medprepbd.jar

for logs: 
journalctl -u mastermcq-react

for medprepbd since the user is admin, use sudo with all commands of systemctl
