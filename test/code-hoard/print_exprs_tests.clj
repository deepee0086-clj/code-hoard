(ns code-hoard.print-exprs-tests
  (:require [code-hoard.print-exprs :as p-e]
            [clojure.test :as t]))

(t/testing "print-exprs"
  t/testing "print")

#_(macroexpand-1 (print-exprs
                  ("Basic Operators"
                   (+ 1 1)
                   (+ 2 2))

                  ("Defining variables"
                   (let [a 1
                         b 3]
                     (str "The sum is: " (+ a b))))))

#_(pprint (macroexpand-1 (process-exprs
                          '("Afterall"
                            (+ 1 2)
                            (+ 3 4)
                            (/ 1 1)))))

#_(macroexpand-1 (get-expr-print '(+ 1 2)))

#_(macroexpand-1 (print-header '("Afterall")))

#_(macroexpand-1 `(ns example))
#_(macroexpand-1 (make-ns "This is a namespace"))
