package cn.chenhuanming.hello.akka.http

import akka.Done
import akka.actor.{ActorSystem, Props}
import akka.pattern.ask

import scala.concurrent.duration._
import akka.http.scaladsl.server.{HttpApp, Route}
import akka.util.Timeout
import cn.chenhuanming.hello.akka.http.actor.HelloActor
import cn.chenhuanming.hello.akka.http.domain.{HelloDTO, JsonSupport, YourName}

import scala.concurrent.{ExecutionContext, Future}
import scala.util.Try

/**
  *
  * @author chenhuanming
  *         Created at 2018/6/13
  */
object Server extends HttpApp with JsonSupport {

  implicit val actorSystem = ActorSystem("simple-web")
  implicit val helloActor = actorSystem.actorOf(Props[HelloActor])

  implicit val timeout = Timeout(5.seconds)

  override protected def routes: Route = {
    path(""){
      get{
        complete("hello")
      }
    }~
    path("helloDTO"){
      get{
        complete(HelloDTO("hello","martin"))
      }
    }~
    (get & path("helloActor")){
      parameter("name"){name=>
        onSuccess(helloActor ? YourName(name)){
          case helloDTO:HelloDTO=>complete{helloDTO}
        }
      }
    }~
    (get & pathPrefix("static")){
      getFromResourceDirectory("static")
    }
  }

  override protected def waitForShutdownSignal(system: ActorSystem)(implicit ec: ExecutionContext): Future[Done] = Future.never


  override protected def postServerShutdown(attempt: Try[Done], system: ActorSystem): Unit = {
    actorSystem.terminate()
    super.postServerShutdown(attempt, system)
  }

  def main(args: Array[String]): Unit = {
    Server.startServer("0.0.0.0",8080,actorSystem)
  }
}
