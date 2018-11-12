package decompression.util

import java.io.{File, InputStream, FileOutputStream, IOException}

import scala.io.{BufferedSource, Codec}

trait UnZipIO {

  def writeUnZipToFile(bufferedSource: InputStream, unZipFile: File): Unit = {
    try {
      val inputSrc = new BufferedSource(bufferedSource) //(Codec.ISO8859)
      val ostream = new FileOutputStream(unZipFile)
      inputSrc foreach { c: Char =>
        ostream.write(c)
      }
      inputSrc.close
      ostream.close
    } catch {
      case e: IOException => {
        e.printStackTrace
      }
      case unknown: Throwable =>
        println("Got this unknown exception: " + unknown)
    }
  }

}
