#!/bin/sh
set -e
export LOG_LEVEL=${LOG_LEVEL:-"warn"}
export PORT=${PORT:-'80'}
export MGMT_PORT=${MGMT_PORT:-'10083'}
export RESOLVER_ADDRESS=${RESOLVER_ADDRESS:-'127.0.0.11'}  # defaults to docker internal DNS

curr_dir="$(dirname "$0")"
"$curr_dir/replace-env.sh"

echo "starting crond"
crond crond -f &
echo "starting nginx"
exec nginx -g 'daemon off;'
