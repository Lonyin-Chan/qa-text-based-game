pipeline {
    agent any
    tools {
        maven 'M3'
    }
    environment {
        VM_HOST = "35.210.121.134" 
        PROJECT_DIR = "/qa-item-task-build" 
        JAR_NAME = "text-based-game-1.0-SNAPSHOT.jar" 
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm 
                echo "Project directory: ${PROJECT_DIR}"  
            }
        }
        stage('SSH into Target VM') {
            steps {
                sh """
                ssh -o StrictHostKeyChecking=no jenkins@${VM_HOST} 'echo SSH successful'
                """
            }
        }
        stage('Prepare Target Directory on VM') {
            steps {
                sh """
                ssh -o StrictHostKeyChecking=no jenkins@${VM_HOST} '
                    sudo mkdir -p /qa-item-task-build &&  # Create the directory if it doesn't exist
                    sudo chown jenkins:jenkins /qa-item-task-build  # Grant write permissions to the jenkins user
                '
                """
            }
        }
        stage('Copy Project to Root Directory on Target VM') {
            steps {
                sh """
                tar czf project.tar.gz -C ${env.WORKSPACE} . &&
                scp project.tar.gz jenkins@${VM_HOST}:${PROJECT_DIR}
                """
            }
        }
        stage('Build JAR on Target VM') {
            steps {
                sh """
                ssh -o StrictHostKeyChecking=no jenkins@${VM_HOST} '
                cd / &&
                tar xzf project.tar.gz &&
                cd qa-item-task-build &&
                mvn clean package
                '
                """
            }
        }
        stage('Build Docker Image on Target VM') {
            steps {
                sh """
                ssh -o StrictHostKeyChecking=no jenkins@${VM_HOST} '
                cd /qa-item-task-build &&
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
                rm -rf /qa-item-task-build/project.tar.gz
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
