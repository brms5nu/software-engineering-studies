(ns challenge-009.my-solution)

(def user {:name     "John"
           :email    "john@example.com"
           :password "secret123"
           :ssn      "123-45-6789"
           :age      30})

(defn remove-sensitive [user]
  (dissoc user :password :ssn))

(println (remove-sensitive user))

(defn -test []
  ;; Happy path - removes both sensitive fields
  (assert (= (remove-sensitive {:name "John" :email "john@test.com"
                                :password "secret" :ssn "123-45-6789" :age 30})
             {:name "John" :email "john@test.com" :age 30})
          "Should remove :password and :ssn, keeping everything else")

  ;; Result must not contain :password
  (assert (not (contains? (remove-sensitive user) :password))
          "Result should not contain :password key")

  ;; Result must not contain :ssn
  (assert (not (contains? (remove-sensitive user) :ssn))
          "Result should not contain :ssn key")

  ;; Preserves all non-sensitive fields
  (let [result (remove-sensitive {:name "Ana" :email "ana@t.com" :age 28
                                  :role :admin :password "p" :ssn "s"})]
    (assert (= result {:name "Ana" :email "ana@t.com" :age 28 :role :admin})
            "Should preserve all non-sensitive fields including extra ones"))

  ;; Missing :password - no error, :ssn still removed
  (assert (= (remove-sensitive {:name "Bob" :email "bob@t.com" :ssn "111-22-3333"})
             {:name "Bob" :email "bob@t.com"})
          "Should handle missing :password gracefully (dissoc ignores absent keys)")

  ;; Missing :ssn - no error, :password still removed
  (assert (= (remove-sensitive {:name "Eve" :email "eve@t.com" :password "pass"})
             {:name "Eve" :email "eve@t.com"})
          "Should handle missing :ssn gracefully")

  ;; Neither sensitive field present
  (assert (= (remove-sensitive {:name "Dan" :email "dan@t.com"})
             {:name "Dan" :email "dan@t.com"})
          "Should return map unchanged when no sensitive fields exist")

  ;; Empty map
  (assert (= (remove-sensitive {})
             {})
          "Should return empty map for empty input")

  ;; Immutability - original map is not modified
  (let [original {:name "Test" :password "secret" :ssn "000-00-0000"}]
    (remove-sensitive original)
    (assert (contains? original :password)
            "Original map should still contain :password after removal")
    (assert (contains? original :ssn)
            "Original map should still contain :ssn after removal"))

  ;; Result is a map
  (assert (map? (remove-sensitive user))
          "Should return a map")

  (println "✓ All tests passed for remove-sensitive"))
(-test)
