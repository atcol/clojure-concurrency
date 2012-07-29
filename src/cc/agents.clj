(ns cc.agents
  (:import (java.awt BorderLayout Dimension))
  (:import (javax.swing JLabel JFrame JList JScrollPane DefaultListModel)))

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
        lbl (JLabel. (str "Here are all the prime numbers for " i ":"))
        lm (DefaultListModel.)
        jl (JList. lm)
        sp (JScrollPane. jl)
        pane (.getContentPane fr)]
    (def f (future (find-primes i))) ; asynchronously find the primes while we set up the GUI
    (.setPreferredSize lbl (Dimension. 410 20))
    (.setPreferredSize sp (Dimension. 410 190))
    (.setSize fr 410 210)
    (.setVisibleRowCount jl 5)
    (.add pane lbl BorderLayout/PAGE_START)
    (.add pane sp BorderLayout/CENTER)
    (.setVisible fr true)
    ;(println (count @f))
    (dotimes [n 10] (.addElement lm (str "Moo " n))
    (println "Done"))))

(show-primes 60000)
