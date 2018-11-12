package compression

import java.io.File

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import scala.collection.immutable.HashMap

import concurrent.FutureConcurrency

class Compressor(directoryMap: HashMap[String, List[File]],
                 outputPath: String,
                 bytes: Long)
    extends FutureConcurrency {

  def execute(callback: (List[File], String, String, Long) => Unit) = {
    directoryMap.foreach {
      case (directory, files) =>
        futureList = Future {
        callback(files, directory, outputPath, bytes)
      } :: futureList

    }
  }

}
