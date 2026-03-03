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
                  reposSource: [$class: "ManuallyEnteredRepositorySource", url: "https://github.com/KhanhToanLe/dummy-java.git"],
                  contextSource: [$class: "ManuallyEnteredCommitContextSource", context: "ci/jenkins/build-status"],
                  errorHandlers: [[$class: "ChangingBuildStatusErrorHandler", result: "UNSTABLE"]],
                statusResultSource: [$class: 'ConditionalStatusResultSource', results: [[$class: 'AnyBuildStepStatusSource', message: 'Build and Validation Passed', state: 'SUCCESS']]]
            ])
            
        }
        failure {
            // 4. Report Failure to GitHub
            step([$class: 'GitHubCommitStatusSetter', 
                reposSource: [$class: "ManuallyEnteredRepositorySource", url: "https://github.com/KhanhToanLe/dummy-java.git"],
                contextSource: [$class: "ManuallyEnteredCommitContextSource", context: "ci/jenkins/build-status"],
                errorHandlers: [[$class: "ChangingBuildStatusErrorHandler", result: "UNSTABLE"]],
                statusResultSource: [$class: 'ConditionalStatusResultSource', results: [[$class: 'AnyBuildStepStatusSource', message: 'Build or Validation Failed', state: 'FAILURE']]]
            ])
        }
    }
}