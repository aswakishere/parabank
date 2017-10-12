pipeline {
    agent any
    environment{
        CONN = 'marcin@172.17.0.1'
        TOMCAT_HOME = '/home/marcin/tools/apache-tomcat-8.5.23'
        ENV_URL = 'http://172.17.0.1:8080/parabank-3.0.0-SNAPSHOT/index.htm?ConnType=JDBC'
        WEBDRIVER_URL = 'http://172.17.0.1:4444/wd/hub'
        ENV_BROWSER = 'chrome-remote'
    }
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
                sh 'ssh ${CONN} rm -R ${TOMCAT_HOME}/webapps/parabank-3.0.0*'
                sh 'ssh ${CONN} ls ${TOMCAT_HOME}/webapps/'
                sh 'scp target/parabank-3.0.0-SNAPSHOT.war ${CONN}:${TOMCAT_HOME}/webapps/'
            }
        }
        stage ('Start tomcat') {
            steps {
                sh 'ssh ${CONN} "${TOMCAT_HOME}/bin/catalina.sh start"'
            }
        }
        stage ('Selenium tests') {
            steps {
                sh 'mvn -Pselenium-tests -Denv_url=${ENV_URL} -Denv_browser=${ENV_BROWSER} -Dwebdriver_url=${WEBDRIVER_URL}'
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
                sh 'ssh ${CONN} "${TOMCAT_HOME}/bin/catalina.sh stop"'
            }
        }
    }
}