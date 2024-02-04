## Overview

## Take-home technical challenge
Agregio sells energie on multiple `markets`, notably 3 main ones:
- Réserve Primaire
- Réserve Sécondaire
- Réserve Rapide

On each of these markets, Agregio can suggest `offers`, which are composed of time `blocks` (e.g. one 24h day could have 8 3h blocks).
Each block has a `quantity` of energy that Agregio can sell at that time, and a `maxSellPrice` that Agregio won't go over.

The `quantity` of energy that each `offer` can produce depends on the energy producers (A.K.A `parcs`) that are associated with it.
Each of these `parcs` has a `quantity` that is the amount of energy it can produce for a given time `block`.

The goal of this challenge is to create a simple application that exposes multiple REST endpoints to manage the following:
- Create operation on `offers`
- Get list of `offers` that are available on a given `market`
- Create operation on `parcs`
- Get list of `parcs` that sell energy on a given `market`

## Solution
We'll implement a simple REST API using Spring, making sure to follow the hexagonal architecture principles.

<img width="1010" alt="Screenshot 2024-02-04 at 11 30 38" src="https://github.com/JackAlgera/agregio-interview/assets/26692718/39c5ae60-222d-44cd-9d8c-0b44770c2e23">

## Lint
We'll use spotless to lint our code, following the google-java-format rules.
Can be run with `./gradlew spotlessApply`
