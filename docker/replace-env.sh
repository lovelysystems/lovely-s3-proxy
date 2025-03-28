#!/bin/sh
set -e

# replace all env variables
grep -ro '__ENV__[A-Z0-9_]\+' /usr/local/openresty/nginx/conf/|sed s/__ENV__// | while IFS=':' read f e
do
    if ! v=`printenv ${e}`
    then
        echo "env $e used in $f is not defined"
        exit 1
    fi
    ESCAPED=$(echo "${v}" | sed '$!s@$@\\@g')
    echo "replacing $e in $f with '$v'"
    sed  -i "s#__ENV__$e#$ESCAPED#g" $f
done
