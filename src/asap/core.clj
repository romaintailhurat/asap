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
    (file-seq (clojure.java.io/file source)))

(defn get-dir-collection [file-seq]
  "Return java.io/file that are directories"
  (filter #(.isDirectory %) file-seq))

(defn get-file-collection [file-seq]
  "Return java.io/file that are files"
  (filter
    #(nil? (some #{"vendor" "__tests__"}
      (clojure.string/split (.getAbsolutePath %) #"/")))
    (filter #(.isFile %) file-seq)))

(defn js? [file]
  "<true> if '.js' is found in the file name"
  (not (nil? (re-find #".js" (.getName file)))))

(defn with-require? [line]
  (not (nil? (re-find #"require" line))))

(defn not-commented? [line]
  (nil? (re-find #"//" line)))

(defn get-js-file-collection [file-seq]
  "Return only .js file"
  (filter js? file-seq))

(defn filename-from-require [require-declaration]
  "Return the filename in a require declaration"
  "regexp, see http://stackoverflow.com/a/2403159"
  ;;(println "Parsing require : " require-declaration)
  (last
    (clojure.string/split
      ;; FIXME so hideous !
      (nth (re-find #"\(('|\")(.*?)('|\")\)" require-declaration) 2)
        #"/")))

(defn directory-from-require [require-declaration]
  ""
  (let [data (clojure.string/split (last (re-find #"\(('|\")(.*?)('|\")\)" require-declaration)) #"/")
        len (count data)]
    (nth data (- len 2))))

(defn collect-require [file]
  "Return a sequence of 'require' declarations"
  (with-open [reader (clojure.java.io/reader file)]
    ;; doall trick, see http://stackoverflow.com/questions/6613470/
    (doall
      (filter with-require? (filter not-commented? (line-seq reader))))))

(defn extract-dependency [file]
  ""
  ;;FIXME use a record ? a map ? a type ?
  ;;(println "Extracting dependency from : " file)
  {:name (.getName file) :deps (vec (map filename-from-require (collect-require file)))})

(defn -main
  "MAIN !"
  [& args]
  (println "-- Starting asap job")
  (let [source (nth args 0)
        js-files (get-js-file-collection (get-file-collection (source-as-seq source)))]
    (println "Source is " source)
    (println "Size of js files collection :" (count js-files))

    (spit "data.edn"  (pr-str (map extract-dependency js-files)))))
