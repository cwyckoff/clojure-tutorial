(ns clojure-tutorial.functions)

(def numbers [1 2 3 4 5])
(def whole-numbers (iterate inc 1))

(defn add-two [x]
  (+ 2 x))

;; BUILDING FROM EXISTING COLLECTIONS

;; Adding to a collection.  cons always adds to the beginning of a collection.
(cons 0 numbers)

;; Clojure will conj onto a collection either at the beginning or end of a collection depending on the collection type.
;; Numbers is a vector.  6 is added onto the end of the new collection returned by conj because that operation is more efficient for a vector.
(conj numbers 6)

;; Compare that with how conj works on a list data structure.
(conj (list 1 2 3 4 5) 6)

;; #conj is also variadic; in other words, it takes an infinite number of args.
(conj numbers 6 7 8 9)

(concat (range 0 10) (range 10 20))

;; #assoc: return a new map w/ mapping of keys to values
(let [m {:foo "bar"}]
  (assoc m :boo "baz" :cuk "coo"))

;; #assoc-in: return a new map w/ mapping of nested keys to values
(let [m {:a {:b "c"}}]
  (assoc-in m [:a :d] "e"))


;; CREATING COLLECTIONS

(vector 1 2 3 4 5)

(vec (list 1 2 3 4 5))

;; Instead of explicitly defining numbers as we did above, we could just specify a range of numbers.
(range 1 6)

;; We could also do this:
(take 5 (iterate inc 1))
;; #iterate begins w/ x and continue forever applying function f to each value to calculate the next.

;; Repeat an element n times
(repeat 5 1)

;; Getting the first few values from a collection
(take 3 numbers)

;; Dropping the first few values from a collection
(drop 3 numbers)

;; Take a collection and cycle it infinitely
(take 10 (cycle (range 3)))

;; partition a collection
(take 10 (partition 3 (cycle (range 1 10))))
;; with step
(take 10 (partition 3 1 (cycle (range 1 10))))

;; Take multiple collections and produce a new one that interleaves values from each
;; N.B., #interleave stops when one of the collections is exhausted.
(let [whole-numbers (iterate inc 1)]
  (interleave whole-numbers ["a" "b" "c" "d" "e"]))

;; concatenate nested collections
(flatten [[1 2] [3 4] [5 6]])

;; Exercises:
(def inventory-map {:book {:title "Javascript Allonge"
                           :price 23.99
                           :isbn "B00FLKRCVO"
                           :author "Reg Braithwaite"}})
;; 1) create a collection of all vowels, cycle them, and take the first 50.
;; 2) create a list of numbers from 1 to 100, convert them to a vector, append 101 to the end, and prepend 0 to the beginning
;; 3) using inventory-map, add a :video key with a map value (e.g., {:title ..., :speaker ..., :conf ...}) at that key.
;; 4) using inventory-map, add a :pub-date to the book.
;; 5) write a function that takes a collection and returns the max value (e.g., (my-max [1 8 3 4]).  do not use the #max fn
;; 6) write a function that produces a list of all the rotations of a collection (e.g., (rotations "chris") ;; => ((\c \h \r \i \s) (\h \r \i \s \c) (\r \i \s \c \h) (\i \s \c \h \r) (\s \c \h \r \i))


;; TRANSFORMING COLLECTIONS

;; Applying a function to all values in a collection
;; Note that "map" is a "higher order function" since it takes a function as its first parameter
(map add-two numbers)

;; Applying a function of two parameters function to all values in two collections
(map * numbers numbers)

;;; Anonymous Functions
(fn [x] (+ x 10))

#(+ % 10)
(#(+ % 10) 20)
(let [add-ten #(+ % 10)]
  (add-ten 20))

#(+ %1 %2)
(#(+ %1 %2) 3 4)
(let [f #(+ %1 %2)]
  (f 3 4))

;; Applying an anonymous function to all values in a collection
(map #(+ % 100) numbers)

;; filter
(filter even? [1 2 3 4 5 6 7 8 9 10])
(filter (fn [x] (> x 5)) [1 2 3 4 5 6 7 8 9 10])

;; reduce
(reduce + (range 1 10))

;; list comprehensions
(for [n numbers]
  (add-two n))

;;; can emulate filter by using a :when clause
(take 10 (for [n whole-numbers :when (even? n)] n))

(for [x (range 0 6)
      :let [y (* x 3)]
      :when (even? y)]
  y)

;;; working w/ more than one binding expression
;;; collections are iterated in a nested fashion, rightmost fastest
(for [a (range 1 5)
      b (range 1 5)
      c (range 1 5)]
  [a b c])

;; Exercises:
;; 1) use map to mimic #interleave's functionality.  Hint, #flatten will take a collection of collections and 'flatten' them into one.
;;    (e.g., (my-interleave ["a" "b" "c" "d" "e"] [1 2 3 4 5]) ;; => ("a" 1 "b" 2 "c" 3 "d" 4 "e" 5))
;; 2) write a function that duplicates each element of a sequence.
;;    (e.g., (my-dup [1 2 3]) ;; => [1 1 2 2 3 3]
;; 3) write a function to split a sequence into two parts
;;    (e.g., (my-split-at 1 [:a :b :c :d]) ;; => [[:a] [:b :c :d]]
;; 4) write a function that determines if it's input is a palindrome
;;    (e.g., (palindrome "racecar") ;; => true
;;           (palindrome [1 1 3 3 1 1]) ;; => true
;;           (palindrome [:a :b :c]) ;; => false
;; 5) write a function that drops every nth item from a collection
;; 6) using a list comprehension, produce a sequence of numbers less than 40 that are divisible by 4
