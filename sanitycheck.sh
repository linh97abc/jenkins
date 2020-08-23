#!/bin/bash

device_serial="/dev/ttyUSB0"
platform="s32r45_cortex_m7"
testcase="all"
mode="build"

helpFunction()
{
   echo ""
   echo "Usage: $0 [OPTIONS]"
   echo ""
   echo "Options:"
   echo -e "\t-p , --platform platform"
   echo -e "\t-T , --testcase Test case"
   echo -e "\t-d , --device   Device serial."
   echo -e "\t-m , --mode     Mode run/build test."
   echo "Default:"
   echo -e "\tplatform     : ${platform}"
   echo -e "\tTest case    : ${testcase}"
   echo -e "\tDevice serial: ${device_serial}"
   echo -e "\tMode         : ${mode}"
   exit 1 # Exit script after printing help
}

if [ -z "$1" ]; then
   helpFunction
fi

while [ "$1" != "" ]; do
    case $1 in
         -d | --device )         shift
                                 device_serial=$1
                                 ;;
         -T | --testcase )       shift
                                 testcase=$1
                                 ;;
         -p | --platform )       shift
                                 platform=$1
                                 ;;
         -m | --mode )           shift
                                 mode=$1
                                 ;;
         -h | --help )           helpFunction
                                 exit
                                 ;;
         --print )               shift
                                 echo $1
                                 exit
                                 ;;
         * )                     helpFunction
                                 exit 1
    esac
    shift
done

export ZEPHYR_BASE=$(pwd)/zephyr
export ZEPHYR_TOOLCHAIN_VARIANT=gnuarmemb
export GNUARMEMB_TOOLCHAIN_PATH=/home/linhbt3dap/gcc_arm/i686-linux/

device_test_flag=
if [ "${mode}" = "run" ]; then
   device_test_flag="--device-testing --device-serial ${device_serial} --west-flash"
fi

if [ "${testcase}" = "all" ]; then
   testcase_flag="-T zephyr/samples -T zephyr/tests"
else
   testcase_flag="-T ${testcase}"
fi

./zephyr/script/sanitycheck -p ${platform} ${testcase_flag} ${device_test_flag}
