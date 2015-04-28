(ns asap.core
  (:gen-class))

(require '[clojure.java.io :as io])

(def pogues-js-dir "/Users/romaintailhurat/code/javascript/Pogues/src/js")

(defn source-as-seq [source]
  "Return a sequence of java.io/file from a source directory"
  (file-seq
    (clojure.java.io/file source)))

(defn get-dir-collection [file-seq]
  "Return java.io/file that are directory"
  (filter #(.isDirectory %) file-seq))

(defn -main
  "MAIN !"
  [& args]
  (println
    (get-dir-collection
      (source-as-seq pogues-js-dir))))
