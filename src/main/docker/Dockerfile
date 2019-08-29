FROM centos:latest

RUN yum install java-1.8.0-openjdk.x86_64 -y \
	&& yum install iputils -y

ADD tivit-monitoracao*.jar /root/tivit-monitoracao.jar

EXPOSE 9090

ENTRYPOINT [ "bash", "-c", "java -jar /root/tivit-monitoracao.jar" ]