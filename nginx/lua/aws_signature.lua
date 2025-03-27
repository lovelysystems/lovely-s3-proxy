-- /usr/local/openresty/lualib/aws_signature.lua
local _M = {}
local resty_sha256 = require "resty.sha256"
local resty_hmac = require "resty.hmac"
local resty_str = require "resty.string"

function _M.get_aws_datetime()
    local now = ngx.now()
    return os.date("!%Y%m%dT%H%M%SZ", now)
end

function _M.get_eight_digit_date()
    local now = ngx.now()
    return os.date("!%Y%m%d", now)
end

local function sha256(data)
    local r_sha256 = resty_sha256:new()
    r_sha256:update(data)
    return r_sha256:final()
end

function _M.sha256_hex(data)
    return resty_str.to_hex(sha256(data))
end

function _M.hmac_sha256(key, data)
    local h = resty_hmac:new(key, resty_hmac.ALGOS.SHA256)
    h:update(data)
    local result = h:final()
    h:reset()
    return result
end

function _M.get_signing_key(date, region, secret)
    local k_date = _M.hmac_sha256(secret, date)
    local k_region = _M.hmac_sha256(k_date, region)
    local k_service = _M.hmac_sha256(k_region, "s3")
    return _M.hmac_sha256(k_service, "aws4_request")
end


return _M
