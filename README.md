# Gozer
Gozer is a microservice that acts as a gatekeeper protecting private services
behind it. Gozer will provide authentication, session management, and authorization.

# Features
## Authentication
- For the initial release, Gozer supports authentication with Datomic. Future releases will support LDAP authentication as well. Gozer is built with extensibitlity in mind though. By implementing the Authentictor protocol, you can easily connect Gozer with a different data source.
- Gozer will automatically encrypt passwords with a one-way hashing algorithm. By default, they will be encrypted with bcrypt but that can be modified to use MD5 or SHA.

## Session Management - Coming Soon
- Gozer will offload session management from your application enabling you to scale your microservices much easier.
- Gozer's API will provide the ability to configure the session token TTL (Time To Live) to update any time a request is made with it or can simply let the session time out automatically from the first successful authentication. By default, the session TTL will be set for 1200 seconds (20 min), but can be configured to a different value.

## Authorization - Coming Soon
- Gozer performs role based authorization through its RESTFul endpoint.
- Roles and endpoints can be configured before start up in an EDN formatted file, in the database, or at runtime with a RESTful API call. To make the API call, you must first set up a public/private key that can be used by Gozer and the authorized client to prevent unwanted access. Gozer will provide utilities to generate a public key that can be used by the client.


## License
MIT

Copyright © 2015 Jared Holmberg
