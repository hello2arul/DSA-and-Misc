
/*
 * https://leetcode.com/problems/gas-station/description/
 */
public class CircularGasStation {
    public int canCompleteCircuit(int[] petrols, int[] distance) {
        for (int start = 0; start < petrols.length; start++) {
            int currentPetrols = 0;
            int end = start;
            while (true) {
                currentPetrols += (petrols[end] - distance[end]);
                if (currentPetrols < 0)
                    break;
                // circular array
                end = (end + 1) % petrols.length;
                // if we reach the starting position return
                if (end == start)
                    return end ;
            }
        }
        return -1;
    }

    //O(n)
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int tank = 0;
        for(int i = 0; i < gas.length; i++)
            tank += gas[i] - cost[i];
        if(tank < 0)
            return - 1;
        
        int start = 0;
        int accumulate = 0;
        for(int i = 0; i < gas.length; i++){
            int curGain = gas[i] - cost[i];
            if(accumulate + curGain < 0){
                start = i + 1;
                accumulate = 0;
            }
            else accumulate += curGain;
        }

        return start;
    }
}
