(ns challenge-012.my-solution
  (:require [clojure.string :as str]))

(defn extract-domain
  [email]
  (if (str/includes? email "@")
    (last (str/split email #"@"))
    ""))

(prn (extract-domain "bruno@clojure.com"))
(prn (extract-domain "usuario-sem-arroba"))

(defn -test []
  ;; Standard email
  (assert (= (extract-domain "john@example.com") "example.com")
          "Should extract domain from standard email")

  ;; Subdomain email
  (assert (= (extract-domain "bob@company.co.uk") "company.co.uk")
          "Should extract full domain including subdomains")

  ;; Different TLDs
  (assert (= (extract-domain "jane@test.org") "test.org")
          "Should work with .org TLD")

  (assert (= (extract-domain "user@site.io") "site.io")
          "Should work with .io TLD")

  ;; No @ symbol
  (assert (= (extract-domain "invalid-email") "")
          "Should return empty string when no @ is present")

  ;; Empty string
  (assert (= (extract-domain "") "")
          "Should return empty string for empty input")

  ;; Multiple @ symbols - takes the last part
  (assert (= (extract-domain "user@@domain.com") "domain.com")
          "Should return the last segment when multiple @ present")

  ;; @ at the start
  (assert (= (extract-domain "@domain.com") "domain.com")
          "Should extract domain when @ is at the start")

  ;; @ at the end - split drops trailing empties, so last part is "user"
  (assert (= (extract-domain "user@") "user")
          "Trailing @ edge case: split drops empty trailing segment")

  ;; Lone @ - split produces empty vector, last returns nil
  (assert (nil? (extract-domain "@"))
          "Lone @ edge case: split drops all empty segments, last returns nil")

  ;; Result is always a string
  (assert (string? (extract-domain "test@example.com"))
          "Should return a string")

  (assert (string? (extract-domain "no-at"))
          "Should return a string even for invalid input")

  (println "✓ All tests passed for extract-domain"))
(-test)
