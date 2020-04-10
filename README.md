# lets-record

Provides the letr form to record all symbol bindings in an atom.

[![Clojars Project](https://img.shields.io/clojars/v/lets-record.svg)](https://clojars.org/lets-record)
![CI](https://github.com/p14n/lets-record/workflows/CI/badge.svg)

## Why?

When performing complex calculations, if the result is not as expected it can be useful to see the interim "working out".  For example:

```clojure
(let [days (- end-days start-days)
      years (/ days 365)
      amount (* price quanity)
      period-rate (* rate years)
      interest (* period-rate amount)]
  interest) --> 0.5
```

If the amount of interest charged as incorrect, it would be useful to see what the other values were - is the number of days incorrect?  Or perhaps the amount?

Using the letr form:
```clojure
(def record (atom []))

(letr record 
     [days (- end-days start-days)
      years (/ days 365)
      amount (* price quanity)
      period-rate (* rate years)
      interest (* period-rate amount)]
  interest) --> 0.5

@record --> [{:days 365 :years 1 :amount 10 :period-rate 0.05 :interest 0.5}]
```
we can access a map of those values from the `record` atom.
