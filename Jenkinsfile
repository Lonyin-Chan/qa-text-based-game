pipeline {
    agent any
    tools {
        maven 'M3'
    }
    environment {
        VM_HOST = "35.210.121.134"  // Target VM IP address
        PROJECT_DIR = "${env.WORKSPACE}/qa-item-task-build"  // Full path to your project
        JAR_NAME = "text-based-game-1.0-SNAPSHOT.jar"  // Your JAR file name (if applicable)
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm  // Checkout the code from the source repository
                echo "Project directory: ${PROJECT_DIR}"  // Debug output to confirm the path
            }
        }
        stage('SSH into Target VM') {
            steps {
                sh """
                ssh -o StrictHostKeyChecking=no jenkins@${VM_HOST} 'echo SSH successful'
                """
            }
        }
        stage('Copy Project to Target VM') {
            steps {
                sh """
                tar czf project.tar.gz -C ${env.WORKSPACE} qa-item-task-build && 
                scp project.tar.gz jenkins@${VM_HOST}:${PROJECT_DIR}
                """
            }
        }
        stage('Build JAR on Target VM') {
            steps {
                sh """
                ssh -o StrictHostKeyChecking=no jenkins@${VM_HOST} '
                cd ${PROJECT_DIR} &&
                tar xzf project.tar.gz &&
                mvn clean package
                '
                """
            }
        }
        stage('Build Docker Image on Target VM') {
            steps {
                sh """
                ssh -o StrictHostKeyChecking=no jenkins@${VM_HOST} '
                cd ${PROJECT_DIR} &&
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
        stage('Clean Up Project Files on Target VM') {
            steps {
                sh """
                ssh -o StrictHostKeyChecking=no jenkins@${VM_HOST} '
                rm -rf ${PROJECT_DIR}/project.tar.gz
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
