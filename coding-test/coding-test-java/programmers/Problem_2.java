package programmers;

import java.util.*;
import java.util.regex.*;

// 불량 사용자
public class Problem_2 {

    public static void main(String[] args) {
        Problem_2 p = new Problem_2();

        String[] user = {"aa", "ab", "ac", "ad", "ae", "be"};
        String[] banned = {"a*", "a*", "*e", "**"};

        int result = p.solution(user, banned);
        System.out.println(result);
    }
    public int solution(String[] user_id, String[] banned_id) {
        List<List<String>> list = new ArrayList<>();
        for (int i = 0; i < banned_id.length; i++) {
            String banId = banned_id[i];
            Pattern p = Pattern.compile(banId.replaceAll("\\*", "."));

            List<String> innerList = new ArrayList<>();
            for (String user: user_id) {
                Matcher m = p.matcher(user);
                if (user.length() == banId.length() && m.find()) {
                    innerList.add(user);
                }
            }

            list.add(innerList);
        }

        Set<List<String>> answer = new HashSet<>();
        makeSet(answer, list, banned_id.length, 0, new ArrayList<>());
        return answer.size();
    }

    public void makeSet(Set<List<String>> set, List<List<String>> users, int n, int idx, List<String> value) {
        if (value.size() == n || idx >= n) {
            Collections.sort(value);
            set.add(value);
            return;
        }

        List<String> table = users.get(idx);
        for (int i = 0; i < table.size(); i++) {
            if (!value.contains(table.get(i))) {
                value.add(table.get(i));
                makeSet(set, users, n, idx + 1, value);
                value.remove(table.get(i));
            }
        }
    }
}
