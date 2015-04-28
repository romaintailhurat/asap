(ns asap.core-test
  (:require [clojure.test :refer :all]
            [asap.core :refer :all]))

(def pogues-js-dir "/Users/romaintailhurat/code/javascript/Pogues/src/js")

(defn source-as-seq [source]
  "Return a sequence of java.io/file from a source directory"
  (file-seq
    (clojure.java.io/file source)))

(defn get-dir-collection [file-seq]
  "Return java.io/file that are directory"
  (filter #(.isDirectory %) file-seq))

(deftest dir-read
  (testing "wesh"
    (let [js-dir (source-as-seq pogues-js-dir)]
      (println (get-dir-collection js-dir)))))
