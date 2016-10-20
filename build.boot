(def project 'boot-web)
(def version "1.0.0-SNAPSHOT")

(set-env! :resource-paths #{"resources" "src"}
          :dependencies   '[[org.clojure/clojure "RELEASE"]
                            [ring-server "0.4.0"]
                            [ring "1.4.0"]
                            [ring/ring-defaults "0.1.5"]
                            [compojure "1.4.0"]
                            [adzerk/boot-template "1.0.0"]])

(require '[adzerk.boot-template :as bt])
(require '[boot-web.boot-tasks :as boot-tasks])

(defn generate-version
  []
  (str
   (clojure.string/replace version "SNAPSHOT" "")
   (boot-tasks/generate-timestamp)))

(task-options!
 aot {:namespace   #{'boot-web.core}})

(deftask template-index
  [v version VERSION str "The application version"]
  (bt/template :paths ["public/index.html"] :subs {"version" version
                                                   "color" (boot-tasks/generate-color)}))

(deftask test-template
  []
  (let [dir #{"target"}
        version (generate-version)]
    (comp (template-index :version version) (target :dir dir))))

(deftask versioned-jar
  [v version VERSION str "The application version"]
  (jar :main        'boot-web.core
       :file        (str "examples/boot-web-" version ".jar")))


(deftask build
  "Build the project locally as a JAR."
  [d dir PATH #{str} "the set of directories to write to (target)."]
  (let [dir (if (seq dir) dir #{"target"})
        version (generate-version)]
    (comp (template-index :version version) (aot) (uber) (versioned-jar :version version) (target :dir dir))))

(deftask run
  "Run the project."
  [a args ARG [str] "the arguments for the application."]
  (require '[boot-web.core :as app])
  (apply (resolve 'app/-main) args))

