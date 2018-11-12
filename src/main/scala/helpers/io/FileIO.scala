package helpers.io

import java.io.{File, IOException}

trait FileIO {

  def mkdirs(path: String): File = {
    val folder: File = new File(path);
    try {
      if (!folder.exists()) { folder.mkdirs() }
    } catch {
      case e: IOException => {
        e.printStackTrace
      }
      case unknown: Throwable =>
        println("Got this unknown exception: " + unknown)
    }
    folder
  }

}
