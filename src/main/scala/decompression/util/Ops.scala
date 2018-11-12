package decompression.util

import java.io.File

import decompression.algo._

object Ops {

  def unZip(archiveFile: File, outputPath: String): Unit = {
    val _unZip = new UnZip
    _unZip.decompress(archiveFile, outputPath)
  }

  def gzipD(archiveFile: File, outputPath: String): Unit = {}

  def unRar(archiveFile: File, outputPath: String): Unit = {}

  def _7zipD(archiveFile: File, outputPath: String): Unit = {}

}
