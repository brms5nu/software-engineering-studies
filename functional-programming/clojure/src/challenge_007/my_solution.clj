(ns challenge-007.my-solution
  (:require [clojure.string :as str]))

(def names ["john" "jane" "bob"])
(def names-2 ["" "jane" ""])

(defn uppercase-names [names]
  (mapv str/upper-case names))

(println (uppercase-names names))
(println (uppercase-names names-2))

(defn -test []
  ;; Lowercase names
  (assert (= (uppercase-names ["john" "jane" "bob"])
             ["JOHN" "JANE" "BOB"])
          "Should convert all lowercase names to uppercase")

  ;; Mixed case names
  (assert (= (uppercase-names ["Alice" "Charlie" "bOB"])
             ["ALICE" "CHARLIE" "BOB"])
          "Should handle mixed case names")

  ;; Already uppercase
  (assert (= (uppercase-names ["JOHN" "JANE"])
             ["JOHN" "JANE"])
          "Already uppercase names should remain unchanged")

  ;; Empty vector
  (assert (= (uppercase-names [])
             [])
          "Should return empty vector for empty input")

  ;; Empty strings in the vector
  (assert (= (uppercase-names ["" "test" ""])
             ["" "TEST" ""])
          "Empty strings should remain empty")

  ;; Single element
  (assert (= (uppercase-names ["hello"])
             ["HELLO"])
          "Should work with a single element")

  ;; Names with spaces
  (assert (= (uppercase-names ["john doe" "jane smith"])
             ["JOHN DOE" "JANE SMITH"])
          "Should uppercase names that contain spaces")

  ;; Names with special characters
  (assert (= (uppercase-names ["o'brien" "josé"])
             ["O'BRIEN" "JOSÉ"])
          "Should handle special characters and accented letters")

  ;; Preserves order
  (assert (= (uppercase-names ["charlie" "alice" "bob"])
             ["CHARLIE" "ALICE" "BOB"])
          "Should preserve original order")

  ;; Returns a vector
  (assert (vector? (uppercase-names ["test"]))
          "Should return a vector, not a lazy sequence")

  (println "✓ All tests passed for uppercase-names"))
(-test)
