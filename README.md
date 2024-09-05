# Generating Root Cert

openssl req -x509 -sha256 -days 3650 -newkey rsa:4096 \
  -keyout rootCA.key -out rootCA.crt -passout pass:12345678

# Generating Server key and CSR

openssl req -new -newkey rsa:4096 -keyout server.key -out server.csr \
  -passout pass:12345678

# Content of Server EXT file (server.ext)

   authorityKeyIdentifier=keyid,issuer
    basicConstraints=CA:FALSE
    subjectAltName = @alt_names
    [alt_names]
    DNS.1 = api-server.manish.com


# Recall Step-3, CA would take CSR, use it's key and password 
# and issue Certificate

openssl x509 -req -CA rootCA.crt -CAkey rootCA.key -in server.csr \
  -out server.crt -days 365 -CAcreateserial -extfile server.ext \
  -passin pass:12345678

# Generate p12 file (Contains the key and cert)

openssl pkcs12 -export -out server.p12 -name "api-server.manish.com" \
  -inkey server.key -in server.crt -passin pass:12345678 \
  -password pass:12345678
  
# Create KeyStore

keytool -importkeystore -srckeystore server.p12 -srcstoretype PKCS12 \
-destkeystore myserver_keystore.jks -deststoretype JKS

# For One Way TLS communication, Steps till generating KeyStore is sufficient.

# Generate Trust Store

keytool -import -trustcacerts -noprompt -alias ca \
  -ext san=dns:${SERVER_CN},ip:127.0.0.1 -file rootCA.crt \
  -keystore myserver_truststore.jks

# So far, Server setup is fully done. Now for any client to communicate 
# to the server, we need to generate the "CLIENT" certs and get it signed 
# by the same Authority whose cert is stored in Trust Store.

# Client generates it's key and CSR

openssl req -new -newkey rsa:4096 -keyout client.key -out client.csr \
  -passout pass:12345678

# CA use it's key and credentials along with the CSR and issue Cert

openssl x509 -req -CA rootCA.crt -CAkey rootCA.key -in client.csr \
  -out client.crt -days 365 -CAcreateserial -passin pass:12345678

openssl pkcs12 -export -out client.p12 -name "client" -inkey client.key \
  -in client.crt -passin pass:12345678 -password pass:12345678
