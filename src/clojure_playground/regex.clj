#!/usr/bin/clojure

;;; A bit on regex
;;; https://clojuredocs.org/clojure.core/re-pattern
(re-pattern "\\d+")

(re-find (re-pattern "\\d+") "abc123def") 

;; If you want to construct a regex pattern dynamically at run time,
;; then you need to use re-pattern to convert a string to a pattern
;; that can be used for matching.  But if your pattern is one you
;; write into the source code, it is more convenient to use the
;; #"pattern" syntax.  The previous example can be written as follows.
(re-find #"\d+" "abc123def") 

;; Below are two examples that are equivalent in the patterns they
;; use, but the #"pattern" syntax helps significantly, because it lets
;; us avoid the requirement to escape every \ character with another \
;; character.  See the example with embedded comments below for more
;; detail on what the pattern matches.
(re-find #"\\\d+\s+\S+" "\\ it sh0uld match in \\5 here somewhere.")

(re-find (re-pattern "\\\\\\d+\\s+\\S+")
                "\\ it sh0uld match in \\5 here somewhere.")

;; If you want to embed (ignored) whitespace and comments from #
;; characters until end-of-line in your regex patterns, start the
;; pattern with (?x)
(re-find #"(?x)  # allow embedded whitespace and comments
                  \\    # backslash
                  \d+   # one or more digits
                  \s+   # whitespace
                  \S+   # non-whitespace"
                "\\ it sh0uld match in \\5 here somewhere.")

;; Other pattern flags like Java's DOTALL, MULTILINE and UNICODE_CASE
;; pattern matching modes, can be set by combining these embedded flags

;; (?d) Unix lines (only match \newline)
;; (?i) Case-insensitive
;; (?u) Unicode-aware Case
;; (?m) Multiline
;; (?s) Dot matches all (including newline)
;; (?x) Ignore Whitespace and comments
(re-seq #"(?ix) test #Case insensitive and comments allowed"
               "Testing,\n testing,\n 1 2 3")

;;;------------------------------------------------------------ 

(def pat (re-pattern "\\d+"))
(re-find pat "abc1223def")
(def pat2 (re-pattern "(?:(?!\\.symlink).)*"))
(re-find pat2 "test...symlink")
(re-find (re-pattern "test\\.*") "test...symlink")
(re-find #"test\.*" "test...symlink")
(re-find #"test.*" "test...symlink")

