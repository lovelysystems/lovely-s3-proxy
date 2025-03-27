# lovely-s3-proxy

This project provides an NGINX-based reverse proxy for AWS S3 with AWS Signature Version 4 authentication. It uses openresty and lua.

## **Usage**

It is easiest to run as a Docker container:

```sh
docker run -d \
  -p 80:80 \
  --env-file .env \
  lovelysystems/lovely-s3-proxy
```

## **Configuration Options**

| Environment Variable   | Description                                      | Default      |
|------------------------|--------------------------------------------------|--------------|
| `PORT`                 | The port on which the server listens             | `80`         |
| `MGMT_PORT`            | The port on which the metrics server listens     | `10083`      |
| `RESOLVER_ADDRESS`     | DNS resolver address for upstream requests       | `127.0.0.11` |
| `S3_ENDPOINT`          | S3 endpoint                                      | Required     |
| `S3_BUCKET_NAME`       | S3 bucket name                                   | Required     |
| `S3_REGION`            | AWS region for authentication                    | Required     |
| `S3_ACCESS_KEY_ID`     | AWS access key for signing requests              | Required     |
| `S3_SECRET_ACCESS_KEY` | AWS secret key for signing requests              | Required     |
| `KEY_PREFIX`           | Prefix to prepend to object keys in S3 requests. | Required     |
| `S3_PROTOCOL`          | Protocol for S3 requests (`http` or `https`)     | `https`      |
| `LOG_LEVEL`            | Logging level (`debug`, `info`, `warn`, `error`) | `info`       |


## Metrics

The proxy collects metrics of all requests and exposes these on port 10083 (or env `MGMT_PORT`) under `/metrics`

