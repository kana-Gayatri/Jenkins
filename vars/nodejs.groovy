def call(COMPONENT) {
    pipeline {
        agent any

        stages {

            stage('Compile') {
                steps {
                    sh "echo COMPONENT = ${COMPONENT}"
                }
            }

//            stage('Code Quality') {
//                steps {
//                    sh 'echo Code Quality'
//                }
//            }
//
//            stage('Test Cases') {
//                steps {
//                    sh 'echo Test Cases'
//                }
//            }
//
        }
    }

}
