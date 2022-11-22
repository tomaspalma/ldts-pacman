import org.ldts.pacman.models.AliveStrategy
import org.ldts.pacman.models.GameActions
import org.ldts.pacman.models.GhostStrategy
import org.ldts.pacman.models.VulnerableStrategy
import spock.lang.Specification

class GhostStrategies extends Specification {
   def "Collision action between pacman and a specific ghost should return a valid GameActions.ghostCollisionWithPacman"() {
      given:
         GhostStrategy vulnerableStrat = new VulnerableStrategy()
         GhostStrategy aliveStrat = new AliveStrategy()
      when:
         def vulnerableResult = vulnerableStrat.collisionWithPacman()
         def aliveResult = aliveStrat.collisionWithPacman()
      then:
         vulnerableResult == GameActions.ghostCollisionWithPacman.KILL_GHOST
         aliveResult == GameActions.ghostCollisionWithPacman.KILL_PACMAN
   }
}
