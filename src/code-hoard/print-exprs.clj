(ns code-hoard.print-exprs
  (:require [clojure.spec.alpha :as s]
            [clojure.pprint :refer [pprint]]
            [clojure.string :as str]))

;;;; Repl-in-your-step
;;; This library will provide a context that you can
;;; put multiple forms into.  It will, at a provided speed
;;; go through each expression, printing the forms and evaluation
;;; in the repl output.

;;;; Example
#_(print-exprs
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
;; TODO Will create a namespace for every group of forms, named after the string that appears as the first.

;; Allow keyboard navigation to step through (stretch).
#_(defn make-ns
    [header]
    (let [ns-name (-> header
                      (str/replace #" " "_")
                      .toLowerCase)] )
    `(ns (symbol ns-name)))

(defn get-expr-print
  [expr]
  `(println '~expr "  ;=> " ~expr))

(defn print-header
  [header]
  `(println " * " ~header))

(defn process-exprs [[header & exprs]]
  (let [p-head (print-header header)
        p-exprs (map get-expr-print exprs)]
    `(~p-head ~@p-exprs (print "\n"))))

(defmacro print-exprs
  [& forms]
  `(do
     ~@(mapcat process-exprs forms)))
