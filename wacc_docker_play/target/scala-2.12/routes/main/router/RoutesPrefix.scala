
// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/thomc/Downloads/wacc_docker_play/conf/routes
// @DATE:Mon Oct 16 14:08:46 CEST 2017


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}
