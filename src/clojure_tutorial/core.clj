(ns clojure-tutorial.core
  (:require [clojure.pprint :refer [pprint]]))

(+ 1 1 1 1 1 1 1 1)

;; Hello World in Clojure
(println "Hello World")

;; str is a function that converts its argument to a string.  If multiple arguments are passed, it concatenates them.
(str 1)
(str "http://foo" "/" "bar" ".com")

;; Truthy and Falsey
;; nil and false are treated as "false"; everything else is true.

;; Declaring a variable
(def x 1)

;; Arithmetic
(+ 2 5)

;; Arithmetic using already-defined variables
(+ x 10)

;; Declare a variable with local scope
(let [x 2] (+ x 10))

;; Exercises:
;; 1) multiply x by 10
;; 2) subtract x from 10
;; 3) multiply 2 by 3 by 4 by 5 (hint: multiply (*) is a variadic function)

;; Defining a function
(defn add-two [x]
  (+ 2 x))

;; Applying a function
(add-two 100)

;; Control structures
(if (> 1 2)
  :foo
  :bar)

(let [x 42]
  (cond (< x 0) :negative
        (> x 0) :positive
        :else :zero))

;; Exercises:
;; 1) define a function which doubles a number.
;; 2) define a function to take two arguments, subtracting the first from the product of the second and 10.
;; 3) define a function called emphatic that takes an argument (a string) and appends "!!!" to that argument.
;; 4) define a function that assigns letter grades based on a numeric value passed in (e.g., (assign-grade 75) ;; => "C")
