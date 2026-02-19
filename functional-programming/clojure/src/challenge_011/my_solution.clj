(ns challenge-011.my-solution)

(def user [{:name "John" :age 25}
           {:name "Jane" :age 30}
           {:name "Bob" :age 45}])

(def user-2 [{:name "Alice" :age 18}])

(defn sum-ages
  [user]
  (reduce + (map :age user)))

(println (sum-ages []))
(println (sum-ages user))
(println (sum-ages user-2))

(defn -test []
  ;; Multiple users
  (assert (= (sum-ages [{:name "John" :age 25}
                         {:name "Jane" :age 30}
                         {:name "Bob" :age 45}])
             100)
          "Should sum ages of multiple users")

  ;; Single user
  (assert (= (sum-ages [{:name "Alice" :age 18}])
             18)
          "Should return the age itself for a single user")

  ;; Empty vector
  (assert (= (sum-ages [])
             0)
          "Should return 0 for empty vector")

  ;; All same age
  (assert (= (sum-ages [{:name "A" :age 20}
                         {:name "B" :age 20}
                         {:name "C" :age 20}])
             60)
          "Should sum correctly when all ages are equal")

  ;; Age zero
  (assert (= (sum-ages [{:name "Baby" :age 0}
                         {:name "Adult" :age 30}])
             30)
          "Should handle age 0 without affecting the sum")

  ;; Large ages
  (assert (= (sum-ages [{:name "Elder" :age 100}
                         {:name "Senior" :age 99}])
             199)
          "Should handle large age values")

  ;; Many users
  (assert (= (sum-ages (mapv #(hash-map :name (str "User " %) :age 10) (range 100)))
             1000)
          "Should handle a large collection (100 users x 10 = 1000)")

  ;; Result is a number
  (assert (number? (sum-ages user))
          "Should return a number")

  (println "✓ All tests passed for sum-ages"))
(-test)

