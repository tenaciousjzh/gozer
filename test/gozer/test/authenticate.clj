(ns gozer.test.authenticate
  (:require [clojure.test :refer :all]
            [gozer.security.authenticate :refer :all]
            [gozer.db.core :as dbc]
            [datomic.api :as d]
            [buddy.core.hash :as hash]
            [buddy.core.codecs :refer [bytes->hex]]))

(deftest test-authenticate
  (testing "authenticate"
    (let [conn (:db-conn (dbc/connect))
          _ (println "conn: " conn)
          _ (dbc/apply-db-schema )
          username "torg"
          password (-> (hash/sha3-512 "shubs&zouls")
                       (bytes->hex))
          user-added (-> (d/transact
                          conn
                          [[:db/add (d/tempid :db.part/user)
                            :user/username username
                            :user/password password
                            :user/first "Torg"
                            :user/last "Of Zoul"
                            :user/recovery-email "gozer@ghostbusters.com"]])
                         deref)
          _ (println "user-added: " user-added)]
      (login username password))))
