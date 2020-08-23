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
    echo "platform ${params.platform}"
    echo "jenkin: ${params.JENKINS_HOME}"
    echo "jenkin: ${JENKINS_HOME}"
    sh 'whoami'
    sh 'pwd'
    sh 'gcc -g -o main.bin modules/hal/cmsis/main.c'
}

def artifact() {
    echo "===============artifact====================="
}

def params() {
    choice(name: 'platform', choices: ['frdm_k64f'], description: 'platform choice')
    choice(name: 'testcase', choices: ['samples', 'tests/kernel','tests/drivers','tests/ztest/base'])
    booleanParam(name: 'runtest', defaultValue: false)
    string(name: 'serial', defaultValue: 'ttyACM0', description: 'serial line')
}

return this