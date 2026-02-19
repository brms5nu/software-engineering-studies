(ns challenge-006.my-solution)

(def users [{:name "John" :age 25}
            {:name "Jane" :age 17}
            {:name "Bob" :age 30}])

(def users-2 [{:name "John" :age 16}
            {:name "Jane" :age 17}
            {:name "Bob" :age 15}])

(defn filter-adults [users]
  (filterv #(>= (:age %) 18) users))

(println (filter-adults users))
(println (filter-adults users-2))

(defn -test []
  ;; Mixed ages - keeps only adults
  (assert (= (filter-adults [{:name "John" :age 25}
                              {:name "Jane" :age 17}
                              {:name "Bob" :age 30}])
             [{:name "John" :age 25} {:name "Bob" :age 30}])
          "Should keep only users with age >= 18")

  ;; All minors - returns empty vector
  (assert (= (filter-adults [{:name "Alice" :age 16}
                              {:name "Charlie" :age 15}])
             [])
          "Should return empty vector when no adults exist")

  ;; All adults - keeps everyone
  (assert (= (filter-adults [{:name "Diana" :age 18}
                              {:name "Eve" :age 45}])
             [{:name "Diana" :age 18} {:name "Eve" :age 45}])
          "Should keep all users when everyone is an adult")

  ;; Boundary - exactly 18
  (assert (= (filter-adults [{:name "Alex" :age 18}])
             [{:name "Alex" :age 18}])
          "Age 18 should be included (boundary)")

  ;; Boundary - exactly 17
  (assert (= (filter-adults [{:name "Sam" :age 17}])
             [])
          "Age 17 should be excluded (just below boundary)")

  ;; Empty input
  (assert (= (filter-adults [])
             [])
          "Should return empty vector for empty input")

  ;; Single adult
  (assert (= (filter-adults [{:name "Solo" :age 30}])
             [{:name "Solo" :age 30}])
          "Should work with a single adult")

  ;; Preserves order
  (assert (= (filter-adults [{:name "C" :age 20}
                              {:name "A" :age 25}
                              {:name "B" :age 19}])
             [{:name "C" :age 20} {:name "A" :age 25} {:name "B" :age 19}])
          "Should preserve original order of elements")

  ;; Returns a vector (not a lazy seq)
  (assert (vector? (filter-adults [{:name "X" :age 20}]))
          "Should return a vector, not a lazy sequence")

  ;; Edge case - age 0
  (assert (= (filter-adults [{:name "Baby" :age 0}])
             [])
          "Age 0 should be excluded")

  (println "✓ All tests passed for filter-adults"))
(-test)
