package com.ing.wbaa.rokku.proxy.util

import akka.http.scaladsl.model.HttpRequest

object HttpUtils {

  def extractHeaderOption(httpRequest: HttpRequest, header: String): Option[String] =
    if (httpRequest.getHeader(header).isPresent)
      Some(httpRequest.getHeader(header).get().value())
    else
      None

  def extractClient(userAgent: String): Option[String] =
    """(\S+)/\S+""".r
      .findFirstMatchIn(userAgent)
      .map(_ group 1)

  def extractUserAgent(httpRequest: HttpRequest): String =
    extractHeaderOption(httpRequest, "User-Agent").flatMap(extractClient).getOrElse("")
}
