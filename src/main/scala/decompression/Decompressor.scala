package decompression

import java.io.File

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

import concurrent.FutureConcurrency

class Decompressor(fileList: List[File], outputPath: String)
    extends FutureConcurrency {

  def execute(callback: (File, String) => Unit) = {
    fileList.foreach { file: File =>
      futureList = Future {
        callback(file, outputPath)
      } :: futureList
    }
  }
}
