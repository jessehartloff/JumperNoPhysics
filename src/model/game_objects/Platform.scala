package model.game_objects

import physics.PhysicsVector
import physics.objects.{DynamicObject, Face}
import model.Player

object Platform {
  var nextID: Int = 0
}

class Platform(location: PhysicsVector, dimensions: PhysicsVector) extends JumperObject(location, dimensions){

  val platformID: Int = Platform.nextID
  Platform.nextID += 1

  override def collideWithDynamicObject(otherObject: DynamicObject, face: Integer): Unit = {
//    notification match {
//      case Email(sender, title, _) =>
//        s"You got an email from $sender with title: $title"
//      case SMS(number, message) =>
//        s"You got an SMS from $number! Message: $message"
//      case VoiceRecording(name, link) =>
//        s"you received a Voice Recording from $name! Click the link to hear it: $link"
//    }

    otherObject match {
      case player: Player =>
        if(face == Face.top){
          otherObject.velocity.z = 0.0
          otherObject.location.z = this.location.z + this.dimensions.z
          player.platformCollision()
        }
    }

  }

}
