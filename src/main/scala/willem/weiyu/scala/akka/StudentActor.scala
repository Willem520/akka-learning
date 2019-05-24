package willem.weiyu.scala.akka

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.util.Random

class StudentActor extends Actor with ActorLogging{
  val quesArr = Array("1+1等于多少?","中国历史上最后一位皇帝是谁?","腾讯第一网红是谁?","无解的问题")
  // 远程Actor的路径，通过该路径能够获取到远程Actor的一个引用
  val remoteServerRef = context.actorSelection("akka.tcp://teacherService@127.0.0.1:4999/user/teacherActor")
  // 获取到远程Actor的一个引用，通过该引用可以向远程Actor发送消息

  def receive = {
    case res: String => {
      //打印出从老师 Actor 获得的答案
      log.info("答：{}",res)
    }
    case num: Int => {
      val ques = quesArr(num)
      log.info("问：{}", ques)
      remoteServerRef ! ques
    }
  }
}

object StudentActor{

  def main(args: Array[String]): Unit = {
    val config = ConfigFactory
      .parseResources("system.conf")
      .getConfig("RemoteClientSideActor")
    //读入客户端配置
    val actorSystem = ActorSystem("studentClient",  config);
    //使用配置，建立 ActorSystem
    val studentActor = actorSystem.actorOf(Props[StudentActor])
    //获得 StudentActor 的一个引用。
    //在程序中 Actor 不能直接被访问。
    //所有操作都必须通过 ActorRef 引用。
    val rand = new Random();
    while(true){
      val num = rand.nextInt(4)
      studentActor ! num
      Thread.sleep(2000)
    }
  }
}