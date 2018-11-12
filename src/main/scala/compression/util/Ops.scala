package compression.util

import java.io.File

import compression.algo._

object Ops {

  def zip(inputFiles: List[File],
          directory: String,
          outputPath: String,
          bytes: Long): Unit = {
    val zip = new Zip
    zip.createArchive(inputFiles, directory, outputPath, bytes)
  }

  def gzip(inputFiles: List[File],
           directory: String,
           outputPath: String,
           bytes: Long): Unit = {
    inputFiles.foreach { file: File =>
      println(f"Future GZIP\nthis will compress ${file.getName} to .gzip")
    }
  }

  def rar(inputFiles: List[File],
          directory: String,
          outputPath: String,
          bytes: Long): Unit = {
    inputFiles.foreach { file: File =>
      println(f"Future RAR\nthis will compress ${file.getName} to .rar")
    }
  }

  def _7zip(inputFiles: List[File],
            directory: String,
            outputPath: String,
            bytes: Long): Unit = {
    inputFiles.foreach { file: File =>
      println(f"Future 7ZIP\nthis will compress ${file.getName} to .7zip")
    }
  }

}
