(ns challenge-014.my-solution)

(defn add-full-name [{:keys [first-name last-name] :as person}]
  (assoc person :full-name (str first-name " " last-name)))

(prn (add-full-name {:first-name "John" :last-name "Doe"}))
(prn (add-full-name {:first-name "Jane" :last-name "Smith"}))

(defn -test []
  ;; Standard case
  (assert (= (add-full-name {:first-name "John" :last-name "Doe"})
             {:first-name "John" :last-name "Doe" :full-name "John Doe"})
          "Should add :full-name combining first and last name")

  ;; Different names
  (assert (= (:full-name (add-full-name {:first-name "Jane" :last-name "Smith"}))
             "Jane Smith")
          "Should produce correct full name for any input")

  ;; Preserves existing fields
  (assert (= (add-full-name {:first-name "Bob" :last-name "Johnson" :age 30 :email "bob@test.com"})
             {:first-name "Bob" :last-name "Johnson" :age 30 :email "bob@test.com" :full-name "Bob Johnson"})
          "Should preserve all existing fields when adding :full-name")

  ;; Overwrites existing :full-name
  (assert (= (:full-name (add-full-name {:first-name "Ana" :last-name "Lima" :full-name "old"}))
             "Ana Lima")
          "Should overwrite an existing :full-name with the computed value")

  ;; Special characters in names
  (assert (= (:full-name (add-full-name {:first-name "María José" :last-name "O'Brien"}))
             "María José O'Brien")
          "Should handle accented characters and apostrophes")

  ;; Immutability - original map is not modified
  (let [original {:first-name "Dan" :last-name "Test"}
        result   (add-full-name original)]
    (assert (not (contains? original :full-name))
            "Original map should not contain :full-name")
    (assert (contains? result :full-name)
            "Result map should contain :full-name"))

  ;; Full name has a space between parts
  (let [result (add-full-name {:first-name "A" :last-name "B"})]
    (assert (= (:full-name result) "A B")
            "Full name should have exactly one space between first and last name"))

  ;; Missing :last-name - destructuring yields nil
  (assert (= (:full-name (add-full-name {:first-name "Solo"}))
             "Solo ")
          "Missing :last-name results in nil, str produces trailing space")

  ;; Missing :first-name
  (assert (= (:full-name (add-full-name {:last-name "Only"}))
             " Only")
          "Missing :first-name results in nil, str produces leading space")

  ;; Result is a map
  (assert (map? (add-full-name {:first-name "X" :last-name "Y"}))
          "Should return a map")

  (println "✓ All tests passed for add-full-name"))
(-test)
