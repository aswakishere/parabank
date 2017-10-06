pipeline {
    agent any
    tools {
        maven 'mvn_3.5'
        jdk 'JDK1.8'
    }
    stages{
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
            }

        }

        stage ('Build') {
            steps {
                sh 'mvn -Dmaven.test.failure.ignore=true clean package'
            }
        }
        stage ('Deploy') {
            steps {
                sh 'ssh marcin@172.17.0.1 rm -R /home/marcin/tools/apache-tomcat-8.5.23/webapps/parabank-3.0.0*'
                sh 'ssh marcin@172.17.0.1 ls /home/marcin/tools/apache-tomcat-8.5.23/webapps/'
                sh 'scp target/parabank-3.0.0-SNAPSHOT.war marcin@172.17.0.1:/home/marcin/tools/apache-tomcat-8.5.23/webapps/'
            }
        }
        stage ('Start tomcat') {
            steps {
                sh 'ssh marcin@172.17.0.1 "/home/marcin/tools/apache-tomcat-8.5.23/bin/catalina.sh start"'
            }
        }
        stage ('Functional tests') {
            steps {
                sh 'mvn -DskipTests verify'
            }
            post {
                success {
                    junit 'target/**/*.xml'
                    jacoco(execPattern: 'target/jacoco.exec')
                }
            }
        }
        stage ('Stop tomcat') {
            steps {
                sh 'ssh marcin@172.17.0.1 "/home/marcin/tools/apache-tomcat-8.5.23/bin/catalina.sh stop"'
            }
        }
    }
}