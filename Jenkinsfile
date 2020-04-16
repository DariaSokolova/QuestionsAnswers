pipeline {
    agent any
    tools { 
        maven 'maven' 
        jdk 'jdk8' 
    }
    stages {
        stage('Build') {
            steps {
                mvn clean compile
            }
        }
        stage('Test') {
            steps {
                mvn test
            }
        }
    }
}