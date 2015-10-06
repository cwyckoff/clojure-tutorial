(ns clojure-tutorial.collections)

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

(-> nested-map :pianist)
(-> nested-map :pianist :address)

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
;; 3) create a person map with the following keys: 'name', 'age', 'experience' (e.g., 'senior', 'mid-level', 'junior'), and 'company'
;; 4) create a function that takes your person map and calculate :hourly-wage based on experience; senior people make 100.00 per hour, mid-levels make 75.00 per hour, and juniors make 50.00 per hour
;; 5) get all the names of the tech team from the employees map above (hint, you'll need to use the #map fn)
