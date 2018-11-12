package ahsan

import java.io.File

import scala.collection.immutable.HashMap

import ahsan.util._

object Main extends App {

  println("Compress or Decompress")

  args.length match {
    case 0 | 1 => println("dude, i need at least two parameter")
    case 2 =>
      println("Decompressing....")
      val fileList: List[File] =
        DirectoryUtil.getArchiveFileList(inputPath = args(0), verbose = false)
      DecompressUtil.decompress(fileList, args(1))
    case 3 =>
      println("Compressing....")
      val directoryMap: HashMap[String, List[File]] =
        DirectoryUtil.getdirectoryMap(inputPath = args(0), verbose = false)
      CompressUtil.compress(directoryMap, args(1), sizeToBytes(args(2)))
    case _ => println("dude, i need at max three parameter")
  }

  def sizeToBytes(mb: String): Long = {
    val mbs = mb.toLong
    val bytes = mbs * 1024 * 1024
    bytes
  }

}
