;;----------------------------------------------------------------------
;; File pthunkify.clj
;; Written by Chris Frisz
;; 
;; Created  2 Apr 2012
;; Last modified 22 Apr 2012
;; 
;; Defines the protocol for thunkifying expression in the TCO compiler.
;;----------------------------------------------------------------------

(ns ctco.protocol.pthunkify)

(defprotocol PThunkify
  "Protocol for expressions that can be thunkified in the TCO compiler."
  (thunkify [this]
    "Transform all functions to return thunks."))