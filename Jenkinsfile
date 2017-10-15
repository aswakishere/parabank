pipeline {
    agent any
    environment{
        CONN='tomcat@localhost'
        TOMCAT_HOME = '/hdd/apache-tomcat-8.5.23'
        ENV_URL = 'http://localhost:8082/parabank-3.0.0-SNAPSHOT/index.htm?ConnType=JDBC'
        INITIALIZE_URL = 'http://localhost:8082/parabank-3.0.0-SNAPSHOT/initializeDB.htm'
        WEBDRIVER_URL = 'http://localhost:4444/wd/hub'
        ENV_BROWSER = 'chrome'
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
                sh 'mvn -DskipTests clean package'
            }
//           post {
//             always {
//                junit "target/**/*.xml"
//              }
//           }
        }
        stage ('Deploy') {
            steps {
                sh 'ssh ${CONN} rm -f -R ${TOMCAT_HOME}/webapps/parabank-3.0.0*'
                sh 'ssh ${CONN} ls ${TOMCAT_HOME}/webapps/'
                sh 'scp target/parabank-3.0.0-SNAPSHOT.war ${CONN}:${TOMCAT_HOME}/webapps/'
            }
        }
        stage ('Start tomcat') {
            steps {
                sh 'ssh ${CONN} "${TOMCAT_HOME}/bin/catalina.sh start"'
                sh './wait_until_deployed.sh ${INITIALIZE_URL}'
            }
        }
        stage ('Selenium tests') {
            steps {
                sh 'mvn -Pselenium-tests -Denv_url=${ENV_URL} -Denv_browser=${ENV_BROWSER} -Dwebdriver_url=${WEBDRIVER_URL} -Dmaven.test.failure.ignore=true'
            }
            post {
                success {
                    junit 'target/**/*.xml'
                    jacoco(execPattern: 'target/jacoco.exec')
                    archive "target/**/*"
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
