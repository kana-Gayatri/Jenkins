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

            stage('Labeling Build') {
                steps {
                    script {
                        str = GIT_BRANCH.split('/').last()
//                        addShortText background: 'yellow', color: 'black', borderColor: 'yellow', text: "COMPONENT = ${params.COMPONENT}"
                        addShortText background: 'yellow', color: 'black', borderColor: 'yellow', text: "BRANCH = ${str}"
//            addShortText background: 'orange', color: 'black', borderColor: 'yellow', text: "${ENV}"
                    }
                }
            }

            stage('Compile') {
                steps {
                    sh "echo COMPONENT = ${params.COMPONENT}"
                }
            }

//code added 1
            stage('Download NodeJS Dependencies') {
                steps {
                    sh """
            echo "+++++++ Before"
            ls -l
            npm install
            echo "+++++++ After"
            ls -l
          """
                }
            }
//end of 1

            stage('Code Quality') {
                steps {
                    sh 'echo Code Quality'
                }
            }

            stage('Test Cases') {
                steps {
                    sh 'echo Test Cases'
                }
            }

            stage('Upload Artifacts') {
//                when {
//                    expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true' ]) }
//                }
                steps {
                    sh 'echo Test Cases'
                    sh 'env'
                }
            }

        }
    }


}