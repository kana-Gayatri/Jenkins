def call(Map params = [:]) {
    // Start Default Arguments
    def args = [
            COMPONENT                  : '',
            LABEL                      : 'WORKSTATION'
    ]
    args << params
    pipeline {
        agent {
            label params.LABEL
        }
        stages {
            stage('Compile') {
                steps {
                    sh "echo COMPONENT = ${params.COMPONENT}"
                }
            }


            stage('Labeling Build') {
                steps {
                    script {
                        str = GIT_BRANCH.split('/').last()
//            addShortText background: 'yellow', color: 'black', borderColor: 'yellow', text: "${params.COMPONENT}"
                        //addShortText background: 'yellow', color: 'black', borderColor: 'yellow', text: "${APP_VERSION}"
                        //addShortText background: 'yellow', color: 'black', borderColor: 'yellow', text: "COMPONENT = ${params.COMPONENT}"
                        addShortText background: 'yellow', color: 'black', borderColor: 'yellow', text: "BRANCH = ${str}"
//            addShortText background: 'orange', color: 'black', borderColor: 'yellow', text: "${ENV}"
                    }
                }
            }
//      stage('Code Quality') {
//        steps {
//          sh 'echo Code Quality'
//        }
//      }
//
//      stage('Test Cases') {
//        steps {
//          sh 'echo Test Cases'
//        }
//      }
            stage('Upload Artifacts') {
                steps {
                    sh 'echo Test Cases'
                    sh 'env'
                }
            }
        }
    }
}