package willem.weiyu.scala.akka

import akka.actor.{Actor, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

class TeacherActor extends Actor{

  override def receive: Receive = {
    case "1+1等于多少?"           => {
      sender ! "1+1等于2"
    }
    case "历史上规模最大的众筹行动是什么？" => {
      sender ! "历史上规模最大的众筹行动是 +1s"
    }
    case "腾讯第一网红是谁？"     => {
      sender ! "腾讯第一网红是\"我去\""
    }
    case _              => {
      sender ! "这个问题，老师也得查查书"
    }
  }
}

object TeacherService {

  def main(args: Array[String]): Unit = {
    val config = ConfigFactory
      .parseResources("system.conf")
      .getConfig("RemoteServerSideActor")
    //读入配置
    val system = ActorSystem("teacherService",  config)
    //使用配置，建立 Actor 模型系统 ActorSystem。
    //ActorSystem 是访问 Actor 模型系统的接口，类似于 Spark 的 SparkContext。

    system.actorOf(Props[TeacherActor], "teacherActor")
    //创建TearcherActor，返回一个引用
    //teacherActor 是 Actor 的名，客户端需要用
  }
}
