package willem.weiyu.akka

import akka.actor.{Actor, ActorSystem, Props}

class HelloActor extends Actor {

  override def receive: Receive = {
    case "hello" => println("您好!")
    case _ => println("您是？")
  }
}

object Main {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("HelloSystem")
    val helloActor = system.actorOf(Props[HelloActor], name = "helloActor")
    helloActor ! "hello"
    helloActor ! "喂"
  }
}
