(ns asap.core-test
  (:require [clojure.test :refer :all]
            [asap.core :refer :all]))

(deftest js-file-pattern-matcher
  (testing "true when a js file is found"
    (is (js? "test.js")))
  (testing "false when it is not a js file"
    (is (not (js? "test.java")))))

(deftest js-file-from-require
  (testing "return the file name from a require declaration"
    (def req-dec "var PoguesDispatcher = require('../dispatchers/pogues-dispatcher');")
    (is (= "'../dispatchers/pogues-dispatcher'" (filename-from-require req-dec)))))
