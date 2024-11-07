pipeline {
    agent any
    tools {
        maven 'M3' 
    }
    environment {
        VM_HOST = "35.210.121.134" 
        GIT_REPO = "https://github.com/Lonyin-Chan/qa-text-based-game.git" 
        PROJECT_DIR = "qa-item-task-build" 
        JAR_NAME = "text-based-game-1.0-SNAPSHOT.jar" 
        TARGET_DIR = "/home/jenkins" 
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
                echo "Checked out code from: ${env.GIT_REPO}"
            }
        }

        stage('Build JAR on Jenkins') {
            steps {
                script {
                    sh 'mvn clean package -DskipTests'
                    echo "JAR build successful"
                }
            }
        }

        stage('Copy JAR and Dockerfile to Target VM') {
            steps {
                script {
                    sh """
                    scp target/${JAR_NAME} jenkins@${VM_HOST}:${TARGET_DIR}
                    scp Dockerfile jenkins@${VM_HOST}:${TARGET_DIR}
                    """
                }
            }
        }

        stage('Build Docker Image on Target VM') {
            steps {
                sh """
                    ssh -o StrictHostKeyChecking=no jenkins@${VM_HOST} '
                    sudo docker build -t my-java-app .'
                """
            }
        }

        stage('Run Docker Container on Target VM') {
            steps {
                script {
                    sh """
                    ssh -o StrictHostKeyChecking=no jenkins@${VM_HOST} '
                    sudo docker run -d -p 8081:8080 -v ${TARGET_DIR}/${JAR_NAME}:/app/${JAR_NAME} my-java-app
                    '
                    """
                }
            }
        }

        stage('Clean Up') {
            steps {
                script {
                    sh """
                    ssh -o StrictHostKeyChecking=no jenkins@${VM_HOST} '
                    sudo rm -f ${TARGET_DIR}/${JAR_NAME}
                    '
                    """
                }
            }
        }
    }

    post {
        success {
            echo 'Build, copy, and containerization successful!'
        }
        failure {
            echo 'There was an error during the process.'
        }
    }
}
