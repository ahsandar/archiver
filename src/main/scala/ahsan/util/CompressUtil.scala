package ahsan.util

import java.io.File

import scala.collection.immutable.HashMap

import compression._
import compression.util._

object CompressUtil {

  def compress(directoryMap: HashMap[String, List[File]],
               outputPath: String,
               bytes: Long): Unit = {

    val compressor: Compressor =
      new Compressor(directoryMap, outputPath, bytes)
    compressor.execute(Ops.zip)
    // compressor.execute(Ops.gzip)
    // compressor.execute(Ops.rar)
    // compressor.execute(Ops._7zip)

    compressor.awaitFutureList
    println("Compression Done - Adios !!!!!!!")
  }
}
