#!/usr/bin/env bash

mountPoint="$1"

if [ -z "$1" ]
  then
    echo "Mount point is not specified"
    exit 1
fi

if [ "$(ls -A $1)" ]; then
   echo "Mount point is not empty"
   exit 1
fi

if [ ! -d "$mountPoint/master" ]; then
	mkdir -p "$mountPoint/master"
fi

cd "target"

java -cp p2pfs.jar -Xmx512M -XX:MaxDirectMemorySize=512M prototype.FSPeer "$mountPoint/master"
