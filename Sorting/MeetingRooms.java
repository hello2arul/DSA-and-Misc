package Sorting;

/*
 * https://neetcode.io/problems/meeting-schedule
 */
public class MeetingRooms {
    public boolean canAttendMeetings(List<Interval> intervals) {
        Collections.sort(intervals, Comparator.comparingInt(i -> i.start));

        for (int i = 1; i < intervals.size(); i++) {
            Interval i1 = intervals.get(i - 1);
            Interval i2 = intervals.get(i);

            if (i1.end > i2.start) {
                return false;
            }
        }

        return true;
    }
}
