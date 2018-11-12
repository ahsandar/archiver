package ahsan.util

import java.io.File

import scala.collection.immutable.HashMap

import helpers.io.DirectoryIO

object DirectoryUtil {

  def getdirectoryMap(inputPath: String,
                      verbose: Boolean = true): HashMap[String, List[File]] = {
    val directoryMap: HashMap[String, List[File]] =
      DirectoryIO.parseDirectory(inputPath)

    if (verbose) {
      println("########## Files ##################")
      directoryMap.foreach(println(_))
      println("#######################################")
    }
    directoryMap
  }

  def getArchiveFileList(inputPath: String,
                         verbose: Boolean = true): List[File] = {
    val fileList: List[File] = DirectoryIO.collectArchiveList(inputPath)

    if (verbose) {
      println("########## Files ##################")
      fileList.foreach(println(_))
      println("#######################################")
    }
    fileList
  }

}
