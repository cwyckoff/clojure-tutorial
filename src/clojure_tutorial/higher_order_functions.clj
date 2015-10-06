(ns clojure-tutorial.higerh-order-functions)

(defn make-adder [n]
  (fn [x]
    (+ x n)))

;; Applying a generated function
((make-adder 7) 10)

(let [add-one (make-adder 1)
      add-ten (make-adder 10)]
  (+ (add-one 20)
    (add-ten 3)))

;; Partially applied functions
(defn adder [x y]
  (+ x y))

(let [add-one (partial adder 1)
      add-ten (partial adder 10)]
  (+ (add-one 20)
    (add-ten 3)))
