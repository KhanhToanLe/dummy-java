pipeline {
    agent any 
    stages {
        stage('Install Dependencies') {
            steps {
                sh 'mvn install' // or pip install, etc.
            }
        }
        stage('Run Validations') {
            steps {
                // This is the "Gatekeeper" step. 
                // If this command returns an error, the merge is blocked.
                sh 'mvn checkstyle:check' 
            }
        }
    }
}