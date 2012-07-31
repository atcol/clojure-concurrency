(ns cc.refs)

(defstruct person :name :age)

(def people (ref ()))

(def ages (ref ()))

(def names (ref ()))

(def counter (ref 0))

(defn rint [] (rand-int (* (rand) 100)))

(defn record-person [p]
  (dosync
    (ref-set people (cons p @people))
    (ref-set ages (cons (p :age) @ages))
    (ref-set names (cons (p :name) @names))
    (ref-set counter (inc @counter))))

(defn rand-person []
  "Create a new person struct with random name and age"
  (struct person (str (char (rint))) (rint)))

(dotimes [n 100] (future (record-person (struct person (str "No. " n) n))))

