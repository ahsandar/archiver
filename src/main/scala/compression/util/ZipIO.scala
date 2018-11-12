package compression.util

import java.io.{File, FileInputStream,  IOException}
import java.util.zip.{ZipEntry, ZipOutputStream}

import scala.io.{BufferedSource, Codec}

trait ZipIO {

  def writeZipToFile(filename: String, zipOutputStream: ZipOutputStream): Unit = {
    try {
     val inputFileStream = new FileInputStream(filename)
        val inputSrc =
          new BufferedSource(inputFileStream)//(Codec.ISO8859)

        inputSrc foreach { c: Char =>
          zipOutputStream.write(c)
        }
        inputSrc.close
    } catch {
      case e: IOException => {
        e.printStackTrace
      }
      case unknown: Throwable =>
        println("Got this unknown exception: " + unknown)
    }
  }

   def createEntry(file: File, directory: String): ZipEntry = {

    val relative = directory.replaceFirst("/", "")
    val path =
      if (relative.isEmpty) f"${file.getName}"
      else f"$relative/${file.getName}"
    new ZipEntry(path)
  }


}
