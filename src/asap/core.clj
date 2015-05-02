(ns asap.core
  (:gen-class))

(require '[clojure.java.io :as io])

(def pogues-js-dir "/Users/romaintailhurat/code/javascript/Pogues/src/js")

(defn source-as-seq [source]
  "Return a sequence of java.io/file from a source directory"
  (file-seq
    (clojure.java.io/file source)))

(defn get-dir-collection [file-seq]
  "Return java.io/file that are directories"
  (filter #(.isDirectory %) file-seq))

(defn get-file-collection [file-seq]
  "Return java.io/file that are directories"
  (filter #(.isFile %) file-seq))

(defn js? [file-name]
  "<true> is '.js' is found in the file name"
  (not (nil? (re-find #".js" file-name))))

(defn get-js-file-collection [file-seq]
  "Return only .js file"
  (filter js? (map #(.getName %) file-seq)))

(defn -main
  "MAIN !"
  [& args]
  (let [
    source (nth args 0)
    first (nth
      (get-js-file-collection (source-as-seq source)) 0)
    ]
    (println first)))
