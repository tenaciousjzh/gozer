(ns gozer.db.users-schema
  (:require [gozer.db.schema-loader :as load]
            [io.rkn.conformity :as c]
            [taoensso.timbre :as timbre]
            [datomic.api :as d]))

(def users
  {:txes [[{:db/id (d/tempid :db.part/db)
            :db/ident :user/username
            :db/valueType :db.type/string
            :db/cardinality :db.cardinality/one
            :db/index true
            :db.install/_attribute :db.part/db}

           {:db/id (d/tempid :db.part/db)
            :db/ident :user/password
            :db/valueType :db.type/string
            :db/cardinality :db.cardinality/one
            :db/index true
            :db.install/_attribute :db.part/db}

           {:db/id (d/tempid :db.part/db)
            :db/ident :user/first
            :db/valueType :db.type/string
            :db/cardinality :db.cardinality/one
            :db/index false
            :db.install/_attribute :db.part/db}

           {:db/id (d/tempid :db.part/db)
            :db/ident :user/last
            :db/valueType :db.type/string
            :db/cardinality :db.cardinality/one
            :db/index false
            :db.install/_attribute :db.part/db}

           {:db/id (d/tempid :db.part/db)
            :db/ident :user/recovery-email
            :db/valueType :db.type/string
            :db/cardinality :db.cardinality/one
            :db/index false
            :db.install/_attribute :db.part/db}
           ]]})

(def schema
  {:gozer/migration-2015-11-30-16-00-00 users})

(defrecord UserSchemaLoader []
  load/SchemaLoader
  (load-schema [this connection]
    (do
      (timbre/info "Applying users schema: " schema)
      (timbre/info "Connection: " connection)
      (c/ensure-conforms connection schema)
      (timbre/info "Complete"))))

(defn new-loader
  "Called by gozer.db.core/apply-db-schema to create the user schema in datomic"
  []
  (map->UserSchemaLoader {}))
