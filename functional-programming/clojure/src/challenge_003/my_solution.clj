(ns challenge-003.my-solution
  (:require [clojure.set :as set]))

(def input-map {:firstName "Bruno", :lastName "Santos", :emailAddress "bruno.moreira@nubank.com.br"})

(def camel->kebab-keys
  {:firstName    :first-name
   :lastName     :last-name
   :emailAddress :email})

(println (set/rename-keys input-map camel->kebab-keys))

(defn user-transform [{:keys [firstName lastName emailAddress]}]
  {:first-name firstName
   :last-name  lastName
   :email      emailAddress})

(println (user-transform input-map))

(defn -test []
  ;; Happy path - full input
  (assert (= (user-transform {:firstName "Bruno" :lastName "Santos" :emailAddress "bruno@nu.com"})
             {:first-name "Bruno" :last-name "Santos" :email "bruno@nu.com"})
          "Should transform all keys from camelCase to kebab-case")

  ;; Both approaches produce the same result
  (let [input {:firstName "Ana" :lastName "Lima" :emailAddress "ana@test.com"}]
    (assert (= (user-transform input)
               (set/rename-keys input camel->kebab-keys))
            "user-transform and rename-keys should produce identical output"))

  ;; Missing keys - destructuring yields nil values
  (assert (= (user-transform {:firstName "Bruno"})
             {:first-name "Bruno" :last-name nil :email nil})
          "Missing keys should result in nil values")

  (assert (= (user-transform {})
             {:first-name nil :last-name nil :email nil})
          "Empty map should produce all nil values")

  ;; Extra keys are ignored by destructuring
  (assert (= (user-transform {:firstName "Bruno" :lastName "Santos" :emailAddress "b@t.com" :age 30})
             {:first-name "Bruno" :last-name "Santos" :email "b@t.com"})
          "Extra keys should be ignored")

  ;; Values with special characters
  (assert (= (user-transform {:firstName "María José" :lastName "O'Brien" :emailAddress "mj@test.com"})
             {:first-name "María José" :last-name "O'Brien" :email "mj@test.com"})
          "Should handle special characters in values")

  (println "✓ All tests passed for user-transform"))
(-test)
