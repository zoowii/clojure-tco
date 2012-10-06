;;----------------------------------------------------------------------
;; File do.clj
;; Written by Chris Frisz
;; 
;; Created 16 Apr 2012
;; Last modified  5 Oct 2012
;; 
;; Defines the Do and DoSync record types and operations for 'do' and
;; 'dosync' expressions in the Clojure TCO compiler.
;;
;; Do implements the following protocols:
;;
;;      PUnparse:
;;              Unparses (recursively) the syntax for the expression as
;;              `(do ~@expr*).
;;----------------------------------------------------------------------

(ns ctco.expr.do
  (:require [ctco.protocol :as proto]))

(defrecord Do [expr*]
  proto/PLoadTrampoline
    (load-tramp [this tramp]
      (proto/walk-expr this #(proto/load-tramp % tramp) nil))

  proto/PThunkify
    (thunkify [this]
      (proto/walk-expr this proto/thunkify nil))
  
  proto/PUnparse
    (unparse [this]
      `(do ~@(map proto/unparse (:expr* this))))

  proto/PWalkable
    (walk-expr [this f _]
      (Do. (reduce #(conj %1 (f %2)) [] (:expr* this)))))
