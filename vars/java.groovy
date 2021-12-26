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
//INSTALL MAVEN START
            stage('Maven Compile') {
                steps {
                    sh """
            mvn compile
          """
                }
            }
//INSTALL MAVEN END
            stage('Submit Code Quality') {
                steps {
               //     sh """
//                    sonar-scanner -Dsonar.projectKey=${params.COMPONENT} -Dsonar.sources=. -Dsonar.host.url=http://172.31.16.240:9000 -Dsonar.login=0edd0d89a476069a048b9cb00ddd4a900869c515

                 //   """
                    sh 'echo maven code quality Cases'


                }
            }

            stage('Test Cases') {
                steps {
                    sh 'echo Test Cases'
                }
            }

//            stage('Upload Artifacts') {
////                when {
////                    expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true' ]) }
////                }
//                steps {
//                    sh 'echo Test Cases'
//                    sh 'env'
//                }
//            }

            stage('Upload Artifacts') {
//            when {
//                expression { sh([returnStdout: true, script: 'echo ${GIT_BRANCH} | grep tags || true' ]) }
//            }
            steps {
                sh """
            GIT_TAG=`echo ${GIT_BRANCH} | awk -F / '{print \$NF}'`
            echo \${GIT_TAG} >version
            zip -r ${params.COMPONENT}-\${GIT_TAG}.zip node_modules server.js version
//Nexus instance private id

            curl -f -v -u ${NEXUS} --upload-file ${params.COMPONENT}-\${GIT_TAG}.zip http://172.31.7.184:8081/repository/${params.COMPONENT}/${params.COMPONENT}-\${GIT_TAG}.zip
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
