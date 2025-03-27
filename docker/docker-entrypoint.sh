#!/bin/sh
set -e
export LOG_LEVEL=${LOG_LEVEL:-"info"}
export PORT=${PORT:-'80'}
export MGMT_PORT=${MGMT_PORT:-'10083'}
export RESOLVER_ADDRESS=${RESOLVER_ADDRESS:-'127.0.0.11'}  # defaults to docker internal DNS
export S3_PROTOCOL=${S3_PROTOCOL:-'https'}
export S3_UPSTREAM=${S3_UPSTREAM:-"${S3_ENDPOINT}:443"}

curr_dir="$(dirname "$0")"
"$curr_dir/replace-env.sh"

echo "starting crond"
crond crond -f &
echo "starting nginx"
exec nginx -g 'daemon off;'
