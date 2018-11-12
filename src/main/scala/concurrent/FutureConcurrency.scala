package concurrent

import scala.concurrent.{Future, Await}
import scala.concurrent.duration.Duration
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

trait FutureConcurrency {

  var futureList: List[Future[Unit]] = List()

  def awaitFutureList {
    println("##################################")
    println(f"No. of Workers => ${futureList.length}")

    Await.result(
      Future.sequence(futureList),
      Duration.Inf
    )
    println("#######################################")
    println(f"Workers Completion Success")
    println("#######################################")

  }
}
