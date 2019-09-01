package view

import controller.{ArrowInputs, WASDInputs}
import javafx.scene.input.KeyEvent
import model.Game
import model.game_objects.JumperObject
import physics.PhysicsVector
import physics.objects.DynamicObject
import scalafx.animation.AnimationTimer
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.paint.Color
import scalafx.scene.shape.{Rectangle, Shape}
import scalafx.scene.{Group, Scene}

object PlatformerGUI extends JFXApp {

  var lastUpdateTime: Long = System.nanoTime()

  val game = new Game()

  val scaleFactor: Double = 30.0

  val windowWidth: Double = game.gridWidth * scaleFactor
  val windowHeight: Double = game.gridHeight * scaleFactor

  val playerSpriteSize: Double = game.playerSize
//  val platformThickness = 0.1

  var platformSprites: Map[Int, Shape] = Map[Int, Shape]()

  var sceneGraphics: Group = new Group {}


  val player1Sprite: Shape = playerSprite(game.player1, Color.Blue)
  val player2Sprite: Shape = playerSprite(game.player2, Color.Red)

  sceneGraphics.children.add(player1Sprite)
  sceneGraphics.children.add(player2Sprite)

  // Convert game coordinates to GUI coordinates
  // We would use adapter if the GUI was using an interface for coordinates. Since the GUI wants us to set x and y
  // manually writing methods will suffice
//  def convertX(gameX: Double, width: Double): Double = {
//    (gameX - width / 2.0) * scaleFactor
//  }

  def convertY(gameY: Double, height: Double): Double = {
    (game.gridHeight - (gameY - game.killLine) - height) * scaleFactor
  }


  def playerSprite(player: DynamicObject, color: Color): Shape = {
    new Rectangle {
      width = player.dimensions.x * scaleFactor
      height = player.dimensions.z * scaleFactor
      translateX = player.location.x * scaleFactor
//      translateX = convertX(player.location.x, player.dimensions.x)
      translateY = convertY(player.location.z, player.dimensions.z)
      fill = color
    }
  }


//  def playerSprite(xLocation: Double, yLocation: Double, color: Color): Shape = {
//    new Rectangle {
//      width = playerSpriteSize * scaleFactor
//      height = playerSpriteSize * scaleFactor
//      translateX = convertX(xLocation, playerSpriteSize)
//      translateY = convertY(yLocation, playerSpriteSize)
//      fill = color
//    }
//  }



  def computeDistance(v1: PhysicsVector, v2: PhysicsVector): Double = {
    Math.sqrt(Math.pow(v1.x - v2.x, 2.0) + Math.pow(v1.z - v2.z, 2.0))
  }


  def staticSprite(platform: JumperObject): Rectangle = {
    new Rectangle() {
      width = platform.dimensions.x * scaleFactor
      height = platform.dimensions.z * scaleFactor
      translateX = platform.location.x * scaleFactor
      translateY = convertY(platform.location.z, platform.dimensions.z)
      fill = Color.Black
    }
  }

  // assumes no angled platforms
//  def platformSprite(platform: Platform): Rectangle = {
//    val distance: Double = computeDistance(platform.start, platform.end)
//
//    new Rectangle() {
//      width = distance * scaleFactor
//      height = platformThickness * scaleFactor
//      translateX = convertX((platform.start.x + platform.end.x) / 2.0, distance)
//      translateY = convertY(platform.start.z, platformThickness)
//      fill = Color.Black
//    }
//  }


  this.stage = new PrimaryStage {
    this.title = "Climber"
    scene = new Scene(windowWidth, windowHeight) {
      content = List(sceneGraphics)

      addEventHandler(KeyEvent.ANY, new WASDInputs(game.player1))
      addEventHandler(KeyEvent.ANY, new ArrowInputs(game.player2))
    }

    val update: Long => Unit = (time: Long) => {
      val dt: Double = (time - lastUpdateTime) / 1000000000.0
      lastUpdateTime = time
      game.update(dt)

      player1Sprite.translateX.value = game.player1.location.x * scaleFactor
      player1Sprite.translateY.value = convertY(game.player1.location.z, game.player1.dimensions.z)

      player2Sprite.translateX.value = game.player2.location.x * scaleFactor
      player2Sprite.translateY.value = convertY(game.player2.location.z, game.player2.dimensions.z)

      // platforms
      game.staticObjects.foreach((staticObject: JumperObject) => {
        if (platformSprites.contains(staticObject.objectID)) {

//          val distance: Double = computeDistance(platform.start, platform.end)
          platformSprites(staticObject.objectID).translateX.value = staticObject.location.x * scaleFactor
          platformSprites(staticObject.objectID).translateY.value = convertY(staticObject.location.z, staticObject.dimensions.z)
//          platformSprites(platform.platformID).translateX.value = convertX((platform.start.x + platform.end.x) / 2.0, distance)
//          platformSprites(platform.platformID).translateY.value = convertY(platform.start.z, platformThickness)

        } else {
          val sprite: Rectangle = staticSprite(staticObject)
          sceneGraphics.children.add(sprite)
          platformSprites += (staticObject.objectID -> sprite)
        }
      })
    }

    // Start Animations. Calls update 60 times per second (takes update as an argument)
    AnimationTimer(update).start()
  }

}
