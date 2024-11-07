pipeline {
    agent any
    tools {
        maven 'M3'
    }
    environment {
        VM_HOST = "35.210.121.134"
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        stage('SSH into Target VM') {
            steps {
                sh """
                ssh -o StrictHostKeyChecking=no jenkins@${VM_HOST} 'echo SSH successful'
                """
            }
        }
        stage('Build on Target VM') {
            steps {
                sh """
                ssh -o StrictHostKeyChecking=no jenkins@${VM_HOST} '
                cd /path/to/your/project &&
                git pull &&
                mvn clean package
                '
                """
            }
        }
        stage('Build Docker Image on Target VM') {
            steps {
                sh """
                ssh -o StrictHostKeyChecking=no jenkins@${VM_HOST} '
                cd /path/to/your/project &&
                docker build -t my-java-app .
                '
                """
            }
        }
        stage('Run Docker Container on Target VM') {
            steps {
                sh """
                ssh -o StrictHostKeyChecking=no jenkins@${VM_HOST} '
                docker run -d -p 8081:8080 my-java-app
                '
                """
            }
        }
    }
    post {
        success {
            echo 'Build and run successful on target VM!'
        }
        failure {
            echo 'Build or run failed on target VM.'
        }
    }
}
