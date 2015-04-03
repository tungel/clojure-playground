;;;
;;; https://github.com/everbot/clojure-playground
;;;

(ns clojure-playground.mystuff
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn sum [n]
  (if (= n 0)
    0
    (+ n (sum (- n 1)))))

(sum 10)

(reverse "abc")

