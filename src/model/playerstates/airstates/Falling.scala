package model.playerstates.airstates

import model.Player
import model.playerstates.groundstates.Standing

class Falling(player: Player) extends InAir(player) {

  override def platformCollision(): Unit = {
    player.state = new Standing(player)
  }

}
