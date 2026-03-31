PLACE YOUR MySQL JDBC CONNECTOR JAR FILE HERE.

Steps:
1. Download "mysql-connector-j-8.x.x.jar" from:
   https://dev.mysql.com/downloads/connector/j/
   (Select "Platform Independent" option)

2. Copy the downloaded .jar file into THIS folder:
   WebContent/WEB-INF/lib/

3. In Eclipse:
   - Right-click project → Build Path → Configure Build Path
   - Libraries tab → Add JARs → select the jar from WEB-INF/lib/
   - Click Apply and Close

Without this JAR, you will get:
   ClassNotFoundException: com.mysql.cj.jdbc.Driver
