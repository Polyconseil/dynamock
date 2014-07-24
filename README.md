dynamock
========

Gestionnaire de mocks

Il faudra ajouter les deux variables suivant :
server.port=numéro de port du serveur tomcat
spring.data.mongodb.uri=mongodb://[databaseServer]:[databasePort]/[databaseName]

Exemple :
java -Dserver.port=8089 -Dspring.data.mongodb.uri=mongodb://92.243.29.40:27017/dynaMock -jar dyna-mock-1.0.1-SNAPSHOT.jar