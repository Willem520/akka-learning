package willem.weiyu.scala.akka

import akka.actor.{Actor, ActorSystem, Props}

/**
  * @Author weiyu
  * @Description
  * @Date 2019/3/4 11:31
  */
object HelloAkka2 {

  def main(args: Array[String]): Unit = {
    val system = ActorSystem("actor-demo-scala")
    val hello = system.actorOf(Props[Hello2], "hello")
    hello ! Greeting("Hello")
    hello ! Greet("Bob")
    hello ! Greet("Alice")
    hello ! Greeting("Hola")
    hello ! Greet("Alice")
    hello ! Greet("Bob")
    Thread sleep 1000
    hello ! "stop"
    hello ! Greet("123")
  }
}

case class Greeting(greet: String)
case class Greet(name: String)

class Hello2 extends Actor{
  var greeting = ""

  override def receive: Receive = {
    case Greeting(greet) => greeting = greet
    case Greet(name) => println(s"$greeting $name")
    case "stop" => {
      //停止自己的actorRef
      context.stop(self)
      //关闭ActorSystem，即关闭其内部的线程池
      //context.system.terminate()
    }
  }
}


