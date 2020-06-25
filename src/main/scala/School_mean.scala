import java.io.{BufferedReader, InputStreamReader}
import org.saddle.io._


class SaddleCsvSource(url: String) extends CsvSource {
  val reader = new BufferedReader(new InputStreamReader(new java.net.URL(url).openStream()))
  override def readLine: String = {
    reader.readLine()
  }
}
object School extends App {
  val file = new SaddleCsvSource("https://data.cityofnewyork.us/api/views/zt9s-n5aj/rows.csv?accessType=DOWNLOAD")

  val frame = CsvParser.parse(file)

  val df = frame.withColIndex(0)

  val reading_mean = df.col("Critical Reading Mean").mapValues(CsvParser.parseInt).mean
  val mathematics_mean = df.col("Mathematics Mean").mapValues(CsvParser.parseInt).mean
  val writing_mean = df.col("Writing Mean").mapValues(CsvParser.parseInt).mean

  println(reading_mean)
  println(mathematics_mean)
  println(writing_mean)

}