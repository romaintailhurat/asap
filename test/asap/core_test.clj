(ns asap.core-test
  (:require [clojure.test :refer :all]
            [asap.core :refer :all]))

(defn yo [] "yo")

(deftest yo-test
  (testing "Simple yo test"
    (is (= "yo" "yo"))))

(def pogues-dir (file-seq (clojure.java.io/file "/Users/romaintailhurat/code/javascript/Pogues/")))

(deftest dir-read
  (testing "wesh"
    (println (filter #(.isFile %) pogues-dir))))
