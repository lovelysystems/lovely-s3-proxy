FROM bitnami/git:2.49.0 AS builder

RUN git clone --depth 1 --branch 0.06-1 https://github.com/jkeys089/lua-resty-hmac.git /lua-resty-hmac



FROM openresty/openresty:1.25.3.2-3-alpine
#RUN rm -rf /usr/local/openresty/nginx/conf/*

RUN apk update \
        && apk add curl perl \
        && opm get knyar/nginx-lua-prometheus \
        && mkdir -p /var/log/nginx \
        && mkdir /var/www \
        && rm /etc/nginx/conf.d/default.conf \
        && apk del curl perl \
        && rm -rf /var/cache/apk/*

COPY --from=builder /lua-resty-hmac /lua-resty-hmac


#COPY nginx/lua /usr/local/openresty/nginx/lua
COPY nginx/conf /usr/local/openresty/nginx/conf
COPY replace-env.sh /

COPY docker-entrypoint.sh /

ENTRYPOINT ["/docker-entrypoint.sh"]
