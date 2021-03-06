(ns asap.core-test
  (:require [clojure.test :refer :all]
            [asap.core :refer :all]))

(require '[clojure.java.io :as io])

(deftest js-file-pattern-matcher
  (testing "true when a js file is found"
    (is (js? (io/file "../../resources/test.js"))))
  (testing "false when it is not a js file"
    (is (not (js? (io/file "../../resources/test.txt"))))))

(deftest js-file-from-require
  (testing "return the file name from a require declaration"
    (def req-dec "var PoguesDispatcher = require('../dispatchers/pogues-dispatcher');")
    (is (= "pogues-dispatcher" (filename-from-require req-dec))))
  (testing "return the dir name from a require declaration"
    (def req-dec "var PoguesDispatcher = require('../dispatchers/pogues-dispatcher');")
    (is (= "dispatchers" (directory-from-require req-dec)))))
