package model.game_objects

import physics.PhysicsVector
import physics.objects.StaticObject


object JumperObject{
  var nextID: Int = 0
}

class JumperObject(location: PhysicsVector, dimensions: PhysicsVector) extends StaticObject(location, dimensions){
  val objectID: Int = JumperObject.nextID
  JumperObject.nextID += 1
}
