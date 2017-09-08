(ns clj.learn-quil
  (:require [quil.core :as q]))

(defn setup []
  (q/frame-rate 10)
  (q/background))

(defn draw []
  (q/stroke (q/random 255))
  (q/stroke-weight (q/random 10))
  (q/fill (q/random 255))

  (let [diam (q/random 100)
        x (q/random (q/width))
        y (q/random (q/height))]
    (q/ellipse x y diam diam)))

(q/defsketch example
  :title "fiddling with quil"
  :settings #(q/smooth 2)
  :setup setup
  :draw draw
  :size [800 600])
