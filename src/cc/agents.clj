(ns cc.agents)

;(def primes (agent []))

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
	(let [primes []]
	  (println primes)
	  (dotimes [n i]
		(if (prime? n) (conj primes n)))
	  primes)))

