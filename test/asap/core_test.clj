(ns asap.core-test
  (:require [clojure.test :refer :all]
            [asap.core :refer :all]))

(require '[clojure.java.io :as io])

(deftest js-file-pattern-matcher
  (testing "true when a js file is found"
    (is (js? (io/file "../../resources/test.js"))))
  (testing "false when it is not a js file"
    (is (not (js? (io/file "test.java"))))))
