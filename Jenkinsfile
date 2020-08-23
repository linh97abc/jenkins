def gv

pipeline{
    agent any
    parameters {
        // choice(name: 'platform', choices: ['frdm_k64f'], description: 'platform choice')
        // choice(name: 'testcase', choices: ['samples', 'tests/kernel','tests/drivers','tests/ztest/base'])
        // booleanParam(name: 'runtest', defaultValue: false)
        // string(name: 'serial', defaultValue: 'ttyACM0', description: 'serial line')
        script {
            gv.params()
        }
    }
    stages{
        stage("init"){
            steps{
                script{
                    gv = load "script.groovy"
                    gv.west_init()
                }
            }   
        }
        stage("Build"){            
            steps{
                script {
                    gv.build()
                }
            }
            post{
                always{
                    script {
                        gv.artifact()
                    }
                }
                success{
                    echo "====++++Run executed successfully++++===="
                }
                failure{
                    echo "====++++Run execution failed++++===="
                }
        
            }
        }
    }

    post{
        always{
            archiveArtifacts artifacts:'main.bin,.west/*', fingerprint: true
            // junit '.west/*'
        }
        success{
            echo "========pipeline executed successfully ========"
        }
        failure{
            echo "========pipeline execution failed========"
        }
    }
}