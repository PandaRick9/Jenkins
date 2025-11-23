pipeline {
    agent any
    tools { maven 'M3' }

    stages {
        stage('Checkout') {
            steps { checkout scm }
        }

        stage('Build') {
            steps { sh 'mvn clean compile' }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
                junit 'target/surefire-reports/*.xml'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
                archiveArtifacts 'target/*.jar'
            }
        }

        stage('Deploy Local') {
            steps {
                sh 'pkill -f "food-delivery" || true'
                sh 'sleep 3'
                sh 'nohup java -jar target/*.jar > app.log 2>&1 &'
                sh 'echo "App deployed - check app.log"'
            }
        }
    }
}