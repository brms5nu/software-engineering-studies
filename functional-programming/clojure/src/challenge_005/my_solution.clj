(ns challenge-005.my-solution)

(defn validate-and-fetch
  [user-id]
  (if (pos? user-id)
    {:status :success :user {:id user-id :name (str "User " user-id)}}
    {:status :error :message "Invalid user ID"}))

(println (validate-and-fetch 1))
(prn (validate-and-fetch 0))

(defn -test []
  ;; Success cases - positive user IDs
  (assert (= (validate-and-fetch 1)
             {:status :success :user {:id 1 :name "User 1"}})
          "Should return success for user ID 1")

  (assert (= (validate-and-fetch 42)
             {:status :success :user {:id 42 :name "User 42"}})
          "Should return success for any positive user ID")

  (assert (= (:status (validate-and-fetch 999)) :success)
          "Status should be :success for positive IDs")

  ;; Error cases - zero and negative IDs
  (assert (= (validate-and-fetch 0)
             {:status :error :message "Invalid user ID"})
          "Should return error for user ID 0")

  (assert (= (validate-and-fetch -1)
             {:status :error :message "Invalid user ID"})
          "Should return error for negative user ID")

  (assert (= (:status (validate-and-fetch -100)) :error)
          "Status should be :error for negative IDs")

  ;; Structure checks - success response shape
  (let [result (validate-and-fetch 5)]
    (assert (contains? result :status)
            "Success response should contain :status")
    (assert (contains? result :user)
            "Success response should contain :user")
    (assert (contains? (:user result) :id)
            "Nested :user should contain :id")
    (assert (contains? (:user result) :name)
            "Nested :user should contain :name"))

  ;; Structure checks - error response shape
  (let [result (validate-and-fetch 0)]
    (assert (contains? result :status)
            "Error response should contain :status")
    (assert (contains? result :message)
            "Error response should contain :message")
    (assert (not (contains? result :user))
            "Error response should NOT contain :user"))

  (println "✓ All tests passed for validate-and-fetch"))

(comment
  (-test))
