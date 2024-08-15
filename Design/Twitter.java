package Design;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/*
 * https://leetcode.com/problems/design-twitter
 */
class Twitter {
    private static int timeStamp = 0;

    private Map<Integer, User> userMap;

    public Twitter() {
        userMap = new HashMap<>();
    }

    private class Tweet {
        int id;
        int time;
        Tweet next;

        Tweet(int id) {
            this.id = id;
            this.time = timeStamp++;
            next = null;
        }
    }

    public class User {
        int id;
        Set<Integer> followed;
        Tweet tweetHead;

        User(int id) {
            this.id = id;
            followed = new HashSet<>();
            followed.add(id);
            tweetHead = null;
        }

        public void post(int tweetId) {
            Tweet t = new Tweet(tweetId);
            t.next = tweetHead;
            tweetHead = t;
        }

        public void follow(int followerId) {
            followed.add(followerId);
        }

        public void unfollow(int followerId) {
            followed.remove(followerId);
        }
    }
    
    public void postTweet(int userId, int tweetId) {
        if (!userMap.containsKey(userId)) {
            User u = new User(userId);
            userMap.put(userId, u);
        }
        userMap.get(userId).post(tweetId);
    }
    
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();

        if (!userMap.containsKey(userId))
            return res;
        Set<Integer> followed = userMap.get(userId).followed;
        PriorityQueue<Tweet> maxHeap = new PriorityQueue<>(
            followed.size(), (a, b) -> Integer.compare(b.time, a.time)
        );

        for (int u: followed) {
            Tweet t = userMap.get(u).tweetHead;

            if (t != null) {
                maxHeap.offer(t);
            }
        }

        int n = 0;
		while(!maxHeap.isEmpty() && n < 10){
		  Tweet t = maxHeap.poll();
		  res.add(t.id);
		  n++;
		  if (t.next != null)
			maxHeap.offer(t.next);
		}

		return res;

    }
    
    public void follow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId)) {
            userMap.put(followerId, new User(followerId));
        }
        if (!userMap.containsKey(followeeId)) {
            userMap.put(followeeId, new User(followeeId));
        }
        userMap.get(followerId).follow(followeeId);
    }
    
    public void unfollow(int followerId, int followeeId) {
        if (!userMap.containsKey(followerId) || followerId == followeeId)
            return;
        userMap.get(followerId).unfollow(followeeId);
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */