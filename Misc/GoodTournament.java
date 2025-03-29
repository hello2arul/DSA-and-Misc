package Misc;

import java.util.ArrayList;
import java.util.List;

/*
https://leetcode.com/discuss/post/6586444/google-mock-interview-l4-india-by-anonym-z38m/
Google
You are given a list of players rank 1 to N denoting their rank 1 being the top rank player 
and N being the worst ranked player. We are given a list.. say 1 2 3 4 5 6 7 8 
(these are ranks of these 8 players). The rule is that whenever 2 players play. 
the one with the highest rank will always win. so the tournament will go like this. 
1 plays 2 -> 1 wins 3 plays 4 -> 3 wins, 5 plays 6 -> 5 wins, 7 plays 8, 7 wins.
 now this will continue 1 will play 3 -> 1 wins, 5 plays 7 -> 5 wins. the 3rd round 1 plays 
5 -> 1 wins and he is the winner. this is how the players will play.

1 2 3 4 5 6 7 8
1 3 5 7
1 5
1

We need to determine if a tournament is a good tournament or not? A good tournament is defined as 
a tournament in which in every round the best rank player from top plays the worst rank player 
from bottom.

the above tournament is not a good tournament. let me give u an example of good tournament.

8 1 4 5 2 7 3 6
1 4 2 3
1 2
1

in the 1st round 1 plays 8 (1 wins) 4 plays 5(4 wins) 2 plays 7 (2 wins) and 3 plays 6(3 wins). 
in the second round too the order is maintained 1 plays 4 (1 wins) 2 plays 3(2 wins) and in the 
3rd round too 1 plays 2 and finally wins. this is an example of a good tournament.

we need to write a function which will take these ranks in the form of list. and return if a
tournament is good or not.

## (0th player will play 1st .. 2nd will play 3rd and so on..... this is fixed rule)
*/
public class GoodTournament {

    public boolean isGoodTournament(List<Integer> ranks) {
        while (ranks.size() > 1) {
            List<Integer> nextRound = new ArrayList<>();

            // Simulate the current round
            for (int i = 0; i < ranks.size(); i += 2) {
                int player1 = ranks.get(i);
                int player2 = ranks.get(i + 1);

                // Check if the pairing is valid (best vs worst)
                if (player1 + player2 != ranks.size() + 1) {
                    return false; // Not a good tournament
                }

                // Add the winner to the next round
                nextRound.add(Math.min(player1, player2));
            }

            // Move to the next round
            ranks = nextRound;
        }

        return true; // All rounds satisfied the condition
    }
}
