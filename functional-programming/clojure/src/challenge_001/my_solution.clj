(ns challenge-001.my-solution)

(defn check-if-adult
      "Check if a person is adult or not."
      [age]
      (>= age 18))

(defn -test []
      (assert (= (check-if-adult 18) true)
              "Should return true for age 18 (exactly legal)")
      (assert (= (check-if-adult 17) false)
              "Should return false for age 17 (below legal)")
      (assert (= (check-if-adult 25) true)
              "Should return true for age 25 (above legal)")
      (assert (= (check-if-adult 0) false)
              "Edge case - should return false for age 0")
      (assert (= (check-if-adult 100) true)
              "Edge case - should return true for age 100")
      (assert (= (check-if-adult 16) false)
              "Should return false for teenager age 16")
      (assert (= (check-if-adult 19) true)
              "Should return true for age 19 (just above)")
      (println "✓ All tests passed! The adult? function works correctly."))

(-test)
