package cn.chenhuanming.hello.akka.http.actor

import akka.actor.Actor
import cn.chenhuanming.hello.akka.http.domain.{HelloDTO, YourName}

/**
  *
  * @author chenhuanming
  *         Created at 2018/6/13
  */
class HelloActor extends Actor{
  override def receive: Receive = {
    case YourName(name) =>
      sender ! HelloDTO("hello",name)
  }
}
