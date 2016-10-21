(ns boot-web.core
  (:require [ring.adapter.jetty :refer [run-jetty]]
            [compojure.core :refer [GET defroutes]]
            [compojure.route :refer [not-found resources]]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]])
  (:gen-class))

(defroutes routes
  (resources "/")
  (not-found "Not Found"))

(def app
  (wrap-defaults #'routes site-defaults))

(defn -main [& args]
  (let [port 5000]
    (run-jetty app {:port port :join? true})))


