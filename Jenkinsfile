def gv

pipeline{
    agent any
    parameters {
        choice(name: 'platform', choices: ['s32r45_cortex_m7','s32r45_cortex_a53','frdm_k64f',], description: 'platform choice')
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

                // sh 'mkdir -p modules/hal/cmsis'
                // dir('modules/hal/cmsis'){                    
                //     git url: 'https://github.com/linh97abc/HN20_FR_EMB_02.git',
                //     branch: 'master',
                //     // credentialsId: 'aaa'
                // }

                // sh 'mkdir -p modules/hal/nxp'
                // dir('modules/hal/nxp'){
                //     git url: 'https://github.com/linh97abc/test.git',
                //     branch: 'master',
                //     // credentialsId: 'aaa'
                // } 

                // sh 'mkdir -p zephyr'
                // dir('zephyr'){
                //     git url: 'https://github.com/linh97abc/test.git',
                //     branch: 'master',
                //     // credentialsId: 'aaa'
                // }

                // sh 'mkdir -p .west'
                // sh 'echo \"[manifest]\\r\\npath=zephyr\\r\\n[zephyr]\\r\\nbase=zephyr\">.west/config'
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
        }
        success{
            echo "========pipeline executed successfully ========"
        }
        failure{
            echo "========pipeline execution failed========"
        }
    }
}