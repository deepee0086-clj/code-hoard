(ns repl-in-your-step)
;;;; Repl-in-your-step
;;; This library will provide a context that you can
;;; put multiple forms into.  It will, at a provided speed
;;; go through each expression, printing the forms and evaluation
;;; in the repl output.

;;;; Example
#_(repl-step-do
   500
   '("Basic Operators"
     (+ 1 1)
     (+ 2 2))

   '("Some Other Stuff"
     (def a 12)
     (str "This number is a " a ".")))

;;;; Output
;;; * Basic Operators
;;;   (+ 1 1) => 2
;;;   (+ 2 2) => 4

;;; * "Some Other Stuff"
;;;   (def a 12) =>#'basic-operators/a
;;;   (str "This number is a " a ".") => "This number is a 12"

(type ('"testing a b c"))
(type (symbol "testing a b c"))
;;;; Notes
;; Will create a namespace for every group of forms, named after the string that appears as the first.
;; will automatically iterate through each top level form and print result at provided rate.
;; Allow keyboard navigation to step through (stretch).
