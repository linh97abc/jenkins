// def gv

// // node{
// //     properties(
// //         parameters([
// //         choice(name: 'platform', choices: ['s32r45_cortex_m7','s32r45_cortex_a53','frdm_k64f'], description: 'platform choice'),
// //         choice(name: 'testcase', choices: ['all','samples', 'tests/kernel','tests/drivers','tests/ztest/base']),
// //         choice(name: 'mode', choices: ['build', 'run'])
// //         ])
// //     )
// // }

// // parameters ([
// //         choice(name: 'platform', choices: ['s32r45_cortex_m7','s32r45_cortex_a53','frdm_k64f'], description: 'platform choice'),
// //         choice(name: 'testcase', choices: ['all','samples', 'tests/kernel','tests/drivers','tests/ztest/base']),
// //         choice(name: 'mode', choices: ['build', 'run'])
// //     ])

// pipeline{
//     agent any
//     // parameters {
//     //     choice(name: 'platform', choices: ['s32r45_cortex_m7','s32r45_cortex_a53','frdm_k64f'], description: 'platform choice')
//     //     choice(name: 'testcase', choices: ['all','samples', 'tests/kernel','tests/drivers','tests/ztest/base'])
//     //     choice(name: 'mode', choices: ['build', 'run'])
//     //     string(name: 'serial', defaultValue: 'ttyACM0', description: 'serial line')
//     // }
//     stages{
//         stage("init"){
//             steps{
//                 script{
//                     gv = load "script.groovy"
//                     gv.west_init()
//                 }
//             }   
//         }
//         stage("Build"){            
//             steps{
//                 script {
//                     gv.build()
//                 }
//             }
//             post{
//                 always{
//                     script {
//                         gv.artifact()
//                     }
//                 }
//                 success{
//                     echo "====++++Run executed successfully++++===="
//                 }
//                 failure{
//                     echo "====++++Run execution failed++++===="
//                 }
        
//             }
//         }
//     }

//     post{
//         always{
//             archiveArtifacts artifacts:'main.bin,.west/*', fingerprint: true
//             // archiveArtifacts artifacts:'sanity-out/*.csv,sanity-out/*.log,report/*.zip', fingerprint: true
//             // junit '.west/*'
//             echo "job: ${JOB_NAME}"
//         }
//         success{
//             echo "========pipeline executed successfully ========"
//         }
//         failure{
//             echo "========pipeline execution failed========"
//         }
//     }
// }

node {
    def job = load "script.groovy"
    properties(
        [
            parameters(job.getparam()),
        ]
    )
    stage('Example') {
        echo 'I only execute on the master branch'
        echo 'I execute elsewhere'
        script{
            job.artifact()
        }
        
    }
}