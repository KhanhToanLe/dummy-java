@NonCPS
def getChangedFilesList() {
    def changedFiles = []
    for (changeLogSet in currentBuild.changeSets) {
        for (entry in changeLogSet.getItems()) { // for each commit in the detected changes
            for (file in entry.getAffectedFiles()) {
                changedFiles.add(file) // add changed file to list
            }
        }
    }
    return changedFiles
}

pipeline {
    // TODO: delete this some dummy code
    // here too 1
    // here too 2 
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
    
    
    // TODO: delete this some dummy code
    // here too 1
    // here too 2 

    stages {
        stage('Run Validations') {
            steps {
                // If this fails, the 'post { failure { ... } }' block triggers
                sh 'mvn checkstyle:check' 
            }
        }
        
        stage('Checkout'){
            steps{
                checkout scm 
            }
        }
        
        stage('Get list change'){
            steps{
                script{
                    def changedFiles  = getChangedFilesList()
                    echo "change list:"
                    for(fileName in changedFiles){
                        
                        echo "- ${fileName}" 
                    }
                }
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
                statusResultSource: [$class: 'ConditionalStatusResultSource', results: [[$class: 'AnyBuildResult', message: 'Build and Validation Passed', state: 'SUCCESS']]]
            ])
            
        }
        failure {
            // 4. Report Failure to GitHub
            step([$class: 'GitHubCommitStatusSetter', 
                reposSource: [$class: "ManuallyEnteredRepositorySource", url: "https://github.com/KhanhToanLe/dummy-java.git"],
                contextSource: [$class: "ManuallyEnteredCommitContextSource", context: "ci/jenkins/build-status"],
                errorHandlers: [[$class: "ChangingBuildStatusErrorHandler", result: "UNSTABLE"]],
                statusResultSource: [$class: 'ConditionalStatusResultSource', results: [[$class: 'AnyBuildResult', message: 'Build or Validation Failed', state: 'FAILURE']]]
            ])
        }
    }
}