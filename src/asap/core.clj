"
Structure / design of a javascript application

TODO filter 'vendor' js files
TODO struct of dirs and files like -->
  {
    :components [menu.js editor.js]
    :actions [pogues-actions.js]
  }
"

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

(defn js? [file]
  "<true> is '.js' is found in the file name"
  (not (nil? (re-find #".js" (.getName file)))))

(defn with-require? [line]
  (not (nil? (re-find #"require" line))))

(defn get-js-file-collection [file-seq]
  "Return only .js file"
  (filter js? file-seq))

(defn collect-require [file]
  ;; TODO
  (with-open [r (clojure.java.io/reader file)]
    (doseq [line (line-seq r)]
      (if (with-require? line) (println line)))))

(defn -main
  "MAIN !"
  [& args]
  (let [
    source (nth args 0)
    js-files (get-js-file-collection (source-as-seq source))
    ]
    (map collect-require js-files)))
