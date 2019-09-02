package model.game_objects

import physics.PhysicsVector
import physics.objects.{DynamicObject, Face}


class Platform(location: PhysicsVector, dimensions: PhysicsVector) extends JumperObject(location, dimensions) {

  override def collideWithDynamicObject(otherObject: DynamicObject, face: Integer): Unit = {

    if (face == Face.top) {
      otherObject.velocity.z = 0.0
      otherObject.location.z = this.location.z + this.dimensions.z
      otherObject.onGround()
    }

  }

}
