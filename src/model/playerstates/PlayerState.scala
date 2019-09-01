package model.playerstates

import model.Player

abstract class PlayerState(player: Player) {

  var timeInState: Double = 0.0

  println(this.getClass.getSimpleName)

  def update(dt: Double): Unit = {
    timeInState += dt

    if(player.leftKeyHeld){
      this.leftPressed()
    }
    if(player.rightKeyHeld){
      this.rightPressed()
    }
  }



  // API methods. Most methods do nothing by default. Only override methods that are needed for each state
  def leftPressed(): Unit = {}

  def rightPressed(): Unit = {}

  def jumpPressed(): Unit = {}

  def leftReleased(): Unit = {
    player.stop()
  }

  def rightReleased(): Unit = {
    player.stop()
  }

  def jumpReleased(): Unit = {}

  def platformCollision(): Unit = {
//    player.velocity.z = 0.0
  }

//  def noPlatformCollision(): Unit = {}

  def isAlive: Boolean = {
    true
  }
}
