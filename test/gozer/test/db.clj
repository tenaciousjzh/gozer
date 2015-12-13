(ns gozer.test.db
  (:require [clojure.test :refer :all]
            [gozer.db.core :refer :all]
            [environ.core :refer [env]]
            [io.rkn.conformity :as c]))

(deftest test-connection-settings
  (testing "db connection uri"
    (is (= "datomic:free://localhost:4334/gozer_test" (:database-uri env)))))

(deftest test-connection
  (testing "db connection"
    (let [conn (connect )
          _ (println "conn :" conn)]
      (is (not (nil? conn))))))

(deftest test-db
  (testing "db"
    (let [conn (connect )]
      (is (= datomic.db.Db (type (db )))))))

(deftest test-apply-db-schema
  (testing "db schema"
    (let [conn (connect )
          result (apply-db-schema )
          _ (println "result: " result)]
      ;TODO: run schema test
      (is (true? (c/has-attribute? (db ) :user/username)))
      (is (true? (c/has-attribute? (db ) :user/password)))
      (is (true? (c/has-attribute? (db ) :user/first)))
      (is (true? (c/has-attribute? (db ) :user/last))))))
