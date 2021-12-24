def call(Map params = [:]) {
    // Start Default Arguments
    def args = [
            COMPONENT                  : '',
            LABEL                      : 'master'
    ]
    args << params

    pipeline {
        agent {
            label "${params.LABEL}"
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

        }
    }

//}
