(ns gozer.test.db
  (:require [clojure.test :refer :all]
            [gozer.db.core :refer :all]
            [environ.core :refer [env]]))

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
