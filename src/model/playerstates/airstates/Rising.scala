package model.playerstates.airstates

import model.Player

class Rising(player: Player) extends InAir(player) {

  override def jumpReleased(): Unit = {
    player.velocity.z /= 2.0
  }

  override def falling(): Unit = {
    player.state = new Falling(player)
  }

}

