package willem.weiyu.scala.akka

import akka.actor.{Actor, ActorSystem, Props}

class GreetActor extends Actor {

  override def receive: Receive = {
    case "hello" => println("您好!")
    case _ => println("您是？")
  }
}

object HelloAkka {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("HelloActor")
    val helloActor = system.actorOf(Props[GreetActor], name = "helloActor")
    helloActor ! "hello"
    helloActor ! "喂"
  }
}
