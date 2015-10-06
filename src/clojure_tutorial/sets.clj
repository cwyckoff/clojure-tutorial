(ns clojure-tutorial.sets
  (:require [clojure.set :refer [difference intersection union select]]))

;; sets return distinct elements
(def nums (set [1 1 2 3 3 3 3 4 5 5 5 5 5]))

(into #{} [:foo :bar :boo :boo :foo :baz :bar])

;; sets conform to sequence abstraction
(first nums)
(rest nums)
(cons 6 nums)
(conj nums 6)

;; sets are functions that test membership in the set
(def names #{:bob :carol :alice})

(filter names [:steve :tom :nick :bob :brian :leslie :jane :carol])

(def vowels #{\a \e \i \o \u})
(def consonants (complement vowels))

(filter consonants "when you wish upon a star")

;; fns for working on sets

;;; first set w/o elements of the remaining set
(difference #{1 2} #{2 3} #{2 5})

;;; return a set that is the intersection of the input sets
(intersection #{1 2} #{2 3} #{2 5})

;;; return a set that is the union of the input sets
(union #{1 2} #{2 3} #{2 5})

;;; return a set of elements for which pred is true
(select odd? #{1 2 3 4 5})


;; Exercises
;; 1) write a function that takes a collection of names as a guest list for a party and
;;    returns a scrubbed version of the list that excludes banned guests.  for example,
;;    if the banned guests are #{"fred" "nancy"}, calling (acceptable-guests ["bob" "judy" "fred"])
;;    should return ("bob" "judy").
;; 2) write a function that takes a string and filters out all of its vowels
