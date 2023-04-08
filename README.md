# Password-Manager-GUI

This is a window based application made with swing, a GUI framework of Java using which we can generate random strong passwords, then, save it with the website name where we used this password to sign-up/login. The password is saved using a simple encrypted algorithm. We can retrieve the decrypted password by authenticating our identity. I have used MySQL table in the backend, connected it using JDBC, added mysql jar file, that is, JDBC driver for mysql, to store the encrypted passwords.

To run into your system, you need to create a mysql database "password_generator" -> create new table "password" with 3 columns "ID", "Website", "password". A screenshot of mysql table schema is added in the "images" folder. Considering your username and password for mysql applicaion is "root", "root". Otherwise, just make necessary changes in either the code or your mysql application.

in the command prompt, start with 

javac Main.java
java Main

there you go!

see the video in "working" folder to get a sneak peek of how the app works.

for any queries: mkumar47114@gmail.com
