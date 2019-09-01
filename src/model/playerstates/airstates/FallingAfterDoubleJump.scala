package model.playerstates.airstates

import model.Player

class FallingAfterDoubleJump(player: Player) extends Falling(player) {

  override def jumpPressed(): Unit = {}
}
