def west_init() {
    sh 'mkdir -p modules/hal/cmsis'
    sh 'mkdir -p modules/hal/nxp'
    sh 'mkdir -p zephyr'
    sh 'mkdir -p .west'
    sh 'echo \"[manifest]\\r\\npath=zephyr\\r\\n[zephyr]\\r\\nbase=zephyr\">.west/config'
    dir('modules/hal/cmsis'){                    
        git url: 'https://github.com/linh97abc/HN20_FR_EMB_02.git',
        // credentialsId: 'aaa',
        branch: 'master'
    }

    dir('modules/hal/nxp'){
        git url: 'https://github.com/linh97abc/test.git',
        // credentialsId: 'aaa',
        branch: 'master'
    } 

    dir('zephyr'){
        git url: 'https://github.com/linh97abc/test.git',
        // credentialsId: 'aaa',
        branch: 'master'
    }
}

def build() {
    echo "========executing Build========"
    // echo "platform ${param.platform}"
    // echo "jenkin: ${param.J"
    sh 'whoami'
    sh 'pwd'
    sh 'gcc -g -o main.bin hn_repo/main.c'
}

return this