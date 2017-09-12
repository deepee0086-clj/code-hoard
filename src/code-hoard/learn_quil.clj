(ns code-hoard.learn-quil
  (:require [quil.core :as q]))

(defn setup []
  (q/frame-rate 30)
  (q/background))

(def canvas-width 300)
(def canvas-height 300)

(defn- get-grid []
  (for [x (range canvas-width)
        y (range canvas-height)]
    [x y (rem (bit-xor x y) 255)]))

(defn draw []
  (q/clear)
  (q/stroke (q/green))
  (q/)
  )

(q/defsketch example
  :title "fiddling with quil"
  :settings #(q/smooth 2)
  :setup setup
  :draw draw
  :size [canvas-width canvas-height])
