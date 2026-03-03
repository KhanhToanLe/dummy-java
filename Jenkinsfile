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
                // 1. Tell GitHub we are starting
                step([$class: 'GitHubCommitStatusSetter', 
                    contextSource: [$class: 'DefaultCommitContextSource', context: 'Jenkins/Maven-Build'], 
                    statusResultSource: [$class: 'ConditionalStatusResultSource', results: [[$class: 'AnyBuildStepStatusSource', message: 'Installing dependencies...', state: 'PENDING']]]
                ])
                // 2. Run the actual command
                // If this fails, the pipeline jumps straight to the 'post { failure }' block
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
            // 3. Report Success to GitHub
            step([$class: 'GitHubCommitStatusSetter', 
                contextSource: [$class: 'DefaultCommitContextSource', context: 'Jenkins/Maven-Build'], 
                statusResultSource: [$class: 'ConditionalStatusResultSource', results: [[$class: 'AnyBuildStepStatusSource', message: 'Build and Validation Passed', state: 'SUCCESS']]]
            ])
        }
        failure {
            // 4. Report Failure to GitHub
            step([$class: 'GitHubCommitStatusSetter', 
                contextSource: [$class: 'DefaultCommitContextSource', context: 'Jenkins/Maven-Build'], 
                statusResultSource: [$class: 'ConditionalStatusResultSource', results: [[$class: 'AnyBuildStepStatusSource', message: 'Build or Validation Failed', state: 'FAILURE']]]
            ])
        }
    }
}