package ahsan.util

import java.io.File

import decompression._
import decompression.util._

object DecompressUtil {

  def decompress(fileList: List[File], outputPath: String) = {
    val decompressor: Decompressor =
      new Decompressor(fileList, outputPath)
    decompressor.execute(Ops.unZip)
    // decompressor.execute(Ops.gzipD)
    // decompressor.execute(Ops.unRar)
    // decompressor.execute(Ops._7zipD)

    decompressor.awaitFutureList
    println("Decompression Done - Adios !!!!!!!")
  }

}
