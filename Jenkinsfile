pipeline {
    agent any
    environment {
        JAVA_HOME = 'C:\\Program Files\\Java\\jdk-21.0.12'
    }
    stages {
        stage('Build') {
            steps {
                bat 'apache-maven-3.10.0\\bin\\mvn.cmd compile'
            }
        }
        stage('Test') {
            steps {
                bat 'apache-maven-3.10.0\\bin\\mvn.cmd test'
            }
        }
        stage('Package') {
            steps {
                bat 'apache-maven-3.10.0\\bin\\mvn.cmd package -DskipTests'
            }
        }
    }
    post {
        always {
            junit 'target/surefire-reports/*.xml'
        }
    }
}