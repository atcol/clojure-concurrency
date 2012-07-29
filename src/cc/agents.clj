(ns cc.agents
  (:import (java.awt BorderLayout Dimension))
  (:import (javax.swing JLabel JFrame)))

(def primes (agent []))

; Deliberately poor prime algorithm
(defn prime? [n] 
  "Return true if n is prime, otherwise false"
  (if (< n 2)
	false
  (loop [x 2]
	(if (>= x n) true (if (= 0 (mod n x)) false (recur (inc x)))))))

(defn find-primes [i] 
  "Find all primes up to i inclusive"
  (if (< i 2) 
	[]
	(let []
	  (dotimes [n (inc i)]
		(if (prime? n) (send primes #(conj %1 n))))
	  (await primes) ; block until primes is ready
	  @primes)))

; Demonstrates the use of futures and agents and presents results in a Swing GUI
(defn show-primes [i]
  "Find all primes up to i inclusive and present them in a GUI"
  (let [fr (JFrame. "Prime Numbers")
        lbl (JLabel. "Calculating primes...")
        pane (.getContentPane fr)]
    (def f (future (find-primes i)))
    (.setPreferredSize lbl (Dimension. 200 200))
    (.setPreferredSize fr (Dimension. 200 200))
    (.setSize fr 500 500)
    (.add pane lbl BorderLayout/CENTER)
    (.setVisible fr true)
    (.setText lbl (str "Prime numbers for " i " are " @f))))

(show-primes 60000)
