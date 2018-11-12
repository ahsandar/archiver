package helpers.io

import java.io.File

import scala.collection.immutable.HashMap

object DirectoryIO {
  private var dirRoot = "/"

  def listFiles(path: String,
                filterOut: String = ".zip"): HashMap[String, List[File]] = {
    val d = new File(path)
    var directoryMap: HashMap[String, List[File]] = HashMap()
    if (d.exists && d.isDirectory) {
      directoryMap += (parseDirKey(path) -> d.listFiles.filter { file: File =>
        file.isFile && !file.getName.endsWith(".zip")
      }.toList)
      val directoryList: List[File] = d.listFiles.filter(_.isDirectory).toList
      if (!directoryList.isEmpty) {
        directoryList.foreach { file: File =>
          directoryMap = directoryMap ++ listFiles(file.getAbsolutePath)
        }
      }
    } else {
      println(f"$path - Doesn't exist")
    }
    directoryMap
  }

  def parseDirectory(root: String): HashMap[String, List[File]] = {
    dirRoot = root
    listFiles(root)
  }

  def parseDirKey(dirPath: String): String = {
    val key: String = dirRoot.r.replaceAllIn(dirPath, "")
    val sanitizedKey: String = "//".r.replaceAllIn(key, "/")
    val parsedKey: String = if (sanitizedKey.isEmpty) "/" else sanitizedKey
    parsedKey
  }

  def collectArchiveList(inputPath: String, ext: String = ".zip"): List[File] = {
    val d = new File(inputPath)
    var fileList: List[File] = List()
    if (d.exists && d.isDirectory) {
      fileList = d.listFiles.filter { file: File =>
        file.isFile && file.getName.endsWith(".zip")
      }.toList
    }
    fileList
  }

}
