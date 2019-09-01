package model.playerstates.airstates

import model.Player

class RisingAfterDoubleJump(player: Player) extends Rising(player) {

  override val nextState = new FallingAfterDoubleJump(player)

  override def jumpPressed(): Unit = {}
}
