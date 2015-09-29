(ns intro.core
  (:require [clojure.pprint :refer [pprint]]))

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
(let [x 2]
  (+ x 10))

;; Exercises:
;; 1) multiply x by 10
;; 2) subtract x from 10
;; 3) multiply 2 by 3 by 4 by 5 (hint: multiply (*) is a variadic function)


;; Defining a function
(defn add-two [x]
  (+ 2 x))

;; Applying a function
(add-two 10)

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
;; 2) define a function to take two arguments, subtracting the first from the product of the second.
;; 3) define a function called emphatic that takes an argument (a string) and appends "!!!" to that argument.
;; 4) define a function that assigns letter grades based on a numeric value passed in (e.g., (assign-grade 75) ;; => "C")


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; COLLECTIONS

;; Defining a vector
(def numbers [1 2 3 4 5])

;; Defining a list
(def number-list (list 1 2 3 4 5))
;; or (def number-list '(1 2 3 4 5))

;; Defining a set
(def number-set #{1 2 3 4 5})

;; Defining a map
(def my-map {:first-name "Glenn" :last-name "Gould" :vocation "pianist"})


;; Accessing elements in a collection

;; By index (n.b., vectors can act as functions that map an index to a value in the vector)
(numbers 2)
;; #nth does the same thing
(nth numbers 2)

(first numbers)
(second numbers)
(last numbers)
(rest numbers)

;; maps are functions so you can do this
(my-map :first-name)
;; but keywords are also functions so you can do this
(:first-name my-map)
;; which is especially helpful when coupled with the thread-first operator
(def nested-map {:pianist {:first-name "Glenn"
                           :last-name "Gould"
                           :address {:street "123 Main"
                                     :apt 3
                                     :city "Toronto"
                                     :province "Ontario"
                                     :country "CA"}}})

(-> nested-map :pianist :address :city)
;; returns nil if any element in the chain is missing
(-> nested-map :pianist :address :foo)
(-> nested-map :pianist :foo :city)
(-> nested-map :foo :address :city)

;; or, you could use get-in
(get-in nested-map [:pianist :address :city])
(get-in nested-map [:pianist :foo :city] :oops)

;; reversing a sequence
(reverse numbers)

;; Exercises:
(def employees {:tech [{:name "joe" :title "developer"}
                       {:name "sally" :title "ops"}
                       {:name "timmy" :title "qa"}]
                :sales [{:name "bobby" :title "regional-sales"}
                        {:name "julie" :title "glad-hander"}]
                :marketing [{:name "tommy" :title "account-exec"}
                            {:name "julie" :title "director"}]})

;; 1) return the sales team
;; 2) get the name one member of the tech team
;; 3) create a function that takes the parameters 'name', 'age', 'experience' (e.g., 'senior', 'junior'), and 'company' and returns a person map with those keys and values
;; 4) update your function to calculate :hourly-wage based on experience; senior people make 100.00 per hour and junior people make 75.00 per hour
;; 5) get all the names of the tech team (hint, you'll need to use #map)


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
;; 5) write a function that takes a collection and returns the max value (e.g., (my-max [1 8 3 4])
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

;; Exercises:
;; 1) use map to mimic #interleave's functionality.  Hint, #flatten will take a collection of collections and 'flatten' them into one.
;; 2) write a function that duplicates each element of a sequence.
;;    (e.g., (my-dup [1 2 3]) ;; => [1 1 2 2 3 3]
;; 3) write a function to split a sequence into two parts
;;    (e.g., (my-split-at 1 [:a :b :c :d]) ;; => [[:a] [:b :c :d]]
;; 4) write a function that determines if it's input is a palindrome
;;    (e.g., (palindrome "racecar") ;; => true
;;           (palindrome [1 1 3 3 1 1]) ;; => true
;;           (palindrome [:a :b :c]) ;; => false
;; 5) write a function that drops every nth item from a collection


;;;;;;;;;;;;;;;;;;;;;;;;;
;; HIGHER ORDER FUNCTIONS
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


