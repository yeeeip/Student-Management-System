<h1>How to build this project?</h1>

# Requirements: 
<ul>
  <li>Java 17 (or higher): Download <a href="https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html">HERE</a></li>
  <li>PostgresSQL 15.1: Download <a href="https://www.enterprisedb.com/downloads/postgres-postgresql-downloads">HERE</a></li>
  <li>Apache Maven 3.3+: Download <a href="https://maven.apache.org/download.cgi">HERE</a></li>
  <li>JAVA_HOME variable: <a href="https://javatutorial.net/set-java-home-windows-10/">How to set JAVA_HOME</a></li>
  <li>MAVEN_HOME variable: <a href="https://maven.apache.org/download.cgi">How to set MAVEN_HOME</a></li>
</ul> 

# Step 1 (Clone project)
Clone this project in any directory of your computer, e.g. in your Desktop.

Open cmd in the necessary directory and type following command: <b>git clone https://github.com/yeeeip/Student-Management-System.git</b>

Folder named Student-Management-System should appear in your directory
 
# Step 2 (Ensure everything is OK)
We need to ensure that your JAVA_HOME and MAVEN_HOME variables are set correctly, so open cmd and type: <b>java -version</b>

![2023-01-31_12-55-47](https://user-images.githubusercontent.com/81825828/215714215-2cc6645a-b417-4fa8-95c2-eba5fe0c8096.png)

Ensure that your java version is 17 or higher

Now, type <b>mvn --version</b> in cmd

![2023-01-31_12-44-28](https://user-images.githubusercontent.com/81825828/215712293-6b2be5e9-1f0a-40df-9f14-9bc190503bb0.png)

Ensure that your maven version is 3.3+ and that Maven uses java 17 or higher

# Step 3 (Define application.yaml)
This app uses PostgresSQL as a data storage, so we need to provide our app an information it will use to connect to our database

We will use the default user <b>postgres</b> to connect to our database. You also need to create the database for the application. 

You can do it via the pgAdmin tool, GUI for managing your databases, you can give any name to your database, e.g. student_management_system

Now, open project directory go to <b>src/main/resources</b>. Here, create new file with name <b>application.yaml</b>

Open this file, and fill it with the following:

![2023-01-31_13-58-59](https://user-images.githubusercontent.com/81825828/215728825-c9f8127d-df3d-4343-badb-41e2bb52db3a.png)

<h3>Don't forget to replace your_password with your PostgresSQL password</h3>

# Step 4 (Build JAR file with Maven)
Open cmd in the project directory and type <b>mvn clean install</b>

In case of success, you should notice green title "BUILD SUCCESS" and new folder <b>target</b> in project directory

![2023-01-30_21-45-57](https://user-images.githubusercontent.com/81825828/215554489-1286ec07-7694-49d5-ab80-a72195f5cbd4.png)
  
# Step 5 (Run the JAR file)

The last thing you should do is to enter <b>target</b> directory of the project and open cmd, then type: <b>java -jar name_of_the_jar_file.jar</b>

You should see a lot of text appearing in the console and text **Started Main** in the bottom

![2023-01-31_14-06-17](https://user-images.githubusercontent.com/81825828/215730779-279b1465-33b7-4f17-a3da-7a35f2180f1c.png)

Now, go to the http://localhost:8080/api/v1/students and enjoy the app
