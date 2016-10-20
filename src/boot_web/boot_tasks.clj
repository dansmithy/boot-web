(ns boot-web.boot-tasks
  (:import [java.text SimpleDateFormat]
           [java.util Date]))
(defn generate-timestamp
  []
  (.format (SimpleDateFormat. "yyyyMMddHHmmss")
           (Date.)) )

(defn generate-color
  []
  (apply str "#" (repeatedly 6 #(rand-nth "0123456789abcdef"))))

 
