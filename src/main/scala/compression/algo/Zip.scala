package compression.algo

import java.io.{
  File,
  FileInputStream,
  FileOutputStream,
  InputStream,
  IOException,
  OutputStream
}
import java.net.URI
import java.util.zip.{ZipEntry, ZipFile, ZipOutputStream}

import scala.io.{BufferedSource, Codec}

import helpers.io.FileIO
import compression.util.ZipIO

class Zip extends FileIO with ZipIO {

  private var archiveFileList: List[List[File]] = List(List())
  private var archiveList: List[File] = List()

  def createArchive(inputFiles: List[File],
                    directory: String,
                    outputPath: String,
                    bytes: Long): Unit = {

    var remaining = bytes
    inputFiles.foreach { file: File =>
      if ((remaining > file.length()) || bytes == remaining) {
        remaining = remaining - file.length()
        archiveList = file :: archiveList
      } else {
        archiveFileList = List(file) :: archiveFileList
      }
    }
    archiveFileList = archiveList :: archiveFileList
    archiveFiles(archiveFileList, directory, outputPath)
  }

  def archiveFiles(archiveFileList: List[List[File]],
                   directory: String,
                   outputPath: String): Unit = {
    var splits = 0
    archiveFileList.foreach { fileList: List[File] =>
      if (!fileList.isEmpty) {
        createOutput(fileList, directory, outputPath, splits)
        splits = splits + 1
      }
    }
  }

  def createOutput(files: List[File],
                   directory: String,
                   outputPath: String,
                   splits: Int): Unit = {

    mkdirs(outputPath)
    val outputFilename: String = directory.replaceAll("/", "_")
    var fileOutputStream =
      new FileOutputStream(f"$outputPath/${outputFilename}_$splits.zip")
    var zipOutputStream = new ZipOutputStream(fileOutputStream)

    try {
      files.foreach { file: File =>
        val filename = file.getAbsolutePath
        val zipEntry = createEntry(file, directory)
        zipOutputStream.putNextEntry(zipEntry)
        writeZipToFile(filename, zipOutputStream)
      }
    } catch {
      case e: IOException => {
        e.printStackTrace
      }
      case unknown: Throwable =>
        println("Got this unknown exception: " + unknown)
    } finally {
      zipOutputStream.closeEntry
      zipOutputStream.close
      fileOutputStream.close
    }
  }

}
