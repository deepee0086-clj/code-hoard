(ns repl-in-your-step
  (:require [clojure.spec.alpha :as s]
            [clojure.pprint :refer [pprint]]
            [clojure.string :as str]))

;;;; Repl-in-your-step
;;; This library will provide a context that you can
;;; put multiple forms into.  It will, at a provided speed
;;; go through each expression, printing the forms and evaluation
;;; in the repl output.

;;;; Example
#_(repl-step-do
   500
   ("Basic Operators"
    (+ 1 1)
    (+ 2 2))

   ("Some Other Stuff"
    (def a 12)
    (str "This number is a " a ".")))

;;;; Output
;;; * Basic Operators
;;;   (ns basic-operators)
;;;   (+ 1 1) => 2
;;;   (+ 2 2) => 4

;;; * "Some Other Stuff"
;;;   (ns some-other-stuf)
;;;   (def a 12) =>#'basic-operators/a
;;;   (str "This number is a " a ".") => "This number is a 12"

;;;; Notes
;; Will create a namespace for every group of forms, named after the string that appears as the first.
;; will automatically iterate through each top level form and print result at provided rate.
;; Allow keyboard navigation to step through (stretch).
(defn make-ns [header]
  `'(clojure.core/ns `~(-> header (str/replace #" " "_") .toLowerCase)))

(defn print-header [header]
  `(println ~header))

#_(macroexpand-1 (print-header '("Afterall")))

(defn get-expr-print [expr]
  `(println '~expr))

#_(macroexpand-1 (get-expr-print '(+ 1 2)))

(defn process-exprs [[header & exprs]]
  (let [p-head (print-header header)
        p-exprs (map get-expr-print exprs)]
    `(~p-head ~p-exprs)))

#_(pprint (macroexpand-1 (process-exprs
                          '("Afterall"
                            (+ 1 2)
                            (+ 3 4)
                            (/ 1 1)))))

(defmacro repl-step-do
  [& forms]
  `(do
     ~@(mapcat process-exprs forms)))

#_(macroexpand-1 (repl-step-do
                  ("Basic Operators"
                   (+ 1 1)
                   (+ 2 2))
                  #_("Some Other Stuff"
                     (def a 12)
                     (str "This number is a " a "."))))
