pipeline {
    agent any
    stages {
        stage('Setup') {
            steps {
                script {
                    dir('api') {
                        // This changes the working directory for all later stages
                    }
                }
            }
        }
        stage('Build') {
            steps {
                sh 'mvn -B -DskipTests clean package'
            }
        }
        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
    }
}