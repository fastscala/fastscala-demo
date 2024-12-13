#!/bin/sh

NAME="fs_demo"

SSHUSER="root"
SSHHOST="REPLACE BY THE SERVER IP ADDRESS"

RPM_FILE=fs-demo-0.0.3-0.0.3.noarch.rpm
RPM_PATH=demo/target/rpm/RPMS/noarch

SBT_PROJ_PATH="$(pwd)/"
PACKAGE_CMD="fs_demo/rpm:packageBin ; exit ;"

DEFAULT_CONFIG_FILE_CONTENTS="JAVA_OPTS=\"-Dcom.fastscala.server.helper.is-local=false\""
