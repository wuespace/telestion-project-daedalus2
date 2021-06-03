#!/usr/bin/env bash

# Telestion-D2-Starter
# Authors: Ludwig Richter, Pablo Klaschka

# Fail on fail :P
set -e

# On site configuration
#SERIAL_UART_DEVICE="/dev/pts/3" # the serial device in /dev/... that handles receives/writes to UART
SERIAL_UART_DEVICE="/dev/ttyACM0" # the serial device in /dev/... that handles receives/writes to UART

# "Internal" configuration
IMAGE="ghcr.io/wuespace/telestion-project-daedalus2:latest" # registry URL

clear

toilet -t -f pagga "BACKSTEIN OS"

echo "Welcome to BACKSTEIN OS v1.0"

echo "Pulling Docker Image"
sudo docker pull "$IMAGE"

echo "Starting the docker image ;-)"
if sudo docker run -it -p 9870:9870 --privileged --add-host=host.docker.internal:host-gateway --mount "type=bind,source=/data/app-data,target=/usr/telestion/telestion-project-daedalus2/data" --mount "type=bind,source=/usr/bin/nano,target=/usr/bin/nano" --mount "type=bind,source=${SERIAL_UART_DEVICE},target=/dev/telestion-serial" "$IMAGE" "$@"; then 
	echo "If you see this, you've found a weird edge case :P"
fi

# After it all ends
echo "You've found the box :P"

echo "-----"
echo "This is a triumph"
sleep 0.4
echo "I'm making a note here, huge success"
sleep 0.4
echo "It's hard to overstate my satisfaction"
sleep 0.4
echo "W├╝Space's science"
sleep 0.4
echo "We do what we must because we can"
sleep 0.4
echo "For the good of all of us"
sleep 0.4
echo "Except the ones on the Planke"
sleep 0.4
echo "But there's no sense crying over every mistake"
sleep 0.4
echo "We just keep on trying 'til we run out of cake"
sleep 0.4
echo "And the science gets done, and we make a neat box"
sleep 0.4
echo "For the people who are in the boot"
