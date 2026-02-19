(ns challenge-013.my-solution)

(defn birthday
  [user]
  (update user :age + 1))

(println (birthday {:name "John" :age 25}))

(defn birthday
  [user]
  (update user :age inc))

(println (birthday {:name "Bob" :age 0}))
(println (birthday {:name "Alice" :age 99}))

(defn -test []
  ;; Standard increment
  (assert (= (birthday {:name "John" :age 25})
             {:name "John" :age 26})
          "Should increment age by 1")

  ;; Boundary - turning adult
  (assert (= (birthday {:name "Jane" :age 17})
             {:name "Jane" :age 18})
          "Should increment from 17 to 18")

  ;; Age zero - newborn's first birthday
  (assert (= (birthday {:name "Bob" :age 0})
             {:name "Bob" :age 1})
          "Should increment from 0 to 1")

  ;; Large age
  (assert (= (birthday {:name "Alice" :age 99})
             {:name "Alice" :age 100})
          "Should increment from 99 to 100")

  ;; Preserves all other fields
  (assert (= (birthday {:name "Eve" :age 30 :email "eve@test.com" :role :admin})
             {:name "Eve" :age 31 :email "eve@test.com" :role :admin})
          "Should preserve all fields besides :age")

  ;; Immutability - original map is not modified
  (let [original {:name "Dan" :age 40}
        result   (birthday original)]
    (assert (= (:age original) 40)
            "Original map should still have age 40")
    (assert (= (:age result) 41)
            "Result map should have age 41"))

  ;; Only :age changes
  (let [input  {:name "Test" :age 20}
        result (birthday input)]
    (assert (= (:name result) (:name input))
            "Name should remain unchanged")
    (assert (= (inc (:age input)) (:age result))
            "Age should be exactly input age + 1"))

  ;; Result is a map
  (assert (map? (birthday {:name "X" :age 0}))
          "Should return a map")

  (println "✓ All tests passed for birthday"))
(-test)
