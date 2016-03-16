package com.agilemeditation;

public class Game {
  private int score;
  private Roll previousScores[] = new Roll[2];
  private int rollCount;
  private boolean nextRollTerminatesFrame = false;

  public int getScore() {
    return score;
  }

  public void roll(int rollScore) {
    //add bonuses
    if (getRoll(rollCount - 2).shouldAddSecondNextRollAsBonus()) {
      score += rollScore;
    }
    if (getRoll(rollCount - 1).shouldAddNextRollAsBonus()) {
      score += rollScore;
    }

    if (nextRollTerminatesFrame) {
      //Spare or 'normal' frame scenarios
      if (getRoll(rollCount - 1).getScore() + rollScore == 10) {
        previousScores[rollCount % 2] = new Roll(rollScore, true, false);
        nextRollTerminatesFrame = false;
      } else {
        previousScores[rollCount % 2] = new Roll(rollScore, false, false);
        nextRollTerminatesFrame = false;
      }
    } else if (rollScore == 10) {
      // Strike scenario
      previousScores[rollCount % 2] = new Roll(rollScore, true, true);
      nextRollTerminatesFrame = false;
    } else {
      // First roll 'normal' scenario
      previousScores[rollCount % 2] = new Roll(rollScore, false, false);
      nextRollTerminatesFrame = true;
    }

    score += rollScore;
    rollCount++;
  }

  private Roll getRoll(int rollNumber) {
    if (rollNumber < 0) {
      return new Roll(0, false, false);
    }
    return previousScores[rollNumber % 2];
  }


  private static class Roll {
    private final int score;
    private final boolean addNextRollAsBonus;
    private final boolean addSecondNextRollAsBonus;

    public Roll(int score, boolean addNextRollAsBonus, boolean addSecondNextRollAsBonus) {
      this.score = score;
      this.addNextRollAsBonus = addNextRollAsBonus;
      this.addSecondNextRollAsBonus = addSecondNextRollAsBonus;
    }

    public int getScore() {
      return score;
    }

    public boolean shouldAddNextRollAsBonus() {
      return addNextRollAsBonus;
    }

    public boolean shouldAddSecondNextRollAsBonus() {
      return addSecondNextRollAsBonus;
    }
  }
}
