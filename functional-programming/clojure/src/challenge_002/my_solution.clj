(ns challenge-002.my-solution
  (:require [clojure.string :as str]))

(defn valid-email? [email]
  (and (not (str/blank? email))
       (str/includes? email "@")))

(println (valid-email? "test"))
(println (valid-email? ""))
(println (valid-email? "bruno.moreira@nubank.com.br"))

(defn -test []
  ;; Valid emails
  (assert (= (valid-email? "user@domain.com") true)
          "Standard email should be valid")
  (assert (= (valid-email? "user@domain.com.br") true)
          "Email with subdomain should be valid")
  (assert (= (valid-email? "user@domain") true) 
          "Domain without dot should be valid")
  
  ;; Missing @ symbol
  (assert (= (valid-email? "userdomain.com") false)
          "Missing @ symbol should be invalid")
  (assert (= (valid-email? "plaintext") false)
          "Plaintext should be invalid")
  
   ;; Blank / empty inputs
  (assert (= (valid-email? "") false)
          "Empty string should be invalid")
  (assert (= (valid-email? "   ") false)
          "Whitespace-only string should be invalid")

  ;; Edge cases around @
  (assert (= (valid-email? "@") true)
          "Lone @ technically passes both checks") 
  (assert (= (valid-email? " @domain.com") true)
          "Leading space with @ passes (no trim in function)")

  (println "✓ All tests passed for valid-email?"))
(-test)
