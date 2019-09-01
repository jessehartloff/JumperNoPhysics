package model.playerstates.groundstates

import model.Player
import model.playerstates.PlayerState
import model.playerstates.airstates.Falling

abstract class OnGround(player: Player) extends PlayerState(player) {

  override def update(dt: Double): Unit = {
    // call the original update method from PlayerState
    super.update(dt)

    // add more functionality to update
    if (player.velocity.z < -0.01) {

      if(this.timeInState > 0.2) {
        player.state = new Falling(player)
      }
    }

  }

  override def leftPressed(): Unit = {
    player.walkLeft()
    player.state = new Walking(player)
  }

  override def rightPressed(): Unit = {
    player.walkRight()
    player.state = new Walking(player)
  }

//  override def noPlatformCollision(): Unit = {
//    if(this.timeInState > 0.2) {
//      player.state = new Falling(player)
//    }
//  }
}
