def west_init() {
    sh 'mkdir -p modules/hal/cmsis'
    dir('modules/hal/cmsis'){                    
        git url: 'https://github.com/linh97abc/HN20_FR_EMB_02.git',
        // credentialsId: 'aaa',
        branch: 'master'
    }

    sh 'mkdir -p modules/hal/nxp'
    dir('modules/hal/nxp'){
        git url: 'https://github.com/linh97abc/test.git',
        // credentialsId: 'aaa',
        branch: 'master'
    } 

    sh 'mkdir -p zephyr'
    dir('zephyr'){
        git url: 'https://github.com/linh97abc/test.git',
        // credentialsId: 'aaa',
        branch: 'master'
    }

    sh 'mkdir -p .west'
    sh 'echo \"[manifest]\\r\\npath=zephyr\\r\\n[zephyr]\\r\\nbase=zephyr\">.west/config'
}

def build() {
    echo "========executing Build========"
    // sh 'rm -rf sanity-out*'
    sh 'gcc -g -o main.bin modules/hal/cmsis/main.c'
    sh "ls"
    sh "chmod +x sanitycheck.sh"
    sh "./sanitycheck.sh --print ${params.platform}"
    // sh "./sanitycheck.sh -p ${params.platform} -T ${params.testcase} -m ${params.mode} -d ${params.device_serial}"
}

def artifact() {
    echo "===============artifact====================="
    echo "job: ${JOB_NAME}"
    echo "job_base: ${JOB_BASE_NAME}"
    // sh "rm -rf report"
    // sh "mkdir -p report"
    // sh "zip -r report/_.zip sanity-out"
}

def getParams() {
    return [choice(name: 'platform', choices: ['s32r45_cortex_m7','s32r45_cortex_a53','frdm_k64f']),
            choice(name: 'testcase', choices: ['all','samples', 'tests/kernel','tests/drivers','tests/ztest/base']),
            choice(name: 'mode', choices: ['build'])
    ]
}

return this