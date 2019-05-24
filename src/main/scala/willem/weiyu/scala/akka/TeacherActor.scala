package willem.weiyu.scala.akka

import akka.actor.{Actor, ActorLogging, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class TeacherActor extends Actor with ActorLogging{

  override def receive: Receive = {
    case "1+1等于多少?" => {
      sender ! "1+1=2"
    }
    case "中国历史上最后一位皇帝是谁?" => {
      sender ! "中国历史上最后一位皇帝是爱新觉罗·溥仪"
    }
    case "腾讯第一网红是谁?" => {
      sender ! "腾讯第一网红是企鹅"
    }
    case _ => {
      sender ! "这个问题，老师也得查查书"
    }
  }
}

object TeacherActor {

  def main(args: Array[String]): Unit = {
    //读入配置
    val config = ConfigFactory
      .parseResources("system.conf")
      .getConfig("RemoteServerSideActor")

    //使用配置，建立 Actor 模型系统 ActorSystem。
    //ActorSystem 是访问 Actor 模型系统的接口，类似于 Spark 的 SparkContext。
    val system = ActorSystem("teacherService",  config)

    //创建teacherActor，返回一个引用，teacherActor 是 Actor 的名，客户端需要用
    system.actorOf(Props[TeacherActor], "teacherActor")
  }
}
