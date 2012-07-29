(ns cc.test.agents
  (:use [cc.agents])
  (:use [clojure.test]))

(deftest test-find-primes 
  (is (= [] (find-primes 1)))
  (is (= [2 3 5 7 11 13 17 19 23 29 31 37 41 43 47 53 59] (find-primes 60))))

(deftest test-is-prime-true
  (is (= true (prime? 2)))
  (is (= true (prime? 3)))
  (is (= true (prime? 11)))
  (is (= true (prime? 59)))
  (is (= true (prime? 1361)))
  (is (= true (prime? 2591)))
  (is (= true (prime? 3257)))
  (is (= true (prime? 7919))))

(deftest test-is-prime-false
  (is (= false (prime? -1)))
  (is (= false (prime? 0)))
  (is (= false (prime? 1)))
  (is (= false (prime? 7918))))
