package model.playerstates.airstates

import model.Player

class RisingAfterDoubleJump(player: Player) extends Rising(player) {

  override def jumpPressed(): Unit = {}

  override def falling(): Unit = {
    player.state = new FallingAfterDoubleJump(player)
  }
}
