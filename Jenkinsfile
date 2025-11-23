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
                sh 'mvn -B clean compile'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn -B test'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Package') {
            steps {
                sh 'mvn -B package -DskipTests'
            }
            post {
                success {
                    archiveArtifacts 'target/*.jar'
                }
            }
        }

        stage('Deploy Local') {
             steps {
                 sh '''
                     export LANG=C.UTF-8
                     export LC_ALL=C.UTF-8
                     java -Dfile.encoding=UTF-8 -jar target/*.jar
                    '''
                sh 'pkill -f "food-delivery" || true'
                sh 'sleep 3'
                sh 'nohup java -jar target/food-delivery-1.0-SNAPSHOT.jar > app.log 2>&1 &'
                sh 'echo "Application deployed - check app.log"'
            }
        }
    }

    post {
        failure {
            echo "Build failed - check logs"
        }
    }
}