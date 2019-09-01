package model.playerstates.groundstates

import model.Player
import model.playerstates.airstates.Rising

class Standing(player: Player) extends OnGround(player) {

  override def jumpPressed(): Unit = {
    player.velocity.z = player.standingJumpVelocity
    player.state = new Rising(player)
  }

}
