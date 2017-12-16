package titanic

import cats._, implicits._
import schemes._
import schemes.data._

import titanic.model._

package object hypothesis extends EveryoneDiesHypothesis
    with FemalesSurviveHypothesis {

  type Tree = Fix[TreeF]

  implicit def treeHypothesis: Hypothesis.Aux[Tree, Example] =
    new Hypothesis[Tree] {

      type Example = titanic.model.Example

      def extract(row: TestDataRow): Example = titanic.model.Example(
        gender = extraction.gender(row),
        age = extraction.age(row),
        ticketClass = extraction.ticketClass(row),
        familySize = extraction.familySize(row),
        hasCabin = extraction.hasCabin(row),
        port = extraction.port(row)
      )

      def predict(tree: Tree)(example: Example): Label =
        Schemes.hylo[Label Either ?, Tree, Label](tree)(TreeF.explore(example), _.merge)
    }

  type Counts = Map[Label, Int]
  type AttrFCount[A] = AttrF[Counts, A]
  type AttrFCostInfo[A] = AttrF[Prune.CostInfo, A]

  /** Type alias for inputs to tree building process */
  type Input = (List[TrainingExample[Example]], List[Feature[Example]])


  type SubtreeListF[A] = ListF[Fix[AttrFCostInfo], A]
}
