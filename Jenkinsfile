pipeline {
    agent any
    tools {
        maven 'M3'
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Build Docker Image') {
            steps {
                script {
                    docker.build("my-java-app")
                }
            }
        }
        stage('Run Docker Container') {
            steps {
                script {
                    docker.image("my-java-app").run("-p 8081:8080")
                }
            }
        }
    }
    post {
        success {
            echo 'Build and run successful!'
        }
        failure {
            echo 'Build or run failed.'
        }
    }
}
