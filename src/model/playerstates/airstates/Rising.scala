package model.playerstates.airstates

import model.Player

class Rising(player: Player) extends InAir(player) {

  val nextState = new Falling(player)

  override def update(dt: Double): Unit = {
    // call the original update method from PlayerState
    super.update(dt)

    // add more functionality to update
    if (player.velocity.z <= 0.0) {
      player.state = nextState
    }

  }


  // Do nothing on platform collision instead of the default of stopping z velocity
//  override def platformCollision(): Unit = {}


  override def jumpReleased(): Unit = {
    player.velocity.z /= 2.0
  }

}

