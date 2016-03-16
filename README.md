# Möbius Bowling
Long Long time ago  
In a galaxy far away  
Someone started playing Bowling...  
and that person is still playing

# What?
This is a new take on the famous [10 pin bowling game kata](http://codingdojo.org/cgi-bin/index.pl?KataBowling), but with a twist: the game doesn't finish on the 10th frame, but it keeps going and going and going.

# Why?
This variant helps to think in terms of infinite streams of data and how to build and design a solution that can work in these scenarios.

# Bowling scoring rules
Please see [bowling-rules.md](bowling-rules.md).

# Goal
The goal is to be able to report the partial score at any point in time, even if there are strike or spare bonuses that cannot be fully
calculated yet.

For example, if we take the following short game

| Frame | Roll N| Score  | Notes  |
| ----- | ----- | ------ | ------ |
|   1   |   1   |   10   | Strike |
|   2   |   2   |   10   | Strike |
|   3   |   3   |    5   | Strike |

If we get the score at this stage, we should get back 45.
* The total score of the first frame has all the bonuses assigned, and adds up to 25.
* The total score of the second frame cannot be calculated yet, but the partial result is 15.
* The total score of the third frame cannot be calculated yet, but the partial result is 5.
