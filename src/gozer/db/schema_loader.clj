(ns gozer.db.schema-loader)

(defprotocol SchemaLoader
  (load-schema [this connection]))
