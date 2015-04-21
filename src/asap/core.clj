(ns asap.core
  (:gen-class))

(require '[clojure.java.io :as io])

(defn readfile [name]
  "TODO many things"
  (->> name
    io/resource
    io/reader
    line-seq
    println))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (readfile "test.txt"))
