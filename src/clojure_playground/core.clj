;;;
;;; https://github.com/everbot/clojure-playground
;;;

(ns clojure-playground.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn square [x]
  (* x x))

(def a (list 1 2 3)) ; creat variable a that contains a list

(println a)

; (extend-type java.util.List
;   Concatenatable
;   (cat [this other]
;     (concat this other)))
; (cat [1 2 3] [4 5 6])

(println "Square result is:" (square 3))

(println 0x7E)
(println (- 2 1.9))
(/ 3.0 2)

(println \a) ; print character a

[1 2 :a :b] ; vector

{1 "one", 2 "two", 3 "three"} ; map

#{1 2 "three" :four 0x5} ; set: includes unique items

#{} ; empty set

(defn mk-set [x y] #{ x y }) ; return a set of 2 input values

(defn mk-set-any
  ([x] #{x})
  ([x y] #{ x y }))

(println (mk-set 10 11))
(println (mk-set-any 11))

; series of block of expression that need to be treated as one, only the last
; one will be returned
(do
  6
  (+ 5 3)
  (+ 2 3))


(let [r 5
      pi 3.1415
      r-square (* r r)]
  (println "Radius is " r)
  (* pi r-square))

(range 5)
(range 1 5)
(count (range 5))
(count (range 1 5))
(count [1 2 3])
(count (list 1 2 3))

(for [x (range 3) y (range 2)] [x y])

(for [x (range 10)] (println x))

; (find-doc "xor") ; find the doc for xor function
(bit-xor 10 9)


(Math/sqrt 9) ; access Java class' static member
(Math/E) ; value of the constant e
(println Math/PI) ; print constant PI
(rem 9 5) ; remainder
(println Integer/MAX_VALUE)
(println Integer/MIN_VALUE)

; create Java class instance
(new java.util.HashMap {"foo" 42 "bar" 9 "baz" "test"})

; or use the dot after a class name to signify constructor call
(java.util.HashMap. {"foo" 42 "bar" 9 "baz" "test"})

(.x (java.awt.Point. 10 20)) ; use . operator to access Java instance member

; set Java instance properties
(let [origin (java.awt.Point. 0 0)]
  (set! (.x origin) 15)
  (str origin))

; use .. macro to make the code easier to read compare to the preceding code
(.. (java.util.Date.) toString (endsWith "2015"))

(defn fastPower [a b]
  (if (zero? b)
    1
    (if (even? b)
      (fastPower (* a a) (quot b 2))
      (* a (fastPower (* a a) (quot b 2))))))

(defn fast-power [a b]
  (cond (zero? b) 1
        (= b 1) a
        (even? b) (square (fast-power a (quot b 2)))
        (odd? b) (* a (fast-power a (dec b)))))

(fastPower 2 10)
(fast-power 2 10)

(quot 1 2)

(defn sum [n]
  (if (zero? n)
    0
    (+ n (sum (dec n)))))
(sum 10)


(defn mirror [ls]
  (if (empty? ls)
    ()
    (let [f (list (first ls))]
      (concat f
              (mirror (rest ls))
              f))))
(mirror (list 1 2 3))

(defn isPrime
  " Test if a number is prime number"
  [x]
  (if (= 2 x)
    true
    (if (even? x)
      false
      (let [max (Math/sqrt x)]
        (loop [i 3]
          (if (> i max)
            true
            (if (zero? (rem x i))
              false
              (recur (+ i 2)))))))))
(isPrime 15)

; ===================================
; showing intermediate result of sum
(reductions + (range 10))

; test
(defn add+
  {:test #(do
            (assert (= (add+ 2 3) 5))
            (assert (= (add+ 1 8) 9)))}
  [x y] (+ x y))
(test #'add+) ; trigger


; clojure.pprint/cl-format
(clojure.pprint/cl-format nil "~:r" 12345)
; => "twelve thousand, three hundred forty-fifth"
(clojure.pprint/cl-format nil "~@r" 1234)
; => "MCCXXXIV"


; browse URL
(def url "https://google.com")
;; (clojure.java.browse/browse-url url)

; Java doc
;; (clojure.java.javadoc/javadoc (list* 1 []))
; open clojure.lang.Cons Javadoc


; inspect
(require '[clojure.inspector :as ins])
(def m {:a "a"
        :b {:c "c"
            :d [1 2 3]
            :e {:f "f"
                :g "g"
                :h "h"}}
        :i [4 5 6]
        :l {:m "m"
            :n "n"}
        :o [{:p "p" :q "q"}]})
;; (ins/inspect-tree m)

; ======================================================================
;;; Experiment with graphics
;;; Code reference from Michael Fogus & Chris Houser - The Joy of Clojure
; (def frame (java.awt.Frame.))
; (for [method (seq (.getMethods java.awt.Frame))
;       :let [method-name (.getName method)]
;       :when (re-find #"Vis" method-name)]
;   method-name)
; (.setVisible frame true)
; (.setSize frame (java.awt.Dimension. 500 500))
; (def gfx (.getGraphics frame))
; (.fillRect gfx 100 100 50 75)
; (.setColor gfx (java.awt.Color. 255 128 0))
; (.fillRect gfx 100 150 75 50)

; (defn xors [maxX maxY]
;   (for [x (range maxX) y (range maxY)]
;     [x y (bit-xor x y)]))
; (doseq [[ x y xor ] (xors 200 200)]
;         (.setColor gfx (java.awt.Color. xor xor xor))
;         (.fillRect gfx x y 1 1))

; (.dispose frame) ; close the frame
; ======================================================================

(reverse "tung")
(str \t)
(str 3)
(apply str (reverse "tung"))
(filter even? (range 10))

;;; test if x is divisible by y
(defn divisible [y]
  (fn [x]
    (zero? (rem x y))))
((divisible 3) 9)
(filter (divisible 5) (range 0 20))

; use Java's System.out.println to print, but this will print out to the stdout
(.println System/out "hello world hihihi")
(.println java.lang.System/out "hi")
(.. (System/out) (println "hello"))



(defn strToBytes [str]
  (bytes (byte-array (map (comp byte int) str))))
; (def a (bytes (byte-array (map (comp byte int) "aA-clojure"))))
(def a (strToBytes "abc"))
(get a 2)

; print hex value
(format "%x" 255)

(defn checkSum [packet]
  (bit-and (bit-not (reduce + packet)) 0xFF))

(defn buildMsg [id address val1 val2]
  (let [packet
    [id
    7
    3
    address
    (bit-and val1 0xFF)
    (bit-shift-right (bit-and val1 0xFF00) 8)
    (bit-and val2 0xFF)
    (bit-shift-right (bit-and val2 0xFF00) 8)
    ]]
    (into [255 255] (conj packet (checkSum packet)))))

(defn readModel [id address l]
  (let [packet
    [id
    4
    2
    address
    l
    ]]
    (into [255 255] (conj packet (checkSum packet)))))

; try serial port
; (require '[serial.core :as s])
; (require '[serial.util :as u])
; (u/list-ports)
; (def port (s/open "/dev/ttyUSB0" :baud-rate 115200))
; (s/listen port #(println (format "%x" (.read %)))) ; `print` doesn't work!
; (s/listen port #(println "tung"))
; (s/write port [97 65])
; (s/write port (strToBytes "hello RS232"))
; (s/unlisten! port)
; (s/close port)


; (use 'serial.core)
; (use 'serial.util)
; (list-ports)
; ; (def port (open "/dev/ttyUSB0"))
; (def port (open "/dev/ttyUSB0" :baud-rate 9600))

; (write port [97 65])
; (write port (strToBytes "hello RS232"))

; ; move motor
; (println (buildMsg 3 30 200 1000))
; (s/write port (buildMsg 1 30 500 300))
; (print (buildMsg 3 30 1000 200))
; (map #(format "%x" %) (buildMsg 3 30 1000 200))
; write_port("ff ff 3 7 3 1e e8 3 c8 0 21")
; (print (buildMsg 3 30 1 200))
; (map #(format "%x" %) (buildMsg 3 30 1 200))
; write_port("ff ff 3 7 3 1e 1 0 c8 0 b")
; (s/write port (readModel 2 0 3))
; (print (readModel 3 0 3))


; for testing
; clojure-playground.core=> (print (buildMsg 3 30 1000 200))
; [255 255 3 7 3 30 232 768 200 0 36]nil
; clojure-playground.core=> (print (buildMsg 3 30 1 200))
; [255 255 3 7 3 30 1 0 200 0 11]nil
; clojure-playground.core=> (print (readModel 3 0 3))
; [255 255 3 4 2 0 3 243]nil


; ; read model number
; (println (buildMsg 1 0 3))
; (format "%x" (checkSum [1 4 2 3]))

; (write port [255 255 3 7 3 30 100 0 232 768 138])
; (format "%x" (checkSum [2]))

; ; callback function to print out received data from RS232
; (listen port #(print (char (.read %))))
; (close! port)

