(ns asap.core-test
  (:require [clojure.test :refer :all]
            [asap.core :refer :all]))

(deftest js-file-pattern-matcher
  (testing "true when a js file is found"
    (is (js? "test.js")))
  (testing "false when it is not a js file"
    (is (not (js? "test.java")))))
