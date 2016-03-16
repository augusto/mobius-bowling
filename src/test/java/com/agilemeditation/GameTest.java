package com.agilemeditation;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

public class GameTest {


  private Game game;

  @Before
  public void setup() {
    game = new Game();
  }

  @Test
  public void scoreShouldBeZeroAtTheStartOfTheGame() {
    assertThat(game.getScore(), equalTo(0));
  }

  @Test
  public void scoreShouldBeZeroAfterAllGutters() {
    rollTimes(0, 55);
    assertThat(game.getScore(), equalTo(0));
  }

  @Test
  public void scoreShouldAddUpNormalRolls() {
    rollTimes(2, 55);
    assertThat(game.getScore(), equalTo(2 * 55));
  }

  @Test
  public void scoreShouldAddUpSpareBonusWhenFrameIsFullyPlayed() {
    rollTimes(5, 2);
    rollTimes(2, 2);

    assertThat(game.getScore(), equalTo(10 + 2 + 4));
  }

  @Test
  public void scoreShouldAddUpStrikeBonusWhenFrameIsFullyPlayed() {
    rollTimes(10, 1);
    rollTimes(2, 2);

    assertThat(game.getScore(), equalTo(10 + 4 + 4));
  }

  @Test
  public void scoreShouldAddUpBonusOfConsecutiveSparesHalfPlayed() {
    rollTimes(5, 5);

    assertThat(game.getScore(), equalTo(15 + 15 + 5));
  }

  @Test
  public void scoreShouldAddUpBonusOfConsecutiveStrikesHalfPlayed() {
    rollTimes(10, 3);

    assertThat(game.getScore(), equalTo(30 + 20 + 10));
  }

  @Test
  public void scoreShouldAddUpWhenBonusesAreMixed() {
    rollTimes(10, 3); //Partial frames: 30+20+10 = 60
    rollTimes(5, 5);  // Partial frames: 10+5 (bonus) + 15 + 15 + 5 = 50
    rollTimes(3, 3);  // Partial frames: 3 (part of previous frame) + 6 = 9

    assertThat(game.getScore(), equalTo(60 + 50 + 9));
  }

  private void rollTimes(int rollScore, int rollTimes) {
    for (int i = 0; i < rollTimes; i++) {
      game.roll(rollScore);
    }
  }
}
