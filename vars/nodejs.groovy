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
            //addShortText background: 'yellow', color: 'black', borderColor: 'yellow', text: "${COMPONENT}"
            addShortText background: 'yellow', color: 'black', borderColor: 'yellow', text: "${APP_VERSION}"
            addShortText background: 'orange', color: 'black', borderColor: 'yellow', text: "${ENV}"
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
