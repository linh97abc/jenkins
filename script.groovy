def west_init() {
    sh 'mkdir -p modules/hal/cmsis'
    sh 'mkdir -p modules/hal/nxp'
    sh 'mkdir -p zephyr'
    sh 'mkdir -p .west'
    sh 'echo \"[manifest]\\r\\npath=zephyr\\r\\n[zephyr]\\r\\nbase=zephyr\">.west/config'
}

def build() {
    echo "========executing Build========"
    echo "platform ${param.platform}"
    // echo "jenkin: ${param.J"
    sh 'whoami'
    sh 'pwd'
    sh 'gcc -g -o main.bin hn_repo/main.c'
}

return this