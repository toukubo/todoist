PATH=$PATH:/opt/apache-maven-3.0.4/bin
export M2_HOME=/opt/apache-maven-3.0.4
export JAVA_HOME=/opt/jdk1.6.0_27

cd /home/toukubo/todoist
mvn exec:java -Dexec.mainClass="net.enclosing.todoist.Todoist"

