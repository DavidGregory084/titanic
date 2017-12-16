package titanic

object Config {

  // path to the resources folder, in case of classpath issues.
  val basePath: String = "/home/david/Repos/titanic/src/main/resources/"

  val preshuffledFileName: String = basePath + "original.csv"
  val trainingFileName: String = basePath + "training.csv"
  val testFileName: String = basePath + "test.csv"

  val testSize: Int = 400
  val trainingSize: Int = 400
}
