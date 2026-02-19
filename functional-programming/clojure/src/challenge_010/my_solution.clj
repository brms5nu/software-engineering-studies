(ns challenge-010.my-solution
  (:require [clojure.string :as str]))

(defn fetch-and-sanitize [id]
  (let [res (if (pos? id)
              {:status :success :user {
                                       :id       id,
                                       :name     (str "User " id)
                                       :email    (str/lower-case (str "user" id "@example.com"))
                                       :password "secret"}}
              {:status :error :message "Invalid user ID"})]

    (if (= (:status res) :success)
      (update res :user dissoc :password)
      res)))

(println (fetch-and-sanitize 1))
(prn (fetch-and-sanitize 0))



(defn fetch-and-sanitize
  [user-id]
  (if (pos? user-id)
    (let [fetched-user {:id       user-id
                        :name     (str "User " user-id)
                        :email    (str/lower-case (str "user" user-id "@example.com"))
                        :password "secret"}
          sanitized-user (dissoc fetched-user :password)]
      {:status :success
       :user   sanitized-user})
    {:status  :error
     :message "Invalid user ID"}))

(println (fetch-and-sanitize 1))
(prn (fetch-and-sanitize 0))

(defn -test []
  ;; Success - valid ID returns sanitized user
  (assert (= (fetch-and-sanitize 1)
             {:status :success :user {:id 1 :name "User 1" :email "user1@example.com"}})
          "Should return success with sanitized user for ID 1")

  (assert (= (fetch-and-sanitize 42)
             {:status :success :user {:id 42 :name "User 42" :email "user42@example.com"}})
          "Should return success with sanitized user for ID 42")

  ;; Error - zero and negative IDs
  (assert (= (fetch-and-sanitize 0)
             {:status :error :message "Invalid user ID"})
          "Should return error for ID 0")

  (assert (= (fetch-and-sanitize -5)
             {:status :error :message "Invalid user ID"})
          "Should return error for negative ID")

  ;; Password is removed from success response
  (assert (not (contains? (:user (fetch-and-sanitize 1)) :password))
          "Success response should NOT contain :password in :user")

  ;; Success response structure
  (let [result (fetch-and-sanitize 10)]
    (assert (= (:status result) :success)
            "Status should be :success for valid ID")
    (assert (contains? result :user)
            "Success response should contain :user key")
    (assert (= (get-in result [:user :id]) 10)
            "User :id should match the input ID")
    (assert (= (get-in result [:user :name]) "User 10")
            "User :name should be 'User X' where X is the ID")
    (assert (= (get-in result [:user :email]) "user10@example.com")
            "User :email should be 'userX@example.com' where X is the ID"))

  ;; Error response structure - no :user key
  (let [result (fetch-and-sanitize -1)]
    (assert (= (:status result) :error)
            "Status should be :error for invalid ID")
    (assert (contains? result :message)
            "Error response should contain :message key")
    (assert (not (contains? result :user))
            "Error response should NOT contain :user key"))

  ;; Email is always lowercase
  (assert (= (get-in (fetch-and-sanitize 999) [:user :email]) "user999@example.com")
          "Email should be lowercase")

  ;; Large ID
  (assert (= (:status (fetch-and-sanitize 1000000)) :success)
          "Should handle large positive IDs")

  (println "✓ All tests passed for fetch-and-sanitize"))
(-test)
