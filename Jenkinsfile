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
}