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

//        environment {
//            NEXUS = credentials("NEXUS")
//        }

        stages {

            stage('Labeling Build') {
                steps {
                    script {
                        str = GIT_BRANCH.split('/').last()
                        addShortText background: 'yellow', color: 'black', borderColor: 'yellow', text: "BRANCH = ${str}"
                    }
                }
            }


            stage('Docker Build') {
                when {
                    expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true' ]) }
                }
                steps {
                    sh """
          GIT_TAG=`echo ${GIT_BRANCH} | awk -F / '{print \$NF}'`
          echo \${GIT_TAG} >version
         // aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 946075822778.dkr.ecr.us-east-1.amazonaws.com
          aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 946075822778.dkr.ecr.us-east-1.amazonaws.com
          docker build -t 946075822778.dkr.ecr.us-east-1.amazonaws.com/${params.COMPONENT}:\${GIT_TAG} .
          docker push 946075822778.dkr.ecr.us-east-1.amazonaws.com/${params.COMPONENT}:\${GIT_TAG}
          docker tag 946075822778.dkr.ecr.us-east-1.amazonaws.com/${params.COMPONENT}:\${GIT_TAG} 739561048503.dkr.ecr.us-east-1.amazonaws.com/${params.COMPONENT}:latest
          docker push 946075822778.dkr.ecr.us-east-1.amazonaws.com/${params.COMPONENT}:latest
          """

                }
            }

        }
        post {
            always {
                cleanWs()
            }
        }

    }

}
