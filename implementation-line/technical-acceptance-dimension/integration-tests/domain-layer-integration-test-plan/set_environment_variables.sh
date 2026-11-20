#!/bin/bash
# Bash support
set -a
. ./target/test-classes/env.properties
set +a

# ZSH support
set -o allexport
source ./target/test-classes/env.properties
set +o allexport

echo "Required environment variables have been updated into this system context"
