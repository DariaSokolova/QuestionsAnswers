pipeline {
    agent any
    tools { 
        maven 'maven' 
        jdk 'jdk8' 
    }
    stages {
        stage('Build') {
            steps {
                bat 'mvn clean compile'
            }
        }
        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }
    }
}
