(ns spec
  (:require [clojure.spec.alpha :as s]))

(s/conform even? 1000)
(s/conform even? 23)

(s/valid? even? 10)
(s/valid? even? 21)
(s/valid? nil? nil)
(s/valid? nil? 22)

(s/valid? #{:a 1 3} 3)
(s/valid? #{:a 1 3} :a)
(s/valid? #{:a 1 3} 1)
(s/valid? #{:a 1 3} 4)

;; Define a spec and add it to the registry
(s/def ::deck #{0 1 2 3 4 5 6 7 8 9 10}) ; define specification for ::deck type
(s/valid? ::deck 1); validate a deck value

(= 2 (s/conform ::deck 2))
(= :clojure.spec.alpha/invalid (s/conform ::deck 88))
(doc ::date)
