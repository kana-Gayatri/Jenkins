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
                        addShortText background: 'yellow', color: 'black', borderColor: 'yellow', text: "COMPONENT = ${params.COMPONENT}"
                        addShortText background: 'yellow', color: 'black', borderColor: 'yellow', text: "BRANCH = ${str}"
//            addShortText background: 'orange', color: 'black', borderColor: 'yellow', text: "${ENV}"
                    }
                }
            }
//install npm
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
//install npm end
            stage('Compile') {
                steps {
                    sh "echo COMPONENT = ${params.COMPONENT}"
                }
            }


            //Sonar cube script
            stage('Code Quality') {
                steps {
                    sh '''
                        sonar-scanner -Dsonar.projectKey=${params.COMPONENT} -Dsonar.sources=. -Dsonar.host.url=http://172.31.16.240:9000  -Dsonar.login=0edd0d89a476069a048b9cb00ddd4a900869c515
                    '''
                }
            }

            stage('Test Cases') {
                steps {
                    sh 'echo Test Cases'
                }
            }

            stage('Upload Artifacts') {
//                when {
//                    expression { GIT_BRANCH ==~ "/*tags*/" }
//                }
                steps {
                    sh 'echo Test Cases'
                    sh 'env'
                }
            }

        }
    }

}
