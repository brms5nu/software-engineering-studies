(ns challenge-008.my-solution)

(def user {:name "John" :email "john@example.com"})

(defn add-status [user]
  (assoc user :status :active))

(println (add-status user))

(defn -test []
  ;; Happy path - adds :status to a standard user map
  (assert (= (add-status {:name "John" :email "john@example.com"})
             {:name "John" :email "john@example.com" :status :active})
          "Should add :status :active to user map")

  ;; Preserves all existing fields
  (assert (= (add-status {:name "Jane" :email "jane@test.com" :age 25 :role :admin})
             {:name "Jane" :email "jane@test.com" :age 25 :role :admin :status :active})
          "Should preserve all existing fields when adding :status")

  ;; Empty map
  (assert (= (add-status {})
             {:status :active})
          "Should add :status even to an empty map")

  ;; Overwrites existing :status
  (assert (= (add-status {:name "Bob" :email "bob@test.com" :status :inactive})
             {:name "Bob" :email "bob@test.com" :status :active})
          "Should overwrite existing :status with :active")

  ;; Immutability - original map is not modified
  (let [original {:name "Alice" :email "alice@test.com"}
        result   (add-status original)]
    (assert (not (contains? original :status))
            "Original map should not be modified")
    (assert (contains? result :status)
            "Result map should contain :status"))

  ;; Status value is the keyword :active
  (assert (= (:status (add-status {:name "X" :email "x@test.com"}))
             :active)
          "Status value should be the keyword :active")

  (assert (keyword? (:status (add-status {:name "X" :email "x@test.com"})))
          "Status value should be a keyword type")

  ;; Result is still a map
  (assert (map? (add-status {:name "Test" :email "t@t.com"}))
          "Should return a map")

  (println "✓ All tests passed for add-status"))
(-test)
