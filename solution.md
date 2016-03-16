# Solution
This solution uses a circular buffer which has the last 2 rolls, as that's all the history we need, in order to support
strikes.

## Things that are not super pretty

* The class Game.Roll does represent a roll (good) but it's not *great* that it also stores what type of bonus should 
be awarded in future rolls.
* I don't really like the flag `rollCompletesFrame`.
* The chain of `if` clauses is confusing.
* Cannot deal with extreme long games, as the score or the circular buffer index would overflow.

## Things that surprised me
* The code is actually quite compact in a nice way.
