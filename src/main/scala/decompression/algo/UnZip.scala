package decompression.algo

import java.io.{File, FileInputStream, FileOutputStream, IOException}
import java.util.zip.{ZipEntry, ZipFile}

import scala.io.{BufferedSource, Codec}

import scala.collection.JavaConversions.enumerationAsScalaIterator

import helpers.io.FileIO
import decompression.util.UnZipIO

class UnZip extends FileIO with UnZipIO {

  def decompress(file: File, outputPath: String): Unit = {
    try {
      mkdirs(outputPath)
      val zip = new ZipFile(file)

      zip.entries.foreach { zipEntry =>
        val fileName: String = zipEntry.getName()
        val unZipFile: File = new File(outputPath + File.separator + fileName)
        mkdirs(unZipFile.getParent())
        writeUnZipToFile(zip.getInputStream(zipEntry), unZipFile)
      }

    } catch {
      case e: IOException => {
        e.printStackTrace
      }
      case unknown: Throwable =>
        println("Got this unknown exception: " + unknown)
    }
  }

}
