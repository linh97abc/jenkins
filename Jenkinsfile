def gv

pipeline{
    agent any
    parameters {
        choice(name: 'platform', choices: ['frdm_k64f'], description: 'platform choice')
        choice(name: 'testcase', choices: ['samples', 'tests/kernel','tests/drivers','tests/ztest/base'])
        booleanParam(name: 'runtest', defaultValue: false)
        string(name: 'serial', defaultValue: 'ttyACM0', description: 'serial line')
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
                    echo "========always========"
                }
                success{
                    echo "========Build executed successfully========"
                }
                failure{
                    echo "========Build execution failed========"
                }
            }
        }
        stage("Run"){
            steps{
                echo "====++++executing Run++++===="
            }
            post{
                always{
                    echo "====++++always++++===="
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
            echo "========always========"
            archiveArtifacts artifacts:'main.bin', fingerprint: true
            // junit 'modules/hal/cmsis/*'
        }
        success{
            echo "========pipeline executed successfully ========"
        }
        failure{
            echo "========pipeline execution failed========"
        }
    }
}