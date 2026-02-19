(ns challenge-015.my-solution
  (:require [clojure.string :as str]))

(defn validate-user
  [user]
  (cond (str/blank? (:name user)) {:status :error :message "Name cannot be empty"}
        (not (str/includes? (:email user) "@")) {:status :error :message "Invalid email format"}
        (not (>= (:age user) 18)) {:status :error :message "User must be adult"}
        :else {:status :success :user user}))

(prn (validate-user {:name "John" :email "john@example.com" :age 25}))
(prn (validate-user {:name "" :email "john@example.com" :age 25}))
(prn (validate-user {:name "John" :email "invalid" :age 25}))
(prn (validate-user {:name "John" :email "john@example.com" :age 17}))

(defn -test []
  ;; All valid - success
  (assert (= (validate-user {:name "John" :email "john@example.com" :age 25})
             {:status :success :user {:name "John" :email "john@example.com" :age 25}})
          "Should return success when all validations pass")

  ;; Empty name
  (assert (= (validate-user {:name "" :email "john@example.com" :age 25})
             {:status :error :message "Name cannot be empty"})
          "Should return error for empty name")

  ;; Blank name (whitespace only)
  (assert (= (validate-user {:name "   " :email "john@example.com" :age 25})
             {:status :error :message "Name cannot be empty"})
          "Should return error for whitespace-only name")

  ;; Invalid email (no @)
  (assert (= (validate-user {:name "John" :email "invalid" :age 25})
             {:status :error :message "Invalid email format"})
          "Should return error for email without @")

  ;; Underage
  (assert (= (validate-user {:name "John" :email "john@example.com" :age 17})
             {:status :error :message "User must be adult"})
          "Should return error for age under 18")

  ;; Boundary - exactly 18
  (assert (= (:status (validate-user {:name "Jane" :email "jane@test.com" :age 18}))
             :success)
          "Age 18 should pass validation")

  ;; Validation order: name checked before email
  (assert (= (:message (validate-user {:name "" :email "invalid" :age 10}))
             "Name cannot be empty")
          "Name validation should trigger first, even if email and age are also invalid")

  ;; Validation order: email checked before age
  (assert (= (:message (validate-user {:name "John" :email "invalid" :age 10}))
             "Invalid email format")
          "Email validation should trigger before age validation")

  ;; Success response contains the original user map
  (let [user   {:name "Ana" :email "ana@test.com" :age 30}
        result (validate-user user)]
    (assert (= (:user result) user)
            "Success response should contain the original user map"))

  ;; Error response does not contain :user
  (let [result (validate-user {:name "" :email "x@x.com" :age 20})]
    (assert (not (contains? result :user))
            "Error response should NOT contain :user key"))

  ;; Success response does not contain :message
  (let [result (validate-user {:name "Valid" :email "v@v.com" :age 20})]
    (assert (not (contains? result :message))
            "Success response should NOT contain :message key"))

  ;; Age 0
  (assert (= (:message (validate-user {:name "Baby" :email "b@b.com" :age 0}))
             "User must be adult")
          "Age 0 should fail age validation")

  (println "✓ All tests passed for validate-user"))
(-test)
