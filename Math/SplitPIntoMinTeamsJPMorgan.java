package Math;
/*
 * given two team sizes k1, k2 and number of people, p
 * group p into min possible teams of sizes k1, k2
 */
public class SplitPIntoMinTeamsJPMorgan {
    public int splitP(int k1, int k2, int p) {
        int minTeams = Integer.MAX_VALUE;
        for (int i = 0; i <= p / k1; i++) {
            for (int j = 0; j <= p / k2; j++) {
                int remaining = p - (i * k1) - (j * k2);

                if (remaining >= 0 && remaining % (k1 + k2) == 0) {
                    minTeams = Math.min(minTeams, i + j + (remaining / (k1 + k2)) * 2);
                }
            }
        }
        return minTeams;
    }
}
