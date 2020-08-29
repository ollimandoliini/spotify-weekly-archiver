import java.nio.charset.StandardCharsets
import scala.util.{Try, Success, Failure}

object Main extends App {
  val clientId = sys.env("SPOTIFY_CLIENT_ID")
  val clientSecret = sys.env("SPOTIFY_CLIENT_SECRET")

  def getToken(id: String, secret: String): Try[String] = {
    val authUrl = "https://accounts.spotify.com/api/token"
    val params = Map(
      "grant_type" -> "client_credentials"
    )
    val b64Credentials =
      java.util.Base64.getEncoder
        .encodeToString(s"$id:$secret".getBytes(StandardCharsets.UTF_8))
    val headers = Map(
      "Content-Type" -> "application/x-www-form-urlencoded",
      "Authorization" -> s"Basic $b64Credentials"
    )

    val response = Try(
      requests
        .post(authUrl, headers = headers, params = params)
        .data
        .toString()
    )

    response

  }
  val token = getToken(clientId, clientSecret)
  println(token)
}
