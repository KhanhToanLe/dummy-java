pipeline {
    agent any 
    tools {
        maven 'Maven 3.9.12'
        jdk 'Java 17.0.4.1'
    }
    
    options {
        // This automatically updates the GitHub commit status
        // without needing complex scripted steps in every stage.
        githubProjectProperty(projectUrlStr: 'https://github.com/KhanhToanLe/dummy-java.git')
    }

    stages {
        stage('Initialize') {
            steps {
                // Notifies GitHub that the check has started
                githubNotify context: 'Jenkins/Validation', status: 'PENDING', description: 'Jenkins is verifying your code...'
            }
        }
        stage('Install Dependencies') {
            steps {
                sh 'mvn install'
            }
        }
        stage('Run Validations') {
            steps {
                // If this fails, the 'post { failure { ... } }' block triggers
                sh 'mvn checkstyle:check' 
            }
        }
    }

    post {
        success {
            githubNotify context: 'Jenkins/Validation', status: 'SUCCESS', description: 'All checks passed! Safe to merge.'
        }
        failure {
            githubNotify context: 'Jenkins/Validation', status: 'FAILURE', description: 'Build or Validation failed. Check Jenkins logs.'
        }
        aborted {
            githubNotify context: 'Jenkins/Validation', status: 'ERROR', description: 'The build was cancelled.'
        }
    }
}