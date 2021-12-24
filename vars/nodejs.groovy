def call(String COMPONENT, String LABEL) {
    pipeline {
        agent {
            label "${LABEL}"
        }

        stages {

            stage('Compile') {
                steps {
                    sh "echo COMPONENT = ${COMPONENT}"
                    sh "echo EX_COMP = ${EX_COMP}"
                }
            }


//            stage('Labeling Build') {
//                steps {
//                    script {
//                        addShortText background: 'yellow', color: 'black', borderColor: 'yellow', text: "${COMPONENT}"
//            addShortText background: 'yellow', color: 'black', borderColor: 'yellow', text: "${APP_VERSION}"
//            addShortText background: 'orange', color: 'black', borderColor: 'yellow', text: "${ENV}"
//                    }
//                }
//            }

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

        }
    }

}
