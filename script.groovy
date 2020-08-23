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
    echo "jenkin: ${param.JENKINS_HOME}"
    sh 'whoami'
    sh 'pwd'
    sh 'gcc -g -o main.bin hn_repo/main.c'
}

return this