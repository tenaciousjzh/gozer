(ns gozer.db.users-schema
  (:require [gozer.db.schema-loader :as load]
            [io.rkn.conformity :as c]))

(def users
  [{:db/id #db/id [:db.part/db]
    :db/ident :user/username
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/index true
    :db.install/_attribute :db.part/db}
   {:db/id #db/id [:db.part]
    :db/ident :user/password
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/index true
    :db.install/_attribute :db.part/db}
   {:db/id #db/id [:db.part/db]
    :db/ident :user/first
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/index false
    :db.install/_attribute :db.part/db}
   {:db/id #db/id [:db.part/db]
    :db/ident :user/last
    :db/valueType :db.type/string
    :db/cardinality :db.cardinality/one
    :db/index false
    :db.install/_attribute :db.part/db}
   ])

(def schema
  {:gozer/migration-2015-11-30-16-00-00 users})

(defrecord UserSchemaLoader []
  load/SchemaLoader
  (load-schema [this connection]
    (c/ensure-conforms connection schema)))

(defn new-loader
  "Called by gozer.db.core/apply-db-schema to create the user schema in datomic"
  []
  (map->UserSchemaLoader {}))
