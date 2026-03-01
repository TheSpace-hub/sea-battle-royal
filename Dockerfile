FROM node:latest
LABEL authors="TheSpace-hub"

COPY . .

RUN apt-get update && apt-get install -y openjdk-17-jdk
RUN npm install -g typescript
RUN tsc
RUN ./gradlew :app:build
CMD ["java", "-jar", "./app/build/libs/app.jar"]

EXPOSE 8080
