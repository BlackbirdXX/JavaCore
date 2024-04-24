FROM openjdk:latest
WORKDIR /usr/src/app
COPY ./src/main/java .
RUN javac -sourcepath . -d out ./javacore/homework01/task/Price.java
WORKDIR /usr/src/app/out
#ENTRYPOINT ["java", "javacore.homework01.task.Price."]
CMD java -classpath . javacore.homework01.task.Price