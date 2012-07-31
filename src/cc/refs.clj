(ns cc.refs)

(defstruct person :name :age)

(def people (ref ()))

(def ages (ref ()))

(def names (ref ()))

(defn rand-num [] (* 100 (rand)))

(defn record-person [p]
  (dosync
    (ref-set people (cons p @people)
    (ref-set ages (cons (p :age) @ages))
    (ref-set names (cons (p :name) @ages))))

(defn rand-person []
  "Create a new person struct with random name and age"
  (struct person (str (char (rand-num))) (int (rand-num))))

(println (rand-person))
