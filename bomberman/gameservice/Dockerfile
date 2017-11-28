FROM jetty:9.4.5-alpine
COPY build/libs/web_hackaton-1.0-SNAPSHOT.jar /services/chat.jar
EXPOSE 8090
CMD ["java", "-jar", "/services/garbager.jar"]