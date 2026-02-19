(ns challenge-004.my-solution)

(def user-map
  {:name    "John Doe"
   :email   "john@example.com"
   :age     30
   :address "123 Main St"
   :phone   "555-1234"})

(defn extract-content-info [user]
  (select-keys user [:name :email]))

(println (extract-content-info user-map))

(defn -test []
  ;; Happy path - extracts only :name and :email
  (assert (= (extract-content-info {:name "John" :email "john@test.com" :age 30 :phone "555"})
             {:name "John" :email "john@test.com"})
          "Should extract only :name and :email, ignoring other keys")

  ;; Exact match - input has only the selected keys
  (assert (= (extract-content-info {:name "Ana" :email "ana@test.com"})
             {:name "Ana" :email "ana@test.com"})
          "Should return same map when input has only :name and :email")

  ;; Missing :email
  (assert (= (extract-content-info {:name "Bruno"})
             {:name "Bruno"})
          "Should return only :name when :email is absent")

  ;; Missing :name
  (assert (= (extract-content-info {:email "test@test.com"})
             {:email "test@test.com"})
          "Should return only :email when :name is absent")

  ;; Empty map
  (assert (= (extract-content-info {})
             {})
          "Should return empty map when input is empty")

  ;; No matching keys at all
  (assert (= (extract-content-info {:age 25 :phone "555" :address "Main St"})
             {})
          "Should return empty map when neither :name nor :email exist")

  ;; Values can be any type
  (assert (= (extract-content-info {:name nil :email nil :age 30})
             {:name nil :email nil})
          "Should include keys even when their values are nil")

  (println "✓ All tests passed for extract-content-info"))

(comment
  (-test))
