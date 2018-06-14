package cn.chenhuanming.hello.akka.http.domain

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

/**
  *
  * @author chenhuanming
  *         Created at 2018/6/13
  */
case class YourName(name:String)
case class HelloDTO(str:String,name:String)

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol{
  implicit val helloDTOFormat = jsonFormat2(HelloDTO)
}
